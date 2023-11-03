package com.user.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserPayVO {
	private String resno;
	private String userid;
	private Date getdate;
	private int price;
	private int mileage;
}
