import computor.logic.Solver;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class App {
	public static void main(String... equations) {
//		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(equations.length);
//		for ( String equation : equations ) executor.submit(Solver.of(equation));
//		executor.shutdown();
		Solver.of("5 * X^0 + 4 * X^1 - 9.3 * X^2 = 1 * X^0").call();
		Solver.of("5 * X^0 - 4 * X^1 = 4 * X^0").call();
	}
}
