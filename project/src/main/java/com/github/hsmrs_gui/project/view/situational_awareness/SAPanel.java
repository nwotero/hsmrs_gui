package src.main.java.com.github.hsmrs_gui.project.view.situational_awareness;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import src.main.java.com.github.hsmrs_gui.project.controller.ConsoleController;
import net.miginfocom.swing.MigLayout;


public class SAPanel extends JPanel{

	private boolean showCamera = true;
	private boolean showMap = false;
	private ImageView imgView;
	private InteractiveMapView mapView;
	private MuxPanel muxPanel;
	
	public SAPanel(){
		setLayout(new MigLayout("insets 0", "[fill]", "[fill]0[fill]"));
		imgView = new ImageView();
		mapView = new InteractiveMapView();
		muxPanel = new MuxPanel(this);
		
		add(muxPanel, "wrap");
		//add(imgView, "span 2 2, push, grow");
		add(imgView, "push, grow");
		//add(mapView, "push, grow");
		//add(new JButton("Hello World!"));
	}
	
	public void setView(String view){
		long timestamp = System.currentTimeMillis() / 1000;
		ConsoleController.getInstance().addLog("System", timestamp, view);
		if (view.equals("imgView")){
			remove(mapView);
			add(imgView, "push, grow");
		} else if (view.equals("mapView")){
			remove(imgView);
			add(mapView, "push, grow");
		}
		this.validate();
		repaint();
	}
}
