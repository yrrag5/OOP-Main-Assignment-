// Author: Garry Cummins
// OOP Project
package ie.gmit.oop;

import java.util.Scanner;

public class Menu {
	private int select = -1;
	private String f1, f2;
	private int shingle, blockingQueueSize;
	private boolean run = true;
	private Scanner sc = new Scanner(System.in);
	
	public Menu() {
		
	}
	
	public void show() {
		do {
			System.out.println("===== Java API =====");
			System.out.println("(1) Parse and compare documents\n(2) Exit");
			select = sc.nextInt();
			
			switch (select) {
			case 1:
				compareFiles();
				break;
				
			case 2:
				run = false;
				System.out.println("Exiting program");
				break;
			}
		} while(run);
	}// Show


	private void compareFiles() {
		System.out.println("Comparing documents...");
	
		System.out.println("Enter file name 1:");
		f1 = sc.next();
	
		System.out.println("Enter file name 2:");
		f2 = sc.next();
	
		System.out.println("Enter the size of the shingle");
		shingle = sc.nextInt();		
		
		System.out.println("Enter blocking queue size");
		blockingQueueSize = sc.nextInt();
		
		Launcher l = new Launcher();
		l.Launch(f1, f2, shingle, blockingQueueSize);
	}// Compare files
	
	public static void showResults(Compare sim) {
		//menu to display results for comparisons
		float jacard = sim.calcJac();

		System.out.println("Comparing complete");
		System.out.println("=========================================");
		System.out.println("File 1: " + sim.getA() + " shingles");
		System.out.println("File 2: " + sim.getB() + " shingles");
		System.out.println("\nNumber of Comparisons : " + sim.getIntersect());
		System.out.printf("\nPercentage: %.2f", jacard);
		System.out.println("\n\n");
	}
}// Menu
