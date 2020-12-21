package uab.se.drone.util;

import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber.Exception;

import javax.swing.*;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;


public class StreamPlayer extends Thread{

	private static final CanvasFrame window = new CanvasFrame( "Live Feed", 1.0 );
	private static final Queue<Frame> frames = new LinkedList<>();
	private volatile boolean running = false;
	private int port;
	private int frameWidth, frameHeight;
	
	public StreamPlayer(int port) {
		this.port = port;
	}

	@SuppressWarnings("resource")
	public void run() {
		System.out.println("Stream On" + '\n');

		running = true;

		final FFmpegFrameGrabber fg = new FFmpegFrameGrabber("udp://@:" + port);
		window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		// window.setCanvasSize(frameWidth, frameHeight);

		try {
			fg.start();
			window.setCanvasSize(fg.getImageWidth(), fg.getImageHeight());
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		Frame frame;
		while(running) {
			try {
				if((frame = fg.grabImage()) != null) {
					synchronized(frames) {
						frames.add(frame.clone());
						while(!frames.isEmpty()) {
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

	public int getFrameWidth() {
		return frameWidth;
	}

	public void setFrameWidth(int frameWidth) {
		this.frameWidth = frameWidth;
	}

	public int getFrameHeight() {
		return frameHeight;
	}

	public void setFrameHeight(int frameHeight) {
		this.frameHeight = frameHeight;
	}
}