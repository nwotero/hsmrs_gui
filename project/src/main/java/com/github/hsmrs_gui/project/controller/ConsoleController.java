package src.main.java.com.github.hsmrs_gui.project.controller;

import java.util.List;

import src.main.java.com.github.hsmrs_gui.project.model.ConsoleModel;
import src.main.java.com.github.hsmrs_gui.project.view.feedback.ConsoleView;

public class ConsoleController {

	private static ConsoleController instance;
	private ConsoleModel consoleModel;
	private ConsoleView consoleView;
	
	private ConsoleController(){}
	
	public static ConsoleController getInstance(){
		if (instance == null){
			instance = new ConsoleController();
		}
		return instance;
	}
	
	public void setConsoleModel(ConsoleModel consoleModel){
		this.consoleModel = consoleModel;
	}
	
	public void createConsole(List<String> channelNames){
		this.consoleModel = new ConsoleModel(channelNames);
	}
	
	public void setConsoleView(ConsoleView consoleView){
		this.consoleView = consoleView;
	}
	
	public void addConsoleChannel(String name){
		consoleModel.addChannel(name);
		consoleView.addChannel(name);
	}
	
	public void removeConsoleChannel(String name){
		consoleModel.removeChannel(name);
		consoleView.removeChannel(name);
	}
	
	public void addLog(String sender, long timestamp, String text){
		consoleModel.addLog(sender, timestamp, text);
		consoleView.setChannelLogText("All", consoleModel.getLogFor("All"));
		consoleView.setChannelLogText(sender, consoleModel.getLogFor(sender));
	}

	public List<String> getChannelNames() {
		return consoleModel.getChannelNames();
	}
}
