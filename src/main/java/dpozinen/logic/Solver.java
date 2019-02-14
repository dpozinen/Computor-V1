package dpozinen.logic;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import dpozinen.logic.Operand;
import dpozinen.io.Output;
import dpozinen.logic.Solution;;

public class Solver {
	List<Operand> operands;

	public Solver(List<Operand> operands) {
		this.operands = operands;
	}

	public void solve() {
		reduceForm(operands);
		Output.showReducedForm(operands);
		Operand max = Collections.max(operands);
		Output.showPolinomDegree(max); // throws exception at > 3
		Output.showSolution(getSolutions(operands));
	}

	private Solution getSolutions(List <Operand> operands) {
		Solution solution = new Solution();
		// count Discriminant
		// logic for x1
		// logic for x2
		return solution;
	}

	// 5 * X^0 + 4 * X^1 - 9.3 * X^2 = 1 * X^0
	// 4 * X^0 + 4 * X^1 - 9.3 * X^2 = 0
	private void reduceForm(List<Operand> operands) {
		// sort all in ascending
		Collections.sort(operands);

		// swap signs where necessary
		swapSigns(operands);

		// perform + -:
		// each will find another one with the same power and add it to itself, deleting the one added
		// TODO: test this
		for (Operand o : operands) {
			for (int i = 1; i < operands.size(); i++ ) {
				Operand op = operands.get(i);
				if (o.equals(op)) {
					o.add(op);
					operands.remove(i);
				}
			}
		}
    }

	private void swapSigns(List<Operand> operands) {
		for (Operand o : operands)
			if (o.isAfterEquals())
				o.changeSign();
	}

}