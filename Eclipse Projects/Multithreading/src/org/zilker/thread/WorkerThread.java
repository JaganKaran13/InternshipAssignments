package org.zilker.thread;

import java.util.logging.Logger;

class WorkerThread implements Runnable {

	private int customerEntry, time;
	private Logger logger = Logger.getLogger(WorkerThread.class.getName());

	public WorkerThread(int s, int time) {
		this.customerEntry = s;
		this.time = time;
	}

	public void run() {
		logger.info("Counter " + Thread.currentThread().getName().substring(14) + " is allocated for customer "
				+ customerEntry);
		processmessage();
		logger.info("Counter " + Thread.currentThread().getName().substring(14) + " is free.");
	}

	private void processmessage() {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}