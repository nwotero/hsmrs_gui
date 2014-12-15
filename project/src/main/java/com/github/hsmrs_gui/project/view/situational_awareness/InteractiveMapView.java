package src.main.java.com.github.hsmrs_gui.project.view.situational_awareness;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import src.main.java.com.github.hsmrs_gui.project.controller.MapButtonListener;
import net.miginfocom.swing.MigLayout;

public class InteractiveMapView extends JPanel
{

	private JButton[][] buttonMatrix;
	private int defaultHeight = 20;
	private int defaultWidth = 20;
	private int height;
	private int width;
	
	public InteractiveMapView(){
		setLayout(new MigLayout("insets 0, gap 0, align center"));
		setBackground(Color.black);
		buttonMatrix = new JButton[defaultHeight][defaultWidth];
		for (int x = 0; x < defaultWidth; x++){
			for (int y = 0; y < defaultHeight; y++){
				JButton btn = new JButton();
				btn.addActionListener(new MapButtonListener(btn));
				btn.setActionCommand(x + "," + y);
				btn.setBackground(Color.white);
				btn.setPreferredSize(new Dimension(500/defaultWidth, 500/defaultHeight));
				btn.setMaximumSize(new Dimension(500/defaultWidth, 500/defaultHeight));
				buttonMatrix[y][x] = btn;
				if (y == defaultHeight - 1)
					add(btn, "gap top 0, wrap");
				else
					add(btn);
			}
		}
	}
}
