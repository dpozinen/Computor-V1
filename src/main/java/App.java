import java.util.List;

import dpozinen.io.*;
import dpozinen.logic.*;

public class App {
	public static void main(String[] args) {
		try {
			List<Operand> operands = new Input().parseInput("42 ∗ X^0 = 42 ∗ X^0");
			// System.out.println(operands);
			Solver solver = new Solver(operands);
			solver.solve();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		// Pattern p = Pattern.compile("(\\d+(\\.\\d+)?) (\\d)");
		// Matcher m = p.matcher("-534.0980975 * X^1 - 9.3 * X^2 = 1 * X^0 - 5 * X ^ 2");
		// m.find();
		// System.out.println(m.group(1));
	}
}
