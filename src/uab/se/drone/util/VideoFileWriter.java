package uab.se.drone.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.SocketException;

import uab.se.drone.communication.VideoReceiver;

public class VideoFileWriter extends VideoReceiver {
	
	public File file;
	public FileOutputStream fos;

	public VideoFileWriter(int videoPort, int buffer, String filepath) throws SocketException, FileNotFoundException {
		super(videoPort, buffer);
		file = new File(filepath);
		fos = new FileOutputStream(file); 
	}
	
	@Override
	protected void handleData(DatagramPacket packet) throws IOException {
		fos.write(packet.getData(), 0, packet.getLength());
	}
	
	@Override
	public void closeVideoSocket() throws IOException {
        setRunning(false);
        fos.close();
    }

	public File getFile() {
		return file;
	}

	@SuppressWarnings("unused")
	private void setFile(File file) {
		this.file = file;
	}

}
