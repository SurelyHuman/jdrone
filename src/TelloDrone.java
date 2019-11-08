
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TelloDrone extends AerialDrone {
	
	public TelloDrone() throws SocketException, UnknownHostException {
		this.controller = new DroneController(9000, 11111, 8889, "192.168.10.1");
	}
	
	public static void main(String[] args) throws IOException {
		TelloDrone tello = new TelloDrone();
		
		System.out.println("Tello Drone Demo" + "\n");
		System.out.println("Tello: command takeoff land flip forward back left right" + "\n" + "      " + " up down cw ccw speed speed?" + "\n");
		System.out.println("end -- quit demo" + "\n");
		
		Scanner scan = new Scanner(System.in);

    	String command = scan.nextLine();
    		
    	while(!command.equals("end") && command != null && !command.trim().isEmpty()) {
    		tello.controller.sendCommand(command);
    		command = scan.nextLine();
    	}
    		
		scan.close();
		tello.controller.closeSockets();
		System.out.println("Exit Program...");
	}
	
}
