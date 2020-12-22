package uab.se.drone.control.physical.tello;

import java.io.IOException;

public class TelloTester {
	
	public static void testGetters() throws IOException, InterruptedException {
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
		tello.streamOn();
		tello.streamViewOn();
		tello.hoverInPlace(10);
		tello.takeoff();
//		tello.gotoXY(1125, 700, 150);
//		tello.gotoXYZ(1125, 700, 900, 150);
//		tello.decreaseAltitude(500); // test crash prevention
		tello.flyForward(100);
//		tello.hoverInPlace(10);
		tello.turnCCW(180);
		tello.flip("b");
		tello.flyForward(100);
		tello.flip("f");
		tello.turnCW(180);
		tello.land();
		tello.streamOff();
		tello.streamViewOff();
		tello.end();
	}
	
	public static void testVideo() throws IOException, InterruptedException {
		TelloDrone tello = new TelloDrone();
		tello.activateSDK();
		tello.streamOn();
		//tello.streamViewOn();
		tello.streamRecordOn();
		tello.hoverInPlace(60);
		tello.streamOff();
		//tello.streamViewOff();
		tello.streamRecordOff();
		tello.end();
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		//testGetters();
		//testFlight();
		testVideo();
		System.exit(0);
	}

}
