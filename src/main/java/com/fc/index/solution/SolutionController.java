package com.fc.index.solution;

import com.fc.test.common.base.BaseController;
import com.fc.test.model.custom.TitleVo;
import net.sf.json.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SolutionController extends BaseController {


    /**
     * 请求到首页
     * @param request
     * @return
     */
    @RequestMapping("/QnBusiness")
    public String QnBusiness(HttpServletRequest request, Model model) {
        try {
            if ((null != SecurityUtils.getSubject() && SecurityUtils.getSubject().isAuthenticated()) || SecurityUtils.getSubject().isRemembered()) {
                JSONObject jsonObject = JSONObject.fromObject(SecurityUtils.getSubject().getPrincipal());
                String username = jsonObject.getString("username");
                setTitle(model, new TitleVo("智慧商圈解决方案", username, true,"欢迎进入", true, true));
                return "index/business";
            } else {
                setTitle(model, new TitleVo("智慧商圈解决方案", "智慧商圈解决方案", true,"欢迎进入", true, false));
                return "index/business";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        setTitle(model, new TitleVo("智慧商圈解决方案", "智慧商圈解决方案", true,"欢迎进入", true, false));
        return "index/business";
    }



}
