package src.main.java.com.github.hsmrs_gui.project.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import src.main.java.com.github.hsmrs_gui.project.view.feedback.ConsoleView;

public class ConsoleModel {
	
	Map<String, ConsoleChannelModel> channels;
	
	public ConsoleModel(){
		this.channels = new HashMap<String, ConsoleChannelModel>();
		ConsoleChannelModel ccmAll = new ConsoleChannelModel("All");
		ConsoleChannelModel ccmSystem = new ConsoleChannelModel("System");
		channels.put("All", ccmAll);
		channels.put("System", ccmSystem);
	}
	
	public ConsoleModel(List<String> channelNames){
		this();
		for (String channelName : channelNames){
			addChannel(channelName);
		}
	}
	
	public void addChannel(String name){
		this.channels.put(name, new ConsoleChannelModel(name));
	}
	
	public void removeChannel(String name){
		channels.remove(name);
	}
	
	public void addLog(String sender, long timestamp, String text){
		ConsoleChannelModel ccmAll = channels.get("All");
		ConsoleChannelModel ccmSender = channels.get(sender);
		
		ccmAll.addLogEntry("<b>[" + String.valueOf(timestamp) + "] "
				+ sender + ": </b>" + text);
		
		ccmSender.addLogEntry("<b>[" + String.valueOf(timestamp) + "] </b>"
				+ text);
	}

	public String getLogFor(String channelName) {
		return channels.get(channelName).getLog();
	}

	public List<String> getChannelNames() {
		return new ArrayList<String>(channels.keySet());
	}
}
