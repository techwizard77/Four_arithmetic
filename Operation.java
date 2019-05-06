package Arithmetic_Test;
import java.lang.String;
public class Operation {
	private int max,brackets,Decimal;
	private double[] Num_Con=new double[10],shu_new=new double[10];
	public String symbol=" ",ti,symbol_sel,Symbol_Con=" ",symbol_new=" ";
	
	
	public Operation(int Max,int tyle)
	{
		max=Max;
		if ((tyle & 1)==1) symbol=symbol+"+";
		if ((tyle & 2)==2) symbol=symbol+"-";
		if ((tyle & 4)==4) symbol=symbol+"*";
		if ((tyle & 8)==8) symbol=symbol+"/";
		if ((tyle & 16)==16) brackets=1;
		if ((tyle & 32)==32) Decimal=1;
	}
	
	
	public void creat()
	{	
		Num_Con[0]=getnum();
		for(int i=1;i<=(int)(Math.random()*3)+1;i++)
		{
			Num_Con[i]=getnum();
			getchar();
			Symbol_Con=Symbol_Con+symbol_sel;
		}
		creatf();
		show();
		count();
		ti=ti+"=";
	}
	
	private void count()
	{
		int no=1,ni=0,top1=0,top2=0;
		double a[]={0,0,0,0};
		char[] b=new char[5];
		a[top1]=shu_new[ni];
		top1++;
		ni++;
		while(true)
		{
			if (no>=symbol_new.length()) break;
			  char ch=symbol_new.charAt(no);
			  no++;
			   if (ch=='*'||ch=='/')
			  {
				  double s=shu_new[ni];
				  ni++;
				  if (ch=='*') a[top1-1]=a[top1-1]*s;
				  else if (ch=='/') a[top1-1]=a[top1-1]/s;
				  
			  }
			  else if (ch=='+'||ch=='-')
			  {
				  double s=shu_new[ni];
				  ni++;
				  if (top2==1&&top1==2)
				  {
					  if (b[0]=='+') a[0]=a[0]+a[1];
					  else if (b[0]=='-') a[0]=a[0]-a[1];
					  
					  b[1]=ch;
					  a[1]=s;
				  }
				  else
				  {
					  b[top2]=ch;
					  top2++;
					  a[top1]=s;
					  top1++;
				  }
			  }
		}
	if (top2==1&&top1==2)
	{
		if (b[0]=='+') a[0]=a[0]+a[1];
		  else if (b[0]=='-') a[0]=a[0]-a[1];
	}
	
	}
	
 	private void show()
	{
		int no=1,ni=0;
		ti=" ";
		if (brackets==1&&Symbol_Con.charAt(1)=='(')
		{
			  while(true)
			  {
				  if (no>=Symbol_Con.length()) break;
				  ti=ti+Symbol_Con.substring(no,no+1);
				  if (Symbol_Con.charAt(no)==')')
				  {
					  no++;
					  if (no>=Symbol_Con.length()) break;
					  ti=ti+Symbol_Con.substring(no,no+1);
			      }
				  if (Decimal==1) ti=ti+String.valueOf(Num_Con[ni]);
				  else ti=ti+String.valueOf((int)Num_Con[ni]);
				  ni++;no++;
				  if (no==Symbol_Con.length()) break;
			  }
		}
		else if (brackets==1&&Symbol_Con.charAt(1)!='(')
		{
			if (Decimal==1) ti=ti+String.valueOf(Num_Con[0]);
			  else ti=ti+String.valueOf((int)Num_Con[0]);
			ni++;
			while(true)
			  {
				if (no>=Symbol_Con.length()) break;
				ti=ti+Symbol_Con.substring(no,no+1);
				no++;
				if (no>=Symbol_Con.length()) break;
				  if (Symbol_Con.charAt(no)=='(')
				  {
					  //no++;
					  ti=ti+Symbol_Con.substring(no,no+1);
			      }
				  else no--;
				  if (Symbol_Con.charAt(no)==')'&&no<Symbol_Con.length())
				  {
					  no++;
					  ti=ti+Symbol_Con.substring(no,no+1);
			      }
				  if (Decimal==1) ti=ti+String.valueOf(Num_Con[ni]);
				  else ti=ti+String.valueOf((int)Num_Con[ni]);
				  ni++;no++;
				  if (no==Symbol_Con.length()) break;
			  }
		}
		else if(brackets==0)
		{
			if (Decimal==1) ti=ti+String.valueOf(Num_Con[ni]);
			  else ti=ti+String.valueOf((int)Num_Con[ni]);
			ni++;
			while(true)
			  {
				if (no>=Symbol_Con.length()) break;
				ti=ti+Symbol_Con.substring(no,no+1);
				if (Decimal==1) 
					{ti=ti+String.valueOf(Num_Con[ni]);}
				  else 
					  {ti=ti+String.valueOf((int)Num_Con[ni]);}
				 ni++;
				 no++;
				 if (no>=Symbol_Con.length()) break;
			  }
		}
	}
	
	private void creatf()
	{
		if (brackets==1)
		{
		int id=Math.abs((int)(Math.random()*(Symbol_Con.length()-1)));
		String a,b,c;
		a=Symbol_Con.substring(0, id+1);
		b=Symbol_Con.substring(id+1,id+2);
		if ((id+2) < (Symbol_Con.length()))  c=Symbol_Con.substring(id+2,Symbol_Con.length());
		else c="";
		Symbol_Con=a+"("+b+")"+c;
		symbol_new=a+c;
		if (id==1)
		{
			if (Symbol_Con.charAt(1)=='+') shu_new[0]=Num_Con[0]+Num_Con[1];
			else if (Symbol_Con.charAt(1)=='-') shu_new[0]=Num_Con[0]-Num_Con[1];
			else if (Symbol_Con.charAt(1)=='*') shu_new[0]=Num_Con[0]*Num_Con[1];
			else if (Symbol_Con.charAt(1)=='/') shu_new[0]=Num_Con[0]/Num_Con[1];
			for (int i=2;i<Num_Con.length;i++)
			{
				shu_new[i-1]=Num_Con[i];
			}
		}
		else if (id==2)
		{
			shu_new[0]=Num_Con[0];
			if (Symbol_Con.charAt(3)=='+') shu_new[1]=Num_Con[1]+Num_Con[2];
			else if (Symbol_Con.charAt(3)=='-') shu_new[1]=Num_Con[1]-Num_Con[2];
			else if (Symbol_Con.charAt(3)=='*') shu_new[1]=Num_Con[1]*Num_Con[2];
			else if (Symbol_Con.charAt(3)=='/') shu_new[1]=Num_Con[1]/Num_Con[2];
			for (int i=3;i<Num_Con.length;i++)
			{
				shu_new[i-1]=Num_Con[i];
			}
			
		}
		else if (id==3)
		{
			shu_new[0]=Num_Con[0];
			shu_new[1]=Num_Con[1];
			if (Symbol_Con.charAt(3)=='+') shu_new[2]=Num_Con[2]+Num_Con[3];
			else if (Symbol_Con.charAt(3)=='-') shu_new[2]=Num_Con[2]-Num_Con[3];
			else if (Symbol_Con.charAt(3)=='*') shu_new[2]=Num_Con[2]*Num_Con[3];
			else if (Symbol_Con.charAt(3)=='/') shu_new[2]=Num_Con[2]/Num_Con[3];
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
		int le=(int)(Math.random()*(symbol.length()-1))+1;
		symbol_sel=symbol.substring(le, le+1);
	}
}