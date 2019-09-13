
import java.io.*;
import java.util.Scanner;

/***
 * 
 * @author MasterControlProgram
 *
 */
public class TelloJavaToPython {

    public static BufferedReader inp;
    public static BufferedWriter out;
    public static Process p;
    public static String pathToPython = Constants.PYTHON_PATH + " ";
    
    /***
     * 
     * @param s
     */
    public static void print(String s) {
    	System.out.println(s);
    }

    public static String pipe(String msg) {
    	String ret;

    	try {
    		out.write(msg + "\n");
    		out.flush();
    		ret = inp.readLine();
    		while (ret.equals("") || ret.equals("forced stop")) {
    			ret = inp.readLine();
    		}
    		return ret;
    	}
    	catch (Exception err) {

    	}
    	return "error";
    }
    
    /***
     * 
     * @param cmds
     */
    public static void commandDrone(String[] cmds) {

    	for (int i = 0; i < cmds.length; i++) {
    		commandDrone(cmds[i]);
    	}
    }
    
    /***
     * 
     * @param cmd
     * @return
     */
    public static String commandDrone(String cmd) {
    	
    	String call = "";
    	
    	String response = "";
    	
    	call = cmd;
    	print(call);
    	response = pipe(call);
    	print(response);
    	return response;
    }
    
    /***
     * 
     * @param filePath
     */
    public static void launchInitialization(String filePath) {
    	
    	String cmd = pathToPython + filePath;
    	
    	try {

    		print(cmd);
    		print(System.getProperty("user.dir"));
    		print("Tello Demo" + "\n");

    		print("Tello: command takeoff land flip forward back left right" + "\n" + "       up down cw ccw speed speed?" + "\n");

    		print("end -- quit demo" + "\n");
    		
    		p = Runtime.getRuntime().exec(cmd);

    		inp = new BufferedReader( new InputStreamReader(p.getInputStream(), "UTF-8"));
    		out = new BufferedWriter( new OutputStreamWriter(p.getOutputStream()));
    		
    		commandDrone("command");
    	}	
    		
    		
    	catch (Exception err) {
    		err.printStackTrace();
    	}
    }
    
    /***
     * 
     */
    public static void launchCompletion() {
    	
    	try {
    		
    		commandDrone("end");
    		
    		p.destroy();
    		print('\n' + "End Program");
    		inp.close();
    		out.close();
    	}

    	catch (Exception err) {
    		err.printStackTrace();
    	}
    }
    
    /***
     * This is a demonstration program designed to show a subset of possible commands
     * along with the required intial and ending commands required by the Tello SDK
     * and the required ordering of the "takeoff" and "land" messages
     * @param cmds
     * @param filePath
     */
    public static void runProgramArray(String[] cmds, String filePath) {
    	
    	String cmd = pathToPython + filePath;
    	
    	try {

    		print(cmd);
    		print(System.getProperty("user.dir"));
    		print("Tello Demo" + "\n");

    		print("Tello: command takeoff land flip forward back left right" + "\n" + "       up down cw ccw speed speed?" + "\n");

    		print("end -- quit demo" + "\n");
    		
    		p = Runtime.getRuntime().exec(cmd);

    		inp = new BufferedReader( new InputStreamReader(p.getInputStream(), "UTF-8"));
    		out = new BufferedWriter( new OutputStreamWriter(p.getOutputStream()));
    		
    		commandDrone(cmds);
    		
    		p.destroy();
    		print('\n' + "End Program");
    		inp.close();
    		out.close();
    	}

    	catch (Exception err) {
    		err.printStackTrace();
    	}
    }
    
    /***
     * This is a demonstration program that is almost identical in function to the
     * original python script execution but instead using a scanner to pipe strings to the Tello SDK
     * Once running you must enter "command" in the console before you can access the other
     * functions of the Tello SDK
     * @param filePath
     */
    public static void runProgramScanner(String filePath) {
    	
    	String cmd = pathToPython + filePath;
    	
    	try {

    		print(cmd);
    		print(System.getProperty("user.dir"));
    		print("Tello Demo" + "\n");

    		print("Tello: command takeoff land flip forward back left right" + "\n" + "       up down cw ccw speed speed?" + "\n");

    		print("end -- quit demo" + "\n");
    		
    		Process p = Runtime.getRuntime().exec(cmd);

    		inp = new BufferedReader( new InputStreamReader(p.getInputStream(), "UTF-8"));
    		out = new BufferedWriter( new OutputStreamWriter(p.getOutputStream()) );
    		Scanner scan = new Scanner(System.in);
    		
    		String command = scan.nextLine();
    		
    		while(!command.equals("end")) {
    			commandDrone(command);
    			command = scan.nextLine();
    		}
    		
    		commandDrone(command);
    		
    		p.destroy();
    		print('\n' + "End Program");
    		inp.close();
    		out.close();
    		scan.close();
    	}

    	catch (Exception err) {
    		err.printStackTrace();
    	}
    }
    	
    /***
     * 
     * @param args
     */
    public static void main(String[] args) {
    	
    	String filePath = Constants.FILE_PATH_DEVELOPER; 
    	String[] cmds = {"command", "battery?", "takeoff", "ccw 360", "flip b", "land", "end"};
    	
    	runProgramArray(cmds, filePath);
    	//runProgramScanner(filePath);
    	
    	//launchInitialization(filePath); // step 1 (required)
    	//commandDrone("battery?"); // step 2
    	//commandDrone("takeoff"); // step 3
    	//commandDrone("land"); // step 4 (if you did step 2 and you don't do this...)
    	//launchCompletion(); //step 5 (required)
    }
}