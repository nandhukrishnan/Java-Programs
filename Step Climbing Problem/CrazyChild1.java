/*Algorithm 1 o(n^2)*/

import java.io.*;
import java.util.*;
class WaysCounter
{
	static int count=0;
	int ways(int n)
	{
		count++;
		if(n==0||n==1)
		{
			return 1;
		}
		else if(n==2)
		{
			return 2;
		}
		else
		{
			return ways(n-1)+ways(n-2);
		}
	}
}
class CrazyChild1
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