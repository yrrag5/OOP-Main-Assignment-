// Author: Garry Cummins
// OOP Project
package ie.gmit.oop;

// Calculation of jacard class 
public class Jacard extends Compare {
	private final int intersect;
	private final int a;
	private final int b;
	private float jaccard;
	
	public Jacard(int intersect, int a, int b) {
		super(intersect, a, b);
		this.intersect = intersect;
		this.a = a;
		this.b = b;
	}

	public float calcJac() {
		jaccard = (float) intersect / (((float) a + (float) b) -(float) intersect);
		return jaccard;
	}

	

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
