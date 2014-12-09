package src.main.java.com.github.hsmrs_gui.project.view.feedback;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

public class FeedbackPane extends JPanel{

	private ConsolePane consoleView;
	
	public FeedbackPane(){
		consoleView = new ConsolePane();
		
		setLayout(new MigLayout("insets 0, fill", "[][]", "[]"));
		
		add(consoleView, "left, span, grow");
	}
}
