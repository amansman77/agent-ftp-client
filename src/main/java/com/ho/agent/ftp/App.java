package com.ho.agent.ftp;

import com.ho.agent.ftp.command.service.CommandService;
import com.ho.agent.ftp.command.service.impl.HelpServiceImpl;
import com.ho.agent.ftp.constant.Constant.Command;

public class App {
	
	public static void main(String[] args) {
		CommandService commandService = null;
		String command = args[0];
		
		if (args.length < 1 || command.equals(Command.HELP)) {
			commandService = new HelpServiceImpl();
		}
		
		commandService.run();
	}
	
}
