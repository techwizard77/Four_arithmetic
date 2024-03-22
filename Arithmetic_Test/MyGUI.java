package Arithmetic_Test;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;

public class MyGUI extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JCheckBox checkbox[];// 选择加减乘除

	private JComboBox<Integer> comb_num, comb_max; // 题目个数和范围
	private int num = 1; // 题目个数
	private int right_num = 0;// 正确题目个数
	private int max = 10; // 参数范围
	private int style = 0; // 判断是否合法生成
	private int I_Con = 0;
	private JButton Button_Creat, Button_Print, Button_Judge, Button_flush; // 生成和打印按钮
	private JTextField[] quiz = new JTextField[104];// 最多五十个框
	private Operation[] t = new Operation[50];// 最多五十道题目

	public MyGUI() {
		this.setTitle("***四则运算生成器***");
		this.setSize(1000, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JToolBar toolbar1 = new JToolBar(); // 建立容器

		this.getContentPane().add(toolbar1, "North");

		String str[][] = { { "题目个数 ：" }, { "加", "减", "乘", "除" }, { " 范围：0~" } };

		toolbar1.add(new JLabel(str[0][0]));
		Integer num1[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25,
				26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50 };
		this.comb_num = new JComboBox<Integer>(num1);
		this.comb_num.addActionListener(this);
		toolbar1.add(this.comb_num);

		toolbar1.add(new JLabel("       "));
		toolbar1.add(new JLabel("参数类型："));
		JRadioButton c1 = new JRadioButton("整数");
		JRadioButton c2 = new JRadioButton("真分数");
		c1.addActionListener(this);
		c2.addActionListener(this);
		ButtonGroup group = new ButtonGroup();
		group.add(c1);
		group.add(c2);
		toolbar1.add(c1);
		toolbar1.add(c2);

		toolbar1.add(new JLabel("       "));
		toolbar1.add(new JLabel("      选择运算符："));
		this.checkbox = new JCheckBox[str[1].length];

		for (int i = 0; i < str[1].length; i++) {
			this.checkbox[i] = new JCheckBox(str[1][i]);
			toolbar1.add(this.checkbox[i]);
			this.checkbox[i].addActionListener(this);
		}
		toolbar1.add(new JLabel("       "));
		toolbar1.add(new JLabel(str[2][0]));

		Integer max1[] = { 10, 20, 30, 40, 50, 60, 70, 80, 90, 100 };
		this.comb_max = new JComboBox<Integer>(max1);
		this.comb_max.addActionListener(this);
		toolbar1.add(this.comb_max);
		toolbar1.add(new JLabel("     "));

		this.Button_Creat = new JButton("	 出题");
		this.Button_Creat.addActionListener(this);
		toolbar1.add(Button_Creat);
		toolbar1.add(new JLabel("     "));

		this.Button_Judge = new JButton("	 判断");
		this.Button_Judge.addActionListener(this);
		toolbar1.add(Button_Judge);
		toolbar1.add(new JLabel("     "));

		this.Button_flush = new JButton("	 清空");
		this.Button_flush.addActionListener(this);
		toolbar1.add(Button_flush);
		toolbar1.add(new JLabel("     "));

		this.Button_Print = new JButton("	 打印");
		this.Button_Print.addActionListener(this);
		toolbar1.add(Button_Print);
		toolbar1.add(new JLabel(""));

		// 产生方框
		JPanel equ = new JPanel();
		this.getContentPane().add(equ, "Center");
		equ.setLayout(new GridLayout(0, 8));
		for (int i = 0; i < 104; i++) {

			if (i % 2 == 0) {
				this.quiz[i] = new JTextField(" ");
				this.quiz[i].setEditable(false);
			}

			else {
				this.quiz[i] = new JTextField("");

			}

			equ.add(this.quiz[i]);
		}

		this.setVisible(true);

	}

	public void Button_Creat_Test() {

		if ((style & 16) == 0 && (style & 32) == 0) {
			JOptionPane.showMessageDialog(this, "请选择运算参数类型");
			return;
		} else if ((style & 1) == 0 && (style & 2) == 0 && (style & 4) == 0 && (style & 8) == 0) {
			// 没有选择加减乘除，不合理
			JOptionPane.showMessageDialog(this, "(只选择了参数类型)"
					+ "请选择四则运算符号");
			return;
		}

		JOptionPane.showMessageDialog(this, "生成新的题目");
		for (int i = 0; i < 2 * num; i += 2) {

			t[i] = new Operation(max, style);//
			t[i].Create();
			this.quiz[i].setText(t[i].equString);
			this.quiz[i].setBackground(new Color(238, 238, 238));
			this.quiz[i + 1].setText("");
			System.out.println(t[i].equString);
		}
		I_Con++;
	}

	public void Button_Judge_Test() {
		this.right_num = 0;// 正确题目数归零
		for (int i = 0; i < 2 * num; i += 2) {

			String res = this.quiz[i + 1].getText();

			t[i].Calculate();
			if (res != null && res.equals(t[i].ansString)) {
				this.quiz[i].setBackground(Color.green);
				this.right_num++;
			} else {
				this.quiz[i].setBackground(Color.RED);
			}

			this.quiz[i].setText(t[i].equString + t[i].ansString);

		}

		JOptionPane.showMessageDialog(this, "判断成功" + "\n" + "总题数：" + this.num + "\n" + "正确数:" + this.right_num);
		return;

	}

	public void Print() throws Exception {
		File file = new File("F:" + File.separator + "JAVA" + File.separator + "Arithmetic_QuiZ.txt");
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		Writer out = new FileWriter(file);
		for (int i = 0; i < num * 2; i += 2) {

			out.write(t[i].equString);
			out.write("\r\n");
		}
		out.close();
		JOptionPane.showMessageDialog(this, "打印成功,文件保存至F:java");
	}

	public void actionPerformed(ActionEvent ev) {
		if (ev.getSource() == this.Button_Creat) {
			Button_Creat_Test();//
		}

		if (ev.getSource() == this.Button_flush) {
			for (int i = 0; i < 2 * num; i += 2) {
				this.quiz[i].setText("");
				this.quiz[i + 1].setText("");
				this.quiz[i].setBackground(new Color(238, 238, 238));
				this.quiz[i + 1].setBackground(Color.white);
			}
		}

		if (ev.getSource() == this.Button_Judge) {
			if (I_Con == 0) {
				JOptionPane.showMessageDialog(this, "判断失败");
				return;
			} else {
				try {
					Button_Judge_Test();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}
		if (ev.getSource() == this.Button_Print) {
			if (I_Con == 0) {
				JOptionPane.showMessageDialog(this, "打印失败");
				return;
			}
			try {
				Print();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (ev.getSource() instanceof JComboBox<?> || ev.getSource() instanceof JCheckBox
				|| ev.getSource() instanceof JMenuItem || ev.getSource() instanceof JRadioButton) {
			Object obj1 = this.comb_num.getSelectedItem();
			num = ((Integer) obj1).intValue();
			Object obj2 = this.comb_max.getSelectedItem();
			max = ((Integer) obj2).intValue();

			if (ev.getActionCommand().equals("加"))
				style = style ^ 1;
			if (ev.getActionCommand().equals("减"))
				style = style ^ 2;
			if (ev.getActionCommand().equals("乘"))
				style = style ^ 4;
			if (ev.getActionCommand().equals("除"))
				style = style ^ 8;
			if (ev.getActionCommand().equals("整数"))
				style = style ^ 16;
			if (ev.getActionCommand().equals("真分数"))
				style = style ^ 32;

		}

	}

	public static void main(String args[]) {
		new MyGUI();
	}
}