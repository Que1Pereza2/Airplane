/*
	You need to create a program that simulates plane ticket booking
We suppose that the airplane has 4 rows of 4 seats each, the rows are assigned 
a number between 1-4 to identify them, and the seats are assigned a letter 
from A-D as an identifier. For example the seat 2C is on the second row and is
the third seat.

	The server will receive this request and respond to the client with the 
message "BOOKED:2C" if the seat hasn't been booked yet, if the seat is already
booked the server will inform the client that the seat is booked and It will 
contain the current status of the plane in the following manner: 
"SEAT BOOKED:XXXL-LXXL-XXXX-LLLL". That response will inform the client that 
the seat "2C" is booked (X) and that the seats that are still available(L) are
"1D","2A","2D" and the entirety of the fourth row. Keep in mind that the state
of the plane is codified using a string of characters("X" for booked and "L" 
for available) and the information for every row it's separated by "-" (the 
rows are shown in numerical order 1 to 4). 

	Every new client that connects to the server will be managed by a new 
thread which will respond to it's booking requests.
	Once the plane is full the server will respond to all the clients with 
"FLIGHT COMPLETE".
	TODO:make a global main class since the clients are threads and I need an
array to store them.
 */
package server;

import java.io.IOException;

//Main class of server
public class MainServer {
  public static void main(String[] args) throws IOException {
      Server serv = new Server(); //Create the server

      System.out.println("Starting server\n");
      serv.startServer(); //Start the server
  }
}