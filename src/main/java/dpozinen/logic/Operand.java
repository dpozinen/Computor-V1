package dpozinen.logic;

public class Operand implements Comparable<Operand> {
	private double	num;
	private boolean	sign;
	private int		power;
	private	boolean isVar;

	public Operand(double num, boolean sign, int power, boolean isVar) {
		this.num = num;
		this.sign = sign;
		this.power = power;
		this.isVar = isVar;
	}

	public double getNum() {
		return this.num;
	}

	public boolean getSign() {
		return this.sign;
	}

	public boolean isVar() {
		return this.isVar;
	}

	public int getPower() {
		return this.power;
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