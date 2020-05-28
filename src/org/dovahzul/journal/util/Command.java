package org.dovahzul.journal.util;

/**
 * 
 * @author dovahzul
 *
 */
public class Command {
	
	private CommandType type;
	private double price = 0;
	private int size = 0;
	
	Command(){
		
	}
	
	public CommandType getType() {
		return this.type;
	}
	public void setType(CommandType type) {
		this.type = type;
	}
	public double getPrice() {
		return this.price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getSize() {
		return this.size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	
	
	Command(CommandType type, int s, int p) {
		this.type = type;
		this.size = s;
		this.price = p;
	}

}
