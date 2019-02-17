package dpozinen.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
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
			Operand max = Collections.max(operands);
			reduceForm(operands, max);
			Output.showReducedForm(operands);
			Output.showPolinomDegree(max); // throws exception at > 3
			Output.showSolution(getSolution(operands, max));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private Solution getSolution(List <Operand> operands, Operand max) {
		if (max.getPower() == 2 && operands.size() == 3) {
			double a = operands.get(2).getNum();
			double b = operands.get(1).getNum();
			double c = operands.get(0).getNum();
			Double x1 = null;
			double x2 = 0;

			double Discriminant = Math.pow(b, 2) - 4 * a * c;
			if (Discriminant >= 0)
				x1 = (-b + Math.sqrt(Discriminant)) / (2 * a);
			if (Discriminant > 0)
				x2 = (-b - Math.sqrt(Discriminant)) / (2 * a);
			return new Solution(Discriminant, x1, x2);
		} else if (max.getPower() == 1 && operands.size() == 2) {
			Double x1 = -operands.get(0).getNum() / operands.get(1).getNum();
			return new Solution(null, x1, 0d);
		} else {
			if (operands.get(0).getNum() == 0d)
				return new Solution(true);
			else
				return new Solution(false);
		}
	}

	/* 5 * X^0 + 4 * X^1 - 9.3 * X^2 = 1 * X^0
	   4 * X^0 + 4 * X^1 - 9.3 * X^2 = 0
		also adding same powered
	*/
	private void reduceForm(List<Operand> operands, Operand max) {
		Collections.sort(operands);

		HashSet<Integer> powers = new HashSet<>(max.getPower());
		for (Operand o : operands)
			powers.add(o.getPower());

		List <Operand> newOpList = new ArrayList<Operand>();
		for (Integer p : powers) {
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