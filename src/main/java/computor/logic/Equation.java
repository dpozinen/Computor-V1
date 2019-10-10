package computor.logic;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Consumer;

import static java.util.stream.Collectors.toList;

public final class Equation implements Iterable<Operand> {
	private final List<Operand> operands = new ArrayList<>();
	private short degree = -1;
	private boolean reduced;

	Equation reduce() {
		Set<Short> usedPowers = new HashSet<>();
		sort();
		for ( ListIterator<Operand> it = operands.listIterator(); it.hasNext(); ) {
			Operand current = it.next();
			if (!usedPowers.add(current.power())) {
				it.remove(); continue;
			}
			for (Operand operand : findMatchingOperands(current))
				current = operand.plus(current);
			it.set(current);
		}
		reduced = true;
		return this.removeZeroes();
	}

	Equation simplify() {
		forEach(Operand::enableSimpleOutput);
		return this;
	}

	private List<Operand> findMatchingOperands(Operand current) {
		return operands.stream().filter(o -> o.powerEquals(current) && !o.equals(current)).collect(toList());
	}

	private Equation removeZeroes() {
		for ( Iterator<Operand> i = iterator(); i.hasNext(); )
			if (i.next().number().compareTo(BigDecimal.ZERO) == 0)
				i.remove();
		return this;
	}

	Operand getOfPower(int power) {
		if (!reduced) reduce();
		return operands.stream().filter(o -> o.power() == power).findAny().orElse(null);
	}

	public void add(Operand operand) {
		operands.add(operand);
	}

	short degree() {
		return degree == -1 ?
			!operands.isEmpty() ? degree = Collections.max(operands).power() : 0
			: degree;
	}

	Equation sort() {
		operands.sort(Comparator.<Operand>naturalOrder().reversed());
		return this;
	}

	@Override public Iterator<Operand> iterator() {
		return operands.iterator();
	}

	@Override public void forEach(Consumer<? super Operand> action) {
		operands.forEach(action);
	}

	@Override public String toString() {
		if (operands.isEmpty()) return "0 = 0";
		StringBuilder sb = new StringBuilder();
		forEach(sb::append); sb.append(" = 0");
		return StringUtils.removeStart(sb.toString(), " + ").trim();
	}

	Equation clean() {
		forEach(Operand::enableCleanOutput);
		return this;
	}
}
