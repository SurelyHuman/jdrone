package uab.se.drone.communication;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class VideoReceiver extends Thread{
	
	private DatagramSocket videoSocket;
	private int videoPort;
	private volatile boolean running = false;
	private byte[] buf;

	
	public VideoReceiver(int videoPort, int buffer) throws SocketException {
		this.videoPort = videoPort;
		this.videoSocket = new  DatagramSocket(videoPort);
		this.buf = new byte[buffer];
	}

	public void run() {
        running = true;
        
        System.out.print("Camera On" + '\n');

        while (running) {
        	try {
        		DatagramPacket packet = new DatagramPacket(buf, buf.length);
        		videoSocket.receive(packet);
        		handleData(packet);
			} 
        	catch (IOException e) {
				e.printStackTrace();
			}
        }
        videoSocket.close();
	}
	
	public void closeVideoSocket() throws IOException {
        running = false;
    }
	
	public void handleData(DatagramPacket packet) throws IOException {
		String received = new String(packet.getData(), 0, packet.getLength());
		System.out.println(received);
	}
	
	public DatagramSocket getVideoSocket() {
		return videoSocket;
	}

	public void setVideoSocket(DatagramSocket videoSocket) {
		this.videoSocket = videoSocket;
	}

	public int getVideoPort() {
		return videoPort;
	}

	public void setVideoPort(int videoPort) {
		this.videoPort = videoPort;
	}

	public byte[] getBuf() {
		return buf;
	}

	public void setBuf(byte[] buf) {
		this.buf = buf;
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}
}
