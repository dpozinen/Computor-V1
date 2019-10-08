package computor.logic;

import java.math.BigDecimal;

final class Utils {
	private Utils() {
		throw new AssertionError();
	}

	static BigDecimal pow2(BigDecimal number) {
		return number.multiply(number);
	}

	static BigDecimal sqrt(BigDecimal discriminant) {
		return BigDecimal.valueOf(Math.sqrt(discriminant.doubleValue()));
	}
}
