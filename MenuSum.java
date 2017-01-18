import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class MenuSum 
{	
	public static void scan(File f)
	{
		ArrayList<Integer> IDs = new ArrayList <Integer>();
		
		try 
		{
			Scanner scanner = new Scanner(f);
									
			String labelSearch = scanner.nextLine();	// current line
			String IDSearch = null;						// previous line read
			
			while(scanner.hasNextLine())
			{
				IDSearch = labelSearch;
				labelSearch = scanner.nextLine();
				if(IDSearch.contains("id") && labelSearch.contains("Label"))
				{
					//remove trailing comma
					if(IDSearch.charAt(IDSearch.length() - 1) == ',')
					{	
						IDSearch = IDSearch.substring(0, IDSearch.length() - 1);
					}
					
					int start = IDSearch.indexOf('i') + 4;
					int end = IDSearch.length();
					String sub = IDSearch.substring(start, end);
					
					int toAdd = Integer.parseInt(sub);
					IDs.add(toAdd);
				}
				
				else if(labelSearch.equals("  },") || labelSearch.equals("]"))
				{
					System.out.println(MenuSum.sum(IDs));
					IDs.clear();
				}
			}
			scanner.close();
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
	}
	
	//Sums all integers stored in the list
	 
	public static int sum(ArrayList<Integer> array)
	{	
		int sum = 0;
		for(int i : array)
		{
			sum = sum + i;
		}
		return sum;
	}
	
	public static void main(String[] args) 
	{
		String path = args[0];
		
		MenuSum.scan(new File(path));
	}

}