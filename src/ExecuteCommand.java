
import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sumeet
 */
public class ExecuteCommand extends Thread{

    GUI h;
    String cmd;
    
    ExecuteCommand(GUI g, String command) {
        h = g;
        cmd = command;
    }
    
    @Override
    public void run() {
        StringBuffer output = new StringBuffer();
        BufferedReader stdInput = null;
        BufferedReader stdError = null;
		Process p;
		try {
			p = Runtime.getRuntime().exec(new String[]{"bash","-c",cmd});
			p.waitFor();
			 stdInput = 
                            new BufferedReader(new InputStreamReader(p.getInputStream()));
                         stdError = 
                            new BufferedReader(new InputStreamReader(p.getErrorStream()));
                        String line = "";			
			while ((line = stdInput.readLine())!= null || (line = stdError.readLine())!= null) {
				output.append(line + "\n");
                        }
                       
		} catch (Exception e) {
			e.printStackTrace();
		}

		h.Receive(output);
    }
    
}
