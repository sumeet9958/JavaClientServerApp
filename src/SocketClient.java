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
import java.net.UnknownHostException;

/**
 * A Simple Socket client that connects to our socket server
 *
 */
public class SocketClient {

    private String hostname;
    private int port;
    Socket socketClient;
    GUI g = new GUI();
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
        DataOutputStream input2server = new DataOutputStream(new DataOutputStream(socketClient.getOutputStream()));
        input2server.writeUTF(cmd);
        //writer.newLine();
        //System.out.println(writer.toString());
        //System.out.println(cmd);
        //writer.flush();
        //writer.close();
        
    }
    
    public String readResponse() throws IOException{
        String userInput;
         
        //String s = null;
        DataInputStream stdIn = new DataInputStream(new DataInputStream(socketClient.getInputStream()));
        
        //System.out.println(stdIn);
        //System.out.println("RESPONSE FROM SERVER:");
        //System.out.println(stdIn.toString());
        //while((userInput = stdIn.readUTF()) != null) {
            //String ch = userInput;
            //System.out.println(userInput);
        //userInput = stdIn.readLine();
            //userInput.append(stdIn.readLine());
            //if(stdIn.readLine() == null)
                //break;
        userInput = stdIn.readUTF();
        //System.out.println("bwahaha" + userInput);
        //g.Receive(userInput);
        return userInput;
    }
    
}