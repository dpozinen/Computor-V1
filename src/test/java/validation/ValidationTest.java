package validation;

import computor.io.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ValidationTest {

	@Test public void fullFormEquationValid() {
		Assertions.assertDoesNotThrow(() -> Validator.validate("5 * X^0 + 4 * X^1 - 9.3 * X^2 = 1 * X^0"));
		Assertions.assertDoesNotThrow(() -> Validator.validate("5 * X^1 + 4 * X  ^ 4 - 94567456.2342343 * X^2 = 1 * X^0"));
		Assertions.assertDoesNotThrow(() -> Validator.validate("5 * X^44 + 4144 * X^41 - 9324.2343 * X^2342 - 1 * X^0 = 0"));
		Assertions.assertDoesNotThrow(() -> Validator.validate("3245 * X^3240 + 334 * X^111 - 93123.43 * X^2 - 1 * X^0 = 1      * X^0"));
		Assertions.assertDoesNotThrow(() -> Validator.validate("5 * X^0 + X^5 - 9.3 * X^2 = 1       * X^0"));
		Assertions.assertDoesNotThrow(() -> Validator.validate("5 * X      ^0 + X^1 - 9.3 * X^2 = 1 * X^0"));
		Assertions.assertDoesNotThrow(() -> Validator.validate("X      ^0 + 4     * X^1 - 9.3 * X^2 = 1 * X^0"));
	}

	@Test public void simpleFormEquationValid() {
		Assertions.assertDoesNotThrow(() -> Validator.validate("5 + 4 * X - 9.3 * X^2 = 1"));
		Assertions.assertDoesNotThrow(() -> Validator.validate("5 * X + 4 * X  ^ 4 - 94567456.2342343 * X^2 = 1 "));
		Assertions.assertDoesNotThrow(() -> Validator.validate("5 * X^44 + 4144 * X^41 - 9324.2343 * X^2342 - 1  = 0"));
		Assertions.assertDoesNotThrow(() -> Validator.validate("3245 * X^3240 + 334 * X^111 - 93123.43 * X^2 - 1  = 1      "));
		Assertions.assertDoesNotThrow(() -> Validator.validate("5  + 4 * X - 9.3 * X^2 = 1  \r\r\r      "));
		Assertions.assertDoesNotThrow(() -> Validator.validate("5 +  		  4  \t\t\t   * X - 9.3 * X^2 = 1 "));
	}

	@Test public void operandValid() {
		Assertions.assertDoesNotThrow(() -> Validator.validateOperand("5"));
		Assertions.assertDoesNotThrow(() -> Validator.validateOperand("5 * X"));
		Assertions.assertDoesNotThrow(() -> Validator.validateOperand("5 * X ^ 2"));
		Assertions.assertDoesNotThrow(() -> Validator.validateOperand("- 5 		*  X ^ 2	"));
		Assertions.assertDoesNotThrow(() -> Validator.validateOperand("- 937.342		*  X ^ 2	"));
		Assertions.assertDoesNotThrow(() -> Validator.validateOperand("- 3094879,342		*  X ^ 2	"));
		Assertions.assertDoesNotThrow(() -> Validator.validateOperand("- 3094879,3394857		*  X ^ 2	"));
	}

	@Test public void invalidEquation() {
		Class<IllegalArgumentException> iae = IllegalArgumentException.class;
		Assertions.assertAll(
			() -> Assertions.assertThrows(iae, () -> Validator.validate("fjghoeriuh")),
			() -> Assertions.assertThrows(iae, () -> Validator.validate("oieru9 334  = 1")),
			() -> Assertions.assertThrows(iae, () -> Validator.validate("=== = 1")),
			() -> Assertions.assertThrows(iae, () -> Validator.validate("5 + 4 * X - 9.3 * X^2 = 4598286798676587658765875374403985703948753094875"))
		);
	}

	@Test public void invalidOperand() {

	}
}
