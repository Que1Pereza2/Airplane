package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class ServerThread extends Thread{
	
	private int id;
	private Socket cs;
	private ArrayList<Seat> seats;
	private boolean notComplete=true;
	private Semaphore sem;
	
	public ServerThread(int id,Socket cs,ArrayList<Seat> seats,Semaphore sem) {
		
		this.id=id;
		this.cs=cs;
		this.seats=seats;
		this.sem=sem;
	}
	
	@Override
	public void run() {
	
		try {
		
			System.out.println("Client online");
	        DataInputStream in = new DataInputStream(cs.getInputStream());
	        DataOutputStream out = new DataOutputStream(cs.getOutputStream());
	        System.out.println(in.readUTF());
	        
			out.writeUTF("WELCOME TO THE SERVICE");
			
	        while(notComplete) {

	        	complete();
	    
	        	String message = in.readUTF();
	        	String[] ani = message.split(":");
	            System.out.println("Recieved message -> " + message + " by Airplane" + id);
	            
	            if(this.notComplete)
	            	out.writeUTF(status(ani[0], ani[1]));
	            else
	            	out.writeUTF("FLIGHT COMPLETE");
	        }
	       	
	        cs.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public String status(String action,String seatId) {
		
		String response="";

		try {	
			sem.acquire();
			
			for(Seat seat:seats)
				if(seat.getId().equalsIgnoreCase(seatId))
					if(seat.isTaken())
						response=returnMessage();
			
					else {
						seat.bookSeat();
						response="BOOKED:"+seatId;
					}
		} catch (InterruptedException e) {e.printStackTrace();}
		
		sem.release();
		return response;
	}

	private String returnMessage() {
		
		String response="SEAT BOOKED:";
		int separator=0;
		
		for(Seat seat:this.seats) {
		
			separator++;
				
			if(!seat.isTaken())
				response+="L";
			else
				response+="X";
			
			if(seat!=this.seats.get(this.seats.size()-1))
				if(separator==4) {
					separator=0;
					response+="-";
				}
		}
		return response;
	}
	
	private void complete() {
		int check=0;
		for(Seat seat:this.seats)
			if(seat.isTaken())
				check++;
		if(check==seats.size())
			notComplete=false;
	}
    
}
