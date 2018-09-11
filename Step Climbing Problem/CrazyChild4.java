/*Algorithm 4 o(logn)*/


import java.io.*;
import java.util.*;
class WaysCounter
{
    static int count=0;
    static int f[];
    public static int ways(int n)
    {
		count++;
        if (n == 0)
        {    
            return 0;
		}
        if (n == 1 || n == 2)
        {
             return (f[n] = 1);
		}
        if (f[n] != 0)
        {
			return f[n];
		}      
        
		int k;		
		
		if(n%2==0)
		{
			k=n/2;
			f[n]=(ways(k) * ways(k) + ways(k - 1) * ways(k - 1));
		}
		else
		{
			k=(n+1)/2;
			f[n]=(2 * ways(k - 1) + ways(k))* ways(k);
		}	    
        
        return f[n];
		
    }
}

class CrazyChild4
{
    public static void main(String args[]) 
    {
		Scanner s=new Scanner(System.in);
		System.out.println("Enter number of steps\n");
		int n=s.nextInt();
	
		WaysCounter wc=new WaysCounter();
		
		WaysCounter.f= new int[1000];
        for(int i=0;i<=n;i++)
		{
			System.out.println("n: "+i+" Ways: "+wc.ways(i)+" Steps: "+WaysCounter.count);
			WaysCounter.count=0;
		}
    }
}
