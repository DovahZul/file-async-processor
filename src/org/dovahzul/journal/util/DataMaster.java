package org.dovahzul.journal.util;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class DataMaster{
	
	private HashMap<Double, Integer> buyStockRequests;
	private HashMap<Double, Integer> sellStockRequests;
	
	private static InputProcessor in;
	private static OutputProcessor out;
	
	public static Queue<Command> commands = new LinkedList<Command>();
	
	
	public static void main(String[] args) {

		//test queue
		/*
		Command c1 = new Command(CommandType.A, 10, 10);
		Command c2 = new Command(CommandType.B, 20, 20);
		Command c3 = new Command(CommandType.S, 30, 30);
		commands.add(c1);
		commands.add(c2);
		commands.add(c3);
		
		commands.poll();
		for(Command c : commands) {
			System.out.println(c.type);
		}
		System.out.println("++++++++");
		commands.poll();
		for(Command c : commands) {
			System.out.println(c.type);
		}
		commands.poll();
		System.out.println("++++++++");
		for(Command c : commands) {
			System.out.println(c.type);
		}
		*/
		
		in = new InputProcessor(commands, "./data/commands.txt");
		//out = new OutputProcessor();
		in.run();
		
		/*
		 * u,9,1,bid
u,11,5,ask
q,best_bid
u,10,2,bid
q,best_bid
o,sell,1
q,size,10
		 */
		
		
		
		for(Command c:commands) {
			System.out.println(c.type);
			System.out.println(c.price);
			System.out.println(c.size);
			System.out.println("---------------");
		}
		//out.run();
		//out.strOut = in.strIn;
	}


}
