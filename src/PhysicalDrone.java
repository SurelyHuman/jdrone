

/***
 * 
 * @author MasterControlProgram
 *
 */
public interface PhysicalDrone {
	
	/***
	 * 
	 */
	public void beginProgram();
	
	/***
	 * 
	 */
	public void endProgram(); 
	
	/***
	 * 
	 */
	public void takeoff();
	
	/***
	 * 
	 */
	public void land();
	
	/***
	 * 
	 */
	public void streamOn();
	
	/***
	 * 
	 */
	public void streamOff();
	
	/***
	 * 
	 */
	public void missionPadOn();
	
	/***
	 * 
	 */
	public void missionPadOff();
	
	/***
	 * 
	 * @param param
	 */
	public void missionPadDirection(int param);
	
	/***
	 * 
	 * @param up
	 */
	public void flyUpward(int up);
	
	/***
	 * 
	 * @param down
	 */
	public void flyDown(int down);
	
	/***
	 * 
	 * @param front
	 */
	public void flyForward(int front);
	
	/***
	 * 
	 * @param back
	 */
	public void flyBackward(int back);
	
	/***
	 * 
	 * @param left
	 */
	public void flyLeft(int left);
	
	/***
	 * 
	 * @param right
	 */
	public void flyRight(int right);
	
	/***
	 * 
	 * @param x
	 * @param y
	 * @param z
	 * @param speed
	 */
	public void gotoXYZ(int x, int y, int z, int speed);
	
	/***
	 * 
	 * @param x
	 * @param y
	 * @param z
	 * @param speed
	 * @param ID
	 */
	public void gotoMissionPadXYZ(int x, int y, int z, int speed, String ID);
	
	/***
	 * 
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
	 * 
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
	 * 
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
	 * @throws InterruptedException
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
	 * 
	 * @return
	 */
	public String getWIFI();
	
	/***
	 * 
	 * @return
	 */
	public String getVersionSDK();
	
	/***
	 * 
	 * @return
	 */
	public String getSerialNumber();
	
}
