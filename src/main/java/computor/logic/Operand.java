package computor.logic;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;

public final class Operand implements Comparable<Operand>{
	private final BigDecimal	number;
	private final short			power;
	private boolean				simpleOutput;
	private boolean				cleanOutput;

	public Operand(BigDecimal number, short power) {
		this.number = number;
		this.power = power;
	}

	Operand plus(Operand other) {
		if ( other.power == this.power)
			return new Operand(this.number.add(other.number), this.power);
		throw new IllegalArgumentException("Operands don't match");
	}

	void enableSimpleOutput() {
		simpleOutput = true; cleanOutput = false;
	}

	void enableCleanOutput() {
		cleanOutput = true; simpleOutput = false;
	}

	short power() {
		return power;
	}

	BigDecimal number() {
		return number;
	}

	boolean powerEquals(Operand o) {
		return this.power == o.power;
	}

	@Override public int compareTo(Operand o) {
		return Short.compare(this.power, o.power);
	}

	@Override public String toString() {
		String sign = number.compareTo(BigDecimal.ZERO) >= 0 ? " + " : " - ";
		String number = StringUtils.removeStart(this.number.stripTrailingZeros().toPlainString(), "-");

		if (simpleOutput) {
			String format = power == 0 ? "%s%s" : power == 1 ? "%s%s * X" : "%s%s * X ^ %d";
			return String.format(format, sign, number, power);
		} else if ( cleanOutput ) {
			if (this.number.compareTo(BigDecimal.ONE) == 0) {
				String format = power == 0 ? "%s%s" :
								power == 1 ? "%sx" : "%sx ^ %d";
				return String.format(format, sign, number, power);
			} else {
				String format = power == 0 ? "%s%s" :
								power == 1 ? "%s%sx" : "%s%sx ^ %d";
				return String.format(format, sign, number, power);
			}
		} else return String.format("%s%s * X ^ %d", sign, number, power);
	}
}
