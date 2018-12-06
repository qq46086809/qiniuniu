package com.fc.test.service;

import com.fc.test.common.base.BasicService;
import com.fc.test.mapper.auto.TuserDao;
import com.fc.test.model.auto.Tuser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 普通用户
 * @ClassName: TUserService
 * @author ws
 * @date 2018年12月03日
 *
 */
@Service
public class TUserService implements BasicService<Tuser> {

    @Autowired
    public TuserDao userDao;


    @Override
    public int deleteByPrimaryKey(String id) {
        return 0;
    }

    @Override
    public int insertSelective(Tuser record) {
        return userDao.insertSelective(record);
    }

    @Override
    public Tuser selectByPrimaryKey(String id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Tuser record) {
        return 0;
    }

    @Override
    public int updateByExampleSelective(Tuser record, Tuser example) {
        return 0;
    }

    @Override
    public int updateByExample(Tuser record, Tuser example) {
        return 0;
    }

    @Override
    public List<Tuser> selectByExample(Tuser example) {
        return null;
    }

    @Override
    public long countByExample(Tuser example) {
        return 0;
    }

    @Override
    public int deleteByExample(Tuser example) {
        return 0;
    }

    public boolean isValidated(String account, String pwd, String check_pwd, String phone){
        if(userDao.isExistAccount(account)!=null){ return false; }

        if(!pwd.equals(check_pwd)){ return false; }

        if (phone.length() != 11) { return false; }

        String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(phone);
        boolean flag = m.matches();
        return flag;
    }

}
