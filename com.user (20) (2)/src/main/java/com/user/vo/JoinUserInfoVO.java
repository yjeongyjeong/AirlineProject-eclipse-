package com.user.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//	<kakaouser>
//	USERID    		NOT NULL VARCHAR2(10) 
//	GRADECODE      		    NUMBER(1)    
//	KORNAME  		 NOT NULL VARCHAR2(10) 
//	ENGNAME 		  NOT NULL VARCHAR2(10) 
//	USERNUM   	NOT NULL NUMBER(13)   
//	PWD       		NOT NULL VARCHAR2(10) 
//	GENDER            		 NUMBER(1)    
//	MAIL               		VARCHAR2(20) 
//	PHONE              		VARCHAR2(15) 
//	POSTCODE          		NUMBER(5)    
//	ADDRESS        		    VARCHAR2(50) 
//	ADMIN            		  NUMBER(1) 
//
//	<userlog>
//	REGIDATE 가입일		    NOT NULL DATE         
//	UPDATEINFO 정보수정일	           DATE               
//	TOTALSUM 총결제금액       	    NUMBER(10)
//
//	<userpay>
//	MILEAGE 마일리지       	  NUMBER(10)
//
//	<termslog>
//	TERMSCODE 약관코드 	NOT NULL NUMBER(1)    
//	AGREE 동의여부     		NOT NULL NUMBER(1)

@Getter @Setter @ToString
public class JoinUserInfoVO {
	private String userid;
	private int gradecode; 
	private String korName;
	private String engName;
	private long usernum;
	private String pwd;
	private int gender;
	private String mail;
	private String phone;
	private int postcode;
	private String address;
	private int admin;
	
	private Date regidate;
	private Date updateinfo;
	private int totalsum;
	
	private int mileage;
	
	private int termscode;
	private int agree;
}
