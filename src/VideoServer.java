import java.io.*;
import java.net.*;

public abstract class VideoServer extends Thread{
	private DatagramSocket videoSocket;
    private boolean running;
    private byte[] buf = new byte[2048];
    private int videoPort;
    
    public VideoServer(int videoPort, int length) {
        this.setVideoPort(videoPort);
        this.buf = new byte[length];
    }
    
    public void run() {
        running = true;

        while (running) {
            try {
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                videoSocket.receive(packet);

                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                packet = new DatagramPacket(buf, buf.length, address, port);
                this.handle(packet.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        videoSocket.close();
    }

    protected abstract void handle(byte[] message);

    public void close() {
        this.running = false;
    }

	public int getVideoPort() {
		return videoPort;
	}

	private void setVideoPort(int videoPort) {
		this.videoPort = videoPort;
	}
}
