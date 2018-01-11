// Author: Garry Cummins
// OOP Project
package ie.gmit.oop;

public abstract class Compare {
	private final int intersect;
	private final int a;
	private final int b;
	
	public Compare(int intersect, int a, int b) {
		this.intersect = intersect;
		this.a = a;
		this.b = b;
	}
	
	public abstract float calcJac();
	
	public int getIntersect() {
		return intersect;
	}

	public int getA() {
		return a;
	}

	public int getB() {
		return b;
	}

}
