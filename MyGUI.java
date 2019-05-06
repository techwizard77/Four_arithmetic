package Arithmetic_Test;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;

public class MyGUI extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private JCheckBox checkbox[];
	private JComboBox<Integer> comb_num,comb_max;
	private int num=1;
	private int max=10;
	private int style =0;
	private int I_Con=0;
	private JButton Button_Creat,Button_Print;
	private JTextField[] quiz=new JTextField[50];
	private Operation[] t=new Operation[50];
	
	public MyGUI()
	{
		this.setTitle("//四则运算生成器//");
		this.setSize(800,600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JToolBar toolbar1 = new JToolBar();   //建立容器
		
		this.getContentPane().add(toolbar1, "North");
		
		String str[][]={{"题目个数 ："},{"加","减","乘","除","括号","小数"},{" 范围：0~"}};
		
		toolbar1.add(new JLabel(str[0][0]));
		Integer num1[]={1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50};
		this.comb_num=new JComboBox<Integer>(num1);
		this.comb_num.addActionListener(this);
		toolbar1.add(this.comb_num);
		toolbar1.add(new JLabel("      选择运算符："));
		this.checkbox=new JCheckBox[str[1].length];
		
		for (int i=0;i<str[1].length;i++)
		{
			this.checkbox[i]=new JCheckBox(str[1][i]);
			toolbar1.add(this.checkbox[i]);
			this.checkbox[i].addActionListener(this);
		}
		toolbar1.add(new JLabel("       "));
		toolbar1.add(new JLabel(str[2][0]));
		Integer max1[]={10,20,30,40,50,60,70,80,90,100};
		this.comb_max=new JComboBox<Integer>(max1);
		this.comb_max.addActionListener(this);
		toolbar1.add(this.comb_max);
		toolbar1.add(new JLabel("     "));
		
		
		this.Button_Creat=new JButton("出题");
		this.Button_Creat.addActionListener(this);
		toolbar1.add(Button_Creat);
		toolbar1.add(new JLabel("     "));
		
		
		this.Button_Print=new JButton("打印");
		this.Button_Print.addActionListener(this);
		toolbar1.add(Button_Print);
		toolbar1.add(new JLabel(""));
		JPanel equ=new JPanel();
		this.getContentPane().add(equ, "Center");		
		equ.setLayout(new GridLayout(0,5));
		
		
		for (int i=0;i<50;i++)
		{			
			this.quiz[i]=new JTextField (" ");
			equ.add(this.quiz[i]);
		}

		this.setVisible(true);
		
	}
	
	public void Button_Creat_Test()
	{
		if (style==0) 
		{
			JOptionPane.showMessageDialog(this, "请选择运算符号");
			return;
		}
		if (style==16||style==32) 
		{
			if (style==16)
				JOptionPane.showMessageDialog(this, "(只选择了括号)"
					+ "请选择四则运算符号");
			if (style==32)
				JOptionPane.showMessageDialog(this, "(只选择了小数)"
					+ "请选择四则运算符号");
			return;
		}
		
		JOptionPane.showMessageDialog(this, "生成新的题目");
		for (int i=0;i<num;i++)
		{
		
			t[i]=new Operation(max,style);//
			t[i].Creat();
			this.quiz[i].setText(t[i].QuiZ);
		}
		I_Con++;
	}
	
    public  void Print() throws Exception{
            File file = new File("D:" + File.separator + "JAVA" + File.separator + "Arithmetic_QuiZ.txt");
            if(!file.getParentFile().exists()){
                file.getParentFile().mkdirs();
            }
            Writer out = new FileWriter(file);
            for (int i=0;i<num;i++)
            {
            	
            	out.write(t[i].QuiZ);
            	out.write("\r\n");
            }
            out.close();
            JOptionPane.showMessageDialog(this, "打印成功,文件保存至D:java");
        }
	
	
	public void actionPerformed(ActionEvent ev)
	{
		if (ev.getSource()==this.Button_Creat)
		{
			Button_Creat_Test();//
		}
		if (ev.getSource()==this.Button_Print)
		{
			if (I_Con==0) 
				{
				JOptionPane.showMessageDialog(this, "打印失败");
				return ;
				}
			try {
				Print();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (ev.getSource()instanceof JComboBox<?>||ev.getSource()instanceof JCheckBox||ev.getSource()instanceof JMenuItem)
		{
			Object obj1=this.comb_num.getSelectedItem();
			num=((Integer)obj1).intValue();
			Object obj2=this.comb_max.getSelectedItem();
			max=((Integer)obj2).intValue();
			if (ev.getActionCommand().equals("加")) style=style^1; 
			if (ev.getActionCommand().equals("减")) style=style^2; 
			if (ev.getActionCommand().equals("乘")) style=style^4; 
			if (ev.getActionCommand().equals("除")) style=style^8; 
			if (ev.getActionCommand().equals("括号")) style=style^16; 
			if (ev.getActionCommand().equals("小数")) style=style^32;
		}
		

		
	}

	public static void main(String args[])
	{
		new MyGUI();
	}
}