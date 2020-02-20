package com.ho.agent.ftp.command.service;

import java.io.IOException;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import com.ho.agent.ftp.property.FtpProperty;

public class FtpService {

	private final FtpProperty ftpProperty = new FtpProperty();
	private final FTPClient ftpClient = new FTPClient();
	
	public void connect() {
		ftpClient.setAutodetectUTF8(true);
		ftpClient.setControlEncoding("UTF-8");
		
		int reply;
		try {
			ftpClient.connect(ftpProperty.getUrl());
		} catch (SocketException e) {
			throw new RuntimeException("Error while connect to ftp server", e);
		} catch (IOException e) {
			throw new RuntimeException("Error while connect to ftp server", e);
		}

		
		reply = ftpClient.getReplyCode();

        if (!FTPReply.isPositiveCompletion(reply)) {
            this.disconnect();
            throw new RuntimeException("FTP server refused connection.");
        }
	}
	
	public void disconnect() {
        try {
        	ftpClient.disconnect();
		} catch (IOException e) {
			throw new RuntimeException("Error while disconnect from ftp server", e);
		}
	}
	
	public void login() {
		try {
			if (!ftpClient.login(ftpProperty.getId(), ftpProperty.getPassword())) {
				ftpClient.logout();
			}
		} catch (IOException e) {
			throw new RuntimeException("Error while login to ftp server", e);
		}
		try {
			ftpClient.setFileType(FTP.ASCII_FILE_TYPE);
		} catch (IOException e) {
			throw new RuntimeException("Error while set file type", e);
		}
		ftpClient.enterLocalPassiveMode();
		ftpClient.setUseEPSVwithIPv4(false);
	}
	
	public String[] getList(String remotePath) {
		List<String> fileNames = new ArrayList<>();
        try {
			for (FTPFile f : ftpClient.listFiles(remotePath)) {
				fileNames.add(f.getName());
			}
		} catch (IOException e) {
			throw new RuntimeException("Error while get list", e);
		}
        return fileNames.toArray(new String[] {});
	}
	
}
