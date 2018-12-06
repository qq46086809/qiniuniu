package com.fc.index.user;

import com.fc.test.common.base.BaseController;
import com.fc.test.model.auto.Tuser;
import com.fc.test.model.custom.TitleVo;
import com.fc.test.service.TUserService;
import com.fc.test.util.MD5Util;
import com.fc.test.util.StringUtils;
import net.sf.json.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
public class TuserController extends BaseController {

    @Autowired
    public TUserService tUserService;
    /**
     * 获取用户注册信息
     * @param request
     * @return
     */
    @ResponseBody
    @PostMapping("admininsert")
    public String TuserRegister(HttpServletRequest request, HttpServletResponse response, Model model) {
        String account=request.getParameter("user");
        String pwd = request.getParameter("pwd");
        String check_pwd = request.getParameter("check-pwd");
        String phone = request.getParameter("phone");
        if(StringUtils.isEmpty(account)||StringUtils.isEmpty(pwd)||
                StringUtils.isEmpty(check_pwd)|| StringUtils.isEmpty(phone)){
            return "index/insert";
        }
        try {
            if(!tUserService.isValidated(account,pwd,check_pwd,phone)){
                return "index/insert";
            }
            Tuser user =  new Tuser();
            user.setAccount(account);
            user.setPassword(MD5Util.encode(pwd));
            user.setPhone(phone);
            if (tUserService.insertSelective(user)>0) {
                setTitle(model, new TitleVo("首页", "首页", true,"欢迎进入", true, false));
                return "index/QnRoot";
            } else {
                setTitle(model, new TitleVo("注册", "注册", true,"欢迎进入", true, false));
                return "index/insert";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        setTitle(model, new TitleVo("注册", "注册", true,"欢迎进入", true, false));
        return "index/insert";
    }

}
