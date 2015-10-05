
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
    String LogLevel;
    private final static Logger LOG = Logger.getLogger(SocketServer.class.getName());
    ExecuteCommand(SocketClientHandler g, String command, String LogLevel) {
        h = g;
        cmd = command;
        this.LogLevel = LogLevel;
    }
    
    @Override
    public void run() {
        
        if(LogLevel == null)
        {
            LOG.setLevel(Level.ALL);
        }
        else if (LogLevel == "INFO")
        {
            LOG.setLevel(Level.INFO);
        }
        else if (LogLevel == "SEVERE")
        {
            LOG.setLevel(Level.SEVERE);
        }
        else
        {
            LOG.setLevel(Level.ALL);
        }
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
                       
		} catch (IOException | InterruptedException e) {
		}

        try {
            LOG.log(Level.SEVERE,"Failed to process request");
            h.sendOutput(output.toString());
        } catch (IOException | InterruptedException ex) {
            // severe log - failed to process request
            //Logger.getLogger(ExecuteCommand.class.getName()).log(Level.SEVERE, null, ex);
            LOG.log(Level.SEVERE,"Failed to process request");
            //LOG.log(Level.WARNING,"This is a warning");
        }
        
  
    }
    
}
