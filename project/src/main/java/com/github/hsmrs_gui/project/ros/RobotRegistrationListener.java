package src.main.java.com.github.hsmrs_gui.project.ros;

import org.ros.node.topic.Subscriber;
import org.ros.node.ConnectedNode;
import org.ros.message.MessageListener;
import org.apache.commons.logging.Log;

import src.main.java.com.github.hsmrs_gui.project.controller.ConsoleController;

import com.github.hsmrs_gui.project.GuiNode;


public class RobotRegistrationListener implements MessageListener<std_msgs.String>{
	private Log log;
	private ConsoleController consoleController;
	
	public RobotRegistrationListener(ConnectedNode connectedNode) {
		log = GuiNode.getLog();
		consoleController = ConsoleController.getInstance();
		
		Subscriber<std_msgs.String> subscriber = connectedNode.newSubscriber(
				 "hsmrs/robot_configuration", std_msgs.String._TYPE);
		subscriber.addMessageListener(this);
	}
	
	@Override
    public void onNewMessage(std_msgs.String message) {
		log.info("Confirm registration: " + message.getData());
		//long timestamp = System.currentTimeMillis() / 1000;
		//consoleController.addLog("System", timestamp, message.getData());
    }
}
