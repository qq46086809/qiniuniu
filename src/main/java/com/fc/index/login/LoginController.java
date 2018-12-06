package com.fc.index.login;

import com.fc.test.common.base.BaseController;
import com.fc.test.model.custom.TitleVo;
import net.sf.json.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController extends BaseController {


    /**
     * 请求到首页
     * @param request
     * @return
     */
    @RequestMapping("/QnRoot")
    public String QnRoot(HttpServletRequest request, Model model) {
        try {
            if ((null != SecurityUtils.getSubject() && SecurityUtils.getSubject().isAuthenticated()) || SecurityUtils.getSubject().isRemembered()) {
                JSONObject jsonObject = JSONObject.fromObject(SecurityUtils.getSubject().getPrincipal());
                String username = jsonObject.getString("username");
                setTitle(model, new TitleVo("首页", username, true,"欢迎进入", true, true));
                return "index/index";
            } else {
                setTitle(model, new TitleVo("首页", "首页", true,"欢迎进入", true, false));
                return "index/index";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        setTitle(model, new TitleVo("首页", "首页", true,"欢迎进入", true, false));
        return "index/index";
    }


    /**
     * 请求到登陆界面
     * @param request
     * @return
     */
    @RequestMapping("/QnLogin")
    public String QnLogin(HttpServletRequest request, Model model) {
        try {
            if ((null != SecurityUtils.getSubject() && SecurityUtils.getSubject().isAuthenticated()) || SecurityUtils.getSubject().isRemembered()) {
                setTitle(model, new TitleVo("首页", "首页", true,"欢迎进入", true, true));
                return "index/index";
            } else {
                System.out.println("--进行登录验证..验证开始");
                return "index/login";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "index/login";
    }
}
