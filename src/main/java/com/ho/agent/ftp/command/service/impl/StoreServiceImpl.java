package com.ho.agent.ftp.command.service.impl;

import com.ho.agent.ftp.command.service.CommandService;
import com.ho.agent.ftp.command.service.FtpService;

public class StoreServiceImpl implements CommandService {

	private String remotePath;
	private String localFile;
	
	public StoreServiceImpl(String remotePath, String localFile) {
		this.remotePath = remotePath;
		this.localFile = localFile;
	}

	@Override
	public void run() {
		FtpService ftpService = new FtpService();
		ftpService.connect();
		ftpService.login();
		
		if (ftpService.store(remotePath, localFile)) {
			System.out.println("Success store file");
		} else {
			System.out.println("Fail store file");
		}
		
		ftpService.disconnect();
	}

}
