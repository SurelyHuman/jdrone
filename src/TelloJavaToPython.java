

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
    		if (ret.equals("") || ret.equals("forced stop")) {
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
    	
    	String call = "";
    	
    	String response = "";
    	
    	for (int i = 0; i < cmds.length; i++) {
    		call = cmds[i];
    		print(call);
    		response = pipe(call);
    		while(response.equals("")){
    			
    		}
    		print(response);
    		response = "";
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
    	while(response.equals("")){

    	}
    	print(response);
    	return response;
    }
    
    /***
     * 
     * @param filePath
     */
    public static void launchInitialization(String filePath) {
    	
    	String cmd = "python " + filePath;
    	
    	try {

    		print(cmd);
    		print(System.getProperty("user.dir"));
    		print("Tello Demo" + "\n");

    		print("Tello: command takeoff land flip forward back left right" + "\n" + "       up down cw ccw speed speed?" + "\n");

    		print("end -- quit demo" + "\n");
    		
    		p = Runtime.getRuntime().exec(cmd);

    		inp = new BufferedReader( new InputStreamReader(p.getInputStream()) );
    		out = new BufferedWriter( new OutputStreamWriter(p.getOutputStream()) );
    		
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
     * 
     * @param cmds
     * @param filePath
     */
    public static void runProgramArray(String[] cmds, String filePath) {
    	
    	String cmd = "python " + filePath;
    	
    	try {

    		print(cmd);
    		print(System.getProperty("user.dir"));
    		print("Tello Demo" + "\n");

    		print("Tello: command takeoff land flip forward back left right" + "\n" + "       up down cw ccw speed speed?" + "\n");

    		print("end -- quit demo" + "\n");
    		
    		p = Runtime.getRuntime().exec(cmd);

    		inp = new BufferedReader( new InputStreamReader(p.getInputStream()) );
    		out = new BufferedWriter( new OutputStreamWriter(p.getOutputStream()) );
    		
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
     * 
     * @param filePath
     */
    public static void runProgramScanner(String filePath) {
    	
    	String cmd = "python " + filePath;
    	
    	try {

    		print(cmd);
    		print(System.getProperty("user.dir"));
    		print("Tello Demo" + "\n");

    		print("Tello: command takeoff land flip forward back left right" + "\n" + "       up down cw ccw speed speed?" + "\n");

    		print("end -- quit demo" + "\n");
    		
    		Process p = Runtime.getRuntime().exec(cmd);

    		inp = new BufferedReader( new InputStreamReader(p.getInputStream()) );
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
    	
    	String filePath = "Tello3ToJava.py"; // enter the filepath to the included file "Tello3ToJava.py"
    	String[] cmds = {"command", "battery?", "takeoff", "ccw 360", "flip b", "land", "end"};
    	
    	runProgramArray(cmds, filePath);
    	//runProgramScanner(filePath);
    	
    	//launchInitialization(filePath); // step 1 (required)
    	//commandDrone("takeoff"); // step 2
    	//commandDrone("land"); // step 3 (if you did step 2 and you don't do this...)
    	//launchCompletion(); //step 4 (required)
    }
}