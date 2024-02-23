package server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Server extends Connection { //Extends connection so we can use the sockets
	ArrayList<Seat> seats=new ArrayList<Seat>();
    char[] col= {'A','B','C','D'};
    private Semaphore sem=new Semaphore(1);
    
	public Server() throws IOException {
    	super("server");
    	for(int i=0;i<4;i++)
    		for(char cha: this.col)
    			seats.add(new Seat(i+1,cha));
    }

    public void startServer() {//Method to start the server
        try {
        	System.out.println("Waiting..."); //Waiting for a connection
        	int i=0;	            
        	while(true) {
	            cs = ss.accept(); //Accept starts the socket and waits for a connection from a client
	            ServerThread serv =new ServerThread(i,this.cs,this.seats,sem);
	            serv.start();
	            i++;
        	}
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    

}