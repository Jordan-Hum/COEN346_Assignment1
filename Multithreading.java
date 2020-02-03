//Jordan Hum - 40095876 | Anthony Iacampo - 40096683
import java.io.*;

class Multithreading implements Runnable 
{
  static int[] bulbs;
  static int numBulbs;
  int min;
  int max;
  static int defectiveCounter;
  static int[] defectiveBulbs;
  static int threadCounter = 0;

  public static void main(String []args) throws Exception
  {
    //Retrieve Input
    readFile();

    //Create initial thread
    Thread t = new Thread(new Multithreading(0, bulbs.length));
    t.start();
    threadCounter++;

    try
    {
      t.join();
    }
    catch(InterruptedException err)
    {
      System.out.println(err);
    }

    //Output
    System.out.print("The defectives bulbs: ");
    printArray(defectiveBulbs);
    System.out.print("\nThe number of threads for this problem: " + threadCounter);
  }

  //Runnable constructor
  public Multithreading( int start, int end)
  {
    this.min = start;
    this.max = end;
  }
    
  //Read the input file
  private static void readFile() throws Exception 
  {
    FileReader fr = new FileReader("Input.txt");
    BufferedReader br = new BufferedReader(fr);
    
    String input;
    int argsIndex = 0;
    
    numBulbs = Integer.parseInt(br.readLine());
    bulbs = new int[numBulbs];
    defectiveBulbs = new int[numBulbs];
    while((input = br.readLine()) != null)
    {
      if(Integer.parseInt(input) == 0 || Integer.parseInt(input) == 1)
      {
    	try
    	{
    	bulbs[argsIndex] = Integer.parseInt(input);
      	argsIndex++;
    	}
    	catch(Exception err)
    	{
    		System.out.println("Invalid array size");
        	System.exit(0);
    	}
      }
      else
      {
    	System.out.println("Invalid input");
    	System.exit(0);
      }
    }
    if(numBulbs > argsIndex)
    {
		System.out.println("Invalid array size");
    	System.exit(0);
    }
    br.close();
  }

  //Run thread
  public void run() 
  {
    FindDefective();
  }

  //Finds the defective bulb
  public void FindDefective()
  {
    int pivot = (min + max) / 2;
    if(max - min == 1)
      {
        if(bulbs[min] == 0)
        {
          defectiveBulbs[defectiveCounter] = max;
          defectiveCounter++;
          return;
        }
      } 
    else 
    {
        if(isDefective())
        {
          //System.out.println("left array"); //Just for test
          Thread left = new Thread(new Multithreading(min, pivot));
          threadCounter++;
          left.start();
    
          //System.out.println("right array"); //Just for test
          Thread right = new Thread(new Multithreading(pivot, max));
          threadCounter++;
          right.start();
    
          try 
          {
            left.join();
            right.join();
          }
          catch(InterruptedException err)
          {
            System.out.println(err);
          }
        }
    }
  }

  //Returns true if a defective bulb is found
  public boolean isDefective()
  {
    for(int i = min; i < max; i++ )
    {
      if(bulbs[i] == 0)
      {
		return true;
	  }
	}
	return false;
  }

  //Prints the array of indexes of defective bulb
  private static void printArray(int[] array)
  {
    for(int i = 0; i < array.length; i++)
    {
      if(array[i] != 0)
      {
        System.out.print(array[i] + " ");
      }
      else
      {
    	  break;
      }
    }
  }
}
