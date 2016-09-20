
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


/**
 * Console application to calculate total number of second required to distribute mugs.
 * I assume the total number of mugs in the list are always less than a total number of people.
 * Unfortunately, this algorithm can't solve the problem if there are equal number of mugs for each people.
 * for example this program can not solve an array with input {0,6,0,0,0,0}.
 * 
 * @author Reza Tasooji 
 * @version 09/20/16
 * 
 *
 */
public class MainClass 
{
	public static void main(String[] args)
	{
		boolean notValidInput = true;

		try
		{
			while (notValidInput)
			{
				boolean secNotValidInput = true;
				int numberOfPeople = 0;	
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				System.out.println("Enter Number of People: ");
				String num = br.readLine();
				try
				{
					numberOfPeople = Integer.parseInt(num);
				}
				catch(NumberFormatException e)
				{
					System.err.println("Please Enter Valid Number!");
					continue;
				}
				MugDistributor mug = new MugDistributor(numberOfPeople);
				int[] mugArray = new int[numberOfPeople];
				while (secNotValidInput)
				{
					System.out.println("Enter number of mugs each person will hold(Example:0,1,2,0,etc): ");
					String inputA = br.readLine();
					Pattern p = Pattern.compile("\\d+");
					Matcher m = p.matcher(inputA);
					int i = 0;
					boolean arrayValid = false;
					while(m.find())
					{
						arrayValid = true;
				        try{
							mugArray[i] = Integer.parseInt(m.group());
							i++;
				        }catch(NumberFormatException nfe){
				            System.err.println("Invalid Format!");
				            arrayValid = false;
				            break;
				        }
					}
					if (arrayValid)
					{
						secNotValidInput = false;	
					}
				}
				mug.distribute(mugArray);
				System.out.println("Total number of second: " + mug.CalculateSec());
				
				while(true)
				{
					System.out.println("Try again? y/n");
					{
						String inputB = br.readLine();
						if ( inputB.equalsIgnoreCase("y") | inputB.equalsIgnoreCase("yes") |inputB.equalsIgnoreCase(""))
						{
							break;
						}
						else if (inputB.equalsIgnoreCase("n"))
						{
							notValidInput = false;
							break;
						}
					}
				}
			}
			System.out.println("Bye :)");
		}
			catch ( IOException e)
			{
				e.printStackTrace();
			}		
	}
}
