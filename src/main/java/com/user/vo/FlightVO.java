package com.user.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FlightVO {
	private int num; //row_num
	private String departureState;	//도착여부
	private String regidate; //출발일자
	private String airline; //항공사
	private String flightnum; //항공기이름
	private String depairportcode; //출발공항코드
	private String depairport; //출발공항
	private String arrairportcode;
	private String arrairport;
	private String plantimes; //계획시간
	private String estimate; //추정시간
	private String arrtime; //도착시간
	private String userid;
	private String purchasedate; 
	private int price;
	private String resno;
}
