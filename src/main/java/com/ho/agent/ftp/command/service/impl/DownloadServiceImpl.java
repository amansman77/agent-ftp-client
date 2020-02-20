package com.ho.agent.ftp.command.service.impl;

import com.ho.agent.ftp.command.service.CommandService;
import com.ho.agent.ftp.command.service.FtpService;

public class DownloadServiceImpl implements CommandService {

	private String remoteFile;
	private String localFile;
	
	public DownloadServiceImpl(String remoteFile, String localFile) {
		this.remoteFile = remoteFile;
		this.localFile = localFile;
	}

	@Override
	public void run() {
		FtpService ftpService = new FtpService();
		ftpService.connect();
		ftpService.login();
		
		if (ftpService.download(remoteFile, localFile)) {
			System.out.println("Success download file");
		} else {
			System.out.println("Fail download file");
		}
		
		ftpService.disconnect();
	}

}
