package src.main.java.com.github.hsmrs_gui.project.view.feedback;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JViewport;

import com.github.hsmrs_gui.project.GuiNode;

import src.main.java.com.github.hsmrs_gui.project.controller.ConsoleController;
import src.main.java.com.github.hsmrs_gui.project.model.Robot;
import src.main.java.com.github.hsmrs_gui.project.model.RobotListModel;
import net.miginfocom.swing.MigLayout;

public class ConsoleView extends JPanel {

	private JTabbedPane sourceTabbedPane;
	private JTextArea output;
	private Map<Robot, String> outputs;
	private String allOutput = "";

	public ConsoleView() {
		// setBackground(Color.white);
		setLayout(new MigLayout("fill", "[]", "[]"));

		sourceTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		output = new JTextArea("");
		output.setLineWrap(true);
		output.setEditable(false);

		ConsoleController cc = ConsoleController.getInstance();
		cc.setConsoleView(this);

		for (String channelName : cc.getChannelNames()) {
			JTextArea temp = new JTextArea();
			temp.setLineWrap(true);
			sourceTabbedPane.addTab(channelName, new JScrollPane(temp));
		}

		// sourceTabbedPane.addTab("All", new JScrollPane(output));
		// RobotListModel rlm = RobotListModel.getRobotListModel();
		// for(int i = 0; i < rlm.getSize(); i++){
		// Robot robot = rlm.getElementAt(i);
		// String robotName = robot.getName();
		// JTextArea temp = new JTextArea();
		// temp.setLineWrap(true);
		// sourceTabbedPane.addTab(robotName, new JScrollPane(temp));
		// }

		add(sourceTabbedPane, "grow");
	}

	public void addChannel(String name) {
		JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
		sourceTabbedPane.addTab(name, new JScrollPane(textArea));
	}

	public void removeChannel(String name) {
		for (int i = 0; i < sourceTabbedPane.getTabCount(); i++) {
			if (sourceTabbedPane.getTitleAt(i).equals(name)) {
				sourceTabbedPane.removeTabAt(i);
				return;
			}
		}
	}

	public void setChannelLogText(String channelName, String logText) {
		GuiNode.getLog().info("Debug: setChannelLogText " + logText);
		JTextArea target = extractTextArea(channelName);
		GuiNode.getLog().info(
				"Debug: setChannelLogText for: " + target.getText());
		target.setText(logText);
		GuiNode.getLog().info(
				"Debug: setChannelLogText Success!");
		return;

	}

	private JTextArea extractTextArea(String channelName) {
		for (int i = 0; i < sourceTabbedPane.getTabCount(); i++) {
			if (sourceTabbedPane.getTitleAt(i).equals(channelName)) {
				JScrollPane spContainer = (JScrollPane) sourceTabbedPane
						.getComponentAt(i);
				JViewport vContainer = spContainer.getViewport();
				Component[] comps = vContainer.getComponents();
				for (int j = 0; j < comps.length; j++) {
					if (comps[j] instanceof JTextArea) {
						return ((JTextArea) comps[j]);
					}
				}
			}
		}
		return null;
	}
}
