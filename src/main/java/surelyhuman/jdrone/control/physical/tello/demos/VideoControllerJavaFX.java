package main.java.surelyhuman.jdrone.control.physical.tello.demos;

import org.apache.log4j.Logger;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class VideoControllerJavaFX {
	
	final static Logger log = Logger.getLogger(VideoControllerJavaFX.class);
	
	@FXML
	private ImageView currentFrame;
	
	public void updateImageView(Image image) {
		Platform.runLater(() -> {
			currentFrame.setImage(image);
		});
	}
}
