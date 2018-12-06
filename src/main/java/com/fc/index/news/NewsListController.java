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
public class NewsListController extends BaseController {


    /**
     * 请求到新闻列表详情页面
     * @param request
     * @return
     */
    @RequestMapping("/QnNewslist1")
    public String QnNewslist1(HttpServletRequest request, Model model) {
        try {
            if ((null != SecurityUtils.getSubject() && SecurityUtils.getSubject().isAuthenticated()) || SecurityUtils.getSubject().isRemembered()) {
                JSONObject jsonObject = JSONObject.fromObject(SecurityUtils.getSubject().getPrincipal());
                String username = jsonObject.getString("username");
                setTitle(model, new TitleVo("新闻列表详情", username, true,"欢迎进入", true, true));
                return "index/newslist1";
            } else {
                setTitle(model, new TitleVo("新闻列表详情", "新闻列表详情", true,"欢迎进入", true, false));
                return "index/newslist1";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        setTitle(model, new TitleVo("新闻列表详情", "新闻列表详情", true,"欢迎进入", true, false));
        return "index/newslist1";
    }






}
