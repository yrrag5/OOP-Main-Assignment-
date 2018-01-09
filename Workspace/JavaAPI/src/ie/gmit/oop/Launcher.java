// Author: Garry Cummins
// OOP Project
package ie.gmit.oop;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Launcher {
	public void Launch(String f1, String f2) {
		BlockingQueue<Shingle> q = new LinkedBlockingQueue<>(blockingQueueSize)	
		// threadPoolSize	

		Thread t1 = new Thread(new DocumentParser(f1, q, shingleSize, k), "T1");
		Thread t2 = new Thread(new DocumentParser(f2, q, shingleSize, k), "T2");
		// t3 for consumer
		
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
	}// Launch

}
