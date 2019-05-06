package Arithmetic_Test;
import java.lang.String;
public class Operation {
	private int max,Brackets,Decimal;
	private double[] Num_Con=new double[10],shu_new=new double[10];
	public String symbol=" ",QuiZ,symbol_sel,Symbol_Con=" ",symbol_new=" ";

	public Operation(int Max,int tyle)
	{
		max=Max;
		if ((tyle & 1)==1) symbol=symbol+"+";
		if ((tyle & 2)==2) symbol=symbol+"-";
		if ((tyle & 4)==4) symbol=symbol+"*";
		if ((tyle & 8)==8) symbol=symbol+"/";
		if ((tyle & 16)==16) Brackets=1;
		if ((tyle & 32)==32) Decimal=1;
	}
	
	
	public void Creat()
	{	
		Num_Con[0]=getnum();
		for(int i=1;i<=(int)(Math.random()*3)+1;i++)
		{
			Num_Con[i]=getnum();
			getchar();
			Symbol_Con=Symbol_Con+symbol_sel;
		}
		Creat_File();
		Display();
		QuiZ=QuiZ+"=";
	}
	
 	private void Display()
	{
		int no=1,ni=0;
		QuiZ=" ";
		if (Brackets==1 && Symbol_Con.charAt(1)=='(')
		{
			  while(true)
			  {
				  if (no>=Symbol_Con.length()) break;
				  QuiZ=QuiZ+Symbol_Con.substring(no,no+1);
				  if (Symbol_Con.charAt(no)==')')
				  {
					  no++;
					  if (no>=Symbol_Con.length()) break;
					  QuiZ=QuiZ+Symbol_Con.substring(no,no+1);
			      }
				  if (Decimal==1) QuiZ=QuiZ+String.valueOf(Num_Con[ni]);
				  else QuiZ=QuiZ+String.valueOf((int)Num_Con[ni]);
				  ni++;no++;
				  if (no==Symbol_Con.length()) break;
			  }
		}
		else if (Brackets==1&&Symbol_Con.charAt(1)!='(')
		{
			if (Decimal==1) QuiZ=QuiZ+String.valueOf(Num_Con[0]);
			  else QuiZ=QuiZ+String.valueOf((int)Num_Con[0]);
			ni++;
			while(true)
			  {
				if (no>=Symbol_Con.length()) break;
				QuiZ=QuiZ+Symbol_Con.substring(no,no+1);
				no++;
				if (no>=Symbol_Con.length()) break;
				  if (Symbol_Con.charAt(no)=='(')
				  {
					  //no++;
					  QuiZ=QuiZ+Symbol_Con.substring(no,no+1);
			      }
				  else no--;
				  if (Symbol_Con.charAt(no)==')'&&no<Symbol_Con.length())
				  {
					  no++;
					  QuiZ=QuiZ+Symbol_Con.substring(no,no+1);
			      }
				  if (Decimal==1) QuiZ=QuiZ+String.valueOf(Num_Con[ni]);
				  else QuiZ=QuiZ+String.valueOf((int)Num_Con[ni]);
				  ni++;no++;
				  if (no==Symbol_Con.length()) break;
			  }
		}
		else if(Brackets==0)
		{
			if (Decimal==1) QuiZ=QuiZ+String.valueOf(Num_Con[ni]);
			  else QuiZ=QuiZ+String.valueOf((int)Num_Con[ni]);
			ni++;
			while(true)
			  {
				if (no>=Symbol_Con.length()) break;
				QuiZ=QuiZ+Symbol_Con.substring(no,no+1);
				if (Decimal==1) 
					{QuiZ=QuiZ+String.valueOf(Num_Con[ni]);}
				  else 
					  {QuiZ=QuiZ+String.valueOf((int)Num_Con[ni]);}
				 ni++;
				 no++;
				 if (no>=Symbol_Con.length()) break;
			  }
		}
	}
	
	private void Creat_File()
	{
		if (Brackets==1)
		{
		int id=Math.abs((int)(Math.random()*(Symbol_Con.length()-1))); //定义 取绝对值 
		String temp1,temp_mid,temp_2;
		temp1=Symbol_Con.substring(0, id+1);
		temp_mid=Symbol_Con.substring(id+1,id+2);
		if ((id+2) < (Symbol_Con.length()))  
			temp_2=Symbol_Con.substring(id+2,Symbol_Con.length());
		else 
			temp_2="";
		
		Symbol_Con=temp1+"("+temp_mid+")"+temp_2;
		symbol_new=temp1+temp_2;
		if (id==1)
		{
			if (Symbol_Con.charAt(1)=='+') 
				shu_new[0] = Num_Con[0]+Num_Con[1];
			else if (Symbol_Con.charAt(1)=='-') 
				shu_new[0] = Num_Con[0]-Num_Con[1];
			else if (Symbol_Con.charAt(1)=='*') 
				shu_new[0] = Num_Con[0]*Num_Con[1];
			else if (Symbol_Con.charAt(1)=='/') 
				shu_new[0] = Num_Con[0]/Num_Con[1];
			for (int i=2;i<Num_Con.length;i++)
			{
				shu_new[i-1]=Num_Con[i];
			}
		}
		else if (id==2)
		{
				shu_new[0]=Num_Con[0];
			if (Symbol_Con.charAt(3)=='+') 
				shu_new[1]=Num_Con[1]+Num_Con[2];
			else if (Symbol_Con.charAt(3)=='-') 
				shu_new[1]=Num_Con[1]-Num_Con[2];
			else if (Symbol_Con.charAt(3)=='*') 
				shu_new[1]=Num_Con[1]*Num_Con[2];
			else if (Symbol_Con.charAt(3)=='/') 
				shu_new[1]=Num_Con[1]/Num_Con[2];
			for (int i=3;i<Num_Con.length;i++)
			{
				shu_new[i-1]=Num_Con[i];
			}
			
		}
		else if (id==3)
		{
			shu_new[0]=Num_Con[0];
			shu_new[1]=Num_Con[1];
			if (Symbol_Con.charAt(3)=='+') 
				shu_new[2]=Num_Con[2]+Num_Con[3];
			else if (Symbol_Con.charAt(3)=='-') 
				shu_new[2]=Num_Con[2]-Num_Con[3];
			else if (Symbol_Con.charAt(3)=='*') 
				shu_new[2]=Num_Con[2]*Num_Con[3];
			else if (Symbol_Con.charAt(3)=='/') 
				shu_new[2]=Num_Con[2]/Num_Con[3];
		}
		}
		else
		{
			symbol_new=Symbol_Con;
			for (int i=0;i<Num_Con.length;i++)
			{
				shu_new[i]=Num_Con[i];
			}
		}
		if (Decimal==0)
		{
			for (int i=0;i<shu_new.length;i++)
				shu_new[i]=(int) shu_new[i];
		}
	}
	
	private double getnum()
	{
		double a=(Math.random()*max)+1;
		a=((int)(a*100))/100.0;
		return (a);
	}
	 
	public void getchar()
	{
		int Mark=(int)(Math.random()*(symbol.length()-1))+1;
		symbol_sel=symbol.substring(Mark, Mark+1);
	}
}