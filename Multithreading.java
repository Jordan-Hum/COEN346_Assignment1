//Jordan Hum - 4009 | Anthony Icampo - 40096683
import java.io.*;

class Multithreading {
  public static void main(String []args) throws Exception
  {
    //region read from txt file
    FileReader fr = new FileReader("Input.txt");
    BufferedReader br = new BufferedReader(fr);

    String input;
    int argsIndex = 0;

    int numBulbs = Integer.parseInt(br.readLine());
    int[] bulbsArray = new int[numBulbs];
    int[] defectiveBulbs = new int[numBulbs];
    
    while((input = br.readLine()) != null)
    {
      bulbsArray[argsIndex] = Integer.parseInt(input);
      argsIndex++;
    }
    br.close();
    //endregion
    
    FindDefective(bulbsArray, 0 ,bulbsArray.length, 0, defectiveBulbs);
  }


  //Finds the defective bulb
  public static void FindDefective(int[] bulbs, int min, int max, int counter, int []defectiveBulbs)
  {
    if(max - min == 1)
    {
      if(bulbs[min] == 0)
      {
        //defectiveBulbs[counter] = max;
        //counter++;
        System.out.println(max);
        return;
      }
    }

    int pivot = (min + max) / 2;
    
    //Left array
    for(int i = min; i < pivot; i++)
    {
      if(bulbs[i] == 0)
      {
        FindDefective(bulbs, min, pivot, counter, defectiveBulbs);
      }
    }

    //Right array
    for(int i = pivot; i < max; i++)
    {
      if(bulbs[i] == 0)
      {
        FindDefective(bulbs, pivot, max, counter, defectiveBulbs);
      }
    }
  }

  static void printArray(int []array)
  {
    for(int i = 0; i < array.length; i++)
    {
      System.out.print(array);
    }
  }
}