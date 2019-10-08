package computor.io;

import java.math.BigDecimal;

import computor.logic.Equation;

public final class Output {
	public static final Output	UNSOLVABLE			= new Output("The provided equation is unsolvable");
	public static final Output	ALL_REAL_NUMBERS	= new Output("The provided equation is unsolvable");
	private static final String	POSITIVE_DISC_MSG	= "Discriminant is strictly positive, the two solutions are:%n%s%n%s";
	private static final String	ZERO_DISC_MSG		= "Discriminant is zero, the solution is:%n%s";
	private static final String	SIMPLE_EQUATION_MSG	= "The solution is:%n%s";

	private final Equation equation;
	private BigDecimal discriminant;
	private BigDecimal[] solutions = new BigDecimal[2];
	private String message;

	private Output(String message) {
		this.equation = null;
		this.message = message;
	}

	public Output(Equation equation, BigDecimal... solutions) {
		this.equation = equation;
		this.solutions = solutions;
	}

	public Output setDiscriminant(BigDecimal discriminant) {
		this.discriminant = discriminant;
		return this;
	}

	@Override public String toString() {
		if (discriminant != null) {
			if (discriminant.compareTo(BigDecimal.ZERO) == 0)
				return String.format(ZERO_DISC_MSG, solutions[0]);
			if (discriminant.compareTo(BigDecimal.ZERO) > 0)
				return String.format(POSITIVE_DISC_MSG, solutions[0], solutions[1]);
		} else if (solutions[0] != null) {
			return String.format(SIMPLE_EQUATION_MSG, solutions[0]);
		}
		return message;
	}
}
