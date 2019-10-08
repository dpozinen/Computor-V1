package computor.io;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;

import computor.logic.Equation;
import computor.logic.Operand;

public final class Validator {
	private static final String FULL_OPERAND_REGEX = "^([+-=]?\\d*\\.?\\d*)\\*X\\^(\\d+)$";
	private static final String FIRST_DEGREE_REGEX = "^([+-=]?\\d*\\.?\\d*)\\*X$";
	private static final String NO_NUMBER_REGEX = "^[+-=]?X\\^(\\d+)$";
	private static final String NO_DEGREE_REGEX = "^([+-=]?\\d*\\.?\\d*)$";

	private Validator() {
		throw new AssertionError();
	}

	/*
		5 * X^0 + 4 * X^1 - 9.3 * X^2 = 1 * X^0
		5*X^0+4*X^1-9.3*X^2=1*X^0
		5*X^0 +4*X^1 -9.3*X^2 1*X^0
	*/
	public static Equation validate(String inputEquation) {
		String eq = RegExUtils.removePattern(inputEquation, "\\s+");
		if (StringUtils.isBlank(eq)) throw new IllegalArgumentException("String is empty");
		if (!eq.contains("=") || eq.replaceFirst("=", "").contains("=")) throw new IllegalArgumentException("Equals sign shenanigans");

		String[] operands = eq.replace("+", " +").replace("-", " -").replace("=", " =").split(" ");
		Equation equation = new Equation();
		for (String operand : operands) equation.add(validateOperand(operand));
		return equation;
	}

	private static Operand validateOperand(String operand) {
		if (operand.matches(FULL_OPERAND_REGEX))
			return extractFull(operand);
		else if (operand.matches(FIRST_DEGREE_REGEX))
			return extractSimplified(operand, FIRST_DEGREE_REGEX, 1);
		else if (operand.matches(NO_DEGREE_REGEX))
			return extractSimplified(operand, NO_DEGREE_REGEX,  0);
		else if (operand.matches(NO_NUMBER_REGEX))
			return extractNoNumber(operand);
		throw new IllegalArgumentException(String.format("Bad operand: %s", operand));
	}

	private static Operand extractFull(String operand) {
		Matcher m = Pattern.compile(FULL_OPERAND_REGEX).matcher(operand);
		// noinspection ResultOfMethodCallIgnored
		m.find();
		BigDecimal number = new BigDecimal(m.group(1));
		short power = Short.parseShort(m.group(2));
		return new Operand(doNegate(operand) ? number.negate() : number, power);
	}

	private static Operand extractSimplified(String operand, String regex, int power) {
		Matcher m = Pattern.compile(regex).matcher(operand);
		// noinspection ResultOfMethodCallIgnored
		m.find();
		BigDecimal number = new BigDecimal(m.group(1));
		return new Operand(doNegate(operand) ? number.negate() : number, (short) power);
	}

	private static Operand extractNoNumber(String operand) {
		Matcher m = Pattern.compile(NO_NUMBER_REGEX).matcher(operand);
		// noinspection ResultOfMethodCallIgnored
		m.find();
		short power = Short.parseShort(m.group(1));
		BigDecimal number = doNegate(operand) ? BigDecimal.ONE.negate() : BigDecimal.ONE ;
		return new Operand(number, power);
	}

	private static boolean doNegate(String operand) {
		return operand.contains("=");
	}
}
