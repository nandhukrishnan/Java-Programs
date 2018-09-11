import java.io.*;
import java.util.*;
class findGcd
{
	int a[],n,gcd;
	findGcd(int a[],int n)
	{
		this.n=n;
		this.a=a;
	}
	void gcdFinder()
	{
		int small=a[0];
		for(int i=0;i<n;i++)
		{
			if(a[i]<small)
				small=a[i];
		}
		
		for(int i=1;i<=small;i++)
		{
			int counter=0;
			for(int j=0;j<n;j++)
			{
				if(a[j]%i==0)
					counter++;
			}
			if(counter==n)
				gcd=i;
		}
		System.out.println("GCD : "+gcd);
	}
}
class gcd
{
	public static void main(String args[])
	{
		Scanner s=new Scanner(System.in);
		System.out.println("Enter limit");
		int n=s.nextInt();
		int a[]=new int[n];
		System.out.println("Enter Elements");
		for(int i=0;i<n;i++)
		{
			a[i]=s.nextInt();
		}
		findGcd f=new findGcd(a,n);
		f.gcdFinder();
	}
}