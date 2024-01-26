package lab03;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class TreeDataAnalyzer 
{
	public static void main(String[] args) 
	throws IOException 
	{
		File filename = new File("forest.txt"); // A file object just represents a path to a file
		Scanner file = new Scanner(filename); // Opens the file for reading (scanning)
		int highestCount =0;
		int lowestCount=0;
		int treeCount;
		while (file.hasNext())
	      {
			
			treeCount = file.nextInt(); // Scan the next int from the file.
			if(treeCount > highestCount)
			{
			    highestCount = treeCount;
			}
			else 
			{
				lowestCount = treeCount;
			}
	      }
		//System.out.println("Total tree count is " + totalTreeCount + ".");
		System.out.println("Highest tree count is " + highestCount + ".");
		System.out.println("Lowest tree count is " + lowestCount + ".");
		
		file.close();
		
		
	}

}


