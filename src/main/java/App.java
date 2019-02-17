import dpozinen.logic.Solver;

public class App {
	public static void main(String[] args) {
		try {
			new Solver().solve("-9 * x ^ 2 + 504 * X ^ 0 = 0 * X ^ 0", true);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
