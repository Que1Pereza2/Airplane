package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;
//import java.util.Scanner;

import server.Seat;



public class ClientThread extends Thread{
	Socket cs;
	String id;
	Random random=new Random();
	ArrayList<Seat> seats=new ArrayList<Seat>();
	private boolean notComplete=true;
	private int bookedSeats;
	private ArrayList<String> availableSeats=new ArrayList<String>();
	char[] col= {'A','B','C','D'};
	
	public ClientThread(Socket cs,String id) {
		this.cs=cs;
		this.id=id;
		for(int i=0;i<4;i++)
    		for(char cha: this.col) {
    			
    			seats.add(new Seat(i+1,cha));
    			String sid=String.valueOf(i+1)+cha;
    			availableSeats.add(sid);
    		}
		}
	
	public void run() {
		try {
		DataInputStream in = new DataInputStream(cs.getInputStream());
		DataOutputStream out = new DataOutputStream(cs.getOutputStream());
		
		out.writeUTF("START BOOKING:CLIENT"+this.id);
		String message = in.readUTF();
        System.out.println(message);
        
			while(notComplete) {
				
				System.out.println("Insert the seat you want to book:");
//				Scanner miauScanner=new Scanner(System.in);
//    			System.out.println("miau");
//    			miauScanner.nextLine();
				if (!this.availableSeats.isEmpty()) {
					int nSeat=random.nextInt(0, this.availableSeats.size());					
					System.out.println(this.availableSeats.get(nSeat));
					out.writeUTF("BOOK:"+availableSeats.get(nSeat));
				
				}else out.writeUTF("2C");
				
				
				String response=in.readUTF();
				System.out.println(response);
				
				action(response);
				
				if (!notComplete)
					break;
			}
        
        System.out.println("Client"+this.id+" has booked "+this.bookedSeats+" seats.Hurray!! O~O");
		cs.close();
		}catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
	
	private void action(String message){
		String[] content = message.split(":");
		char[] seats=new char[this.seats.size()];
		if (content.length>1) {
			seats=content[1].toCharArray();
		}
		
		switch (content[0]) {
			case "SEAT BOOKED": {
				seats=cleanMessage(seats);
				for(int i=0;i<seats.length;i++) {
					if(seats[i]=='X') {
						this.seats.get(i).bookSeat();
						for(int j=0;j<availableSeats.size();j++)
							if(availableSeats.get(j).equalsIgnoreCase(this.seats.get(i).getId()))
								this.availableSeats.remove(j);
					}
				}		
				break;
			}
			
			case "BOOKED": {
				bookedSeats++;
				for(int i=0;i<this.seats.size();i++)
					if(this.seats.get(i).getId().equalsIgnoreCase(content[1])) {
						this.seats.get(i).bookSeat();
						for(int j=0;j<availableSeats.size();j++)
							if(this.availableSeats.get(j).equalsIgnoreCase(content[1]))
								this.availableSeats.remove(j);
					}
				break;
			}
			
			case "FLIGHT COMPLETE": {
				this.notComplete=false;
			}
		}
	}

	private char[] cleanMessage(char[] seats) {
		
		char[] clean =new char[this.seats.size()];
		for(int i=0,j=0;i<seats.length;i++)
			if (seats[i]!='-') {
				clean[j]=seats[i];
				j++;
			}
		return clean;
	}
}
