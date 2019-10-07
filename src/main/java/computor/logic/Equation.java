package computor.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class Equation implements Iterable<Operand> {

	private final List<Operand> operands = new ArrayList<>();

	@Override
	public Iterator<Operand> iterator() {
		return operands.iterator();
	}

	public void add(Operand operand) {
		operands.add(operand);
		Collections.sort(operands);
	}

	@Override public void forEach(Consumer<? super Operand> action) {
		operands.forEach(action);
	}
}
