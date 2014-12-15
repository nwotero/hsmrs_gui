package src.main.java.com.github.hsmrs_gui.project.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class MapButtonListener implements ActionListener {

	private JButton btn;
	
	public MapButtonListener(JButton btn){
		this.btn = btn;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Hello");
		String[] xy = e.getActionCommand().split(",");
		int x = Integer.parseInt(xy[0]);
		int y = Integer.parseInt(xy[1]);
		InteractiveMapController.getInstance().highlightButton(btn, x, y);
	}
	
}
