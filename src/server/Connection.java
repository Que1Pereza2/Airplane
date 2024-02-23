package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Connection {
    private final int PORT = 1234; //Connection Port
    private final String HOST = "localhost"; //Connection Host
    protected ServerSocket ss; //Server Socket
    protected Socket cs; //Client Socket
    
    public Connection(String type) throws IOException {//Constructor
        if(type.equalsIgnoreCase("server")) {
            ss = new ServerSocket(PORT);//Server socket on port 1234
            //cs = new Socket(); //Client Socket
        } else {
            cs = new Socket(HOST, PORT); //Client Socket at localhost on port 1234
        }
    }
}