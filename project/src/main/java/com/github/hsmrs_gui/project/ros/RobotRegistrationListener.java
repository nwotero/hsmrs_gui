package src.main.java.com.github.hsmrs_gui.project.ros;

import org.ros.node.topic.Subscriber;
import org.ros.node.ConnectedNode;
import org.ros.message.MessageListener;
import org.apache.commons.logging.Log;

import com.github.hsmrs_gui.project.GuiNode;


public class RobotRegistrationListener implements MessageListener<std_msgs.String>{
	Log log;
	
	public RobotRegistrationListener(ConnectedNode connectedNode) {
		log = GuiNode.getLog();
		Subscriber<std_msgs.String> subscriber = connectedNode.newSubscriber(
				 "hsmrs/robot_configuration", std_msgs.String._TYPE);
		subscriber.addMessageListener(this);
	}
	
	@Override
    public void onNewMessage(std_msgs.String message) {
		log.info("Confirm registration: " + message.getData());
    }
}
