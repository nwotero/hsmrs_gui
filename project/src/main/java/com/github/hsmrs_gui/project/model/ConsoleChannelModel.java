package src.main.java.com.github.hsmrs_gui.project.model;

public class ConsoleChannelModel {

	private String name;
	private String log;
	
	public ConsoleChannelModel(String name){
		this.name = name;
		log = "";
	}
	
	public String getName(){
		return name;
	}
	
	public void addLogEntry(String logEntry){
		//log += logEntry + ((logEntry.endsWith("\n")) ? ("") : ("\n"));
		log += logEntry + "<br>";
	}
	
	public String getLog(){
		return "<html>" + log + "</html>";
	}
	
	public void clearLog(){
		log = "";
	}
}
