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

package src.main.java.com.github.hsmrs_gui.project.view;

import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.ListModel;

import src.main.java.com.github.hsmrs_gui.project.view.robot.RobotListPanel;
import net.miginfocom.swing.MigLayout;

import org.apache.commons.logging.Log;

import com.github.hsmrs_gui.project.GuiNode;


public class MainFrame extends JFrame {
  JTabbedPane leftPane;
  RobotListPanel robotPane;
  JTabbedPane bottomPane;
  JTabbedPane centerPane;
  JLabel taskList;
  JLabel rightList;
  JLabel console;
  JLabel imageDisplay;
  ImageIcon defaultImage;
  ListModel robotListModel;
  ListModel taskListModel;
  Log log;

  	public MainFrame(ListModel roboListModel, ListModel taskListModel) {
  		this.robotListModel = roboListModel;
  		this.taskListModel = taskListModel;
  		this.log = GuiNode.getLog();
  		log.info("Hello World!");
  		
       setTitle("HSMRS GUI");
       setExtendedState(getExtendedState()|JFrame.MAXIMIZED_BOTH );
       //setSize(700, 700);
       setLocationRelativeTo(null);
       setDefaultCloseOperation(EXIT_ON_CLOSE);        
       setup();
    }

    public void setup(){
      Container cp = this.getContentPane(); 
      cp.setLayout(new MigLayout("insets 0", "[]push[200px]", "[75%]0[25%]"));
      //cp.setLayout(new BorderLayout());
      cp.setBackground(Color.white);

      //Task List
      leftPane = new JTabbedPane();
      JPanel panel = new JPanel(false);
      JLabel filler = new JLabel("Task Filler");
      filler.setHorizontalAlignment(JLabel.CENTER);
      panel.add(filler);
      leftPane.addTab("All Tasks", panel);

      //Robot List
      robotPane = new RobotListPanel(robotListModel);
      
      //Console
      bottomPane = new JTabbedPane();
      JPanel panel3 = new JPanel(false);
      JLabel filler3 = new JLabel("Console Filler");
      filler3.setHorizontalAlignment(JLabel.CENTER);
      panel3.add(filler3);
      bottomPane.addTab("Console Output", panel3);

      //Image label
      //imageDisplay = new JLabel();
      //defaultImage = new ImageIcon(getClass().getResource("/com/github/hsmrs_gui/project/resources/wall_e.jpg"));

      
      cp.add(leftPane, "left, push, grow");
      cp.add(robotPane, "right, push, grow, wrap");
      cp.add(bottomPane, "push, grow, span");
      
      pack();
    }

    public void receiveText(String text){

    }
}
