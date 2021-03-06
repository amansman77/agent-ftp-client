package com.ho.agent.ftp.command.service.impl;

import com.ho.agent.ftp.command.service.CommandService;
import com.ho.agent.ftp.command.service.FtpService;

public class StoreServiceImpl implements CommandService {

	private String remoteFile;
	private String localFile;
	
	public StoreServiceImpl(String remoteFile, String localFile) {
		this.remoteFile = remoteFile;
		this.localFile = localFile;
	}

	@Override
	public void run() {
		FtpService ftpService = new FtpService();
		ftpService.connect();
		ftpService.login();
		
		if (ftpService.store(remoteFile, localFile)) {
			System.out.println("Success store file");
		} else {
			System.out.println("Fail store file");
		}
		
		ftpService.disconnect();
	}

}
