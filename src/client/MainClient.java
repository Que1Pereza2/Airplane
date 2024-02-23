/* 
 	You need to create a program that simulates plane ticket booking
We suppose that the airplane has 4 rows of 4 seats each, the rows are assigned 
a number between 1-4 to identify them, and the seats are assigned a letter 
from A-D as an identifier. For example the seat 2C is on the second row and is
 the third seat.
You need to create a server-client program in which various clients connect to
 the server in order to book as many seats as they can, one by one following:
	
	- The client, once connected to the server will send the first message 
"START BOOKING:CLIENT 1" where "CLIENT 1" is the identity of the client in 
question(it's name). The server processes internally this request and responds
 to the client with the message "WELCOME TO THE SERVICE".
	
	- The client decides at a local level which seat it wants to reserve 
(let's suppose 2C) and it sends the server the request "BOOK:2C".
	With the received information the client can decide what seat to try to 
book next. Requesting to the server using the same syntax.
	
	- The client will book seats while there are available according to the 
pattern previously mentioned. Once the plane is full the server will respond to
all the clients with "FLIGHT COMPLETE". In that moment the client will stop and
 print the total seats it booked.
	
	- Every client is a while loop that tries to book as many seats as 
possible, one by one, according to the described pattern.
 */
package client;

import java.io.IOException;

//Main Class for client
public class MainClient {
//	private static final int CLIENTS=4;
	public static void main(String id) throws IOException {
		Client cli = new Client(id); //Creates the client
	
	    System.out.println("Starting clients\n");
	    cli.startClient(); //Starts the client
	}
}