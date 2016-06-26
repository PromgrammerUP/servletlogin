package org.javachina.login.service;

import org.javachina.login.dao.LoginUserDao;
import org.javachina.login.dto.UserDto;

public class LoginService {
	public int login(UserDto user){
		int result=0;
		String userId = user.getUserId();
		String pwd = user.getPwd();
		LoginUserDao dao = new LoginUserDao();
		String pwdFromDB = dao.queryPwdById(userId);
		//System.out.println(pwdFromDB+":"+pwd);
		if(pwdFromDB==null){
			result=1;
		}
		else if(pwdFromDB.equals(pwd)){
			result=0;
		}
		else {
			result=2;
		}
		return result;
	}
}
