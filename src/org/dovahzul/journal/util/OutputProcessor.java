package org.dovahzul.journal.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Queue;

/**
 * 
 * @author dovahzul
 *
 */
public class OutputProcessor extends Thread {

	private final String DELIMITER = System.getProperty("line.separator"); //"\n";
	private final String DEFAULT_OUPUT_FILE = "./data/output.txt";
	private String outputPath;
	
	private BufferedWriter outputDataStream;
	
	//public ArrayList<String> rawOut = new ArrayList<String>();
	private Queue<String> rawLogs;
	private boolean released = true;
	
	
	public OutputProcessor(Queue<String> rawLogs, String filePath) {
		this.outputPath = filePath != null ? filePath : DEFAULT_OUPUT_FILE;
		this.rawLogs = rawLogs;
	}
	
	public OutputProcessor(Queue<String> rawLogs) {
		this.outputPath = DEFAULT_OUPUT_FILE;
		this.rawLogs = rawLogs;
	}
	
	public boolean isReleased() {
		return this.released;
	}
	/**
	 * Callback to finalize logging & stop.
	 */
	public void setReleased() {
		this.released = true;
		System.out.println(this.getClass() + " I am free! ^_^");
	}
	
	@Override
	public void run() {
		this.released = false;
		
		File file = new File(DEFAULT_OUPUT_FILE);
		try {
			
			outputDataStream = new BufferedWriter(new FileWriter(file));
		} catch (IOException e) {
			System.out.println(this.getClass() + " IO error");
			Thread.currentThread().interrupt();
			e.printStackTrace();
		}
		String line;
		synchronized(rawLogs) {	
			try {
				while( (line = rawLogs.poll()) != null || !this.isReleased()) {

						if(line == null) continue;
						outputDataStream.append(line + DELIMITER);	
						System.out.println(line);
					}
				
				outputDataStream.close();
			} catch (IOException e) {
				System.out.println(this.getClass() + " IO Error");
				e.printStackTrace();
			}
		}
	}
}
