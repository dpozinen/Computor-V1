package dpozinen.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dpozinen.logic.Operand;
import dpozinen.io.Output;
import dpozinen.logic.Solution;

public class Solver {
	List<Operand> operands;

	public Solver(List<Operand> operands) {
		this.operands = operands;
	}

	public void solve() {
		try {
			reduceForm(operands);
			Output.showReducedForm(operands);
			Operand max = Collections.max(operands);
			Output.showPolinomDegree(max); // throws exception at > 3
			Output.showSolution(getSolutions(operands, max));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// TODO: x1 x2
	private Solution getSolutions(List <Operand> operands, Operand max) {
		if (max.getPower() == 2) {
			double	a = operands.get(2).getNum();
			double	b = operands.get(1).getNum();
			double	c = operands.get(0).getNum();
			Double 	x1 = null;
			Double 	x2 = null;

			double Discriminant = Math.pow(b, 2) - 4 * a * c;
			System.out.println(Discriminant);
			if (Discriminant >= 0)
				x1 = (-b + Math.sqrt(Discriminant)) / 2 * a;
			if (Discriminant > 0)
				x2 = (-b - Math.sqrt(Discriminant)) / 2 * a;
			return new Solution(Discriminant, x1, x2);
		}
		else {
			double x1 = operands.get(0).getNum() / operands.get(1).getNum();
			return new Solution(null, x1, 0d);
		}
	}

	/* 5 * X^0 + 4 * X^1 - 9.3 * X^2 = 1 * X^0
	   4 * X^0 + 4 * X^1 - 9.3 * X^2 = 0
		also adding same powered
		TODO: rewrite so that checks each operands' power and marks if checked or not
	*/
	private void reduceForm(List<Operand> operands) {
		Collections.sort(operands);

		List <Operand> newOpList = new ArrayList<Operand>();
		for (int p = 0; p <= 2; p++) {
			for (Operand o : operands) {
				if (o.getPower() == p) {
					for (Operand op : operands) {
						if (o.isOfPower(op)) {
							o.add(op);
							newOpList.add(op);
						}
					}
					break;
				}
			}
		}
		operands.removeAll(newOpList);
	}
}