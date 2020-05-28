package org.dovahzul.journal.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;

public class InputProcessor extends Thread {


	private final String DEFAULT_INPUT_PATH = "./data/input.txt";
	private String inputPath;
	private Queue<Command> commands;
	private boolean isReading = false;
	
	//public ArrayList<String> rawIn = new ArrayList<String>();

	private BufferedReader inputDataStream;
	public String strIn;
	
	public InputProcessor(Queue<Command> val, String file) {
		this.inputPath = file != null ? file : DEFAULT_INPUT_PATH;
		this.commands =  val;
	}
	
	public InputProcessor(Queue<Command> val) {
		this.inputPath = DEFAULT_INPUT_PATH;
		this.commands =  val;
	}
	
	public InputProcessor(String file) {
		this.inputPath = file != null ? file : DEFAULT_INPUT_PATH;
	}
	

	
	@Override
	public void run() {
		
		this.isReading = true;
		
		System.out.println("target file path: " + inputPath);
		try {
			inputDataStream = new BufferedReader(new FileReader(inputPath));
		} catch (FileNotFoundException e) {
			System.out.println(this.getClass() + " Input file not found!");
			Thread.currentThread().interrupt();
			this.isReading = false;
			e.printStackTrace();
		}
		
        String line;
        try {
			while ((line = inputDataStream.readLine()) != null) {
				synchronized(commands) {
					this.commands.add(CommandProcessor.createCommand(line));	
				}
			}
			inputDataStream.close();
		} catch (IOException e) {
			System.out.println(this.getClass() + " IO error");
			this.isReading = false;
			e.printStackTrace();
		}
		
        System.out.println(this.getClass() + " Input finished.");
        this.isReading = false;
	}

	public boolean isReading() {
		return isReading;
	}

}
