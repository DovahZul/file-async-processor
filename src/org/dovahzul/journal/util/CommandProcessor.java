package org.dovahzul.journal.util;

import java.util.Arrays;
import java.util.List;

/**
 * 
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
			
			List<String> rawValues = Arrays.asList(rawLine.split(","));
			switch(rawValues.get(0)) {
			case "u":
				break;
			case "o":
				break;
			case "q":
				break;
			default: System.out.println("CommandProcessor: unable to parse line: " + rawLine);
			}
			
			CommandType type  = CommandType.values()[0];
			int size = 0;
			int price = 0;
			
			Command command = new Command(type, price, size);
			
			
			return command;
		}
		
}
