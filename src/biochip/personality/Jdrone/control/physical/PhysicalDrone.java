package biochip.personality.Jdrone.control.physical;

import java.io.IOException;

import biochip.personality.Jdrone.communication.DroneController;

public abstract class PhysicalDrone {
	
	protected DroneController controller;
	
	/***
	 * 
	 * @param speed
	 * @throws IOException 
	 */
	public abstract void setSpeed(int speed) throws IOException;
	
	/***
	 * 
	 * @return
	 * @throws IOException 
	 * @throws NumberFormatException 
	 */
	public abstract double getSpeed() throws NumberFormatException, IOException;
	
	/***
	 * 
	 * @return
	 * @throws IOException 
	 * @throws NumberFormatException 
	 */
	public abstract int getBattery() throws NumberFormatException, IOException;
	
}
