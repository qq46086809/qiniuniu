package com.fc.index.Trademark;

import com.fc.test.common.base.BaseController;
import com.fc.test.model.custom.TitleVo;
import net.sf.json.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller

public class TrademarkController extends BaseController{

    /**
     * 请求到商标交易页面
     * @param request
     * @return
     */
    @RequestMapping("/QnTrademark")
    public String QnTrademark(HttpServletRequest request, Model model) {
        try {
            if ((null != SecurityUtils.getSubject() && SecurityUtils.getSubject().isAuthenticated()) || SecurityUtils.getSubject().isRemembered()) {
                JSONObject jsonObject = JSONObject.fromObject(SecurityUtils.getSubject().getPrincipal());
                String username = jsonObject.getString("username");
                setTitle(model, new TitleVo("商标交易", username, true,"欢迎进入", true, true));
                return "index/trademark";
            } else {
                setTitle(model, new TitleVo("商标交易", "商标交易", true,"欢迎进入", true, false));
                return "index/trademark";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        setTitle(model, new TitleVo("商标交易", "商标交易", true,"欢迎进入", true, false));
        return "index/trademark";
    }

}
