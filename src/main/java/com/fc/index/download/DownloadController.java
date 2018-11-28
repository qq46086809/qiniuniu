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
public class DownloadController extends BaseController {


    /**
     * 请求到下载页面
     * @param request
     * @return
     */
    @RequestMapping("/QnDownload")
    public String QnDownload(HttpServletRequest request, Model model) {
        try {
            if ((null != SecurityUtils.getSubject() && SecurityUtils.getSubject().isAuthenticated()) || SecurityUtils.getSubject().isRemembered()) {
                JSONObject jsonObject = JSONObject.fromObject(SecurityUtils.getSubject().getPrincipal());
                String username = jsonObject.getString("username");
                setTitle(model, new TitleVo("下载中心", username, true,"欢迎进入", true, true));
                return "index/download";
            } else {
                setTitle(model, new TitleVo("下载中心", "下载中心", true,"欢迎进入", true, false));
                return "index/download";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        setTitle(model, new TitleVo("下载中心", "下载中心", true,"欢迎进入", true, false));
        return "index/download";
    }






}
