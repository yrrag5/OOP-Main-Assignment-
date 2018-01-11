// Author: Garry Cummins
// OOP Project
package ie.gmit.oop;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;

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
		
		BufferedReader br = null;
		try {
		    br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		String line = "";
		try {
			while((line = br.readLine())!= null) {
					if (line.length() > 0) {
						//Splitting the line on one or more 
						//whitespace elements. s is space, + is
						//1 or more spaces.
						String uLine = line.toUpperCase();
						String[] words = uLine.split("\\s+"); // Can also take a regexpression
						addWordsToBuffer(words);
		
				}// if
	
		}// while
			
		while(buffer.size() != 0) {
			Shingle s = getNextShingle();
			if(!(s == null)) {
				queue.put(s); // Blocking method. Add is not a blocking method
			}
		}
		
		} catch(IOException e) {
			System.out.println("Error file " + file + " not found, please try again");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		flushBuffer();
		try {
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
