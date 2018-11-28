package com.fc.index.service;

import com.fc.test.common.base.BaseController;
import com.fc.test.model.custom.TitleVo;
import net.sf.json.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ServiceController extends BaseController {


    /**
     * 请求到首页
     * @param request
     * @return
     */
    @RequestMapping("/QnService")
    public String QnRoot(HttpServletRequest request, Model model) {
        try {
            if ((null != SecurityUtils.getSubject() && SecurityUtils.getSubject().isAuthenticated()) || SecurityUtils.getSubject().isRemembered()) {
                JSONObject jsonObject = JSONObject.fromObject(SecurityUtils.getSubject().getPrincipal());
                String username = jsonObject.getString("username");
                setTitle(model, new TitleVo("技术支持与服务", username, true,"欢迎进入", true, true));
                return "index/service";
            } else {
                setTitle(model, new TitleVo("技术支持与服务", "技术支持与服务", true,"欢迎进入", true, false));
                return "index/service";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        setTitle(model, new TitleVo("技术支持与服务", "技术支持与服务", true,"欢迎进入", true, false));
        return "index/service";
    }



}
