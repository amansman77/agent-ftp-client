package com.ho.agent.ftp.command.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FtpServiceTest {

	FtpService ftpService = new FtpService();
	
	@BeforeEach
	void connect() {
		ftpService.connect();
		ftpService.login();
	}
	
	@AfterEach
	void disconnect() {
		ftpService.disconnect();
	}
	
	@Test
	void testGetList() {
		String[] fileNames = ftpService.getList("/");
		
		assertNotNull(fileNames);
		assertTrue(fileNames.length > 0);
		
		fileNames = ftpService.getList("/테스트");
		
		assertNotNull(fileNames);
		assertEquals(1, fileNames.length);
	}
	
}
