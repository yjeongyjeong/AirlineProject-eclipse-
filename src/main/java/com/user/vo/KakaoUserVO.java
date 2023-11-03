package com.user.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class KakaoUserVO {
	private String userid;
	private int gradecode; 
	private String korName; //username
	private String engName;
	private long usernum;
	private String pwd; //pass
	private int gender;
	private String mail; //email
	private String phone;
	private int postcode;
	private String address;
	private int admin;
	private String nickname;
}
