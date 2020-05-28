package org.dovahzul.journal.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;

public class InputProcessor implements Runnable {


	private final String DEFAULT_INPUT_PATH = "./data/input.txt";
	private String inputPath;
	private Queue<Command> commands;
	
	//public ArrayList<String> rawIn = new ArrayList<String>();

	private BufferedReader inputDataStream;
	public String strIn;

	public InputProcessor(Queue<Command> val) {
		this.inputPath = DEFAULT_INPUT_PATH;
		this.commands =  val;
	}
	
	public InputProcessor(String filePath) {
		this.inputPath = filePath != null ? filePath : DEFAULT_INPUT_PATH;
	}
	

	
	@Override
	public void run() {
		
		System.out.println("target file path: " + inputPath);
		try {
			inputDataStream = new BufferedReader(new FileReader(inputPath));
		} catch (FileNotFoundException e) {
			System.out.println(this.getClass() + " Input file not found!");
			Thread.currentThread().interrupt();
			e.printStackTrace();
		}
		
        String line;
        try {
			while ((line = inputDataStream.readLine()) != null) {
				//rawIn.add(line);
				strIn = line;
				while(strIn != "") {
					System.out.println("Line readed, waiting for empty line.... zzz");
					Thread.currentThread().wait();
				}
				
			}
		} catch (IOException e) {
			System.out.println(this.getClass() + " IO error");
			e.printStackTrace();
		} catch (InterruptedException e) {
			System.out.println(this.getClass() + " Interrupted!");
			e.printStackTrace();
		}
		
        System.out.println(this.getClass() + " Input finished.");
	}

}
