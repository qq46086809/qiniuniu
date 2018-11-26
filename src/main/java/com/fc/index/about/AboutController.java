package com.fc.index.about;

import com.fc.test.common.base.BaseController;
import com.fc.test.model.custom.TitleVo;
import net.sf.json.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AboutController extends BaseController {


    /**
     * 请求到首页
     * @param request
     * @return
     */
    @RequestMapping("/QnAbout")
    public String QnAbout(HttpServletRequest request, Model model) {
        try {
            if ((null != SecurityUtils.getSubject() && SecurityUtils.getSubject().isAuthenticated()) || SecurityUtils.getSubject().isRemembered()) {
                JSONObject jsonObject = JSONObject.fromObject(SecurityUtils.getSubject().getPrincipal());
                String username = jsonObject.getString("username");
                setTitle(model, new TitleVo("关于企牛牛", username, true,"欢迎进入", true, true));
                return "index/about";
            } else {
                setTitle(model, new TitleVo("关于企牛牛", "关于企牛牛", true,"欢迎进入", true, false));
                return "index/about";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        setTitle(model, new TitleVo("关于企牛牛", "关于企牛牛", true,"欢迎进入", true, false));
        return "index/about";
    }



}
