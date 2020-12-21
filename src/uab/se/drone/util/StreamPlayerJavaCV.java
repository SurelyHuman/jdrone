package uab.se.drone.util;

import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber.Exception;

import javax.swing.*;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;


public class StreamPlayerJavaCV extends Thread{

	private static final CanvasFrame window = new CanvasFrame( "Live Feed", 1.0 );
	private static final Queue<Frame> frames = new LinkedList<>();
	private volatile boolean running = false;

	public void run() {
		System.out.println("Stream On" + '\n');

		running = true;

		final FFmpegFrameGrabber fg = new FFmpegFrameGrabber("udp://@:11111");
		window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		try {
			fg.start();
			window.setCanvasSize(fg.getImageWidth(), fg.getImageHeight());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		while(running) {
			Frame frame;
			try {
				if((frame = fg.grabImage()) != null) {
					synchronized(frames) {
						frames.add(frame.clone());
						if(!frames.isEmpty()) {
							window.showImage(frames.remove());
						}
					}
					handleData(frame); // extend class and add CV  
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			fg.release();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/***
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void closeVideoStream() throws IOException, InterruptedException {
		running = false;
	}
	
	/***
	 * Override this method on class extension and add CV methods
	 * @param data
	 */
	public void handleData(Frame data) {
		
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}
}