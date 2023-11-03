package com.user.util;


public class ShareVar_login {

	//시스템 계정생성함
	private String hostID = "systemlocal99@gmail.com";
	private String hostPW = "jilpqhutftrqvbsf";
	public String authEamilCode = null;
	
	private ShareVar_login() { };
	
	private static ShareVar_login instance = new ShareVar_login(); 
	
	public static ShareVar_login getInstance() {
		return instance;
	}

	public String getHostID() {
		return hostID;
	}

	public String getHostPW() {
		return hostPW;
	}
	
	
	
}
