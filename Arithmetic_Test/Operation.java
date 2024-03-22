package Arithmetic_Test;

import java.lang.String;
import java.util.Random;

public class Operation {
	private int max, flag;// max为参数范围，flag表示整数（flag=1）还是真分数运算（flag=0）
	public String equString, ansString;// 存式子和答案
	private char[] syms = new char[4];// 运算符
	private char symbol;// 运算符
	private int idx;// 运算符

	public Operation(int Max, int choice) {
		max = Max;
		if ((choice & 1) == 1)
			syms[idx++] = '+';
		if ((choice & 2) == 2)
			syms[idx++] = '-';
		if ((choice & 4) == 4)
			syms[idx++] = '*';
		if ((choice & 8) == 8)
			syms[idx++] = '/';

		if ((choice & 16) == 16)
			flag = 1;
		else
			flag = 0;

	}

	public void Create() {
		Random random = new Random();
		if (flag == 1) {
			int num1 = random.nextInt(max);
			int num2 = random.nextInt(max);

			symbol = syms[random.nextInt(idx)];
			this.equString = Integer.toString(num1) + symbol + Integer.toString(num2) + "=";
		} else if (flag == 0) {

			int add = random.nextInt(2);
			int num1, num2;
			if (add == 1) {
				num1 = random.nextInt(max); // 带分数的整数部分
				num2 = random.nextInt(max);
			} else {
				num1 = 0;
				num2 = 0;
			}

			int up1 = random.nextInt(max - 1) + 1; // 分子
			int up2 = random.nextInt(max - 1) + 1;

			int down1 = random.nextInt(max - up1) + up1 + 1; // 分母
			int down2 = random.nextInt(max - up2) + up2 + 1;

			symbol = syms[random.nextInt(idx)]; // 运算符

			String prt1 = Integer.toString(up1) + "/" + Integer.toString(down1);
			String prt2 = Integer.toString(up2) + "/" + Integer.toString(down2);

			if (num1 != 0) {
				prt1 = Integer.toString(num1) + "'" + prt1;
			}
			if (num2 != 0) {
				prt2 = Integer.toString(num2) + "'" + prt2;
			}

			if (symbol == '/') {
				prt1 = "(" + prt1 + ")";
				prt2 = "(" + prt2 + ")";
			}
			this.equString = prt1 + " " + symbol + " " + prt2 + "=";

			// 它的计算直接在这里做
			up1 = num1 * down1 + up1; // 第一个带分数化为假分数并约分
			int y1 = gcd(up1, down1);
			up1 /= y1;
			down1 /= y1;

			up2 = num2 * down2 + up2;// 第二个带分数化为假分数并约分
			int y2 = gcd(up2, down2);
			up2 /= y2;
			down2 /= y2;

			System.out.println(up1 + "/" + down1);
			System.out.println(up2 + "/" + down2);

			int num = 0, up, down;// 结果整数，分子，分母
			if (symbol == '+' || symbol == '-') {// 加减
				int b = lcm(down1, down2);// 找两个分数的最小公倍数
				up1 *= (b / down1);
				up2 *= (b / down2);

				if (symbol == '+') {
					up = up1 + up2;
				} else {
					up = up1 - up2;
				}

				down = b;

			} else {// 乘除
				if (symbol == '/') {
					int t = up2;
					up2 = down2;
					down2 = t;
				}

				up = up1 * up2;
				down = down1 * down2;
				// System.out.println("**" + up + "," + down);

			}

			int y = gcd(up, down);
			up /= y;
			down /= y;

			if (up >= down) {
				num = up / down;
				if (up % down == 0) {
					this.ansString = Integer.toString(num);
				} else {
					up = up % down;
					this.ansString = Integer.toString(num) + "'" + Integer.toString(up) + "/" + Integer.toString(down);
				}
			} else {
				this.ansString = Integer.toString(up) + "/" + Integer.toString(down);
			}

		}
	}

	// 最大公约数
	public int gcd(int p1, int p2) {
		int t;
		while (p2 != 0) {
			t = p1 % p2;
			p1 = p2;
			p2 = t;
		}
		return p1;
	}

	// 最小公倍数
	public int lcm(int p1, int p2) {
		int yue = gcd(p1, p2);
		return p1 * p2 / yue;
	}

	public void Calculate() {
		if (flag == 1) {
			int p = equString.indexOf(symbol);
			String prt1 = equString.substring(0, p);
			String prt2 = equString.substring(p + 1, equString.indexOf("="));

			int num1 = Integer.parseInt(prt1);
			int num2 = Integer.parseInt(prt2);

			if (symbol == '+')
				ansString = Integer.toString(num1 + num2);
			if (symbol == '-')
				ansString = Integer.toString(num1 - num2);
			if (symbol == '*')
				ansString = Integer.toString(num1 * num2);
			if (symbol == '/')
				ansString = Integer.toString(num1 / num2);

		}
	}
}