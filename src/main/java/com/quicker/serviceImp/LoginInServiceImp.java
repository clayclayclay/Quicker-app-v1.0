package com.quicker.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quicker.dao.UserDao;
import com.quicker.database.CounselorInfo;
import com.quicker.database.StudentInfo;
import com.quicker.entity.json.BasicJson;
import com.quicker.entity.json.Errmsg;
import com.quicker.service.LoginInService;

@Service("loginInService")
public class LoginInServiceImp implements LoginInService {

    @Autowired
    private UserDao userLogin;


    //app端学生用户登录验证service
    public BasicJson appLoginIn(String username, String password) {
        BasicJson json = new BasicJson();
        Errmsg errMsg = new Errmsg();

        List<StudentInfo> list = userLogin.appLogin(username);

        if (list.size() > 0) {
            if (password.equals(list.get(0).getPassword())) {
                json.setStatus(true);
                return json;
            } else {
                json.setStatus(false);
                errMsg.setDescription("密码错误");
                json.setErrorMsg(errMsg);
            }
        } else {
            json.setStatus(false);
            errMsg.setDescription("用户名不存在");
            json.setErrorMsg(errMsg);
        }
        return json;
    }

    //web端   辅导员/学生组织 登陆验证service
    public BasicJson webLoginIn(String username, String passwd) {
        BasicJson json = new BasicJson();
        Errmsg errMsg = new Errmsg();
        //让前端判断用户经和密码为空的情况
        List<CounselorInfo> list = userLogin.webLogin(username);
        if (list.size() > 0) {
            if (passwd.equals(list.get(0).getPassword())) {
                json.setStatus(true);
                return json;
            } else {
                json.setStatus(false);
                errMsg.setDescription("密码错误");
                json.setErrorMsg(errMsg);
            }
        } else {
            json.setStatus(false);
            errMsg.setDescription("用户名不存在");
        }
        return json;
    }
}
