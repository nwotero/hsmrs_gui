/*
 * Copyright (C) 2014 Nicholas Otero.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.github.hsmrs_gui.project;

import org.apache.commons.logging.Log;
import org.ros.message.MessageListener;
import org.ros.namespace.GraphName;
import org.ros.node.AbstractNodeMain;
import org.ros.node.ConnectedNode;
import org.ros.node.NodeMain;
import org.ros.node.topic.Subscriber;

import src.main.java.com.github.hsmrs_gui.project.model.Robot;
import src.main.java.com.github.hsmrs_gui.project.model.RobotListModel;
import src.main.java.com.github.hsmrs_gui.project.model.Task;
import src.main.java.com.github.hsmrs_gui.project.view.MainFrame;

import java.awt.EventQueue;

public class GuiNode extends AbstractNodeMain {

	private static Log log;
  @Override
  public GraphName getDefaultNodeName() {
    return GraphName.of("rosjava/gui_node");
  }
  
  public static Log getLog(){
	  return log;
  }

  @Override
  public void onStart(ConnectedNode connectedNode) {
    log = connectedNode.getLog();

    EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
            	RobotListModel rlm = RobotListModel.getRobotListModel();
            	rlm.addRobot(new Robot("Hermes", new Task()));
            	rlm.addRobot(new Robot("Oryx", new Task()));
            	rlm.addRobot(new Robot("Husky", new Task()));
            	rlm.addRobot(new Robot("Aero", new Task()));
              MainFrame gui = new MainFrame(rlm, rlm);
              gui.setVisible(true);	
            }});

    Subscriber<std_msgs.String> subscriber = connectedNode.newSubscriber("display", std_msgs.String._TYPE);
    subscriber.addMessageListener(new MessageListener<std_msgs.String>() {
      @Override
      public void onNewMessage(std_msgs.String message) {
        log.info("I heard: \"" + message.getData() + "\"");
      }
    });
  }
}
