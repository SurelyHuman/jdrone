import java.io.IOException;

public class TelloTester {
	
	public static void testGetters() throws IOException {
		TelloDrone tello = new TelloDrone();
		tello.activateSDK();
		System.out.println(tello.getBattery());
		System.out.println(tello.getHeight());
		System.out.println(tello.getSpeed());
		System.out.println(tello.getTemp());
		System.out.println(tello.getBarometer());
		System.out.println(tello.getAttitudePitch());
		System.out.println(tello.getAttitudeRoll());
		System.out.println(tello.getAttitudeYaw());
		System.out.println(tello.getAccelerationX());
		System.out.println(tello.getAccelerationY());
		System.out.println(tello.getAccelerationZ());
		System.out.println(tello.getTOF());
		System.out.println(tello.getFlightTime());
		System.out.println(tello.getWIFI());
		System.out.println(tello.getVersionSDK());
		System.out.println(tello.getSerialNumber());
		tello.end();
	}
	
	public static void testFlight() throws IOException, InterruptedException {
		TelloDrone tello = new TelloDrone();
		tello.activateSDK();
		tello.takeoff();
		tello.flyForward(600);
		tello.hoverInPlace(20);
		tello.turnCCW(720);
		tello.flip("b");
		tello.flyBackward(200);
		tello.land();
		tello.end();
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		//testGetters();
		testFlight();
	}

}
