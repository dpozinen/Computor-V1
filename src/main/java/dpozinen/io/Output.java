package dpozinen.io;

import java.util.List;

import javax.management.InvalidAttributeValueException;

import dpozinen.logic.Operand;
import dpozinen.logic.Solution;

public class Output {

    static public void showReducedForm(List<Operand> operands) {
		System.out.println("Reduced Form: ");
		for (int i = 0; i < operands.size(); i++) {
			if (i != 0) {
				String sign = operands.get(i).getNum() > 0 ? " + " : " - ";
				System.out.print(sign + operands.get(i).show());
			}
			else
				System.out.print(operands.get(i).show());
		}
		System.out.println(" = 0");
    }

    static public void showSolution(Solution solution) {
		System.out.println(solution);
	}

    static public void showPolinomDegree(Operand max) throws InvalidAttributeValueException {
		System.out.println("Polynomial degree: " + max.getPower());
		if (max.getPower() > 2)
			throw new InvalidAttributeValueException("The polynomial degree is stricly greater than 2, I can't solve.");
    }
}