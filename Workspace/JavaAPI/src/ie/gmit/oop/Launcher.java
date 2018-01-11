// Author: Garry Cummins
// OOP Project
package ie.gmit.oop;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Launcher {
	public void Launch(String f1, String f2, int shingleSize, int blockingQueueSize) {
		BlockingQueue<Shingle> q = new LinkedBlockingQueue<>(blockingQueueSize);	
		// threadPoolSize	

		Thread t1 = new Thread(new DocumentParser(f1, q, shingleSize, blockingQueueSize), "T1");
		Thread t2 = new Thread(new DocumentParser(f2, q, shingleSize, blockingQueueSize), "T2");
		// t3 for consumer
		
		try {
		t1.start();
		t2.start();
		} catch(InterruptedException e) {
		e.printStackTrace();
		}
		t1.join();
		t2.join();
	}// Launch

}
