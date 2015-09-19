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
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SocketClientHandler implements Runnable {

	private Socket client;
        SocketClientHandler g;
        String output = null;
        
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
                        
                    } catch (IOException | InterruptedException ex) {
                        Logger.getLogger(SocketClientHandler.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    //try {
                        
                        //client.close();
                        //try {
                        //System.out.println(output);
                        //sendOutput(output);
                        
                        //} catch (IOException | InterruptedException ex) {
                        //  Logger.getLogger(SocketClientHandler.class.getName()).log(Level.SEVERE, null, ex);
                        //}
                    //} catch (IOException ex) {
                      //  Logger.getLogger(SocketClientHandler.class.getName()).log(Level.SEVERE, null, ex);
                    //}
                    
		} /*catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}*/
	}
        
        private void readResponse() throws IOException, InterruptedException {
		String userInput;
		DataInputStream stdIn = new DataInputStream(new DataInputStream(client.getInputStream()));
                //System.out.println(stdIn.toString());
                //System.out.println(stdIn.readLine());
		userInput = stdIn.readUTF();
			//if(userInput.equals("TIME?")){
				System.out.println("REQUEST TO EXECUTE SYSTEM COMMAND");
                                //userInput = stdIn.readLine();
                                //System.out.println(userInput);
                                //g = new SocketClientHandler(client);
                                ExecuteCommand execute = new ExecuteCommand(this,userInput); 
                                execute.start();
				//sendOutput();
				//break;
		//}
                //System.out.println("hej");
	}
                
        void sendOutput(String output) throws IOException, InterruptedException {
                DataOutputStream out2Client = new DataOutputStream(new DataOutputStream(client.getOutputStream()));
                //System.out.println("SCH :" + output);
                //String o = output.toString();
                out2Client.writeUTF(output);
                //writer.newLine();
                //writer.flush();
                //writer.close();
                
        }
}
        
        