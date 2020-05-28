package org.dovahzul.journal.util;

import java.util.Arrays;
import java.util.List;

/**
 * Proceeds not quite efficiently formatted string into Command object.
 * @author dovahzul
 *
 */
public class CommandProcessor {

	/**
	 *  samles:
		u,<price>,<size>,bid
		u,<price>,<size>,ask
		q,best_bid
		q,best_ask
		q,size, <price>
		o,buy, <size>  (for minimum price)
		o,sell,<size>  (for maximum price)
	 * @param rawLine
	 */
	public static Command createCommand(String rawLine) {
		
		Command command = new Command();
		
		String requestedOperation = "";
		CommandType type  = CommandType.values()[0]; // ignore command if UNDEFINED
		int size = -1; // ignore when -1
		double price = -1; // ignore when -1
		
		List<String> rawValues = Arrays.asList(rawLine.split(","));
		switch(rawValues.get(0)) {
		case "u":
				requestedOperation = rawValues.get(3);
				switch(requestedOperation) {
				case "bid":
					type = CommandType.UPDATE_BID;
					price = Integer.parseInt(rawValues.get(1));
					size = Integer.parseInt(rawValues.get(2));
					break;
				case "ask":
					type = CommandType.UPDATE_ASK;
					price = Integer.parseInt(rawValues.get(1));
					size = Integer.parseInt(rawValues.get(2));
					break;
				default: System.out.println("CommandProcessor: unable to parse line: " + rawLine);
				}
			break;
		case "q":
				requestedOperation = rawValues.get(1);
				switch(requestedOperation) {
				case "best_bid":
					type = CommandType.SHOW_BEST_BID;
					break;
				case "best_ask":
					type = CommandType.SHOW_BEST_ASK;
					break;
				case "size":
					type = CommandType.SHOW_SIZE_BY_PRICE;
					price = Integer.parseInt(rawValues.get(2));
					break;
				default: System.out.println("CommandProcessor: unable to parse line: " + rawLine);
				}
			break;
		case "o":
				requestedOperation = rawValues.get(1);
				switch(requestedOperation){
				case "buy":
					type = CommandType.BUY;
					//price = -1; // by minimum price
					size = Integer.parseInt(rawValues.get(2));
					break;
				case "sell":
					type = CommandType.SELL;
					//price = -1 ; // by maximum price
					size =  Integer.parseInt(rawValues.get(2));
					break;
				default: System.out.println("CommandProcessor: unable to parse line: " + rawLine);
				}
			break;
		default: System.out.println("CommandProcessor: unable to parse line: " + rawLine);
		}

		
		command.setType(type);
		command.setPrice(price);
		command.setSize(size);
		
		return command;
	}
	
}
