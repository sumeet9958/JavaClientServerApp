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
import java.net.UnknownHostException;

/**
 * A Simple Socket client that connects to our socket server
 *
 */
public class SocketClient {

    private String hostname;
    private int port;
    Socket socketClient;
    GUI g;
    public SocketClient(String hostname, int port){
        this.hostname = hostname;
        this.port = port;
    }

    public void connect() throws UnknownHostException, IOException{
        System.out.println("Attempting to connect to "+hostname+":"+port);
        socketClient = new Socket(hostname,port);
        System.out.println("Connection Established");
    }
    
    public void sendCommand(String cmd) throws IOException{
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
        writer.write(cmd);
        writer.newLine();
        //System.out.println(writer.toString());
        //System.out.println(cmd);
        //writer.flush();
        
    }
    
    public void readResponse() throws IOException{
        String userInput;
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
        System.out.println(stdIn);
        System.out.print("RESPONSE FROM SERVER:");
        while ((userInput = stdIn.readLine()) != null) {
            //System.out.println(userInput);
            g.Receive(userInput);
        }
    }
    
}