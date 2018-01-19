package org.zilker.thread;

import java.io.*;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class JavaThreadPool {

	private static Logger logger = Logger.getLogger(WorkerThread.class.getName());
	private static FileReader fr = null;
	private static BufferedReader br = null;
	private static String currentDir = System.getProperty("user.dir");
	private static final String FILE_PATH = currentDir + "\\src\\org\\zilker\\resources\\customer.txt";

	public static void main(String[] args) {
		Scanner scn = null;
		String counters;
		String[] queueTime;
		int[] threadTime;
		try {
			fr = new FileReader(FILE_PATH);
			br = new BufferedReader(fr);
			scn = new Scanner(System.in);
			logger.info("Enter the number of counters : ");
			counters = scn.next();
			ExecutorService executor = Executors.newFixedThreadPool(Integer.parseInt(counters));
			queueTime = br.readLine().split(",");
			threadTime = new int[queueTime.length];
			for (int i = 0; i < queueTime.length; i++) {
				threadTime[i] = Integer.parseInt(queueTime[i]) * 1000;
				Runnable worker = new WorkerThread((i + 1), threadTime[i]);
				executor.execute(worker);
			}
			executor.shutdown();
			while (!executor.isTerminated()) { 
				
			}  
	        logger.info("All the customers are being attended.");   
		} catch (NumberFormatException e) {
			logger.warning("Enter integer value.");
		} catch (FileNotFoundException e) {
			logger.warning("Error finding the path of the file.");
		} catch (IOException e) {
			logger.warning("Error retrieving data from the file.");
		} finally {
			closeConnection(fr, br);
		}
	}
	
	private static void closeConnection(FileReader fr, BufferedReader br) {
		try {
			if (br != null) {
				br.close();
			} if (fr != null) {
				fr.close();
			}
		} catch (IOException ex) {
			logger.warning("Error closing the connections");
		}
	}

}
