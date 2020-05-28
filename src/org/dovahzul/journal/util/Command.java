package org.dovahzul.journal.util;

public class Command {
	
	CommandType type;
	double price = 0;
	int size = 0;
	
	Command(){
		
	}
	Command(CommandType type, int s, int p) {
		this.type = type;
		this.size = s;
		this.price = p;
	}

}
