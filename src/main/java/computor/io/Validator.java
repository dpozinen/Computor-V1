package computor.io;

import computor.logic.Equation;
import computor.logic.Operand;
import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
	private static final String OPERAND_REGEX = "^([+-]?\\d*\\.?\\d*)\\*X\\^(\\d+)$";

//	5 * X^0 + 4 * X^1 - 9.3 * X^2 = 1 * X^0
//	5*X^0+4*X^1-9.3*X^2=1*X^0
//	5*X^0 +4*X^1 -9.3*X^2 1*X^0
	public static Equation validate(String inputEquation) {
		String eq = RegExUtils.removePattern(inputEquation, "\\s+");
		if (StringUtils.isBlank(eq)) throw new IllegalArgumentException("String is empty");
		if (!eq.contains("=") || eq.replaceFirst("=", "").contains("=")) throw new IllegalArgumentException("Equals sign shenanigans");

		String[] operands = eq.replace("+", " +").replace("-", " -").replace("=", " =").replace("=", "").split(" ");
		Equation equation = new Equation();
		for (String operand : operands) equation.add(validateOperand(operand));
		return equation;
	}

	private static Operand validateOperand(String operand) {
		if (!operand.matches(OPERAND_REGEX)) throw new IllegalArgumentException(String.format("Bad operand: %s", operand));

		Matcher m = Pattern.compile(OPERAND_REGEX).matcher(operand);
		m.find();
		double number = Double.parseDouble(m.group(1));
		short power = Short.parseShort(m.group(2));

		return new Operand(BigDecimal.valueOf(number), power);
	}
}
