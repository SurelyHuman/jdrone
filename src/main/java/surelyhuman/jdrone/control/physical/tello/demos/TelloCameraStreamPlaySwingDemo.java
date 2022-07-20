package main.java.surelyhuman.jdrone.control.physical.tello.demos;

import java.io.IOException;

import main.java.surelyhuman.jdrone.control.physical.tello.TelloDrone;

public class TelloCameraStreamPlaySwingDemo {

	private static void stream() throws IOException, InterruptedException  {
		TelloDrone tello = new TelloDrone();
		tello.activateSDK();
		tello.streamOn();
		tello.streamViewSwingOn();
		tello.hoverInPlace(30);
		tello.streamOff();
		tello.streamViewSwingOff();
		tello.end();
	}

	public static void main(String[] args) throws InterruptedException, IOException {
		stream();
		System.exit(0);
	}
}
