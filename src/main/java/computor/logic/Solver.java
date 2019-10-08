package computor.logic;

import computor.io.Output;
import computor.io.Validator;

import java.math.BigDecimal;
import java.util.concurrent.Callable;

public final class Solver implements Callable<Output> {
	private final String inputEquation;

	public static Solver of(String equation) {
		return new Solver(equation);
	}

	private Solver(String inputEquation) {
		this.inputEquation = inputEquation;
	}

	@Override public Output call() {
		Equation equation = Validator.validate(inputEquation);
		System.out.printf("Full form: %s%n", equation);
		System.out.printf("Reduced form: %s%n", equation.reduce());
		System.out.printf("Simple form: %s%n", equation.simplify());
		System.out.printf("Polynomial degree: %s%n", equation.degree());
		Output solution = solve(equation);
		System.out.println(solution);
		return solution;
	}

	private Output solve(Equation equation) {
		switch (equation.sort().degree()) {
			case 0 : return Output.ALL_REAL_NUMBERS;
			case 1 : return solveFirstDegree(equation);
			case 2 : return solveSecondDegree(equation);
			default: return Output.UNSOLVABLE;
		}
	}

	private Output solveFirstDegree(Equation equation) {
		Operand kx = equation.getOfPower(1);
		Operand n = equation.getOfPower(0);
		BigDecimal x = n.number().divide(kx.number(), 5, BigDecimal.ROUND_HALF_UP).stripTrailingZeros().negate();
		return new Output(equation, x);
	}

	private Output solveSecondDegree(Equation equation) {
		Operand a = equation.getOfPower(2);
		Operand b = equation.getOfPower(1);
		Operand c = equation.getOfPower(0);
		BigDecimal discriminant = countDiscriminant(a, b, c);
		if (discriminant.compareTo(BigDecimal.ZERO) >= 0)
		{
			BigDecimal discriminantSqrt = Utils.sqrt(discriminant);
			BigDecimal x1 = getX(b, a, discriminantSqrt, 1);
			BigDecimal x2 = getX(b, a, discriminantSqrt, 2);
			return new Output(equation, x1, x2).setDiscriminant(discriminant);
		}
		return Output.UNSOLVABLE;
	}

	/*
	 *  (-b +- discriminantSqrt) / 2 * a
	 */
	private BigDecimal getX(Operand b, Operand a, BigDecimal discriminantSqrt, int xNum) {
		BigDecimal top = xNum == 1 ? b.number().negate().add(discriminantSqrt) : b.number().negate().subtract(discriminantSqrt);
		BigDecimal bottom = a.number().multiply(BigDecimal.valueOf(2.0));
		return top.divide(bottom, BigDecimal.ROUND_HALF_UP);
	}

	private BigDecimal countDiscriminant(Operand a, Operand b, Operand c) {
		BigDecimal bSquared = Utils.pow2(b.number());
		BigDecimal ac4 = BigDecimal.valueOf(4.0).multiply(a.number()).multiply(c.number());
		return bSquared.subtract(ac4);
	}

}
