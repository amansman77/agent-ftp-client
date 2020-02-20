package com.ho.agent.ftp.property;

public class FtpProperty {
	
	AppProperty appProperty = AppProperty.getInstance();
	
	private String url;
	private String id;
	private String password;
	
	public FtpProperty() {
		url = appProperty.getProperty("ftp.url");
		id = appProperty.getProperty("ftp.id");
		password = appProperty.getProperty("ftp.password");
	}
	
	public String getUrl() {
		return url;
	}
	public String getId() {
		return id;
	}
	public String getPassword() {
		return password;
	}
	
}
