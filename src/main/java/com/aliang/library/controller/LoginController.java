package com.aliang.library.controller;

import com.aliang.library.common.FileUploadUtil;
import com.aliang.library.entity.User;
import com.aliang.library.model.MUser;
import com.aliang.library.service.IUserService;
import com.google.common.base.Preconditions;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    private IUserService userService;

    @RequestMapping(path = "/doLogin")
    public String doLogin(HttpServletRequest request, String username, String password) {
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(3000);

        User user = userService.login(username, password);
        if (user == null) {
            session.setAttribute("result", "failed");
            return "redirect:/jsp/login.jsp";
        }

        session.setAttribute("result", "succeed");
        session.setAttribute("username", username);

        MUser mUser = new MUser();
        mUser.setUserId(String.valueOf(user.getId()));
        mUser.setUsername(String.valueOf(user.getUsername()));
        mUser.setPassword(String.valueOf(user.getPassword()));
        mUser.setSex(user.getSex() == 0 ? "男" : "女");
        mUser.setTel(String.valueOf(user.getTel()));
        mUser.setAccount(user.getAccount() == 1 ? "管理员" : "普通用户");

        session.setAttribute("mUser", mUser);
        return "redirect:bookList";
    }

    @RequestMapping(path = "/register", method = RequestMethod.GET)
    public String register() {
        return "register";
    }

    @RequestMapping(path = "/doRegister", method = RequestMethod.POST)
    @Transactional
    public String doRegister(HttpServletRequest request) {
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
            Preconditions.checkNotNull(fileItemMap.get("userName"), "表单缺少userName");
            String userName = fileItemMap.get("userName").getString("UTF-8");
            Preconditions.checkArgument(StringUtils.isNotBlank(userName), "用户名不能为空");

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
            user.setUsername(userName);
            user.setPassword(password);
            user.setSex("1".equals(sex) ? 1 : 0);
            user.setTel(tel);
            user.setAccount("1".equals(account) ? 1 : 0);

            User newUser = userService.addUser(user);
            if (newUser == null) {
                throw new Exception("注册失败");
            }
            session.setAttribute("result", "succeed");
            session.setAttribute("message", "注册成功！");
        } catch (Exception ex) {
            session.setAttribute("result", "failed");
            session.setAttribute("message", "注册失败，错误信息：" + ex.getMessage());
        }
        return "redirect:/jsp/doRegister.jsp";
    }

    @RequestMapping(path = "/doLoginOut", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView doLoginOut(HttpSession session){
        session.invalidate();

        ModelAndView modelAndView = new ModelAndView("redirect:/jsp/login.jsp");
        return modelAndView;
    }

}
