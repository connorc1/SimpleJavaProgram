import java.util.Scanner;

//Author: Connor Chamberlain
//Email: connorc1@utas.edu.au
//Created 25/03/2019


public class Chefcity_Connorc1 
{  
  //### PROGRAM STARTS HERE ###//
  public static void main(String[] args)
  {
    int T = 0;                                  //Represents number of test case inputs (name from specs)
    int[] distances = {};                       //Array to hold as many ints as specified by int T
    long maxDistance = (int)Math.pow(10, 9);     //Specs detailed a max distance of 10 to the power of 9
    Boolean result = false;                     //Used to check if user input is acceptable (spec restrictions)
    
    //First we get the number of test cases
    System.out.println("Enter the number of test cases:");
    do                      //Gets user input and retries if not within spec restrictions
    {
      Scanner input = new Scanner(System.in);   //Collects user input limited to this loop
      result = input.hasNextInt();              //Boolean check used to check if user input is acceptable
      if(result == false)
      {
        System.out.println("Try Again (make sure its an integer value):");
      }
      else                                      //If the result is an integer, later checks restrictions
      {
        T = input.nextInt();
        if (!(T >= 1) || !(T <= 200))           //Checks test case numbers are within restrictions
        {
          result = false;
          System.out.println("Try Again. Make sure the value is both...");
          System.out.println("\t -Greater than or equal to 1");
          System.out.println("\t -Less than or equal to 200");
        }
        else                                    //Initialise the array with the number of test cases specified
        {
          distances = new int[T];
        }
      }
      input.close();
    } while(result == false);
    System.out.println("");
    
    //Now its time to get user input for test case distances
    System.out.println("Now its time to get the distance/s between chef and his/her destination");
    for(int i = 0; i < T; i++)                  //For each of the test cases enter get an integer to use as distance
    {
      result = false;                           //As before catches incorrect inputs
      String toSay = ("Please enter distance (" + i + "/" + T + "):");
      System.out.println(toSay);
      do                                        //Loops until the user input is acceptable
      {
        Scanner input = new Scanner(System.in);
        result = input.hasNextInt();
        if(result == false)                     //Was not an integer
        {
          System.out.println("Try Again (make sure its an integer value):");
        }
        else                                    //An integer which may or may not be within acceptable range
        {
          distances[i] = input.nextInt();
          if (!(distances[0] >= 1) || !(distances[0] <= maxDistance))  //If the distance set is within set values
          {
            result = false;
            System.out.println("Try Again. Make sure the value is both...");
            System.out.println("\t -Greater than or equal to 1");
            System.out.println("\t -Less than or equal to 1,000,000,000");
          }
        }
        input.close();
      } while(result == false);
    }
    System.out.println("");
    
    //Now the program has everything it needs to print results
    System.out.println("The minute results for the test cases are as follows:");
    for(int i = 0; i < T; i++)                  //For each of the test cases, print the minimum travel time
    {
      int travelTime = getMinimumTravelTime(distances[i]);   //See below for function details
      System.out.println(travelTime);
    }
  }
  
   //Function to get the minimum amount of time in minutes
  //   it would take chef to reach their destination
  //WARNING: REQUIRES INPUT TO HAVE ALREADY BEEN RESTRICTED/FILTERED BEFOREHAND
  //    Unfiltered input could result in odd results
  public static int getMinimumTravelTime(int distance)
  {
    int currentPos = 0;      //This is chefs current position, distance from home (0).
    int minutes = 0;         //A counter for the number of minutes it would take to get to the destination
    int i = 0;               //The number of stations chef has visited (named i to match specs)
    
    while(currentPos != distance)
    {
      int nextStationPos = currentPos + (i + 1);      //Gets the distance of the next station from chef
      if (distance >= nextStationPos)                 //Checks the destination is not between chef and next station
      {
        minutes++;
        i++;
        currentPos = nextStationPos;
        if (distance == nextStationPos)               //Checks if the station is the destination
        {
          return minutes;
        }
      }
      else                                            //If the destination is between chef and the next station
      {
        int walkForward = (distance - currentPos);            //Distance to destination walking forward
        int walkBackwards = (nextStationPos - distance + 1);  //Distance to destination taking ONE minute to travel
        //   and then walk to destination
        if (walkForward <= walkBackwards)             //Walking from current station is quicker
        {
          minutes += walkForward;
          return minutes;
        }
        else                                          //Travelling to next station and walking is quicker
        {
          minutes += walkBackwards;
          return minutes;
        }
      }
    }
    //Chef was already at destination (home).
    return 0;
  }
}
