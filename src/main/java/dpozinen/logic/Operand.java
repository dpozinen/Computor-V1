package dpozinen.logic;

public class Operand implements Comparable<Operand> {
	private double	num;
	private int		power;
	private	boolean	afterEquals;

	public Operand(double num, int power, boolean afterEquals) {
		this.num = num;
		this.power = power;
		this.afterEquals = afterEquals;
	}

	public double getNum() {
		return this.num;
	}

	public int getPower() {
		return this.power;
	}

	public boolean isAfterEquals() {
		return this.afterEquals;
	}

	public void changeSign() {
		this.num = -num;
	}

	public void add(Operand o) {
		this.num += o.num;
	}

	@Override
	public int compareTo(Operand o) {
		if (o.power > power)
            return -1;
        if (o.power < power)
            return 1;
        return 0;
	}
}