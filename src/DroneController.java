
import java.io.*;
import java.net.*;

public class DroneController {
	
	private DatagramSocket mainUdpSocket, videoUdpSocket;
	private int mainPort = 9000;
	private int videoPort = 11111;
	private int droneSendPort = 8889;
	private int droneReceiveBufferSize = 1518;
	private String droneAddress = "192.168.10.1";
	private InetAddress destinationAddress, localAddress;
	    
    public String send(String msg) throws IOException{
    	return null;
    }
    
    public void close() {
    	
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
