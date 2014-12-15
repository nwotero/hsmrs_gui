package src.main.java.com.github.hsmrs_gui.project.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;


public class InteractiveMapController{

	private static InteractiveMapController instance;
	private JButton highlightedButton;
	private ConsoleController consoleController;
	
	private InteractiveMapController(){
		consoleController = ConsoleController.getInstance();
	}

	
	public static InteractiveMapController getInstance(){
		if (instance == null){
			instance = new InteractiveMapController();
		}
		
		return instance;
	}
	
	public void highlightButton(JButton btn, int x, int y){
		if (highlightedButton == btn) {
			highlightedButton.setBackground(Color.white);
			highlightedButton = null;
		}
		else if (highlightedButton != null){
			highlightedButton.setBackground(Color.white);
			btn.setBackground(Color.yellow);
			highlightedButton = btn;
		}
		else {
			btn.setBackground(Color.yellow);
			highlightedButton = btn;
		}
		long timestamp = System.currentTimeMillis() / 1000;
		consoleController.addLog("System", timestamp, 
				"Grid cell clicked: " + x + ", " + y);
	}
}
