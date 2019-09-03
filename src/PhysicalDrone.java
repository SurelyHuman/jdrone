

/***
 * 
 * @author MasterControlProgram
 *
 */
public interface PhysicalDrone {
	
	public void beginProgram();
	public void endProgram(); 
	public void takeoff();
	public void land();
	public void streamOn();
	public void streamOff();
	public void missionPadOn();
	public void missionPadOff();
	public void missionPadDirection(int param);
	public void flyUpward(int up);
	public void flyDown(int down);
	public void flyForward(int front);
	public void flyBackward(int back);
	public void flyLeft(int left);
	public void flyRight(int right);
	public void gotoXYZ(int x, int y, int z, int speed);
	public void gotoMissionPadXYZ(int x, int y, int z, int speed, String ID);
	public void flyCurve(int x1, int y1, int z1, int x2, int y2, int z2, int speed);
	public void flyCurveMissionPad(int x1, int y1, int z1, int x2, int y2, int z2, int speed, String ID);
	public void turnCW(int degrees);
	public void turnCCW(int degrees);
	public void flip(String direction);
	public void jumpMissionPad(int x, int y, int z, int speed, int yaw, String ID1, String ID2);
	public void hoverInPlace(int seconds) throws InterruptedException;
	public void stopInPlace();
	
	public void setSpeed(int speed);
	
	public double getSpeed();
	public int getBattery();
	public int getFlightTime();
	public int getHeight();
	public int getTemp();
	public int getAttitudePitch();
	public int getAttitudeRoll();
	public int getAttitudeYaw();
	public double getBarometer();
	public double getAccelerationX();
	public double getAccelerationY();
	public double getAccelerationZ();
	public int getTOF();
	public String getWIFI();
	public String getVersionSDK();
	public String getSerialNumber();
	
}
