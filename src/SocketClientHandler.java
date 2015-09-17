/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sumeet
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SocketClientHandler implements Runnable {

	private Socket client;
        SocketClientHandler g;
        StringBuffer output = null;
        
	public SocketClientHandler(Socket client) {
		this.client = client;
	}

	@Override
	public void run() {
		//try 
                {
			System.out.println("Thread started with name:"+Thread.currentThread().getName());
                    try {
                        readResponse();
                        sendOutput(output);
                    } catch (IOException | InterruptedException ex) {
                        Logger.getLogger(SocketClientHandler.class.getName()).log(Level.SEVERE, null, ex);
                    }
		} /*catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}*/
	}
        
        private void readResponse() throws IOException, InterruptedException {
		String userInput;
		BufferedReader stdIn = new BufferedReader(new InputStreamReader(client.getInputStream()));
                System.out.println("hej");
                //System.out.println(stdIn.readLine());
		while ((userInput = stdIn.readLine()) != null) {
			//if(userInput.equals("TIME?")){
				System.out.println("REQUEST TO EXECUTE SYSTEM COMMAND");
                                userInput = stdIn.readLine();
                                System.out.println(userInput);
                                ExecuteCommand execute = new ExecuteCommand(g,userInput); 
                                execute.start();
				//sendOutput();
				break;
			}
			
		//}
                System.out.println("hej");
	}
                
        void sendOutput(StringBuffer output) throws IOException, InterruptedException {
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
                writer.write(output.toString());
        }
}
        
        