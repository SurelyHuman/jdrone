package main.java.surelyhuman.jdrone.control.physical.tello.demos;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import main.java.surelyhuman.jdrone.control.physical.tello.TelloDrone;
import main.java.surelyhuman.jdrone.util.StreamPlayerJavaFX;

public class TelloCameraStreamPlayJavaFXDemo extends Application{
	
	TelloDrone tello;
	VideoControllerJavaFX controller;
	
	final static Logger log = Logger.getLogger(TelloCameraStreamPlayJavaFXDemo.class);

	ExecutorService executor = new ThreadPoolExecutor(1, 1,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(1));
	
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("DroneDemoView.fxml"));
			BorderPane rootElement = (BorderPane) loader.load();
			Scene scene = new Scene(rootElement, 960, 720);
			primaryStage.setTitle("Live Feed");
			primaryStage.setScene(scene);
			primaryStage.show();

			tello = new TelloDrone();
			tello.activateSDK();
			
			primaryStage.setOnCloseRequest((new EventHandler<WindowEvent>() {
				public void handle(WindowEvent we) {
					try {
						tello.streamOff();
						tello.end();
					} catch (IOException | InterruptedException e) {
						e.printStackTrace();
					}
					System.exit(0);
				}
			}));
			
			tello.streamOn();
			controller = loader.getController();
			startVideo(tello);

			new Thread(() -> controlDrone(tello, primaryStage)).start();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private void startVideo(TelloDrone tello) {
		StreamPlayerJavaFX video = tello.streamViewJavaFXOn();
		new Thread(video).start();

		new Thread(() -> {
			while (true) {
				video.get().ifPresent(image -> controller.updateImageView(image));
			}
		}).start();
	}
	
	void controlDrone(TelloDrone tello, Stage primaryStage) {
		new Thread(() -> {
			int battery = 100;
				 // used as a wait to make sure video has time to activate
				try {
					tello.hoverInPlace(10);
					tello.takeoff();
				} catch (IOException | InterruptedException e1) {
					e1.printStackTrace();
				}
			while (battery >= 50) {
				try {
					battery = tello.getBattery();
					tello.flyForward(150);
					tello.turnCCW(180);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			try {
				tello.land();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}).start();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
