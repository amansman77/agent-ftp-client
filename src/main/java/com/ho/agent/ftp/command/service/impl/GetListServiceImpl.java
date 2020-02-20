package com.ho.agent.ftp.command.service.impl;

import java.util.Arrays;

import com.ho.agent.ftp.command.service.CommandService;
import com.ho.agent.ftp.command.service.FtpService;

public class GetListServiceImpl implements CommandService {

	private String remotePath;
	
	public GetListServiceImpl(String remotePath) {
		this.remotePath = remotePath;
	}

	@Override
	public void run() {
		FtpService ftpService = new FtpService();
		ftpService.connect();
		ftpService.login();
		
		String[] fileNames = ftpService.getList(remotePath);
		
		ftpService.disconnect();
		
		Arrays.stream(fileNames).forEach(System.out::println);
	}

}
