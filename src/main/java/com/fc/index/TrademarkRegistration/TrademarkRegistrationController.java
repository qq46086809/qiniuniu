package com.fc.index.TrademarkRegistration;

import com.fc.test.common.base.BaseController;
import com.fc.test.model.custom.TitleVo;
import net.sf.json.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller

public class TrademarkRegistrationController extends BaseController{

    /**
     * 请求到商标注册页面
     * @param request
     * @return
     */
    @RequestMapping("/QnTrademarkregistration")
    public String QnTrademarkregistration(HttpServletRequest request, Model model) {
        try {
            if ((null != SecurityUtils.getSubject() && SecurityUtils.getSubject().isAuthenticated()) || SecurityUtils.getSubject().isRemembered()) {
                JSONObject jsonObject = JSONObject.fromObject(SecurityUtils.getSubject().getPrincipal());
                String username = jsonObject.getString("username");
                setTitle(model, new TitleVo("商标注册", username, true,"欢迎进入", true, true));
                return "index/trademarkregistration";
            } else {
                setTitle(model, new TitleVo("商标注册", "商标注册", true,"欢迎进入", true, false));
                return "index/trademarkregistration";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        setTitle(model, new TitleVo("商标注册", "商标注册", true,"欢迎进入", true, false));
        return "index/trademarkregistration";
    }

}

