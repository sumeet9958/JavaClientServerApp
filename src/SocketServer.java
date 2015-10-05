/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sumeet
 */
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
* A simple socket server
* @author : Sumeet
*
*/
public class SocketServer {
    
    private ServerSocket serverSocket;
    private final int port;
    private final static Logger LOG = Logger.getLogger(SocketServer.class.getName());
    
    public SocketServer(int port) {
        this.port = port;
    }
    
    public void start(String LogLevel) throws IOException {
        
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
        // Informational log about starting the server
        LOG.log(Level.INFO, "Starting the socket server at port:{0}", port);
        
        serverSocket = new ServerSocket(port);
        
        //Listen for clients. Block till one connects
        Socket client = null;
     
        while(true){
        	// Informational log waiting for clients to connect
                LOG.log(Level.INFO,"Waiting for clients...");
        	client = serverSocket.accept();
        	
        	//Info log - A client has connected to this server. Send welcome message
                LOG.log(Level.INFO, "The following client has connected:{0}", client.getInetAddress().getCanonicalHostName());
            
            Thread thread = new Thread(new SocketClientHandler(client,LogLevel));
            thread.start();
                    }
    }
        
    public static void main(String[] args) {
        // Setting a default port number.
        int portNumber = 9000;
        String LogLevel = null;
        
        if( args.length != 0)
            LogLevel = args[0];
        else
            LogLevel = null;
        
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

        try {
            // initializing the Socket Server
                SocketServer socketServer = new SocketServer(portNumber);
                socketServer.start(LogLevel);
                //socketServer.stop();
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            
            
    }
    
}