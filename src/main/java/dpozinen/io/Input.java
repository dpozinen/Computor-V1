package dpozinen.io;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dpozinen.logic.Operand;

public class Input {

	private void validateEquation(String s) throws IllegalArgumentException {
		if (!s.contains("="))
			throw new IllegalArgumentException("No equals detected");
		if (s.replaceAll("=", "").length() != s.length() - 1)
			throw new IllegalArgumentException("Two equals detected");

		Pattern p = Pattern.compile("((([-+])?\\d+(\\.\\d+)?)[*][a-zA-z][\\^]\\d+[=+-]?)");
		Matcher m = p.matcher(s);
		String sc = "";
		while (m.find())
			sc += m.group(1);
		if (!s.equals(sc))
			throw new IllegalArgumentException("Invalid input");
	}

	// TODO: better parsing: -534.3we5
	public List<Operand> parseInput(String s) {
		final String splitter = "(?=-)|(?=\\+)|((?==)|(?<==))"; // leave '-' & '+' infront of num, separate '='

		List<Operand> operands = null;
		s = s.replace(" ", "").toUpperCase();
		validateEquation(s);
		String [] split = s.split(splitter);
		operands = new ArrayList<Operand>(split.length);

		boolean afterEquals = false;
		for (String sp : split) {
			if (sp.equals("=")) {
				afterEquals = true;
				continue ;
			}
			operands.add(extractOperand(sp, afterEquals));
		}
		return operands;
	}

	// TODO: remember negateive powers?
	private Operand extractOperand(String sp, boolean afterEquals) {
		Operand operand = null;
		try {
			Pattern p = Pattern.compile("(([-+])?\\d+(\\.\\d+)?)"); // int or double i.e. 5 or 5.54, including '-' & '+'
			Matcher m = p.matcher(sp);
			m.find();
			operand = new Operand();
			operand.setNum(Double.parseDouble(m.group(1)));
			if (afterEquals)
				operand.changeSign();

			p = Pattern.compile("(\\d$)");
			m = p.matcher(sp);
			m.find();
			operand.setPower(Integer.parseInt(m.group(1)));
		} catch (Exception e) {
			throw new IllegalArgumentException("Error on input");
		}
		return operand;
	}
}