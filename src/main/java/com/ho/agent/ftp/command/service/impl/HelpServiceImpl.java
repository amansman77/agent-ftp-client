package com.ho.agent.ftp.command.service.impl;

import com.ho.agent.ftp.command.service.CommandService;
import com.ho.agent.ftp.constant.Constant.Command;

public class HelpServiceImpl implements CommandService {

	@Override
	public void run() {
		System.out.println("Command List : ");
		System.out.println(Command.HELP + "\tprint this help message to the output stream");
		System.out.println(Command.GET_LIST + " <remote path>");
		System.out.println("\tprint remote file list to the output stream");
		System.out.println(Command.STORE + " <remote file> <local file>");
		System.out.println("\tstore file on server (upload)");
	}
	
}
