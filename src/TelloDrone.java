import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class TelloDrone extends MultiRotorDrone {
	
	private final int maxGoto = 500, minGoto = -500, minDist = 20, maxSpeed = 100, minSpeed = 10, maxDegrees = 360, minDegrees = 1;
	private final int maxDist = maxGoto;
	
	/***
	 * 
	 * @throws SocketException
	 * @throws UnknownHostException
	 */
	public TelloDrone() throws SocketException, UnknownHostException {
		this.controller = new DroneController(9000, 11111, 8889, "192.168.10.1");
	}
	
	/***
	 * 
	 * @throws IOException
	 */
	public void activateSDK() throws IOException {
		this.controller.sendCommand("command");
	}
	
	public void end() {
		this.controller.closeSockets();
		System.out.println("Exit Program...");
	}
	
	@Override
	public void takeoff() throws IOException {
		this.controller.sendCommand("takeoff");
	}

	@Override
	public void land() throws IOException {
		this.controller.sendCommand("land");
	}
	
	/***
	 * 
	 * @throws IOException
	 */
	public void streamOn() throws IOException {
		this.controller.sendCommand("streamon");
	}
	
	/***
	 * 
	 * @throws IOException
	 */
	public void streamOff() throws IOException {
		this.controller.sendCommand("streamoff");
	}
	
	/***
	 * 
	 * @throws IOException
	 */
	public void missionPadOn() throws IOException {
		this.controller.sendCommand("mon");
	}

	/***
	 * 
	 * @throws IOException
	 */
	public void missionPadOff() throws IOException {
		this.controller.sendCommand("moff");
	}
	
	/***
	 * 
	 * @param param
	 * @throws IOException
	 */
	public void missionPadDirection(int param) throws IOException {
		this.controller.sendCommand("mdirection " + param);
	}

	@Override
	public void increaseAltitude(int up) throws IOException {
		if (up <= minDist) this.controller.sendCommand("up " + minDist);
		else if (up > maxDist) {
			this.controller.sendCommand("up " + maxDist);
			increaseAltitude(Math.abs(maxDist - up));
		}
		else this.controller.sendCommand("up " + up);
	}

	@Override
	public void decreaseAltitude(int down) throws IOException {
		if (down <= minDist) this.controller.sendCommand("down " + minDist);
		else if (down > maxDist) {
			this.controller.sendCommand("down " + maxDist);
			increaseAltitude(Math.abs(maxDist - down));
		}
		else this.controller.sendCommand("up " + down);
	}

	@Override
	public void flyForward(int front) throws IOException {
		if (front <= minDist) this.controller.sendCommand("forward " + minDist);
		else if (front > maxDist) {
			this.controller.sendCommand("forward " + maxDist);
			increaseAltitude(Math.abs(maxDist - front));
		}
		else this.controller.sendCommand("forward " + front);
	}
	
	@Override
	public void flyBackward(int back) throws IOException {
		if (back <= minDist) this.controller.sendCommand("back " + minDist);
		else if (back > maxDist) {
			this.controller.sendCommand("back " + maxDist);
			increaseAltitude(Math.abs(maxDist - back));
		}
		else this.controller.sendCommand("back " + back);
	}

	@Override
	public void flyLeft(int left) throws IOException {
		if (left <= minDist) this.controller.sendCommand("left " + minDist);
		else if (left > maxDist) {
			this.controller.sendCommand("left " + maxDist);
			increaseAltitude(Math.abs(maxDist - left));
		}
		else this.controller.sendCommand("left " + left);
	}

	@Override
	public void flyRight(int right) throws IOException {
		if (right <= minDist) this.controller.sendCommand("right " + minDist);
		else if (right > maxDist) {
			this.controller.sendCommand("right " + maxDist);
			increaseAltitude(Math.abs(maxDist - right));
		}
		else this.controller.sendCommand("right " + right);
	}

	@Override
	public void turnCW(int degrees) throws IOException {
		if (degrees <= minDegrees) this.controller.sendCommand("cw " + minDegrees);
		else if (degrees > maxDegrees) {
			this.controller.sendCommand("cw " + maxDegrees);
			turnCW(Math.abs(maxDegrees - degrees));
		}
		else this.controller.sendCommand("cw " + degrees);
	}

	@Override
	public void turnCCW(int degrees) throws IOException {
		if (degrees <= minDegrees) this.controller.sendCommand("ccw " + minDegrees);
		else if (degrees > maxDegrees) {
			this.controller.sendCommand("ccw " + maxDegrees);
			turnCCW(Math.abs(maxDegrees - degrees));
		}
		else this.controller.sendCommand("ccw " + degrees);
	}
	
	/***
	 * 
	 * @param x
	 * @param y
	 * @param z
	 * @param speed
	 */
	public void gotoXYZ(int x, int y, int z, int speed) {
		 // if (getHeight() + z <= 0) 
		
	}
	
	/***
	 * 
	 * @param x
	 * @param y
	 * @param speed
	 * @throws IOException 
	 */
	public void gotoXY(int x, int y, int speed) throws IOException {
		int z = 0;
		y = -y;
		double slope = y/x;
		if (speed > maxSpeed) speed = maxSpeed;
		else if (speed < minSpeed) speed = minSpeed;
		if (x <= maxGoto && x >= minGoto && y <= maxGoto && y >= minGoto) System.out.println(String.format("go %1$d %2$d %3$d %4$d", x, y, z, speed)); //this.controller.sendCommand(String.format("go %1$d %2$d %3$d %4$d", x, y, z, speed));
		else if (x > maxGoto && y <= maxGoto && y >= minGoto) {
			int partialY = (int) Math.round(slope * maxGoto);
			//this.controller.sendCommand(String.format("go %1$d %2$d %3$d %4$d", maxGoto, partialY, z, speed));
			gotoXY(x - maxGoto, y - partialY, speed);
		}
		else if (x < minGoto && y <= maxGoto && y >= minGoto) {
			int partialY = (int) Math.round(slope * minGoto);
			//this.controller.sendCommand(String.format("go %1$d %2$d %3$d %4$d", minGoto, partialY, z, speed));
			gotoXY(x - minGoto, y - partialY, speed);
		}
		else if (y > maxGoto && x <= maxGoto && x >= minGoto) {
			int partialX = (int) Math.round(maxGoto/slope);
			//this.controller.sendCommand(String.format("go %1$d %2$d %3$d %4$d", maxGoto, partialX, z, speed));
			gotoXY(y - maxGoto, x - partialX, speed);
		}
		else if (y < minGoto && x <= maxGoto && x >= minGoto) {
			int partialX = (int) Math.round(minGoto/slope);
			System.out.println(String.format("go %1$d %2$d %3$d %4$d", minGoto, partialX, z, speed));
			//this.controller.sendCommand(String.format("go %1$d %2$d %3$d %4$d", minGoto, partialX, z, speed));
			gotoXY(y - minGoto, x - partialX, speed);
		}
		else if ((x > maxGoto || x < minGoto) && (y > maxGoto || y < minGoto)) {
			
		}
	}
	
	/***
	 * 
	 * @param x
	 * @param y
	 * @param z
	 * @param speed
	 * @param ID
	 * @throws IOException
	 */
	public void gotoMissionPadXYZ(int x, int y, int z, int speed, String ID) throws IOException {
		// TODO Implement in future release
	}
	
	/***
	 * 
	 * @param x
	 * @param y
	 * @param speed
	 * @param ID
	 * @throws IOException
	 */
	public void gotoMissionPadXYZ(int x, int y, int speed, String ID) throws IOException {
		// TODO Implement in future release
		// int z = 0; 
	}
	
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
	public void flyCurve(int x1, int y1, int z1, int x2, int y2, int z2, int speed) {
		// TODO Implement in future release
	}
	
	@Override
	public void flip(String direction) throws IOException {
		this.controller.sendCommand("flip " + direction);
	}
	
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
	 * @throws IOException
	 */
	public void flyCurveMissionPad(int x1, int y1, int z1, int x2, int y2, int z2, int speed, String ID) throws IOException {
		// TODO Implement in future release
	}

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
	public void jumpMissionPad(int x, int y, int z, int speed, int yaw, String ID1, String ID2) {
		// TODO Implement in future release
	}
	
	/***
	 * using getBattery() to reset watchdog failsafe
	 */
	@Override
	public void hoverInPlace(int seconds) throws InterruptedException, IOException {
		if (seconds > 15) {
			getBattery();
			try {
				TimeUnit.MILLISECONDS.sleep(14970); // less than exactly 15 sec to prevent failsafe landing
			} catch (InterruptedException e) {
				return;
			}
			hoverInPlace(Math.abs(seconds - 15));
		}
		else if (seconds == 15) {
			getBattery();
			try {
				TimeUnit.MILLISECONDS.sleep(14970); // less than exactly 15 sec to prevent failsafe landing
				return;
			} catch (InterruptedException e) {
				return;
			}
		}
		else {
			getBattery();
			try {
				TimeUnit.SECONDS.sleep(seconds);
				return;
			} catch (InterruptedException e) {
				return;
			}
		}
	}
	
	/***
	 * Actual interrupt will determine usefulness in future release
	 * @throws IOException
	 */
	public void stopInPlace() throws IOException {
		this.controller.sendCommand("stop");
	}
	
	/***
	 * EMERGENCY ONLY!!! Shuts down all motors even mid flight
	 * @throws IOException
	 */
	public void emergencyStop() throws IOException {
		this.controller.sendCommand("emergency");
	}
	
	@Override
	public void setSpeed(int speed) throws IOException {
		if (speed <= minSpeed) this.controller.sendCommand("speed " + minSpeed);
		else if (speed > maxSpeed) {
			this.controller.sendCommand("speed " + maxSpeed);
		}
		else this.controller.sendCommand("speed " + speed);
	}

	public double getSpeed() throws NumberFormatException, IOException {
		return Double.parseDouble(this.controller.sendCommand("speed?"));
	}

	@Override
	public int getBattery() throws NumberFormatException, IOException {
		return Integer.parseInt(this.controller.sendCommand("battery?"));
	}
	
	/***
	 * 
	 * @return
	 * @throws IOException
	 */
	public int getTemp() throws IOException {
		String temperature = this.controller.sendCommand("temp?");
		String[] arrayOfStr = temperature.split("~|C", 2);
		int temp1 = Integer.parseInt(arrayOfStr[0]);
		int temp2 = Integer.parseInt(arrayOfStr[1].substring(0, arrayOfStr[1].length() - 1));
		return (temp1 + temp2)/2;
	}
	
	/***
	 * 
	 * @return
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	public double getBarometer() throws NumberFormatException, IOException {
		return Double.parseDouble(this.controller.sendCommand("baro?"));
	}
	
	@Override
	public int getFlightTime() throws IOException {
		String time = this.controller.sendCommand("time?");
		return Integer.parseInt(time.substring(0, time.length() - 1));
	}

	@Override
	public int getHeight() throws IOException {
		String height = this.controller.sendCommand("height?");
		return 10 * Integer.parseInt(height.substring(0, height.length() - 2));
	}

	@Override
	public int getAttitudePitch() throws IOException {
		String attitude = this.controller.sendCommand("attitude?");
		String[] arrayOfStr = attitude.split(":|;", 7);
		return Integer.parseInt(arrayOfStr[1]);
	}

	@Override
	public int getAttitudeRoll() throws IOException {
		String attitude = this.controller.sendCommand("attitude?");
		String[] arrayOfStr = attitude.split(":|;", 7);
		return Integer.parseInt(arrayOfStr[3]);
	}

	@Override
	public int getAttitudeYaw() throws IOException {
		String attitude = this.controller.sendCommand("attitude?");
		String[] arrayOfStr = attitude.split(":|;", 7);
		return Integer.parseInt(arrayOfStr[5]);
	}

	@Override
	public double getAccelerationX() throws IOException {
		String acceleration = this.controller.sendCommand("acceleration?");
		String[] arrayOfStr = acceleration.split(":|;", 7);
		return Double.parseDouble(arrayOfStr[1]);
	}
	
	@Override
	public double getAccelerationY() throws IOException {
		String acceleration = this.controller.sendCommand("acceleration?");
		String[] arrayOfStr = acceleration.split(":|;", 7);
		return Double.parseDouble(arrayOfStr[3]);
	}

	@Override
	public double getAccelerationZ() throws IOException {
		String acceleration = this.controller.sendCommand("acceleration?");
		String[] arrayOfStr = acceleration.split(":|;", 7);
		return Double.parseDouble(arrayOfStr[5]);
	}

	@Override
	public int getTOF() throws IOException {
		String timeOfFlight = this.controller.sendCommand("height?");
		return Integer.parseInt(timeOfFlight.substring(0, timeOfFlight.length() - 2));
	}
	
	/***
	 * 
	 * @return
	 * @throws IOException
	 */
	public String getWIFI() throws IOException {
		return this.controller.sendCommand("wifi?");
	}

	/***
	 * 
	 * @return
	 * @throws IOException
	 */
	public String getVersionSDK() throws IOException {
		return this.controller.sendCommand("sdk?");
	}

	/***
	 * 
	 * @return
	 * @throws IOException
	 */
	public String getSerialNumber() throws IOException {
		return this.controller.sendCommand("sn?");
	}
	
	/***
	 * 
	 * @param args
	 * @throws IOException
	 */
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
