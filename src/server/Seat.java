package server;

public class Seat {
	

	private String id;
	private boolean taken=false;
	
	public Seat(int row,char column) {
		String id=String.valueOf(row)+column;
		this.id=id;
	}
	
	public void bookSeat() {
		if(!taken)
			taken=true;
	}
	
	
	
	
	
//	Getters and setters

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public boolean isTaken() {
		return taken;
	}

	public void setTaken(boolean taken) {
		this.taken = taken;
	}

}
