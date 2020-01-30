//Jordan Hum - 40095876 | Anthony Iacampo - 40096683
import java.io.*;


class Multithreading implements Runnable 
{
  public static int counter;
  public static int[] defectiveBulbs;
  public static int threadCounter = 0;

  //*Keep for Runnable
  public void run() 
  {
    Multithreading.threadCounter++;
  }

  public static void main(String []args) throws Exception
  {
    //region read from txt file
    FileReader fr = new FileReader("Input.txt");
    BufferedReader br = new BufferedReader(fr);

    String input;
    int argsIndex = 0;

    int numBulbs = Integer.parseInt(br.readLine());
    int[] bulbsArray = new int[numBulbs];
    Multithreading.defectiveBulbs = new int[numBulbs];
    
    while((input = br.readLine()) != null)
    {
      bulbsArray[argsIndex] = Integer.parseInt(input);
      argsIndex++;
    }
    br.close();
    //endregion
    
    FindDefective(bulbsArray, 0 ,bulbsArray.length);
    System.out.print("The defectives bulbs: ");
    printArray(Multithreading.defectiveBulbs);
    System.out.println();
    System.out.print("The number of threads for this problem: " + threadCounter);
  }

  //Finds the defective bulb
  public static void FindDefective(int[] bulbs, int min, int max)
  {
    try
    {
      Thread t = new Thread();
      t.start();
      //Multithreading.threadCounter++;
      if(max - min == 1)
      {
        Multithreading.defectiveBulbs[counter] = max;
        Multithreading.counter++;
        return;
      }

      int pivot = (min + max) / 2;
      boolean isLeftDefective = false;
      boolean isRightDefective = false;
    
      //Left array
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
      t.join();
    }
    catch(InterruptedException err)
    {
      System.out.println(err);
    }
  }

  static void printArray(int[] array)
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