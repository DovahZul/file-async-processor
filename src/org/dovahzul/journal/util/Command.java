package org.dovahzul.journal.util;

public class Command {
	
	CommandType type;
	int size = 0;
	int price = 0;
	
	Command(CommandType type, int s, int p) {
		this.type = type;
		this.size = s;
		this.price = p;
	}

}
