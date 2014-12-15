package src.main.java.com.github.hsmrs_gui.project.view.situational_awareness;

import javax.swing.JButton;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;


public class SAPanel extends JPanel{

	private boolean showCamera = true;
	private boolean showMap = false;
	private ImageView imgView;
	private InteractiveMapView mapView;
	
	public SAPanel(){
		setLayout(new MigLayout("insets 0", "[fill]0[fill]", "[fill]0[fill]"));
		imgView = new ImageView();
		mapView = new InteractiveMapView();
		
		add(imgView, "span 2 2, push, grow");
		//add(new JButton("Hello World!"));
	}
	
	
}
