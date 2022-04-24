package com.aliang.library.controller;

import com.aliang.library.common.FileUploadUtil;
import com.aliang.library.common.PageNavUtil;
import com.aliang.library.common.model.MPageNav;
import com.aliang.library.entity.User;
import com.aliang.library.model.MUser;
import com.aliang.library.model.MUserSearch;
import com.aliang.library.service.IUserService;
import com.aliang.library.vo.DoUserDeleteResponse;
import com.aliang.library.vo.UserListRequest;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping(path = "/userList", method = RequestMethod.GET)
    public String userList(UserListRequest userLR, Model model) {

        // 根据用户查询条件的参数，生成用于分页的基准url，也就是baseUrl。
        //这个baseUrl会用于生成分页导航中的a标签的href属性，也就是超链接的值。
        // 同时，还会生成数据库SQL查询的条件清单，也就是queryConditions
        List<String> params = new ArrayList<>();
        Map<String, Object> queryConditions = new HashMap<>();
        if (StringUtils.isNotEmpty(userLR.getUserId())) {
            params.add(String.format("userId = %s", userLR.getUserId()));
            queryConditions.put("userId", userLR.getUserId());
        }
        if (StringUtils.isNotEmpty(userLR.getUsername())) {
            params.add(String.format("username = %s", userLR.getUsername()));
            queryConditions.put("username", userLR.getUsername());
        }
        if (StringUtils.isNotEmpty(userLR.getAccount())) {
            params.add(String.format("account = %s", userLR.getAccount()));
            queryConditions.put("account", userLR.getAccount());
        }

//        // 普通用户不显示
//        HttpSession session = request.getSession(false);
//        MUser mmUser = (MUser) session.getAttribute("mUser");
//        if ("普通用户".equals(mmUser.getAccount())) {
//            queryConditions.add(new QueryCondition("account", "0"));
//        }

        String queryParams = String.join("&", params);
        String baseUrl = "userList";
        if (StringUtils.isNotEmpty(queryParams)) {
            baseUrl = baseUrl + "?" + queryParams;
        }


        //根据指定的条件，查询符合条件的所有的用户的总数。
        //就相当于 select count(*) from t_car where ${queryConditions}
        //获取这个总数的目的，是为了知道分页导航的最大页数是多少，也就是分页导航中尾页的索引号
        int records = (int) userService.countUsers(queryConditions);

        //根据基准url baseUrl，查询页面索引号，每页的记录数，总记录数 这4个参数，
        //生成分页导航的Model数据，也就是MPageNav
        MPageNav mPageNav = PageNavUtil.genMPageNav(baseUrl,
                userLR.getPage(), userLR.getPageSize(), records);
        model.addAttribute("mPageNav", mPageNav);

        //根据查询条件queryConditions，以及分页导航中指定页面需要查询的数据范围，也就是limit和offset
        //查询出页面需要的数据
        queryConditions.put("limit", Integer.parseInt(mPageNav.limit));
        queryConditions.put("offset", Integer.parseInt(mPageNav.offset));
        List<User> users = userService.queryUsers(queryConditions);
        //查询出用户列表数据后，将其按照JSP页面需要的字段，转化为Model数据

        List<MUser> mUsers = new ArrayList<>(users.size());
        for (User user : users) {
            MUser mUser = new MUser();
            mUser.setUserId(String.valueOf(user.getId()));
            mUser.setUsername(String.valueOf(user.getUsername()));
            mUser.setPassword(String.valueOf(user.getPassword()));
            mUser.setSex(user.getSex() == 1 ? "男" : "女");
            mUser.setTel(String.valueOf(user.getTel()));
            mUser.setAccount(user.getAccount() == 1 ? "管理员" : "普通用户");
            mUser.setImagePath(String.format("upload/user_%d.img?t=%d", user.getId(), System.currentTimeMillis()));
            mUsers.add(mUser);
        }
        // 将model数据放入request，之后在JSP中，能够通过requestScope访问到model数据
        model.addAttribute("mUsers", mUsers);

        // MCarSearch用于保存查询的条件，将其按照查询传入的值，填写到搜索表单中。
        MUserSearch mUserSearch = new MUserSearch(userLR.getUserId(), userLR.getUsername(), userLR.getAccount());
        model.addAttribute("mUserSearch", mUserSearch);

        return "userList";
    }

    @RequestMapping(path = "/userAdd", method = RequestMethod.GET)
    public String userAdd() {
        return "userAdd";
    }

    @RequestMapping(path = "/doUserAdd", method = RequestMethod.POST)
    public String doUserAdd(HttpServletRequest request, HttpServletResponse response) {
        Map<String, FileItem> fileItemMap = new HashMap<>();

        HttpSession session = request.getSession();
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
            Preconditions.checkNotNull(fileItemMap.get("username"), "表单缺少username");
            String username = fileItemMap.get("username").getString("UTF-8");
            Preconditions.checkArgument(StringUtils.isNotBlank(username), "用户名不能为空");

            Preconditions.checkNotNull(fileItemMap.get("password"), "表单缺少password");
            String password = fileItemMap.get("password").getString("UTF-8");
            Preconditions.checkArgument(StringUtils.isNotBlank(password), "密码不能为空");

            String sex = fileItemMap.get("sex") == null ? "" : fileItemMap.get("sex").getString("UTF-8");

            Preconditions.checkNotNull(fileItemMap.get("tel"), "表单缺少tel");
            String tel = fileItemMap.get("tel").getString("UTF-8");
            Preconditions.checkArgument(StringUtils.isNotBlank(tel), "电话不能为空");

            String account = fileItemMap.get("account") == null ? "" : fileItemMap.get("account").getString("UTF-8");


            User user = new User();
            user.setId(user.getId());
            user.setUsername(username);
            user.setPassword(password);
            user.setSex("1".equals(sex) ? 1 : 0);
            user.setTel(tel);
            user.setAccount("0".equals(account) ? 0 : 1);

            User newUser = userService.addUser(user);
            if (newUser == null) {
                throw new Exception("新增用户失败");
            } else {
                FileItem imageItem = fileItemMap.get("image");
                if (imageItem.getSize() > 0) {
                    long id = newUser.getId();
                    String fileName = String.format("user_%d.img", id);
                    String filePath = FileUploadUtil.getUploadPath() + File.separator + fileName;
                    File storeFile = new File(filePath);
                    // 在控制台输出文件的上传路径
                    System.out.println(filePath);
                    // 保存文件到硬盘
                    imageItem.write(storeFile);
                }
            }
            session.setAttribute("result", "succeed");
            session.setAttribute("message", "新增用户成功！");
        } catch (Exception ex) {
            session.setAttribute("result", "failed");
            session.setAttribute("message", "新增用户失败，错误信息：" + ex.getMessage());
        }
        return "redirect:/jsp/doUserAdd.jsp";
    }

    @RequestMapping(path = "/userUpdate", method = RequestMethod.GET)
    public String userUpdate(String userId, Model model) {
        if (StringUtils.isBlank(userId)) {
            model.addAttribute("message", "userId不能为空");
            return "userFoundError";
        }
        Map<String, Object> queryConditions = new HashMap<>();
        queryConditions.put("userId", userId);
        List<User> userList = userService.queryUsers(queryConditions);
        if (userList == null || userList.size() == 0) {
            model.addAttribute("message", "找不到该用户");
            return "userFoundError";
        }

        if (userList.size() > 1) {
            model.addAttribute("message", "找到多名用户");
            return "userFoundError";
        }

        User user = userList.get(0);
        MUser mUser = new MUser();
        mUser.setUserId(String.valueOf(user.getId()));
        mUser.setUsername(String.valueOf(user.getUsername()));
        mUser.setPassword(String.valueOf(user.getPassword()));
        mUser.setSex(String.valueOf(user.getSex()));
        mUser.setTel(String.valueOf(user.getTel()));
        mUser.setAccount(String.valueOf(user.getAccount()));
        mUser.setImagePath(String.format("upload/user_%d.img?t=%d", user.getId(), System.currentTimeMillis()));
        model.addAttribute("mUser", mUser);
        return "userUpdate";
    }

    @RequestMapping(path = "/doUserUpdate", method = RequestMethod.POST)
    public String doUserUpdate(HttpServletRequest request) {

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

            Preconditions.checkNotNull(fileItemMap.get("userId"), "表单缺少userId");
            String userId = fileItemMap.get("userId").getString("UTF-8");
            Preconditions.checkArgument(StringUtils.isNotBlank(userId), "用户id不能为空");


            Preconditions.checkNotNull(fileItemMap.get("username"), "表单缺少username");
            String username = fileItemMap.get("username").getString("UTF-8");
            Preconditions.checkArgument(StringUtils.isNotBlank(username), "用户名不能为空");

            Preconditions.checkNotNull(fileItemMap.get("password"), "表单缺少password");
            String password = fileItemMap.get("password").getString("UTF-8");
            Preconditions.checkArgument(StringUtils.isNotBlank(password), "密码不能为空");

            Preconditions.checkNotNull(fileItemMap.get("sex"), "表单缺少sex");
            String sex = fileItemMap.get("sex").getString("UTF-8");
            Preconditions.checkArgument(StringUtils.isNotBlank(sex), "性别不能为空");


            Preconditions.checkNotNull(fileItemMap.get("tel"), "表单缺少tel");
            String tel = fileItemMap.get("tel").getString("UTF-8");
            Preconditions.checkArgument(StringUtils.isNotBlank(tel), "电话号码不能为空");


            Preconditions.checkNotNull(fileItemMap.get("account"), "account");
            String account = fileItemMap.get("account") == null ? "" :
                    fileItemMap.get("account").getString("UTF-8");

            User user = userService.queryUserById(Integer.parseInt(userId));
            if (user == null) {
                request.setAttribute("message", "找不到该用户");
                return "userFoundError";
            }

            user.setUsername(username);
            user.setPassword(password);
            user.setSex("0".equals(sex) ? 0 : 1);
            user.setTel(tel);
            user.setAccount("on".equals(account) ? 0 : 1);

            User newUser = userService.updateUser(user);
            if (newUser == null) {
                throw new Exception("更新用户失败");
            } else {
                FileItem imageItem = fileItemMap.get("image");
                if (imageItem.getSize() > 0) {
                    long id = newUser.getId();
                    String fileName = String.format("user_%d.img", id);
                    String filePath = FileUploadUtil.getUploadPath() + File.separator + fileName;
                    File storeFile = new File(filePath);
                    // 在控制台输出文件的上传路径
                    System.out.println(filePath);
                    // 保存文件到硬盘
                    imageItem.write(storeFile);
                }
            }
            session.setAttribute("result", "succeed");
            session.setAttribute("message", "修改用户成功！");
        } catch (Exception ex) {
            session.setAttribute("result", "failed");
            session.setAttribute("message", "修改用户失败，错误信息：" + ex.getMessage());
        }
        return "redirect:/jsp/doUserUpdate.jsp";
    }

    @RequestMapping(path = "/personal", method = RequestMethod.GET)
    public String personal(String userId, Model model) {
        Map<String, Object> queryConditions = new HashMap<>();
        queryConditions.put("userId", userId);
        List<User> userList = userService.queryUsers(queryConditions);
        if (userList == null || userList.size() == 0) {
            model.addAttribute("message", "找不到该用户");
            return "userFoundError";
        }
        if (userList.size() > 1) {
            model.addAttribute("message", "找到到多个用户");
            return "userFoundError";
        }
        User user = userList.get(0);
        MUser mUser = new MUser();
        mUser.setUserId(String.valueOf(user.getId()));
        mUser.setUsername(String.valueOf(user.getUsername()));
        mUser.setPassword(String.valueOf(user.getPassword()));
        mUser.setSex(user.getSex() == 1 ? "男" : "女");
        mUser.setTel(String.valueOf(user.getTel()));
        mUser.setBalance(String.valueOf(user.getBalance()));
        mUser.setAccount(user.getAccount() == 1 ? "管理员" : "普通用户");
        mUser.setImagePath(String.format("upload/user_%d.img?t=%d", user.getId(), System.currentTimeMillis()));

        model.addAttribute("mUser", mUser);
        return "personal";
    }

    @RequestMapping(path = "/personalUpdate", method = RequestMethod.GET)
    public String personalUpdate(String userId, Model model) {
        if (StringUtils.isBlank(userId)) {
            model.addAttribute("message", "userId不能为空");
            return "userFoundError";
        }
        Map<String, Object> queryConditions = new HashMap<>();
        queryConditions.put("userId", userId);
        List<User> userList = userService.queryUsers(queryConditions);
        if (userList == null || userList.size() == 0) {
            model.addAttribute("message", "找不到该用户");
            return "userFoundError";
        }

        if (userList.size() > 1) {
            model.addAttribute("message", "找到多名用户");
            return "userFoundError";
        }

        User user = userList.get(0);
        MUser mUser = new MUser();
        mUser.setUserId(String.valueOf(user.getId()));
        mUser.setUsername(String.valueOf(user.getUsername()));
        mUser.setPassword(String.valueOf(user.getPassword()));
        mUser.setSex(String.valueOf(user.getSex()));
        mUser.setTel(String.valueOf(user.getTel()));
        mUser.setAccount(String.valueOf(user.getAccount()));
        mUser.setImagePath(String.format("upload/user_%d.img?t=%d", user.getId(), System.currentTimeMillis()));
        model.addAttribute("mUser", mUser);
        return "personalUpdate";
    }

    @RequestMapping(path = "/doPersonalUpdate", method = RequestMethod.POST)
    public String doPersonalUpdate(HttpServletRequest request) {

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

            Preconditions.checkNotNull(fileItemMap.get("userId"), "表单缺少userId");
            String userId = fileItemMap.get("userId").getString("UTF-8");
            Preconditions.checkArgument(StringUtils.isNotBlank(userId), "用户id不能为空");


            Preconditions.checkNotNull(fileItemMap.get("username"), "表单缺少username");
            String username = fileItemMap.get("username").getString("UTF-8");
            Preconditions.checkArgument(StringUtils.isNotBlank(username), "用户名不能为空");

            Preconditions.checkNotNull(fileItemMap.get("password"), "表单缺少password");
            String password = fileItemMap.get("password").getString("UTF-8");
            Preconditions.checkArgument(StringUtils.isNotBlank(password), "密码不能为空");

            Preconditions.checkNotNull(fileItemMap.get("sex"), "表单缺少sex");
            String sex = fileItemMap.get("sex").getString("UTF-8");
            Preconditions.checkArgument(StringUtils.isNotBlank(sex), "性别不能为空");


            Preconditions.checkNotNull(fileItemMap.get("tel"), "表单缺少tel");
            String tel = fileItemMap.get("tel").getString("UTF-8");
            Preconditions.checkArgument(StringUtils.isNotBlank(tel), "电话号码不能为空");


            Preconditions.checkNotNull(fileItemMap.get("account"), "account");
            String account = fileItemMap.get("account") == null ? "" :
                    fileItemMap.get("account").getString("UTF-8");

            User user = userService.queryUserById(Integer.parseInt(userId));
            if (user == null) {
                request.setAttribute("message", "找不到该用户");
                return "userFoundError";
            }

            user.setUsername(username);
            user.setPassword(password);
            user.setSex("0".equals(sex) ? 0 : 1);
            user.setTel(tel);
            user.setAccount("1".equals(account) ? 1 : 0);

            User newUser = userService.updateUser(user);
            if (newUser == null) {
                throw new Exception("更新用户失败");
            } else {
                FileItem imageItem = fileItemMap.get("image");
                if (imageItem.getSize() > 0) {
                    long id = newUser.getId();
                    String fileName = String.format("user_%d.img", id);
                    String filePath = FileUploadUtil.getUploadPath() + File.separator + fileName;
                    File storeFile = new File(filePath);
                    // 在控制台输出文件的上传路径
                    System.out.println(filePath);
                    // 保存文件到硬盘
                    imageItem.write(storeFile);
                }
            }
            session.setAttribute("result", "succeed");
            session.setAttribute("message", "修改用户成功！");
        } catch (Exception ex) {
            session.setAttribute("result", "failed");
            session.setAttribute("message", "修改用户失败，错误信息：" + ex.getMessage());
        }
        return "redirect:/jsp/doPersonalUpdate.jsp";

    }

    @RequestMapping(path = "/doUserDelete", method = RequestMethod.POST)
    @ResponseBody
    public DoUserDeleteResponse doUserDelete(HttpServletResponse response, String userId) {
        response.setCharacterEncoding("utf-8");//设置服务器端编码
        response.setContentType("text/html;charset=utf-8");//设置浏览器端解码

        if (StringUtils.isBlank(userId)) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return new DoUserDeleteResponse(
                    HttpServletResponse.SC_BAD_REQUEST, "请求参数为空", userId);
        }

        User user = userService.deleteUserById(Integer.parseInt(userId));
        if (user == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return new DoUserDeleteResponse(
                    HttpServletResponse.SC_BAD_REQUEST, "找不到用户", userId);
        }

        response.setStatus(HttpServletResponse.SC_OK);
        return new DoUserDeleteResponse(
                HttpServletResponse.SC_OK, "删除用户成功", userId);

    }

}