package Q1;

public class IntAcc
{
	public static void main(String args[])
	{
		int a = 3;
		int b = 0;
		int c = 0;
		int d = 0;

		for (int i=0;i<10;i++)
		{
			b +=a;
			c=(3*(i+1))%10;
			d=b/10;
			System.out.println(d+"."+c);
		}
	}
}
