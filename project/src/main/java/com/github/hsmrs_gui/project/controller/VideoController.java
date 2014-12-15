package src.main.java.com.github.hsmrs_gui.project.controller;

import java.awt.image.BufferedImage;

import com.github.hsmrs_gui.project.GuiNode;

import src.main.java.com.github.hsmrs_gui.project.view.situational_awareness.ImageView;

public class VideoController {

	private static VideoController instance;
	private String source;
	private ImageView imgView;
	
	private VideoController(){}
	
	public static VideoController getInstance(){
		if (instance == null){
			instance = new VideoController();
		}
		return instance;
	}
	
	public void setImageView(ImageView imgView){
		this.imgView = imgView;
	}
	
	public synchronized void receiveNewImage(String source, BufferedImage img){
//		if (imgView == null){
//			return;
//		}
//		else if( !(this.source.equals(source)) ){
//			return;
//		}
//		else{
//			imgView.setImage(img);
//		}
		imgView.setImage(img);
	}
}
