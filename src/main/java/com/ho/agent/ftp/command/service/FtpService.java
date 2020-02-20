package com.ho.agent.ftp.command.service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
		
		int reply;
		try {
			ftpClient.setAutodetectUTF8(true);
			ftpClient.setControlEncoding("UTF-8");
			
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

	public boolean store(String remotePath, String localFile) {
		InputStream input = null;
		try {
			input = new FileInputStream(localFile);
			return ftpClient.storeFile(remotePath, input);
		} catch (IOException e) {
			throw new RuntimeException("Error while store file", e);
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					throw new RuntimeException("Error while close input stream", e);
				}
			}
		}
	}

	public boolean download(String remoteFile, String localFile) {
		OutputStream output = null;
        try {
			output = new FileOutputStream(localFile);
			return ftpClient.retrieveFile(remoteFile, output);
		} catch (IOException e) {
			throw new RuntimeException("Error while download file", e);
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					throw new RuntimeException("Error while close output stream", e);
				}
			}
		}
	}
	
}
