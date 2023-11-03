package com.user.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class UserLogVO {
	private String userid;
	private Date regidate;
	private Date updateinfo;
	private Date gradeupdate; 
	private int totalcount;
	private int totalsum;
}
