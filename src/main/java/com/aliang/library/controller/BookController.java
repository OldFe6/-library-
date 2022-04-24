package com.aliang.library.controller;

import com.aliang.library.common.FileUploadUtil;
import com.aliang.library.common.PageNavUtil;
import com.aliang.library.common.model.MPageNav;
import com.aliang.library.entity.Book;
import com.aliang.library.entity.Record;
import com.aliang.library.entity.Shop;
import com.aliang.library.entity.User;
import com.aliang.library.model.MBook;
import com.aliang.library.model.MBookSearch;
import com.aliang.library.model.MUser;
import com.aliang.library.service.IBookService;
import com.aliang.library.service.IRecordService;
import com.aliang.library.service.IShopService;
import com.aliang.library.service.IUserService;
import com.aliang.library.vo.*;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Preconditions;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class BookController {

    //第一次Git提交到GitHub
    @Autowired
    private IBookService bookService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IRecordService recordService;

    @Autowired
    private IShopService shopService;

    @RequestMapping(path = "/bookList", method = RequestMethod.GET)
    public String bookList(BookListRequest bookListRequest, Model model) {
        // 根据图书查询条件的参数，生成用于分页的基准url，也就是baseUrl。
        // 这个baseUrl会用于生成分页导航中的a标签的href属性，也就是超链接的值。
        // 同时，还会生成数据库SQL查询的条件清单，也就是queryConditions
        List<String> params = new ArrayList<>();
        Map<String, Object> queryConditions = new HashMap<>();
        if (StringUtils.isNotEmpty(bookListRequest.getAuthor())) {
            params.add(String.format("author=%s", bookListRequest.getAuthor()));
            queryConditions.put("author", bookListRequest.getAuthor());
        }
        if (StringUtils.isNotEmpty(bookListRequest.getBookName())) {
            params.add(String.format("bookName=%s", bookListRequest.getBookName()));
            queryConditions.put("bookName", bookListRequest.getBookName());
        }
        if (StringUtils.isNotEmpty(bookListRequest.getPriceOrder())) {
            params.add(String.format("priceOrder=%s", bookListRequest.getPriceOrder()));
            queryConditions.put("priceOrder", bookListRequest.getPriceOrder());
        }

        String queryParams = String.join("&", params);
        String baseUrl = "bookList";
        if (StringUtils.isNotEmpty(queryParams)) {
            baseUrl = baseUrl + "?" + queryParams;
        }

        int records = (int) bookService.countBooks(queryConditions);

        //根据基准url baseUrl，查询页面索引号，每页的记录数，总记录数 这4个参数，
        //生成分页导航的Model数据，也就是MPageNav
        MPageNav mPageNav = PageNavUtil.genMPageNav(baseUrl,
                bookListRequest.getPage(), bookListRequest.getPageSize(), records);
        model.addAttribute("mPageNav", mPageNav);

        queryConditions.put("limit", Integer.parseInt(mPageNav.limit));
        queryConditions.put("offset", Integer.parseInt(mPageNav.offset));
        List<Book> books = bookService.queryBooks(queryConditions);

        List<MBook> mBooks = new ArrayList<>(books.size());
        for (Book book : books) {
            MBook mBook = new MBook();
            mBook.setBookId(String.valueOf(book.getId()));
            mBook.setBookName(String.valueOf(book.getBookName()));
            mBook.setPublish(String.valueOf(book.getPublish()));
            mBook.setAuthor(String.valueOf(book.getAuthor()));
            mBook.setBookCount(String.valueOf(book.getBookCount()));
            mBook.setPrice(String.valueOf(book.getPrice()));
//            mBook.setRent(String.valueOf(book.getRent()));
            mBook.setBookCount(book.getBookCount() > 0 ? "是" : "否");
            mBook.setUsable(book.getUsable() == 1 ? "是" : "否");
//            mBook.setStatus(book.getStatus() == 1 ? "是" : "否");
            mBook.setImagePath(String.format("upload/book_%d.img?t=%d", book.getId(), System.currentTimeMillis()));
            mBooks.add(mBook);
        }
        // 将model数据放入request，之后在JSP中，能够通过requestScope访问到model数据
        model.addAttribute("mBooks", mBooks);

        // MBookSearch用于保存查询的条件，将其按照查询传入的值，填写到搜索表单中。
        MBookSearch mBookSearch = new MBookSearch(
                bookListRequest.getAuthor(), bookListRequest.getBookName(), bookListRequest.getPriceOrder()
        );
        model.addAttribute("mBookSearch", mBookSearch);
        return "bookList";


    }

    @RequestMapping(value = "bookDetail", method = RequestMethod.GET)
    public String bookDetail(String bookId, Model model) {
        if (StringUtils.isBlank(bookId)) {
            model.addAttribute("message", "图书编号为空");
            return "bookFoundError";
        }
        Map<String, Object> queryConditions = new HashMap<>();
        queryConditions.put("bookId", bookId);
        List<Book> bookList = bookService.queryBook4Ext(queryConditions);
        if (bookList == null || bookList.size() == 0) {
            model.addAttribute("message", "找不到该图书");
            return "bookFoundError";
        }
        if (bookList.size() > 1) {
            model.addAttribute("message", "找到多辆图书");
            return "bookFoundError";
        }

        Book book = bookList.get(0);
        MBook mBook = new MBook();
        mBook.setBookId(String.valueOf(book.getId()));
        mBook.setBookName(String.valueOf(book.getBookName()));
        mBook.setAuthor(String.valueOf(book.getAuthor()));
        mBook.setPublish(String.valueOf(book.getPublish()));
        mBook.setPrice(String.valueOf(book.getPrice()));
        mBook.setBookCount(String.valueOf(book.getBookCount()));
        mBook.setRent(String.format("%.2f", book.getRent()));
        mBook.setUsable(book.getUsable() == 0 ? "是" : "否");
        mBook.setStatus(book.getStatus() == 0 ? "是" : "否");
        mBook.setImagePath(String.format("upload/book_%d.img?t=%d", book.getId(), System.currentTimeMillis()));

        model.addAttribute("mBook", mBook);
        return "bookDetail";
    }

    @RequestMapping(path = "/bookAdd", method = RequestMethod.GET)
    public String bookAdd() {
        return "bookAdd";
    }

    @RequestMapping(path = "/doBookAdd", method = RequestMethod.POST)
    public String doBookAdd(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Map<String, FileItem> fileItemMap = new HashMap<>();

        try {
            // 检测是否为多媒体上传
            FileUploadUtil.checkMultipartRequest(request);
            ServletFileUpload upload = FileUploadUtil.prepareFileUpload();

            // 解析请求的内容提取文件数据
            List<FileItem> formItems = upload.parseRequest(request);
            if (formItems != null && formItems.size() > 0) {
                // 迭代表单数据
                for (FileItem item : formItems) {
                    fileItemMap.put(item.getFieldName(), item);
                }
            }

            Preconditions.checkNotNull(fileItemMap.get("bookName"), "表单缺少bookName");
            String bookName = fileItemMap.get("bookName").getString("UTF-8");
            Preconditions.checkArgument(StringUtils.isNotBlank(bookName), "图书名称不能为空");

            Preconditions.checkNotNull(fileItemMap.get("author"), "表单缺少author");
            String author = fileItemMap.get("author").getString("UTF-8");
            Preconditions.checkArgument(StringUtils.isNotBlank(author), "作者不能为空");

            Preconditions.checkNotNull(fileItemMap.get("publish"), "表单缺少publish");
            String publish = fileItemMap.get("publish").getString("UTF-8");
            Preconditions.checkArgument(StringUtils.isNotBlank(publish), "出版社不能为空");

            Preconditions.checkNotNull(fileItemMap.get("bookCount"), "表单缺少bookCount");
            String bookCount = fileItemMap.get("bookCount").getString("UTF-8");
            Preconditions.checkArgument(StringUtils.isNotBlank(bookCount), "图书库存不能为空");

            Preconditions.checkNotNull(fileItemMap.get("price"), "表单缺少price");
            String price = fileItemMap.get("price").getString("UTF-8");
            Preconditions.checkArgument(StringUtils.isNotBlank(price), "图书价格不能为空");

//            Preconditions.checkNotNull(fileItemMap.get("rent"), "表单缺少rent");
//            String rent = fileItemMap.get("rent").getString("UTF-8");
//            Preconditions.checkArgument(StringUtils.isNotBlank(rent), "租金不能为空");


            String usable = fileItemMap.get("usable") == null ? "" :
                    fileItemMap.get("usable").getString("UTF-8");

            Book book = new Book();
            book.setId(book.getId());
            book.setBookName(bookName);
            book.setAuthor(author);
            book.setPublish(publish);
            book.setBookCount(Integer.parseInt(bookCount));
            book.setPrice(Float.parseFloat(price));
//            book.setRent(Float.parseFloat(rent));
            book.setStatus(1);
            book.setUsable("on".equals(usable) ? 1 : 0);

            Book newBook = bookService.addBook(book);
            if (newBook == null) {
                throw new Exception("添加图书失败！");
            } else {
                FileItem imageItem = fileItemMap.get("image");
                if (imageItem.getSize() > 0) {
                    long id = newBook.getId();
                    String fileName = String.format("book_%d.img", id);
                    String filePath = FileUploadUtil.getUploadPath() + File.separator + fileName;
                    File storeFile = new File(filePath);
                    // 在控制台输出文件的上传路径
                    System.out.println(filePath);
                    // 保存文件到硬盘
                    imageItem.write(storeFile);
                }
            }
            session.setAttribute("result", "succeed");
            session.setAttribute("message", "添加图书成功！");
        } catch (Exception ex) {
            session.setAttribute("result", "failed");
            session.setAttribute("message", "添加图书失败，错误信息：" + ex.getMessage());
        }
        return "redirect:/jsp/doBookAdd.jsp";

    }


    @RequestMapping(path = "/bookUpdate", method = RequestMethod.GET)
    public String bookUpdate(String bookId, Model model) {
        if (StringUtils.isBlank(bookId)) {
            model.addAttribute("message", "bookId不能为空");
            return "bookFoundError";
        }
        Map<String, Object> queryConditions = new HashMap<>();
        queryConditions.put("bookId", bookId);
        List<Book> bookList = bookService.queryBooks(queryConditions);
        if (bookList == null || bookList.size() == 0) {
            model.addAttribute("message", "找不到该图书");
            return "bookFoundError";
        }

        if (bookList.size() > 1) {
            model.addAttribute("message", "找到多辆图书");
            return "bookFoundError";
        }

        Book book = bookList.get(0);
        MBook mBook = new MBook();
        mBook.setBookId(String.valueOf(book.getId()));
        mBook.setBookName(String.valueOf(book.getBookName()));
        mBook.setPublish(String.valueOf(book.getPublish()));
        mBook.setAuthor(String.valueOf(book.getAuthor()));
        mBook.setBookCount(String.valueOf(book.getBookCount()));
        mBook.setPrice(String.valueOf(book.getPrice()));
        mBook.setRent(String.format("%s", book.getRent()));
        mBook.setStatus(book.getStatus() == 1 ? "是" : "否");
        mBook.setUsable(book.getUsable() == 1 ? "是" : "否");
        mBook.setImagePath(String.format("upload/book_%d.img?t=%d", book.getId(), System.currentTimeMillis()));
        model.addAttribute("mBook", mBook);
        return "bookUpdate";
    }

    @RequestMapping(path = "/doBookUpdate", method = RequestMethod.POST)
    public String doBookUpdate(HttpServletRequest request) {

        HttpSession session = request.getSession();
        Map<String, FileItem> fileItemMap = new HashMap<>();
        try {
            FileUploadUtil.checkMultipartRequest(request);
            ServletFileUpload upload = FileUploadUtil.prepareFileUpload();
            // 解析请求的内容提取文件数据
            List<FileItem> formItems = upload.parseRequest(request);
            if (formItems != null && formItems.size() > 0) {
                // 迭代表单数据
                for (FileItem item : formItems) {
                    fileItemMap.put(item.getFieldName(), item);
                }
            }

            Preconditions.checkNotNull(fileItemMap.get("bookId"), "表单缺少bookId");
            String bookId = fileItemMap.get("bookId").getString("UTF-8");
            Preconditions.checkArgument(StringUtils.isNotBlank(bookId), "图书编号不能为空");

            Preconditions.checkNotNull(fileItemMap.get("bookName"), "表单缺少bookName");
            String bookName = fileItemMap.get("bookName").getString("UTF-8");
            Preconditions.checkArgument(StringUtils.isNotBlank(bookName), "图书名称不能为空");

            Preconditions.checkNotNull(fileItemMap.get("author"), "表单缺少author");
            String author = fileItemMap.get("author").getString("UTF-8");
            Preconditions.checkArgument(StringUtils.isNotBlank(author), "作者不能为空");

            Preconditions.checkNotNull(fileItemMap.get("publish"), "表单缺少publish");
            String publish = fileItemMap.get("publish").getString("UTF-8");
            Preconditions.checkArgument(StringUtils.isNotBlank(publish), "出版社不能为空");

            Preconditions.checkNotNull(fileItemMap.get("bookCount"), "表单缺少bookCount");
            String bookCount = fileItemMap.get("bookCount").getString("UTF-8");
            Preconditions.checkArgument(StringUtils.isNotBlank(bookCount), "图书库存不能为空");

            Preconditions.checkNotNull(fileItemMap.get("price"), "表单缺少price");
            String price = fileItemMap.get("price").getString("UTF-8");
            Preconditions.checkArgument(StringUtils.isNotBlank(price), "价格不能为空");

//            Preconditions.checkNotNull(fileItemMap.get("rent"), "表单缺少rent");
//            String rent = fileItemMap.get("rent").getString("UTF-8");
//            Preconditions.checkArgument(StringUtils.isNotBlank(rent), "租金不能为空");

            String usable = fileItemMap.get("usable") == null ? "" :
                    fileItemMap.get("usable").getString("UTF-8");

            String status = fileItemMap.get("status") == null ? "" :
                    fileItemMap.get("status").getString("UTF-8");

            Book book = bookService.queryBookById(Integer.parseInt(bookId));
            if (book == null) {
                request.setAttribute("message", "找不到该图书");
                return "bookFoundError";
            }

            book.setBookName(bookName);
            book.setAuthor(author);
            book.setPublish(publish);
            book.setBookCount(Integer.parseInt(bookCount));
            book.setPrice(Float.parseFloat(price));
//            book.setRent(Float.parseFloat(rent));
            book.setUsable("on".equals(usable) ? 1 : 0);
            book.setStatus("on".equals(status) ? 1 : 0);

            Book newBook = bookService.updateBook(book);
            if (newBook == null) {
                throw new Exception("更新图书失败");
            } else {
                FileItem imageItem = fileItemMap.get("image");
                if (imageItem.getSize() > 0) {
                    long id = newBook.getId();
                    String fileName = String.format("book_%d.img", id);
                    String filePath = FileUploadUtil.getUploadPath() + File.separator + fileName;
                    File storeFile = new File(filePath);
                    // 在控制台输出文件的上传路径
                    System.out.println(filePath);
                    // 保存文件到硬盘
                    imageItem.write(storeFile);
                }
            }
            session.setAttribute("result", "succeed");
            session.setAttribute("message", "修改图书成功！");
        } catch (Exception ex) {
            session.setAttribute("result", "failed");
            session.setAttribute("message", "修改图书失败，错误信息：" + ex.getMessage());
        }
        return "redirect:/jsp/doBookUpdate.jsp";
    }

    @RequestMapping(path = "/doBookUpdateUsable", method = RequestMethod.POST)
    @ResponseBody
    public DoBookUpdateUsableResponse doBookUpdateUsable(String bookId, String usable, HttpServletResponse response) {
        response.setCharacterEncoding("utf-8");//设置服务器端编码
        response.setContentType("text/html;charset=utf-8");//设置浏览器端解码

        if (StringUtils.isBlank(bookId) || StringUtils.isBlank(usable)) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return new DoBookUpdateUsableResponse(HttpServletResponse.SC_BAD_REQUEST,
                    "请求参数为空", bookId, usable);
        }

        Book book = bookService.queryBookById(Integer.parseInt(bookId));
        if (book == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return new DoBookUpdateUsableResponse(HttpServletResponse.SC_BAD_REQUEST,
                    "找不到该图书", bookId, usable);
        }
        book.setUsable("是".equals(usable.trim()) ? 0 : 1);
        Book newBook = bookService.updateBook(book);
        if (newBook == null) {
            return new DoBookUpdateUsableResponse(HttpServletResponse.SC_SERVICE_UNAVAILABLE,
                    "服务器内部错误，更新图书上下架状态失败", bookId, usable);
        }

        response.setStatus(HttpServletResponse.SC_OK);
        return new DoBookUpdateUsableResponse(HttpServletResponse.SC_OK, "更新图书上下架状态成功",
                String.valueOf(book.getId()), String.valueOf(book.getUsable()));
    }

    @RequestMapping(path = "/lendBook", method = RequestMethod.GET)
    public String lendBook(String bookId, Model model) {

        Map<String, Object> queryConditions = new HashMap<>();
        queryConditions.put("bookId", bookId);

        List<Book> bookList = bookService.queryBooks(queryConditions);
        if (bookList == null || bookList.size() == 0) {
            model.addAttribute("message", "找不到该图书");
            return "bookFoundError";
        }
        if (bookList.size() > 1) {
            model.addAttribute("message", "找到到多本图书");
            return "bookFoundError";
        }

        Book book = bookList.get(0);
        MBook mBook = new MBook();
        mBook.setBookId(String.valueOf(book.getId()));
        mBook.setBookName(String.valueOf(book.getBookName()));
        mBook.setAuthor(String.valueOf(book.getAuthor()));
        mBook.setPublish(String.valueOf(book.getPublish()));
        mBook.setBookCount(String.valueOf(book.getBookCount()));
        mBook.setPrice(String.valueOf(book.getPrice()));
        mBook.setRent(String.format("%.2f", book.getRent()));
        mBook.setUsable(book.getUsable() == 1 ? "是" : "否");
        mBook.setStatus(book.getStatus() == 1 ? "是" : "否");
        mBook.setImagePath(String.format("upload/book_%d.img?t=%d", book.getId(), System.currentTimeMillis()));

        model.addAttribute("mBook", mBook);
        return "lendBook";
    }

    @RequestMapping(path = "/doBookLend", method = RequestMethod.POST)
    @ResponseBody
    public void doBookLend(String bookId, String userId, HttpServletResponse response) throws IOException {
        Record record = recordService.lendBook(Integer.parseInt(userId), Integer.parseInt(bookId));
        if (record == null) {
            response.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
            response.getWriter().write("服务器内部错误，图书不可借或已下架");
            return;
        }
        response.setStatus(HttpServletResponse.SC_OK);
        DoBookLendResponse doBookLendResponse = new DoBookLendResponse(
                HttpServletResponse.SC_OK, "更新图书是否可借状态成功", Integer.parseInt(bookId), Integer.parseInt(userId)
        );
        response.getWriter().write(JSONObject.toJSONString(doBookLendResponse));
    }

    @RequestMapping(path = "/doBookDelete", method = RequestMethod.POST)
    @ResponseBody
    public DoBookDeleteResponse doBookDelete(HttpServletResponse response, String bookId) {
        response.setCharacterEncoding("utf-8");//设置服务器端编码
        response.setContentType("text/html;charset=utf-8");//设置浏览器端解码

        if (StringUtils.isBlank(bookId)) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return new DoBookDeleteResponse(
                    HttpServletResponse.SC_BAD_REQUEST, "请求参数为空", bookId);
        }

        Book book = bookService.deleteBookById(Integer.parseInt(bookId));
        if (book == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return new DoBookDeleteResponse(
                    HttpServletResponse.SC_BAD_REQUEST, "找不到图书", bookId);
        }

        response.setStatus(HttpServletResponse.SC_OK);
        return new DoBookDeleteResponse(
                HttpServletResponse.SC_OK, "删除图书成功", bookId);

    }

    @RequestMapping(path = "/doBookUpdateStatus", method = RequestMethod.POST)
    @ResponseBody
    public void doBookUpdateStatus(HttpServletRequest request,
                                   String bookId,
                                   String status,
                                   String id,
                                   HttpServletResponse response) throws IOException {

        Map<String, Object> queryConditions = new HashMap<>();
        queryConditions.put("bookId", bookId);

        List<Book> bookList = bookService.queryBooks(queryConditions);
        if (bookList == null || bookList.size() == 0) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().write("找不到该图书");
            return;
        }
        if (bookList.size() > 1) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            request.setAttribute("message", "找到到多本图书");
            return;
        }

        Book book = bookList.get(0);
        Integer bookID = Integer.parseInt(bookId);
        Integer ID = Integer.parseInt(id);
        book.setStatus("还书".equals(status.trim()) ? 0 : 1);
        Record record = recordService.returnBook(ID, bookID);
        if (record == null) {
            response.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
            response.getWriter().write("服务器内部错误，图书已下架");
            return;
        }
        response.setStatus(HttpServletResponse.SC_OK);
        DoBookUpdateStatusResponse statusResponse = new DoBookUpdateStatusResponse(
                HttpServletResponse.SC_OK, "更新图书是否可租状态成功", Long.parseLong(bookId), book.getStatus(), Long.parseLong(id),book.getBookCount()
        );
        response.getWriter().write(JSONObject.toJSONString(statusResponse));
    }


    @RequestMapping(path = "/buyBook", method = RequestMethod.GET)
    public String buyBook(String bookId, Model model,HttpServletResponse response) {

        Map<String, Object> queryConditions = new HashMap<>();
        queryConditions.put("bookId", bookId);

        List<Book> bookList = bookService.queryBooks(queryConditions);
        if (bookList == null || bookList.size() == 0) {
            model.addAttribute("message", "找不到该图书");
            return "bookFoundError";
        }
        if (bookList.size() > 1) {
            model.addAttribute("message", "找到到多本图书");
            return "bookFoundError";
        }

        Book book = bookList.get(0);
        MBook mBook = new MBook();
        mBook.setBookId(String.valueOf(book.getId()));
        mBook.setBookName(String.valueOf(book.getBookName()));
        mBook.setAuthor(String.valueOf(book.getAuthor()));
        mBook.setPublish(String.valueOf(book.getPublish()));
        mBook.setBookCount(String.valueOf(book.getBookCount()));
        mBook.setPrice(String.valueOf(book.getPrice()));
        mBook.setRent(String.format("%.2f", book.getRent()));
        mBook.setUsable(book.getUsable() == 1 ? "是" : "否");
        mBook.setStatus(book.getStatus() == 1 ? "是" : "否");
        mBook.setImagePath(String.format("upload/book_%d.img?t=%d", book.getId(), System.currentTimeMillis()));

        model.addAttribute("mBook", mBook);
        return "buyBook";
    }

    @RequestMapping(path = "/doBookBuy", method = RequestMethod.POST)
    @ResponseBody
    public void doBookBuy(String bookId,HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession(false);
        // 登录获取的用户id
        MUser mUser = (MUser) session.getAttribute("mUser");
        String userId = mUser.getUserId();
        User user = userService.queryUserById(Integer.parseInt(userId));
        float balance = user.getBalance();

        Book book = bookService.queryBookById(Integer.parseInt(bookId));
        float price = book.getPrice();
        float newBalance = balance - price;
        if (newBalance < 0 ) {
            response.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
            response.getWriter().write("钱不够");
            return;
        }
        user.setBalance(newBalance);
        User user2 =  userService.updateUser(user);
        if (user2 == null) {
            response.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
            response.getWriter().write("修改用户余额失败");
            return;
        }

        Shop shop = shopService.buyBook(Integer.parseInt(userId), Integer.parseInt(bookId));
        if (shop == null) {
            response.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
            response.getWriter().write("服务器内部错误，图书不可够买或已下架");
            return;
        }
        response.setStatus(HttpServletResponse.SC_OK);
        DoBookLendResponse doBookLendResponse = new DoBookLendResponse(
                HttpServletResponse.SC_OK, "更新图书是否可卖状态成功", Integer.parseInt(bookId), Integer.parseInt(userId)
        );
        response.getWriter().write(JSONObject.toJSONString(doBookLendResponse));
    }
}
