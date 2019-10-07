
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Tests {
	Solver solver = new Solver();

	@Test
	public void quadraticBIsZero() {
		Solution s1 = new Solution(null, -2d, 2d);
		Solution s2 = solver.solve("-3 * x ^ 2 = -12 * X ^ 0", false);

		assertEquals(s1.getX1(), s2.getX1(), 1E-5);
		assertEquals(s1.getX2(), s2.getX2(), 1E-5);

		s1 = new Solution(null, -11d, 11d);
		s2 = solver.solve("-11 * x ^ 2 + 1331 * x ^ 0 = 0 * x ^ 0", false);

		assertEquals(s1.getX1(), s2.getX1(), 1E-5);
		assertEquals(s1.getX2(), s2.getX2(), 1E-5);

		s1 = new Solution(null, -7.48331477355d, 7.48331477355d);
		s2 = solver.solve("-9 * x ^ 2 + 504 * X ^ 0 = 0 * X ^ 0", false);

		assertEquals(s1.getX1(), s2.getX1(), 1E-5);
		assertEquals(s1.getX2(), s2.getX2(), 1E-5);

	}
}
