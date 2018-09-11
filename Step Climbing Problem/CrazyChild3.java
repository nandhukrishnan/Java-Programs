/*Algorithm 3 o(logn)*/


import java.io.*;
import java.util.*;
class WaysCounter
{
    static int count=0;     
    int ways(int n)
	{
		int a[][] = {{1,1},{1,0}};
		if (n == 0)
			return 0;
		power(a, n);
		return a[0][1];
	}
	void power(int a[][], int n)
	{
		if( n == 0 || n == 1)
			return;
		int b[][] = {{1,1},{1,0}};
		power(a, n/2);
		multiply(a, a);
		if (n%2 != 0)
			multiply(a, b);
	}
 
	void multiply(int a[][], int b[][])
	{
		count++;
		int temp1=a[0][0]*b[0][0] + a[0][1]*b[1][0];
		int temp2=a[0][0]*b[0][1] + a[0][1]*b[1][1];
		int temp3=a[1][0]*b[0][0] + a[1][1]*b[1][0];
		int temp4=a[1][0]*b[0][1] + a[1][1]*b[1][1];
	 
		a[0][0]=temp1;
		a[0][1]=temp2;
		a[1][0]=temp3;
		a[1][1]=temp4;
	}
}

class CrazyChild3
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
