//Jordan Hum - 40095876 | Anthony Iacampo - 40096683
import java.io.*;

class Multithreading implements Runnable 
{
  static int[] bulbs;
  static int numBulbs;
  static int defectiveCounter;
  static int[] defectiveBulbs;
  static int threadCounter = 0;

  public static void main(String []args) throws Exception
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
      bulbs[argsIndex] = Integer.parseInt(input);
      argsIndex++;
    }
    br.close();

    Thread t = new Thread(new Multithreading());
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

  //Run thread
  public void run() 
  {
    FindDefective(bulbs, 0 ,bulbs.length);
  }

  //Finds the defective bulb
  public void FindDefective(int[] bulbs, int min, int max)
  {
      if(max - min == 1)
      {
        defectiveBulbs[defectiveCounter] = max;
        defectiveCounter++;
        return;
      }

      int pivot = (min + max) / 2;
      boolean isLeftDefective = false;
      boolean isRightDefective = false;
    
      //Left array
      Thread left = new Thread();
      threadCounter++;
      left.start();
      for(int i = min; i < pivot; i++)
      {
       if(bulbs[i] == 0)
        {
          isLeftDefective = true;
        }
      }
     if(isLeftDefective)
      {
        FindDefective(bulbs, min, pivot);
      }

      //Right array
      Thread right = new Thread();
      threadCounter++;
      right.start();

      for(int i = pivot; i < max; i++)
      {
       if(bulbs[i] == 0)
       {
         isRightDefective = true;
       }
      }
      if(isRightDefective)
      {
       FindDefective(bulbs, pivot, max);
      }

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

  private static void printArray(int[] array)
  {
    for(int i = 0; i < array.length; i++)
    {
      if(array[i] != 0)
      {
        System.out.print(array[i] + " ");
      }
    }
  }
}