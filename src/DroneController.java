
import java.io.*;
import java.net.*;

public class DroneController {
	
	public DroneController() {
		
	}
	
	public DroneController(int udpPort, int videoPort, int sendToPort, String sendAddress) throws SocketException, UnknownHostException {
		udpSocket = new DatagramSocket(udpPort);
		videoUdpSocket = new DatagramSocket(videoPort);
		this.sendToPort = sendToPort;
		destinationAddress = InetAddress.getByName(sendAddress);
	}
	    
    public String sendCommand(String msg) throws IOException{
    	System.out.println(msg);
    	byte[] data = msg.getBytes();
    	String output = "";
     	DatagramPacket call = new DatagramPacket(data, data.length, destinationAddress,sendToPort);
     	DatagramPacket response = new DatagramPacket(new byte[receiveBufferSize], receiveBufferSize);
     	udpSocket.send(call);
     	udpSocket.setSoTimeout(30000);
     	try {
     		udpSocket.receive(response);
     		output = new String(response.getData(), "UTF-8");
     		System.out.print(output);
     	}		
     	catch (SocketTimeoutException e) {
     		// timeout exception.
            System.out.println("Timeout reached!!! " + e);
            udpSocket.close();
            return "Timeout reached and Socket Closed!!!";
     	}
    	return output;
    }
    
    public void closeSockets() {
    	udpSocket.close();
    	videoUdpSocket.close();
    }
    
	public DatagramSocket getUdpSocket() {
		return udpSocket;
	}

	public DatagramSocket getVideoUdpSocket() {
		return videoUdpSocket;
	}


	public int getSendToPort() {
		return sendToPort;
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

	private DatagramSocket udpSocket, videoUdpSocket;
	//private int udpPort; // = 9000;
	//private int videoPort; // = 11111;
	private int sendToPort; // = 8889;
	private final int receiveBufferSize = 8192;
	//private String sendAddress; // = "192.168.10.1";
	private InetAddress destinationAddress;

}
