import java.util.*;
import java.io.*;

class Cities
{
  String filename = null;
  static String line = null;
  private static ArrayList<String> cities = new ArrayList<String>();
  private static ArrayList<String> distances = new ArrayList<String>();
  
  
  public String[] readCities()
  {
    try
    {
      BufferedReader input = new BufferedReader(new FileReader("Cities.txt"));
      int count = 0; 
      while ((line = input.readLine()) != null)
      {
        String[] x = line.split(",");
        cities.add(x[0]);
        distances.add(x[1]);
        count++;
      }
    }
    catch (Exception e)
    {
      System.out.println("cant find file");
    }
    String[] citiesArr = new String[cities.size()];
    String[] distancesArr = new String[distances.size()];
    citiesArr = cities.toArray(citiesArr);
    distancesArr = distances.toArray(distancesArr);
    return citiesArr;
  }
  
  public String[] readDistances()
  {
    try
    {
      BufferedReader input = new BufferedReader(new FileReader("Cities.txt"));
      int count = 0; 
      while ((line = input.readLine()) != null)
      {
        String[] x = line.split(",");
        distances.add(x[1]);
        count++;
      }
    }
    catch (Exception e)
    {
      System.out.println("cant find file");
    }
    String[] distancesArr = new String[distances.size()];
    distancesArr = distances.toArray(distancesArr);
     
    return distancesArr;
  }
}
