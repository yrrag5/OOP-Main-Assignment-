// Author: Garry Cummins
// OOP Project
package ie.gmit.oop;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Launcher {
	
	public Launcher() {
		
	}
	public void Launch(String f1, String f2, int shingleSize, int blockingQueueSize) {
		BlockingQueue<Shingle> q = new LinkedBlockingQueue<>(blockingQueueSize);	
		// threadPoolSize	

		Thread t1 = new Thread(new DocumentParser(f1, q, shingleSize , 1), "T1");
		Thread t2 = new Thread(new DocumentParser(f2, q, shingleSize, 2), "T2");
		// t3 for consumer
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Thread t3 = new Thread(new MinHasher(q, shingleSize), "T3");
		t3.start();
		
		try {
			t3.join();
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		
	
	}// Launch

}
