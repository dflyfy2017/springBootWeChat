package cn.springboot.wechat.controller;

import cn.springboot.wechat.model.User;
import cn.springboot.wechat.service.UserService;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Author: fuyuan
 * Date: 2017/7/20 10:12
 */
@Controller
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginView(){
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam("account") String account,
                        @RequestParam("password") String password,
                        HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        logger.info("账户名，密码：" + account + "," + password);
        User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        model.addAttribute("account", account);
        model.addAttribute("password", password);

        try {
            user = userService.login(user);
            logger.info("验证成功" + user.getName());
            model.addAttribute("user",user);
            /*session.setAttribute("user",user);*/
        } catch (Exception e) {
            /*session.setAttribute("loginMsg",e.getMessage());*/
            model.addAttribute("loginMsg", e.getMessage());
            return "login";
        }
        return "home";
    }

    @RequestMapping(value = "/loginAjax", method = RequestMethod.POST)
    public void loginAJAX(@RequestParam("account") String account,
                        @RequestParam("password") String password,
                        HttpServletRequest request, HttpServletResponse response){
        logger.info("账户名，密码：" + account + "," + password);
        User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        try {
            user = userService.login(user);
            logger.info("验证成功" + user.getName());
        } catch (Exception e) {

        }
    }

    @RequestMapping(value = "/so", method = RequestMethod.GET)
    public String socket(){
        return "sock";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(){
        return "home";
    }

    /**
     * ajax提交表单实现注册
     * 成功返回注册成功的账号
     * @param name
     * @param email
     * @param password
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public void register(String name, String email, String password,
                           HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        String reMsg = null;
        try {
            reMsg = "注册成功,您的账号为："
                    + userService.register(user).getAccount();
        } catch (Exception e) {
            reMsg = e.getMessage();
        }
        out.print(reMsg);
        out.flush();
        out.close();
    }

    /**
     * 注册：
     * 验证email是否已经存在
     * @param email
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/regEmail", method = RequestMethod.POST)
    public void validateEmail(String email, HttpServletResponse response)
            throws IOException {
        User user = new User();
        user.setEmail(email);
        PrintWriter writer = response.getWriter();
        try {
            user = userService.select("email", email);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (user != null) {
            writer.print(false);
        } else {
            writer.print(true);
        }
        writer.flush();
        writer.close();
    }

}
