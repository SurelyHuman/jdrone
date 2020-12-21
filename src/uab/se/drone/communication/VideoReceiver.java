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
        		byte[] send = packet.getData();
        		handleData(send);
			} 
        	catch (IOException | InterruptedException e) {
				e.printStackTrace();
			}
        }
        videoSocket.close();
	}
	
	public void closeVideoSocket() throws IOException, InterruptedException {
        running = false;
    }
	
	protected void handleData(byte[] packet) throws IOException, InterruptedException {
		String received = new String(packet, 0, packet.length);
		System.out.println(received);
	}
	
	public DatagramSocket getVideoSocket() {
		return videoSocket;
	}

	@SuppressWarnings("unused")
	private void setVideoSocket(DatagramSocket videoSocket) {
		this.videoSocket = videoSocket;
	}

	public int getVideoPort() {
		return videoPort;
	}

	@SuppressWarnings("unused")
	private void setVideoPort(int videoPort) {
		this.videoPort = videoPort;
	}

	public byte[] getBuf() {
		return buf;
	}

	@SuppressWarnings("unused")
	private void setBuf(byte[] buf) {
		this.buf = buf;
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}
}
