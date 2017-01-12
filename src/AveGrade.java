/*Aleksandr Movsesyan
  CS 111B Programming Lab 6
  AveGrade.java

  Inputs from a file a name followed by 4 grades.  Then averages them and
and outputs them to a new file. Both files are choosen by the user.  If
there is a problem outputs an error message and loops.
 */

import java.io.*;

public class AveGrade
{
  private static final int NUM_GRADES = 4;

  public static void main(String[] args)
  {
    double total, ave = 0;
    String inFileName, outFileName, row = null;
    String[] nameGrades = null;
    BufferedReader inFile = null, keyboard;
    PrintStream outFile = null;

    System.out.println("Welcome to the Grade Average Calculator.");

    keyboard = new BufferedReader(new InputStreamReader(System.in));

    do
    {
      try
      {
        System.out.println("Please enter the name of a file to input:");
        inFileName = keyboard.readLine();
      }
      catch(IOException e)
      {
        System.out.println("Error reading keyboard input");
        return;
      }
      try
      {
        inFile = new BufferedReader(new FileReader(inFileName));
        break;
      }
      catch(IOException e)
      {
        System.out.println("Unable to open file: " + inFileName);
      }
    }while(true);
    //breaks if everything goes well and the file opened correctly.

    do
    {
      try
      {
        System.out.println("Enter name of output file to create:");
        outFileName = keyboard.readLine();
      }
      catch(IOException e)
      {
        System.out.println("Error reading keyboard input");
        continue;
      }
      try
      {
        outFile = new PrintStream(new FileOutputStream(new File(outFileName)));
        break;
      }
      catch(IOException e)
      {
        System.out.println("Unable to open file: "  + outFileName);
      }
    }while(true);
    //breaks if everything goes well and the file opened correctly.

    try
    {
      row = inFile.readLine();
    }
    catch(IOException e)
    {
      System.out.println("Unable to read from file.");
    }

    while(row != null)
    {
      total = 0;
      ave = 0;

      try
      {
        nameGrades = row.split(" ");

        for(int i = 1; i <= NUM_GRADES; i++)
        {
          total += Double.parseDouble(nameGrades[i]);
        }

        ave = total / NUM_GRADES;

        outFile.println(nameGrades[0] + " "  + ave);

        row = inFile.readLine();
      }
      catch(IOException e)
      {
        System.out.println("Error reading or writing file: " +
                                              e.getMessage());
        return;
      }
      catch(Exception e)
      {
        System.out.println("Format of the file is incorrect");
        return;
      }
    }

    try
    {
      inFile.close();
      outFile.close();
    }
    catch(IOException e)
    {
      System.out.println("Error closing the file");
    }


    System.out.println("Average grades saved to " + outFileName);
  }
}
