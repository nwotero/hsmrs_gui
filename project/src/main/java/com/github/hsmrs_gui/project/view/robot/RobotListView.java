package src.main.java.com.github.hsmrs_gui.project.view.robot;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ListModel;

import src.main.java.com.github.hsmrs_gui.project.model.Robot;
import src.main.java.com.github.hsmrs_gui.project.util.Colors;
import src.main.java.com.github.hsmrs_gui.project.view.list.SRList;
import net.miginfocom.swing.MigLayout;

public class RobotListView extends JPanel{
	
	private JLabel lblTitle;
	private SRList<Robot> robotListView;
	
	public RobotListView(ListModel listModel) {
		lblTitle = new JLabel("Robot List", JLabel.CENTER);
		lblTitle.setBackground(Colors.bannerColor);
		lblTitle.setOpaque(true);
		lblTitle.setFont(new Font(lblTitle.getFont().getName(), Font.PLAIN, 24));
		
		robotListView = new SRList<Robot>(listModel, new RobotListItemRenderer());
		
		this.setBackground(Color.white);
		this.setBorder(BorderFactory.createMatteBorder(1, 3, 3, 1, Color.black));
		this.setLayout(new MigLayout("insets 0", "[left, fill]", "[]0[fill]"));
		this.add(lblTitle, "wrap");
		this.add(robotListView, "push, growy, wrap");
		
	}

}
