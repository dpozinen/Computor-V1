package dpozinen.logic;

import org.apache.commons.math3.util.Precision;

public class Solution {
    private Double Discriminant;
    private double x1;
	private double x2;
	private boolean trueForAll;

	public Solution(boolean trueForAll){
		this.trueForAll = trueForAll;
	};

	public Solution(Double Discriminant, Double x1, double x2) {
		this.Discriminant = Discriminant;
		this.x1 = x1;
		this.x2 = x2;
	}

	public Double getDiscriminant() {
		return this.Discriminant;
	}

	public Double getX1() {
		return this.x1;
	}

	public double getX2() {
		return this.x2;
	}

	@Override
	public String toString() {
		if (Discriminant != null && Discriminant > 0)
			return "Discriminant is strictly positive, the two solutions are:\n"
			+ Precision.round(x1, 6) + "\n" + Precision.round(x2, 6);
		if (Discriminant != null && Discriminant == 0)
			return "Discriminant is equal to zero, the only solution is:\n" + Precision.round(x1, 6);
		if (Discriminant != null && Discriminant < 0)
			return "Discriminant is less than zero, no solutions found";
		if (trueForAll)
			return "All real numbers are solution";
		if (Discriminant == null)
			return "The solution is:\n" + Precision.round(x1, 6);
		return "No solutions found";
	}
}