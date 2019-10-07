package computor.logic;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

public class Operand implements Comparable<Operand>{
	private final BigDecimal number;
	private final short      power;

	public Operand(BigDecimal number, short power) {
		this.number = number;
		this.power = power;
	}

	public Operand plus(Operand other) {
		if ( other.power == this.power ) return new Operand(this.number.add(other.number), this.power);
		throw new IllegalArgumentException("Powers don't match");
	}

	@Override public int compareTo(Operand o) {
		return Short.compare(o.power, this.power);
	}

	public boolean powerEquals(Operand o) {
		return this.power == o.power;
	}

	@Override public String toString() {
		String sign = number.compareTo(BigDecimal.ZERO) >= 0 ? " + " : " - ";
		String number = StringUtils.removeStart(this.number.stripTrailingZeros().toString(), "-");
		return String.format("%s%s * X ^ %d", sign, number, power);
	}
}
