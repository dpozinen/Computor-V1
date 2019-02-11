import java.util.List;
import dpozinen.io.*;
import dpozinen.logic.*;

public class App {
	public static void main(String[] args) {
		List<Operand> operands = new Input().parseInput(args[0]);
		Solver solver = new Solver(operands);
    }
}
