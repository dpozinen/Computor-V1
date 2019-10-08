package computor.logic;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Consumer;

import static java.util.stream.Collectors.toList;

public final class Equation implements Iterable<Operand> {
	private final List<Operand> operands = new ArrayList<>();
	private short degree = -1;

	Equation reduce() {
		Set<Short> usedPowers = new HashSet<>();
		for ( ListIterator<Operand> it = operands.listIterator(); it.hasNext();  ) {
			Operand current = it.next();
			if (!usedPowers.add(current.power())) {
				it.remove(); continue;
			}
			for (Operand operand : findAllOfSamePower(current))
				it.set(operand.plus(current));
		}
		return this.removeZeroes();
	}

	public Equation simplify() {
		forEach(Operand::enableSimpleOutput);
		return this;
	}

	private List<Operand> findAllOfSamePower(Operand current) {
		return operands.stream().filter(o -> o.powerEquals(current) && !o.equals(current)).collect(toList());
	}

	private Equation removeZeroes() {
		for ( Iterator<Operand> i = iterator(); i.hasNext(); )
			if (i.next().number().compareTo(BigDecimal.ZERO) == 0)
				i.remove();
		return this;
	}

	public void add(Operand operand) {
		operands.add(operand);
	}

	public short degree() {
		return degree == -1 ? degree = Collections.max(operands).power() : degree;
	}

	public Equation sort() {
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
		StringBuilder sb = new StringBuilder();
		forEach(sb::append); sb.append(" = 0");
		return StringUtils.removeStart(sb.toString(), " + ").trim();
	}
}
