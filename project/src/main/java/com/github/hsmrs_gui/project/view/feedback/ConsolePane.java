package src.main.java.com.github.hsmrs_gui.project.view.feedback;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import src.main.java.com.github.hsmrs_gui.project.model.Robot;
import src.main.java.com.github.hsmrs_gui.project.model.RobotListModel;
import net.miginfocom.swing.MigLayout;

public class ConsolePane extends JPanel{

	private JTabbedPane sourceTabbedPane;
	private JTextArea output;
	private Map<Robot, String> outputs;
	private String allOutput = "";
	
	public ConsolePane(){
		//setBackground(Color.white);
		setLayout(new MigLayout("fill", "[]", "[]"));
		
		sourceTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		output = new JTextArea("");
		output.setLineWrap(true);
		output.setEditable(false);
		
		sourceTabbedPane.addTab("All", new JScrollPane(output));
		RobotListModel rlm = RobotListModel.getRobotListModel();
		for(int i = 0; i < rlm.getSize(); i++){
			Robot robot = rlm.getElementAt(i);
			String robotName = robot.getName();
			JTextArea temp = new JTextArea();
			temp.setLineWrap(true);
			sourceTabbedPane.addTab(robotName, new JScrollPane(temp));
		}
		
		add(sourceTabbedPane, "grow");
		//sourceTabbedPane.getTabComponentAt(index)
	}
}
