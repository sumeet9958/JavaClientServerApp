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
        
	public SocketClientHandler(Socket client) {
		this.client = client;
	}

	@Override
	public void run() {
            
            /*try {
                DataInputStream isData = new DataInputStream(new DataInputStream(client.getInputStream()));
            } catch (IOException ex) {
                Logger.getLogger(SocketClientHandler.class.getName()).log(Level.SEVERE, null, ex);
            }*/
		
		System.out.println("Thread started with name:"+Thread.currentThread().getName());
                   
                    
                    while(true){
                        try {
                            readResponse();
                        } catch (IOException | InterruptedException ex) {
                            Logger.getLogger(SocketClientHandler.class.getName()).log(Level.SEVERE, null, ex);
                        }
                   
                    }
        }      
        private void readResponse() throws IOException, InterruptedException {
		String userInput;
		DataInputStream stdIn = new DataInputStream(new DataInputStream(client.getInputStream()));
		userInput = stdIn.readUTF();
                //stdIn.reset();
		System.out.println("REQUEST TO EXECUTE SYSTEM COMMAND");
                ExecuteCommand execute = new ExecuteCommand(this,userInput); 
                execute.start();
		
	}
                
        void sendOutput(String output) throws IOException, InterruptedException {
                DataOutputStream out2Client = new DataOutputStream(new DataOutputStream(client.getOutputStream()));
                out2Client.writeUTF(output);
                out2Client.flush();
                //System.out.println(output);
                //out2Client.wait();
                //client.close();
        }
}
        
        