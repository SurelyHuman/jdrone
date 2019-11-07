
import java.io.*;
import java.net.*;

public class DroneController {
	
	public DroneController() {
		
	}
	
	public DroneController(int hostPort, int videoPort, int dronePort, String droneAddress) throws SocketException, UnknownHostException {
		hostSocket = new DatagramSocket(hostPort);
		videoSocket = new DatagramSocket(videoPort);
		this.dronePort = dronePort;
		destinationAddress = InetAddress.getByName(droneAddress);
	}
	    
    public String sendCommand(String msg) throws IOException{
    	System.out.println(msg);
    	byte[] data = msg.getBytes();
    	String output = "";
     	DatagramPacket call = new DatagramPacket(data, data.length, destinationAddress, dronePort);
     	DatagramPacket response = new DatagramPacket(new byte[receiveBufferSize], receiveBufferSize);
     	hostSocket.send(call);
     	hostSocket.setSoTimeout(30000);
     	try {
     		hostSocket.receive(response);
     		output = new String(response.getData(), "UTF-8");
     		System.out.print(output);
     	}		
     	catch (SocketTimeoutException e) {
     		// timeout exception.
            System.out.println("Timeout reached!!! " + e);
            hostSocket.close();
            return "Timeout reached and Socket Closed!!!";
     	}
    	return output;
    }
    
    public void closeSockets() {
    	hostSocket.close();
    	videoSocket.close();
    }
    
	public DatagramSocket getUdpSocket() {
		return hostSocket;
	}

	public DatagramSocket getVideoUdpSocket() {
		return videoSocket;
	}


	public int getSendToPort() {
		return dronePort;
	}

	public int getReceiveBufferSize() {
		return receiveBufferSize;
	}

	public InetAddress getDestinationAddress() {
		return destinationAddress;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	private DatagramSocket hostSocket, videoSocket;
	private int dronePort;
	private final int receiveBufferSize = 8192;
	private InetAddress destinationAddress;

}
