package com.ho.agent.ftp;

import com.ho.agent.ftp.command.service.CommandService;
import com.ho.agent.ftp.command.service.impl.DownloadServiceImpl;
import com.ho.agent.ftp.command.service.impl.GetListServiceImpl;
import com.ho.agent.ftp.command.service.impl.HelpServiceImpl;
import com.ho.agent.ftp.command.service.impl.StoreServiceImpl;
import com.ho.agent.ftp.constant.Constant.Command;

public class App {
	
	public static void main(String[] args) {
		if (args.length < 1) {
			CommandService commandService = new HelpServiceImpl();
			commandService.run();
			return;
		}
				
		CommandService commandService = null;
		String command = args[0];
		
		if (command.equals(Command.HELP)) {
			commandService = new HelpServiceImpl();
		} else if (command.equals(Command.GET_LIST)) {
			String remotePath = "/";
			if (args.length > 1) {
				remotePath = args[1];
			}
			
			commandService = new GetListServiceImpl(remotePath);
		} else if (command.equals(Command.STORE)) {
			if (args.length < 2) {
				commandService = new HelpServiceImpl();
				commandService.run();
				return;
			}
			String remoteFile = args[1];
			String localFile = args[2];
			
			commandService = new StoreServiceImpl(remoteFile, localFile);
		} else if (command.equals(Command.DOWNLOAD)) {
			if (args.length < 2) {
				commandService = new HelpServiceImpl();
				commandService.run();
				return;
			}
			String remoteFile = args[1];
			String localFile = args[2];
			
			commandService = new DownloadServiceImpl(remoteFile, localFile);
		}
		
		commandService.run();
	}
	
}
