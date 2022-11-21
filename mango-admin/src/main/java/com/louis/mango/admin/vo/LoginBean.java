package com.louis.mango.admin.vo;

import lombok.Data;

/**
 * 登录接口封装对象
 * @author Louis
 * @date Oct 29, 2018
 */
@Data
public class LoginBean {
	private String username;
	private String account;
	private String password;
	private String captcha;
}
