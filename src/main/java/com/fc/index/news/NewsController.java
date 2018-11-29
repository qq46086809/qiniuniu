package com.fc.index.news;

import com.fc.test.common.base.BaseController;
import com.fc.test.model.custom.TitleVo;
import net.sf.json.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class NewsController extends BaseController {


    /**
     * 请求到下载页面
     * @param request
     * @return
     */
    @RequestMapping("/QnNewslist")
    public String QnNewslist(HttpServletRequest request, Model model) {
        try {
            if ((null != SecurityUtils.getSubject() && SecurityUtils.getSubject().isAuthenticated()) || SecurityUtils.getSubject().isRemembered()) {
                JSONObject jsonObject = JSONObject.fromObject(SecurityUtils.getSubject().getPrincipal());
                String username = jsonObject.getString("username");
                setTitle(model, new TitleVo("新闻列表", username, true,"欢迎进入", true, true));
                return "index/newslist";
            } else {
                setTitle(model, new TitleVo("新闻列表", "新闻列表", true,"欢迎进入", true, false));
                return "index/newslist";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        setTitle(model, new TitleVo("新闻列表", "新闻列表", true,"欢迎进入", true, false));
        return "index/newslist";
    }






}
