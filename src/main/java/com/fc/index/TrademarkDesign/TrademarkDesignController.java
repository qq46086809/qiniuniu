package com.fc.index.TrademarkDesign;

import com.fc.test.common.base.BaseController;
import com.fc.test.model.custom.TitleVo;
import net.sf.json.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller

public class TrademarkDesignController extends BaseController{

    /**
     * 请求到商标设计页面
     * @param request
     * @return
     */
    @RequestMapping("/QnTrademarkdesign")
    public String QnTrademarkdesign(HttpServletRequest request, Model model) {
        try {
            if ((null != SecurityUtils.getSubject() && SecurityUtils.getSubject().isAuthenticated()) || SecurityUtils.getSubject().isRemembered()) {
                JSONObject jsonObject = JSONObject.fromObject(SecurityUtils.getSubject().getPrincipal());
                String username = jsonObject.getString("username");
                setTitle(model, new TitleVo("商标设计", username, true,"欢迎进入", true, true));
                return "index/trademarkdesign";
            } else {
                setTitle(model, new TitleVo("商标设计", "商标设计", true,"欢迎进入", true, false));
                return "index/trademarkdesign";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        setTitle(model, new TitleVo("商标设计", "商标设计", true,"欢迎进入", true, false));
        return "index/trademarkdesign";
    }

}

