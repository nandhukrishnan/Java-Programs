/*Huffan coding using Binomial Heap instead of priority queue*/

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
 
public class HuffmanBinomialHeap 
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
        int[] tempCharfreq = { 5, 9, 12, 13, 16, 45 ,58 ,65, 72, 80};
		
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
		
		BinomialHeap bh = new BinomialHeap(); 
        
		for (int i = 0; i < n; i++) 
		{
            HuffmanNode hn = new HuffmanNode();
 
            hn.c = charArray[i];
            hn.data = charfreq[i];
 
            hn.left = null;
            hn.right = null;
 
            bh.insert(hn);
        }
 
        HuffmanNode root = null;
 
        while (bh.getSize() > 1)			
		{
            HuffmanNode x = bh.extractMin();
            HuffmanNode y = bh.extractMin();
 
            HuffmanNode f = new HuffmanNode();
 
            f.data = x.data + y.data;
            f.c = '-';
            f.left = x;
            f.right = y; 
            root = f;
 
            bh.insert(f);
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


class BinomialHeapNode 
{
	int degree;
	HuffmanNode bhn;
	BinomialHeapNode parent;
	BinomialHeapNode sibling;
	BinomialHeapNode child;

	public BinomialHeapNode(HuffmanNode thn) 
	{
		bhn=thn;
		degree = 0;
		parent = null;
		sibling = null;
		child = null;
	}

	public BinomialHeapNode reverse(BinomialHeapNode sibl) 
	{
		BinomialHeapNode ret;
		if (sibling != null)
			ret = sibling.reverse(this);
		else
			ret = this;
		sibling = sibl;
		return ret;
	}

	public BinomialHeapNode findMinNode() 
	{
		BinomialHeapNode x = this, y = this;
		int min = x.bhn.data;

		while (x != null) 
		{
			if (x.bhn.data < min)				
			{
				y = x;
				min = x.bhn.data;
			}
			x = x.sibling;
		}

		return y;
	}

	public BinomialHeapNode findANodeWithKey(int value) 
	{
		BinomialHeapNode temp = this, node = null;

		while (temp != null) 
		{
			if (temp.bhn.data == value)				
			{
				node = temp;
				break;
			}
			if (temp.child == null)
				temp = temp.sibling;
			else 
			{
				node = temp.child.findANodeWithKey(value);
				if (node == null)
					temp = temp.sibling;
				else
					break;
			}
		}

		return node;
	}

	public int getSize() 
	{
		return (1 + ((child == null) ? 0 : child.getSize()) + ((sibling == null) ? 0 : sibling.getSize()));
	}
}

class BinomialHeap 
{
	private BinomialHeapNode Nodes;
	private int size;

	public BinomialHeap() 
	{
		Nodes = null;
		size = 0;
	}

	public boolean isEmpty() 
	{
		return Nodes == null;
	}

	public int getSize() 
	{
		return size;
	}

	public void makeEmpty() 
	{
		Nodes = null;
		size = 0;
	}

	public void insert(HuffmanNode thn) 
	{
		if (thn.data > 0) 
		{
			Counter.count++;
			BinomialHeapNode temp = new BinomialHeapNode(thn);
			if (Nodes == null) 
			{
				Nodes = temp;
				size = 1;
			} 
			else 
			{
				unionNodes(temp);
				size++;
			}
		}
	}

	private void merge(BinomialHeapNode binHeap) 
	{
		BinomialHeapNode temp1 = Nodes, temp2 = binHeap;

		while ((temp1 != null) && (temp2 != null)) 
		{
			if (temp1.degree == temp2.degree) 
			{
				BinomialHeapNode tmp = temp2;
				temp2 = temp2.sibling;
				tmp.sibling = temp1.sibling;
				temp1.sibling = tmp;
				temp1 = tmp.sibling;
			} 
			else 
			{
				if (temp1.degree < temp2.degree) 
				{
					if ((temp1.sibling == null) || (temp1.sibling.degree > temp2.degree)) 
					{
						BinomialHeapNode tmp = temp2;
						temp2 = temp2.sibling;
						tmp.sibling = temp1.sibling;
						temp1.sibling = tmp;
						temp1 = tmp.sibling;
					} 
					else 
					{
						temp1 = temp1.sibling;
					}
				} 
				else 
				{
					BinomialHeapNode tmp = temp1;
					temp1 = temp2;
					temp2 = temp2.sibling;
					temp1.sibling = tmp;
					if (tmp == Nodes) 
					{
						Nodes = temp1;
					} 
					else {

					}
				}
			}
		}
		if (temp1 == null)
		{
			temp1 = Nodes;
			while (temp1.sibling != null) 
			{
				temp1 = temp1.sibling;
			}
			temp1.sibling = temp2;
		} 
		else {

		}
	}

	private void unionNodes(BinomialHeapNode binHeap) 
	{
		merge(binHeap);

		BinomialHeapNode prevTemp = null, temp = Nodes, nextTemp = Nodes.sibling;

		while (nextTemp != null) 
		{
			Counter.count++;
			if ((temp.degree != nextTemp.degree)|| ((nextTemp.sibling != null) && (nextTemp.sibling.degree == temp.degree))) 
			{
				prevTemp = temp;
				temp = nextTemp;
			} 
			else 
			{
				if (temp.bhn.data <= nextTemp.bhn.data) 
				{
					temp.sibling = nextTemp.sibling;
					nextTemp.parent = temp;
					nextTemp.sibling = temp.child;
					temp.child = nextTemp;
					temp.degree++;
				} 
				else 
				{
					if (prevTemp == null)						
					{
						Nodes = nextTemp;
					} 
					else 
					{
						prevTemp.sibling = nextTemp;
					}
					temp.parent = nextTemp;
					temp.sibling = nextTemp.child;
					nextTemp.child = temp;
					nextTemp.degree++;
					temp = nextTemp;
				}
			}
			nextTemp = temp.sibling;
		}
	}

	public int findMinimum() 
	{
		return Nodes.findMinNode().bhn.data;
	}

	public void delete(int value) 
	{
		if ((Nodes != null) && (Nodes.findANodeWithKey(value) != null)) 
		{
			decreaseKeyValue(value, findMinimum() - 1);
			extractMin();
		}
	}

	public void decreaseKeyValue(int old_value, int new_value) 
	{
		BinomialHeapNode temp = Nodes.findANodeWithKey(old_value);
		if (temp == null)
			return;
		temp.bhn.data = new_value;
		BinomialHeapNode tempParent = temp.parent;

		while ((tempParent != null) && (temp.bhn.data < tempParent.bhn.data))
		{
			int z = temp.bhn.data;
			temp.bhn.data = tempParent.bhn.data;
			tempParent.bhn.data = z;

			temp = tempParent;
			tempParent = tempParent.parent;
		}
	}

	public HuffmanNode extractMin() 
	{
		Counter.count++;
		if (Nodes == null)
			return null;

		BinomialHeapNode temp = Nodes, prevTemp = null;
		BinomialHeapNode minNode = Nodes.findMinNode();

		while (temp.bhn.data != minNode.bhn.data) 
		{
			prevTemp = temp;
			temp = temp.sibling;
		}

		if (prevTemp == null) 
		{
			Nodes = temp.sibling;
		} 
		else 
		{
			prevTemp.sibling = temp.sibling;
		}

		temp = temp.child;
		BinomialHeapNode fakeNode = temp;

		while (temp != null) 
		{
			temp.parent = null;
			temp = temp.sibling;
		}

		if ((Nodes == null) && (fakeNode == null)) 
		{
			size = 0;
		} 
		else			
		{
			if ((Nodes == null) && (fakeNode != null)) 
			{
				Nodes = fakeNode.reverse(null);
				size = Nodes.getSize();
			} 
			else 
			{
				if ((Nodes != null) && (fakeNode == null)) 
				{
					size = Nodes.getSize();
				} 
				else 
				{
					unionNodes(fakeNode.reverse(null));
					size = Nodes.getSize();
				}
			}
		}

		return minNode.bhn;
	}
}
