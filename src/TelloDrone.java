
import java.net.SocketException;
import java.net.UnknownHostException;

public class TelloDrone extends AerialDrone{
	
	public TelloDrone() throws SocketException, UnknownHostException {
		this.controller = new DroneController(9000, 111111, 8889, "192.168.10.1");
	}
	
}
