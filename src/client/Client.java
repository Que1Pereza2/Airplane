/**
 * For the moment I will keep this class just because it kinda serves the 
 * purpose of an intermediary
 */

package client;

import java.io.IOException;


public class Client extends Connection {
    private String id;
	public Client(String id) throws IOException {
    	super("client");
    	this.id=id;
    }

    public void startClient() {
    	
//    	int i=0;	            
//    	while(i<clients) {
        ClientThread client =new ClientThread(this.cs,id);
        client.start();
//        i++;
//    	}
    }
}