package org.dovahzul.journal.util;

import java.util.HashMap;

public class DataMaster{
	
	private HashMap<Integer, Double> stock;
	private static InputProcessor in;
	private static OutputProcessor out;
	
	public void DataMaster() {
		

	
	
	}

	public static void main(String[] args) {
		
		in = new InputProcessor("./data/liveinput.txt");
		out = new OutputProcessor();
		in.run();
	}


}
