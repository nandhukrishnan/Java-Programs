/*Algorithm 2 o(n^1.6) Golden ratio*/

import java.io.*;
import java.util.*;
class WaysCounter
{
	static int count=0;
	int ways(int n)
	{
		int a=1,b=1,temp=1;
		for(int i=1;i<n;i++)
		{
			count++;
			temp=a+b;
			a=b;
			b=temp;
		}
		return temp;
	}
}
class CrazyChild2
{
	public static void main(String argv[])
	{
		Scanner s=new Scanner(System.in);
		System.out.println("Enter number of steps\n");
		int n=s.nextInt();
	
		WaysCounter wc=new WaysCounter();
		for(int i=0;i<=n;i++)
		{
			System.out.println("n: "+i+" Ways: "+wc.ways(i)+" Steps: "+WaysCounter.count);
			WaysCounter.count=0;
		}
	}
}