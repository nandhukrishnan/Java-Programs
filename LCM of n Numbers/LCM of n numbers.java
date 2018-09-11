import java.io.*;
import java.util.*;
class findLcm
{
	int a[],n,lcm;
	findLcm(int a[],int n)
	{
		this.n=n;
		this.a=a;
	}
	void lcmFinder()
	{
		int x=1;
		while(true)
		{
			int counter=0;
			for(int i=0;i<n;i++)
			{
				if(x%a[i]==0)
					counter++;
			}
			if(counter==n)
			{
				lcm=x;
				break;
			}
			x++;
		}
		System.out.println("LCM : "+lcm);
	}
}
class lcm
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
		findLcm f=new findLcm(a,n);
		f.lcmFinder();
	}
}