package dpozinen.logic;

public class Operand implements Comparable<Operand> {
	private double	num;
	private int		power;

	public Operand(){};

	public Operand(double num, int power) {
		this.num = num;
		this.power = power;
	}

	public double getNum() {
		return this.num;
	}

	public int getPower() {
		return this.power;
	}

	public void changeSign() {
		this.num = -num;
	}

	public void add(Operand o) {
		this.num += o.num;
	}
	public void setNum(double num) {
		this.num = num;
	}
	public void setPower(int power) {
		this.power = power;
	}

	@Override
	public int compareTo(Operand o) {
		if (o.power > power)
            return -1;
        if (o.power < power)
            return 1;
        return 0;
	}

	public boolean isOfPower(Operand o) {
		return (o.getPower() == power) && (o != this);
	}

	public String show() {
		return Math.abs(num) + " * X^" + power;
	}

	@Override
	public String toString() {
		return num + "X^" + power;
	}
}