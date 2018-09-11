
/*Huffman coding Naive algorithm implementation using Priority Queue*/
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Comparator;
 
class Counter
{
	static int count=0;
}

class HuffmanNode 
{
    int data;
    char c;
 
    HuffmanNode left;
    HuffmanNode right;
}
 
class MyComparator implements Comparator<HuffmanNode> 
{
    public int compare(HuffmanNode x, HuffmanNode y)
    {
        return x.data - y.data;
    }
}
 
public class HuffmanNaive 
{
	static String code[][];static int xc=0;
    public static void printCode(HuffmanNode root, String s)
    {
        if (root.left== null && root.right == null && Character.isLetter(root.c)) 
		{
			System.out.println(root.c + ":" + s);
			code[xc][0]=""+root.c;
			code[xc][1]=""+s;
			xc++;
            return;
        }
 
        printCode(root.left, s + "0");
        printCode(root.right, s + "1");
    }
 
    public static void main(String[] args)
    {
        Scanner s = new Scanner(System.in);
		char[] tempCharArray = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j' };
        int[] tempCharfreq = { 5, 9, 12, 13, 16, 45 ,58 , 65, 72, 80};
		
		int allChars[]=new int[26];
		String inputString="";
		System.out.println("Enter your string");
		inputString=s.nextLine();
		char inputStringArray[]=inputString.toCharArray();
				
		for(int i=0;i<26;i++)
		{
			allChars[i]=0;
		}
		
		for(int i=0;i<inputString.length();i++)
		{
			if(allChars[inputStringArray[i]-97]!=1)
				allChars[inputStringArray[i]-97]=1;
		}
		
		int n=0;
		for(int i=0;i<26;i++)
		{
			if(allChars[i]==1)
			{
				n++;
			}	
		}
		
		code=new String[n][2];
		
		char[] charArray = new char[n];
        int[] charfreq = new int[n];
		
		int c=0;
		for(int i=0;i<26;i++)
		{
			if(allChars[i]==1)
			{
				charArray[c]=tempCharArray[i];
				charfreq[c]=tempCharfreq[i];
				c++;
			}
		}	
 
        PriorityQueue<HuffmanNode> q= new PriorityQueue<HuffmanNode>(n, new MyComparator());
 
        for (int i = 0; i < n; i++) 
		{
            HuffmanNode hn = new HuffmanNode();
 
            hn.c = charArray[i];
            hn.data = charfreq[i];
 
            hn.left = null;
            hn.right = null;
 
            q.add(hn);
			
			Counter.count++;
        }
 
        HuffmanNode root = null;
 
        while (q.size() > 1)			
		{
            HuffmanNode x = q.peek();
            q.poll();
            HuffmanNode y = q.peek();
            q.poll();
 
            HuffmanNode f = new HuffmanNode();
 
            f.data = x.data + y.data;
            f.c = '-';
            f.left = x;
            f.right = y; 
            root = f;
 
            q.add(f);
			
			Counter.count++;
        }
        printCode(root, "");
		
		System.out.println("Count: "+Counter.count);
		System.out.print("\nEncoded String is: ");
		for(int i=0;i<inputString.length();i++)
		{
			char cr=inputStringArray[i];
			for(int j=0;j<xc;j++)
			{
				if(code[j][0].equals(""+cr))
					System.out.print(""+code[j][1]);
			}
		}
    }
}