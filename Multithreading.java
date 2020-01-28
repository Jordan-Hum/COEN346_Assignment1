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

    while((input = br.readLine()) != null)
    {
      bulbsArray[argsIndex] = Integer.parseInt(input);
      argsIndex++;
    }
    br.close();
    //endregion
    
    FindDefective(bulbsArray);
  }

  //Finds the defective bulb
  public static int FindDefective(int[] bulbs)
  {
    if(bulbs.length == 1)
    {
      if(bulbs[0] == 0)
      {
        System.out.println("here");
        return 1;
      }
    }
    boolean isDefective = false;
    int pivot = bulbs.length / 2;

    //Initialize left array
    int[] leftArr = new int[pivot];
    for (int i = 0; i < pivot; i++) 
    {
      leftArr[i] = bulbs[i];
    }

    for(int i = 0; i < leftArr.length; i++)
    {
      if(leftArr[i] == 0)
      {
        isDefective = true;
        FindDefective(leftArr);
      }
    }

    //Initialize right array
    int[] rightArr = new int[pivot];
    for (int i = pivot; i < bulbs.length; i++) 
    {
      rightArr[i - pivot] = bulbs[i];
    }

    for(int i = 0; i < rightArr.length; i++)
    {
      if(rightArr[i] == 0)
      {
        isDefective = true;
        FindDefective(rightArr);
      }
    }

    return 1;
  }
}

  