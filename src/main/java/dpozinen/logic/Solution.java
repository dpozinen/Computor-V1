package dpozinen.logic;

// TODO: redo x to non D
public class Solution {
    private Double Discriminant;
    private Double x1;
    private Double x2;

	public Solution(Double Discriminant, Double x1, Double x2) {
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

	public Double getX2() {
		return this.x2;
	}

	@Override
	public String toString() {
		if (Discriminant != null && Discriminant > 0)
			return "Discriminant is strictly positive, the two solutions are:\n"
			+ x1 + "\n" + x2;
		if (Discriminant != null && Discriminant == 0)
			return "Discriminant is equal to zero, the only solution is:\n" + x1;
		return "The solution is:\n" + x1;
	}
}