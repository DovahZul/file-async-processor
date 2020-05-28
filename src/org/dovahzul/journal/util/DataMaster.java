package org.dovahzul.journal.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * 
 * @author dovahzul
 *
 */
public class DataMaster implements Runnable{
	
	private static HashMap<Double, Integer> buyStockRequests; // by client
	private static HashMap<Double, Integer> sellStockRequests; // by seller
	
	private static InputProcessor in;
	private static OutputProcessor out;
	
	public  static Queue<Command> commands = new LinkedList<Command>();
	public  static Queue<String> rawLogs = new LinkedList<String>();
	
	
	public static void main(String[] args) {


		
		buyStockRequests = new HashMap<Double, Integer>(); // by client
		sellStockRequests = new HashMap<Double, Integer>(); // by seller
		//inOrigin = new InputProcessor(commands, "./data/commands.txt");
		//outOrigin = new OutputProcessor(rawLogs);
		in = new InputProcessor(commands, "./data/commands.txt");
		out = new OutputProcessor(rawLogs);
		
		in.start();
		out.start(); 
		System.out.println("TEST");
		
		while(in.isReading() || !commands.isEmpty()) {
			
			//executeToConsole(commands.poll(), buyStockRequests, sellStockRequests);
			execute(commands.poll(), buyStockRequests, sellStockRequests);
			
			}
		
		/*
		  	u,9,1,bid
			u,11,5,ask
			q,best_bid
			u,10,2,bid
			q,best_bid
			o,sell,1
			q,size,10
			<--------------->
			9,1
			10,2
			1
		 */
		
		
		System.out.println("Work is finished!");
		rawLogs.add("Get some words");
		rawLogs.add("Get some words");
		rawLogs.add("Get some words");
		rawLogs.add("Get some words");
		rawLogs.add("Get some words");
		
		out.setReleased();
	}
	
	public static void printCommand(Command c) {
		System.out.println(c.type);
		System.out.println(c.price);
		System.out.println(c.size);
		System.out.println("---------------");
	}
	
	@Deprecated
	public static void executeToConsole(Command command, HashMap<Double, Integer> buyRequests, HashMap<Double, Integer> sellRequests) {

		if(command == null) return;
		System.out.println("new command...");
		
		switch(command.type) {
		case UNDEFINED:
			System.out.println("UNDEFINED command, something went wrong.");
			break;
		case UPDATE_BID:
			printCommand(command);
			buyRequests.put(command.price, command.size);
			break;
		case UPDATE_ASK:
			printCommand(command);
			sellRequests.put(command.price, command.size);
			break;
		case SHOW_BEST_BID: // highest price from investor
			printCommand(command);
			System.out.println("SHOW_BEST_BID: " + Collections.max(buyRequests.entrySet(), Map.Entry.comparingByKey()));
			break;
		case SHOW_BEST_ASK: // lowest offer price from seller
			printCommand(command);
			System.out.println("SHOW_BEST_ASK: " + Collections.min(sellRequests.entrySet(), Map.Entry.comparingByKey()));
			break;
		case SHOW_SIZE_BY_PRICE:
			printCommand(command);
			System.out.println("SHOW_SIZE_BY_PRICE: " + buyRequests.get(command.price));
			break;
		case BUY: // buy count of stuff from sellRequests
			printCommand(command);
			double minPrice = Collections.max(sellRequests.entrySet(), Map.Entry.comparingByKey()).getKey();
			sellRequests.put(minPrice, sellRequests.get(minPrice) - command.size);
			break;
		case SELL: // sell count of stuff from buyRequests
			printCommand(command);
			double maxPrice = Collections.max(buyRequests.entrySet(), Map.Entry.comparingByKey()).getKey();
			buyRequests.put(maxPrice, buyRequests.get(maxPrice) - command.size);
			break;
		default:
			break;
		}
	
	}
	
	
	public static void execute(Command command, HashMap<Double, Integer> buyRequests, HashMap<Double, Integer> sellRequests) {

		if(command == null) return;
		System.out.println("new command...");
		
		switch(command.type) {
		case UNDEFINED:
			System.out.println("UNDEFINED command, something went wrong.");
			break;
		case UPDATE_BID:
			printCommand(command);
			buyRequests.put(command.price, command.size);
			break;
		case UPDATE_ASK:
			printCommand(command);
			sellRequests.put(command.price, command.size);
			break;
		case SHOW_BEST_BID: // highest price from investor
			printCommand(command);
			double highestPrice = Collections.max(buyRequests.entrySet(), Map.Entry.comparingByKey()).getKey();
			rawLogs.add((int)highestPrice + "," + buyRequests.get(highestPrice));
			break;
		case SHOW_BEST_ASK: // lowest offer price from seller
			printCommand(command);
			double lowestPrice = Collections.min(sellRequests.entrySet(), Map.Entry.comparingByKey()).getKey();
			rawLogs.add((int)lowestPrice + "," + sellRequests.get(lowestPrice));
			break;
		case SHOW_SIZE_BY_PRICE:
			printCommand(command);
			rawLogs.add(buyRequests.get(command.price).toString());
			break;
		case BUY: // buy count of stuff from sellRequests
			printCommand(command);
			double minPrice = Collections.max(sellRequests.entrySet(), Map.Entry.comparingByKey()).getKey();
			sellRequests.put(minPrice, sellRequests.get(minPrice) - command.size);
			break;
		case SELL: // sell count of stuff from buyRequests
			printCommand(command);
			double maxPrice = Collections.max(buyRequests.entrySet(), Map.Entry.comparingByKey()).getKey();
			buyRequests.put(maxPrice, buyRequests.get(maxPrice) - command.size);
			break;
		default:
			break;
		}
	
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}


}
