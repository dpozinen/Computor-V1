package computor.logic;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

public final class Operand implements Comparable<Operand>{
	private final BigDecimal number;
	private final short power;
	private boolean simpleOutput;

	public Operand(BigDecimal number, short power) {
		this.number = number;
		this.power = power;
	}

	public Operand plus(Operand other) {
		if ( other.power == this.power ) return new Operand(this.number.add(other.number), this.power);
		throw new IllegalArgumentException("Powers don't match");
	}

	public void enableSimpleOutput() {
		simpleOutput = true;
	}

	public Short power() {
		return power;
	}

	public boolean powerEquals(Operand o) {
		return this.power == o.power;
	}

	@Override public int compareTo(Operand o) {
		return Short.compare(this.power, o.power);
	}

	@Override public String toString() {
		String sign = number.compareTo(BigDecimal.ZERO) >= 0 ? " + " : " - ";
		String number = StringUtils.removeStart(this.number.stripTrailingZeros().toString(), "-");

		if (simpleOutput) {
			String format = power == 0 ? "%s%s" : power == 1 ? "%s%s * X" : "%s%s * X ^ %d";
			return String.format(format, sign, number, power);
		} else {
			return String.format("%s%s * X ^ %d", sign, number, power);
		}
	}
}
