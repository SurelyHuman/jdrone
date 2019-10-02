

/***
 * Interface containing a selection of possible drone flight commands mostly independent of drone manufacturer
 * 09/30/2019 v1.0
 * @author MasterControlProgram
 *
 */
public interface PhysicalDrone {
	
	/***
	 * This is a required method for every flight routine 
	 * Starts the drone process and readies it to recieve additional SDK instructions
	 * MUST BE INCLUDED AT THE BEGINNING OF EVERY FLIGHT ROUTINE
	 */
	public void beginProgram();
	
	/***
	 * This is a required method for every flight routine
	 * Terminates external processes and cleans up any internal objects
	 * MUST BE INCLUDED AT THE END OF EVERY FLIGHT ROUTINE
	 */
	public void endProgram(); 
	
	/***
	 * Sends the "takeoff" signal to an active drone
	 * Trying to fly in low light or high wind may cause errors
	 * Trying to take off with a low battery may fail
	 */
	public void takeoff();
	
	/***
	 * Sends the "land" signal to an active drone
	 * Drone may land in place despite any obstacles below it
	 * Make sure your landing area is clear
	 */
	public void land();
	
	/***
	 * Activates the drone's onboard video stream
	 * may not function without additional outside processes
	 */
	public void streamOn();
	
	/***
	 * Deactivates a running video stream
	 */
	public void streamOff();
	
	/***
	 * Turns on the mission pad SDK functionality of a drone 
	 * Must be enabled before other mission pad based methods will function
	 */
	public void missionPadOn();
	
	/***
	 * Turns off the mission pad SDK functionality of a drone
	 */
	public void missionPadOff();
	
	/***
	 * Sets whether the drone looks for mission pads in specific directions determined by the SDK  
	 * @param param integer value starting with 0 up to the total directional combinations allowed by the SDK
	 */
	public void missionPadDirection(int param);
	
	/***
	 * Requests drone increase altitude 
	 * System of measurement varies by manufacturer 
	 * @param up integer value starting with SDK lower bound up to upper bound of possible distance travelled
	 */
	public void flyUpward(int up);
	
	/***
	 * Requests drone decrease altitude
	 * System of measurement varies by manufacturer
	 * @param down integer value starting with SDK lower bound up to upper bound of possible distance travelled
	 */
	public void flyDown(int down);
	
	/***
	 * Requests drone fly in forward heading relative to airframe 
	 * System of measurement varies by manufacturer
	 * @param front integer value starting with SDK lower bound up to upper bound of possible distance travelled
	 */
	public void flyForward(int front);
	
	/***
	 * Requests drone fly in reverse heading relative to airframe 
	 * System of measurement varies by manufacturer
	 * @param back integer value starting with SDK lower bound up to upper bound of possible distance travelled
	 */
	public void flyBackward(int back);
	
	/***
	 * Requests drone fly left heading relative to airframe
	 * System of measurement varies by manufacturer
	 * @param left integer value starting with SDK lower bound up to upper bound of possible distance travelled
	 */
	public void flyLeft(int left);
	
	/***
	 * Requests drone fly right heading relative to airframe
	 * System of measurement varies by manufacturer
	 * @param right integer value starting with SDK lower bound up to upper bound of possible distance travelled
	 */
	public void flyRight(int right);
	
	/***
	 * Requests drone fly to point in three dimensions relative to current position as origin 
	 * @param x integer value of point along the x axis with lower bound up and upper bound of possible distance set by SDK
	 * @param y integer value of point along the y axis with lower bound up and upper bound of possible distance set by SDK
	 * @param z integer value of point along the z axis with lower bound up and upper bound of possible distance set by SDK
	 * @param speed integer value setting some distance travelled per some unit time determined by SDK
	 */
	public void gotoXYZ(int x, int y, int z, int speed);
	
	/***
	 * Requests drone fly to point in three dimensions and search for symbol recognition system pattern with specific ID at location
	 * @param x integer value of point along the x axis with lower bound up and upper bound of possible distance set by SDK
	 * @param y integer value of point along the y axis with lower bound up and upper bound of possible distance set by SDK
	 * @param z integer value of point along the z axis with lower bound up and upper bound of possible distance set by SDK
	 * @param speed integer value setting some distance travelled per some unit time determined by SDK
	 * @param ID string identification number of a particular symbol recognition system pattern
	 */
	public void gotoMissionPadXYZ(int x, int y, int z, int speed, String ID);
	
	/***
	 * Requests drone fly along a curve in three dimensions defined by two points 
	 * Starts with current location as origin flying through first point and ending at second point
	 * @param x1
	 * @param y1
	 * @param z1
	 * @param x2
	 * @param y2
	 * @param z2
	 * @param speed
	 */
	public void flyCurve(int x1, int y1, int z1, int x2, int y2, int z2, int speed);
	
	/***
	 * Requests drone fly along a curve in three dimensions defined by two points and then search for symbol recognition system pattern with specific ID at location  
	 * Starts with current location as origin flying through first point and ending at second point
	 * @param x1
	 * @param y1
	 * @param z1
	 * @param x2
	 * @param y2
	 * @param z2
	 * @param speed
	 * @param ID
	 */
	public void flyCurveMissionPad(int x1, int y1, int z1, int x2, int y2, int z2, int speed, String ID);
	
	/***
	 * 
	 * @param degrees
	 */
	public void turnCW(int degrees);
	
	/***
	 * 
	 * @param degrees
	 */
	public void turnCCW(int degrees);
	
	/***
	 * 
	 * @param direction
	 */
	public void flip(String direction);
	
	/***
	 * NOT IN USE
	 * @param x
	 * @param y
	 * @param z
	 * @param speed
	 * @param yaw
	 * @param ID1
	 * @param ID2
	 */
	public void jumpMissionPad(int x, int y, int z, int speed, int yaw, String ID1, String ID2);
	
	/***
	 * 
	 * @param seconds
	 * @throws InterruptedException uses timer classes and may be interruptable
	 */
	public void hoverInPlace(int seconds) throws InterruptedException;
	
	/***
	 * 
	 */
	public void stopInPlace();
	
	/***
	 * 
	 * @param speed
	 */
	public void setSpeed(int speed);
	
	/***
	 * 
	 * @return
	 */
	public double getSpeed();
	
	/***
	 * 
	 * @return
	 */
	public int getBattery();
	
	/***
	 * 
	 * @return
	 */
	public int getFlightTime();
	
	/***
	 * 
	 * @return
	 */
	public int getHeight();
	
	/***
	 * 
	 * @return
	 */
	public int getTemp();
	
	/***
	 * 
	 * @return
	 */
	public int getAttitudePitch();
	
	/***
	 * 
	 * @return
	 */
	public int getAttitudeRoll();
	
	/***
	 * 
	 * @return
	 */
	public int getAttitudeYaw();
	
	/***
	 * 
	 * @return
	 */
	public double getBarometer();
	
	/***
	 * 
	 * @return
	 */
	public double getAccelerationX();
	
	/***
	 * 
	 * @return
	 */
	public double getAccelerationY();
	
	/***
	 * 
	 * @return
	 */
	public double getAccelerationZ();
	
	/***
	 * 
	 * @return
	 */
	public int getTOF();
	
	/***
	 * Request drone's unique SSID
	 * @return string representation of SSID
	 */
	public String getWIFI();
	
	/***
	 * Request drone's current firmware version
	 * @return string representation of firmware version number
	 */
	public String getVersionSDK();
	
	/***
	 * Requests drone's manufacturer serial ID
	 * @return string representation of serial ID
	 */
	public String getSerialNumber();
	
}
