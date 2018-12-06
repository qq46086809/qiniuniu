package com.fc.index.download;


import com.fc.test.common.base.BaseController;
import com.fc.test.model.custom.TitleVo;
import net.sf.json.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProductViewController extends BaseController {


    /**
     * 请求到产品详情
     * @param request
     * @return
     */
    @RequestMapping("/QnProduct")
    public String QnProduct(HttpServletRequest request, Model model) {
        try {
            if ((null != SecurityUtils.getSubject() && SecurityUtils.getSubject().isAuthenticated()) || SecurityUtils.getSubject().isRemembered()) {
                JSONObject jsonObject = JSONObject.fromObject(SecurityUtils.getSubject().getPrincipal());
                String username = jsonObject.getString("username");
                setTitle(model, new TitleVo("产品详情", username, true,"欢迎进入", true, true));
                return "index/product";
            } else {
                setTitle(model, new TitleVo("产品详情", "产品详情", true,"欢迎进入", true, false));
                return "index/product";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        setTitle(model, new TitleVo("产品详情", "产品详情", true,"欢迎进入", true, false));
        return "index/product";
    }



}
