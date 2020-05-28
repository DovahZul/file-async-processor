package org.dovahzul.journal.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class OutputProcessor implements Runnable {

	private final String DELIMITER = System.getProperty("line.separator"); //"\n";
	private final String DEFAULT_OUPUT_FILE = "./data/output.txt";
	private String outputPath;
	
	private BufferedWriter outputDataStream;
	
	//public ArrayList<String> rawOut = new ArrayList<String>();
	public String strOut;
	
	
	public OutputProcessor() {
		this.outputPath = DEFAULT_OUPUT_FILE;
	}
	public OutputProcessor(String filePath) {
		this.outputPath = filePath != null ? filePath : DEFAULT_OUPUT_FILE;
	}
	
	@Override
	public void run() {
		
		File file = new File(DEFAULT_OUPUT_FILE);
		try {
			outputDataStream = new BufferedWriter(new FileWriter(file));
		} catch (IOException e) {
			System.out.println(this.getClass() + " IO error");
			Thread.currentThread().interrupt();
			e.printStackTrace();
		}
		
		while(!Thread.currentThread().isInterrupted()) {
			try {
				outputDataStream.write(strOut + DELIMITER);
			} catch (IOException e) {
				System.out.println(this.getClass() + " IO error");
				Thread.currentThread().interrupt();
				e.printStackTrace();
			}
		}
	}

}
