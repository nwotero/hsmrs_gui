package src.main.java.com.github.hsmrs_gui.project.ros;

//import static com.googlecode.javacv.cpp.opencv_core.*;
//import static com.googlecode.javacv.cpp.opencv_imgproc.CV_THRESH_BINARY;
//import static com.googlecode.javacv.cpp.opencv_imgproc.cvThreshold;

import java.awt.image.BufferedImage;
import java.awt.image.ComponentSampleModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.awt.image.SampleModel;
import java.awt.image.WritableRaster;
import java.io.ByteArrayInputStream;

import javax.imageio.ImageIO;

import org.ros.message.MessageListener;
//import org.ros.message.sensor_msgs.*;
//import org.ros.messages.sensor_msgs;
//import org.ros.rosjava_messages_nav_msgs.*;
//import org.ros.message.*;
//import org.ros.rosjava_messages.*;
import org.ros.node.topic.Subscriber;
import org.ros.node.ConnectedNode;

import com.github.hsmrs_gui.project.GuiNode;

import org.jboss.netty.buffer.ChannelBuffer;

import src.main.java.com.github.hsmrs_gui.project.controller.VideoController;
//import src.main.java.com.github.hsmrs_gui.project.util.JavaCVBridge;

public class ImageListener implements MessageListener<sensor_msgs.Image> {
	long time;

	public ImageListener(ConnectedNode connectedNode){
		Subscriber<sensor_msgs.Image> subscriber = connectedNode.newSubscriber(
				"hsmrs/test_images", sensor_msgs.Image._TYPE);
		subscriber.addMessageListener(this);
	}
	@Override
	public void onNewMessage(sensor_msgs.Image img) {
		time = System.currentTimeMillis();
		String cameraName = img.getHeader().getFrameId();

		// If the camera name is not in the selection box, add it
		// if (!CameraList.contains(cameraName)) {
		// CameraList.add(cameraName);
		// videoSourceSelector.addItem(cameraName);
		// }
		// Only display image if its header name is the same as the name in the
		// selection box
		// if (cameraName.equals((String)
		// videoSourceSelector.getSelectedItem())) {
		// videoDisplayPanel.show(cvImage.getBufferedImage())
		BufferedImage buffImg = messageToBufferedImage(img);
		if (buffImg == null){
			GuiNode.getLog().info("Bad image read");
			return;
		}
		VideoController.getInstance().receiveNewImage(cameraName, buffImg);
		// System.out.println("Time To Display: " +
		// (System.currentTimeMillis()-time));

	}
	
	public static BufferedImage messageToBufferedImage(sensor_msgs.Image imgMsg){
		int width = (int) imgMsg.getWidth();
		int height = (int) imgMsg.getHeight();	
		
		/*GuiNode.getLog().info(width);
		GuiNode.getLog().info(height);
		System.out.println(imgMsg.getData().array().length);
//		 for(int x = 0; x < width; x++) {
//             for(int y = 0; y < height; y++) { 
//            	 System.out.println(imgMsg.getData().readUnsignedShort());
//             }
//         }
		//ChannelBuffer data = imgMsg.getData();
		BufferedImage image = null;
		try{
			image = ImageIO.read( new ByteArrayInputStream( imgMsg.getData().array() ) );
			GuiNode.getLog().info(imgMsg.getData().toByteBuffer().array());
			if (image == null){
				GuiNode.getLog().info("NULL");
			}
		}
		catch(Exception e){
			System.out.println("Ouch");
		}
		//		DataBuffer buffer = new DataBufferByte(data, width*height);
//        SampleModel sampleModel = new ComponentSampleModel(DataBuffer.TYPE_BYTE, width, height, 3, width*3, new int[]{2,1,0});
//        Raster raster = Raster.createRaster(sampleModel, buffer, null);
//        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
//        image.setData(raster);
        return image;
        */
		
		ChannelBuffer data = imgMsg.getData();
		int imageSize = width * height * 3;
		int[] pixels = new int[imageSize];
		int i = 0;
		 while (data.readable()) {
		     pixels[i] = data.readByte();
		     i++;
		 }
		 
		 BufferedImage readImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		 WritableRaster raster = readImage.getRaster();
	     raster.setPixels(0, 0, width, height, pixels);
	     //GuiNode.getLog().info(readImage);
	     return readImage;
		 
	}
}
