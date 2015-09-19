
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    SocketClientHandler h;
    String cmd;
    
    ExecuteCommand(SocketClientHandler g, String command) {
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

        try {
            //System.out.println(output);
            String w = output.toString();
            //System.out.println("EC OUTPUT: " + w);
            h.sendOutput(w);
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(ExecuteCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
