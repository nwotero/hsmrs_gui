package src.main.java.com.github.hsmrs_gui.project.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import src.main.java.com.github.hsmrs_gui.project.view.feedback.ConsoleView;

public class ConsoleModel {
	
	ArrayList<ConsoleChannelModel> channels;
	
	public ConsoleModel(){
		this.channels = new ArrayList<ConsoleChannelModel>();
		ConsoleChannelModel ccmAll = new ConsoleChannelModel("All");
		ConsoleChannelModel ccmSystem = new ConsoleChannelModel("System");
		channels.add(ccmAll);
		channels.add(ccmSystem);
	}
	
	public ConsoleModel(List<String> channelNames){
		this();
		for (String channelName : channelNames){
			addChannel(channelName);
		}
	}
	
	public void addChannel(String name){
		this.channels.add(new ConsoleChannelModel(name));
	}
	
	public void removeChannel(String name){
		ConsoleChannelModel ccm = getChannel(name);
		channels.remove(ccm);
	}
	
	public void addLog(String sender, long timestamp, String text){
		ConsoleChannelModel ccmAll = getChannel("All");
		ConsoleChannelModel ccmSender = getChannel(sender);
		
		ccmAll.addLogEntry("<b>[" + String.valueOf(timestamp) + "] "
				+ sender + ": </b>" + text);
		
		ccmSender.addLogEntry("<b>[" + String.valueOf(timestamp) + "] </b>"
				+ text);
	}

	public String getLogFor(String channelName) {
		return getChannel(channelName).getLog();
	}
	
	public ConsoleChannelModel getChannel(String channelName){
		for (ConsoleChannelModel ccm : channels){
			if (ccm.getName().equals(channelName)){
				return ccm;
			}
		}
		return null;
	}

	public List<String> getChannelNames() {
		ArrayList<String> rtnList = new ArrayList<String>();
		for (ConsoleChannelModel ccm : channels){
			rtnList.add(ccm.getName());
		}
		return rtnList;
	}
}
