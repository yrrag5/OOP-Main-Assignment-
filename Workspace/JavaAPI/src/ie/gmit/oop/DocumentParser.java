// Author: Garry Cummins
// OOP Project
package ie.gmit.oop;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class DocumentParser implements Runnable{
	private BlockingQueue<Shingle>queue;
	private String file;
	private int shingleSize;
	private Deque<String> buffer = new LinkedList<>();
	private int docId;	

	public DocumentParser(String file, BlockingQueue<Shingle>q, int shingleSize, int docId) {
		super();
		this.queue = q;
		this.file = file;
		this.shingleSize = shingleSize;
		this.docId = docId;	
	}// dp
	

	public void run() {
		
		
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String line = null;
			while((line = br.readLine())!= null) {
				if (line.length() > 0) {
					//Splitting the line on one or more 
					//whitespace elements. s is space, + is
					//1 or more spaces.
					String uLine = line.toUpperCase();
					String[] words = uLine.split(" "); // Can also take a regexpression
					addWordsToBuffer(words);
				}
		}// while
			
		while(buffer.size() != 0) {
			Shingle s = getNextShingle();
			if(!(s == null)) {
				queue.put(s); // Blocking method. Add is not a blocking method
			}
		}
		System.out.println("Completed");
		flushBuffer();
		br.close();
		
		} catch(IOException e) {
			System.out.println("Error file " + file + " not found, please try again");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	
	}// Run
	

	private void addWordsToBuffer(String [] words) {
		for(String s : words) {
			buffer.add(s);
		}
    }// Add
	
	private Shingle getNextShingle() {
		StringBuffer sb = new StringBuffer();
		int counter = 0;
		while(counter < shingleSize) {
			if(buffer.peek() != null) {
				sb.append(buffer.poll());
				counter++;
			}
		}  
		if (sb.length() > 0) {
			return(new Shingle(docId, sb.toString().hashCode()));
		}
		else {
			return(null);
		}
  	} // Next shingle
	

	private void flushBuffer() {
		while(buffer.size() > 0) {
			Shingle s = getNextShingle();
			if(s != null) {
				try {
					queue.put(s);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else {
				try {
					queue.put(new Poison(docId, 0));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}// Flush buffer
	
}// Class
