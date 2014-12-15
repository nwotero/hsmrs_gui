package src.main.java.com.github.hsmrs_gui.project.view.feedback;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JViewport;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

import com.github.hsmrs_gui.project.GuiNode;

import src.main.java.com.github.hsmrs_gui.project.controller.ConsoleController;
import src.main.java.com.github.hsmrs_gui.project.model.Robot;
import src.main.java.com.github.hsmrs_gui.project.model.RobotListModel;
import net.miginfocom.swing.MigLayout;

public class ConsoleView extends JPanel implements 
ListDataListener, AdjustmentListener{

	private JTabbedPane sourceTabbedPane;
	private JTextArea output;
	private Map<Robot, String> outputs;
	private String allOutput = "";

	public ConsoleView() {
		// setBackground(Color.white);
		setLayout(new MigLayout("fill, insets 0", "[]", "[]"));
		setBackground(Color.decode("0XC0BCB6"));

		sourceTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		output = new JTextArea("");
		output.setLineWrap(true);
		output.setEditable(false);

		ConsoleController cc = ConsoleController.getInstance();
		cc.setConsoleView(this);

		for (String channelName : cc.getChannelNames()) {
			JTextPane temp = new JTextPane();
			temp.setContentType("text/html");
			temp.setEditable(false);
			sourceTabbedPane.addTab(channelName, new JScrollPane(temp));
		}

		RobotListModel.getRobotListModel().addListDataListener(this);
		add(sourceTabbedPane, "grow");
	}

	public void addChannel(String name) {
		JTextPane textPane = new JTextPane();
		textPane.setContentType("text/html");
		textPane.setEditable(false);
		JScrollPane sPane = new JScrollPane(textPane);
		sPane.getVerticalScrollBar().addAdjustmentListener(this);  
		sourceTabbedPane.addTab(name, sPane);
	}

	public void removeChannel(String name) {
		for (int i = 0; i < sourceTabbedPane.getTabCount(); i++) {
			if (sourceTabbedPane.getTitleAt(i).equals(name)) {
				sourceTabbedPane.removeTabAt(i);
				return;
			}
		}
	}
	
	public void removeChannel(int index) {
		sourceTabbedPane.remove(index + 2);
	}

	public void setChannelLogText(String channelName, String logText) {
		GuiNode.getLog().info("Debug: setChannelLogText " + logText);
		JTextPane target = extractTextPane(channelName);
		GuiNode.getLog().info(
				"Debug: setChannelLogText for: " + target.getText());
		
		HTMLDocument doc = new HTMLDocument();
		HTMLEditorKit editorKit = (HTMLEditorKit)target.getEditorKit();
		try{
			editorKit.insertHTML(doc, 0, logText, 0, 0, null);
			target.setDocument(doc);
		}catch(Exception e)
		{
			GuiNode.getLog().info(
					"Debug: setChannelLogText Failure!");
		}
		
		//target.setText(logText);
		GuiNode.getLog().info(
				"Debug: setChannelLogText Success!");
		return;

	}

	private JTextPane extractTextPane(String channelName) {
		for (int i = 0; i < sourceTabbedPane.getTabCount(); i++) {
			if (sourceTabbedPane.getTitleAt(i).equals(channelName)) {
				JScrollPane spContainer = (JScrollPane) sourceTabbedPane
						.getComponentAt(i);
				JViewport vContainer = spContainer.getViewport();
				Component[] comps = vContainer.getComponents();
				for (int j = 0; j < comps.length; j++) {
					if (comps[j] instanceof JTextPane) {
						return ((JTextPane) comps[j]);
					}
				}
			}
		}
		return null;
	}

	@Override
	public void contentsChanged(ListDataEvent e) {
		//The console does not care if the contents are changed
		
	}

	@Override
	public void intervalAdded(ListDataEvent e) {
		String newRobotName = ((Robot)
				((RobotListModel)
						e.getSource()).getElementAt(e.getIndex0())).getName();
		addChannel(newRobotName);
	}

	@Override
	public void intervalRemoved(ListDataEvent e) {
		//String rmRobotName = ((Robot)
		//		((RobotListModel)
		//				e.getSource()).getElementAt(e.getIndex0())).getName();
		removeChannel(e.getIndex0());
	}

	@Override
	public void adjustmentValueChanged(AdjustmentEvent e) {
		e.getAdjustable().setValue(e.getAdjustable().getMaximum());  
	}
}
