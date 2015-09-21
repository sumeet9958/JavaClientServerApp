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
        input2server.flush();
        //System.out.println(cmd);
    }
    
    public String readResponse() throws IOException{
        String userInput;
        DataInputStream stdIn = new DataInputStream(new DataInputStream(socketClient.getInputStream()));
        userInput = stdIn.readUTF();
        //stdIn.reset();
        //System.out.println(userInput);
        return userInput;
    }

    void close() throws IOException {
         socketClient.close();
    }    
        
    
}