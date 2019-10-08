package computor.logic;

import computor.io.Output;
import computor.io.Validator;

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
		return solve(equation);
	}

	private Output solve(Equation equation) {

//		equation.for

		return Output.UNSOLVABLE;
	}

}
