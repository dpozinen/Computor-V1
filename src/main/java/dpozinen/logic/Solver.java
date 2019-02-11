package dpozinen.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dpozinen.logic.Operand;

public class Solver {
	ArrayList<Operand> operands;

	public Solver(List<Operand> operands) {
		this.operands = operands;
	}

	public void solve() {
		// reduceForm(operands);
		// Output.showReducedForm(operands);
		Operand max = Collections.max(operands);
		// Output.showPolinomDegree(operands); //maxPower
		// getSolutions();
	}

	void getSolutions(List <Operand> operands) {
		// count Discriminant
		// Output.showDiscriminant();
		// logic for x1
		// Output.showSolution(x1);
		// logic for x2
		// Output.show(x2);
	}

}