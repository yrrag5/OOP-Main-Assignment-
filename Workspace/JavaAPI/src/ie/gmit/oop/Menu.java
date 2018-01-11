package ie.gmit.oop;

import java.util.Scanner;

public class Menu {
	private int select = -1;
	private String f1, f2;
	private int shingle;
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
		
		Launcher l = new Launcher();
		l.Launch(f1, f2, shingle);
	}// Compare files
}// Menu
