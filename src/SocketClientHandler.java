/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sumeet
 */
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket; 
import java.util.logging.Level;
import java.util.logging.Logger;

public class SocketClientHandler implements Runnable {

	private final Socket client;
        SocketClientHandler g;
        String output = null;
        private final static Logger LOG = Logger.getLogger(SocketClientHandler.class.getName());
        String LogLevel;
	public SocketClientHandler(Socket client, String LogLevel) {
		this.client = client;
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
		// Info log - starting of the thread 
                LOG.log(Level.INFO, "Thread started with name: {0}", Thread.currentThread().getName());
                    while(true){
                        try {
                            readResponse(LogLevel);
                        } catch (IOException | InterruptedException ex) {
                            break;
                        }
                   
                    }
        }      
        private void readResponse(String LogLevel) throws IOException, InterruptedException {
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
                String userInput;
		DataInputStream stdIn = new DataInputStream(new DataInputStream(client.getInputStream()));
		userInput = stdIn.readUTF();

		// Info Log - request received  
                LOG.log(Level.INFO, "REQUEST TO EXECUTE SYSTEM COMMAND");
                ExecuteCommand execute = new ExecuteCommand(this,userInput,LogLevel); 
                execute.start();
		
	}
                
        void sendOutput(String output) throws IOException, InterruptedException {
                DataOutputStream out2Client = new DataOutputStream(new DataOutputStream(client.getOutputStream()));
                out2Client.writeUTF(output);
                out2Client.flush();
        }
}
        
        