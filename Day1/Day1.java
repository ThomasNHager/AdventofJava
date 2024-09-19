import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Day1 {
  public static void main(String[] args){

    ArrayList<String> inputArray = new ArrayList<String>();
    try {
      File inputFile = new File("./puzzleInput.txt");
      Scanner myReader = new Scanner(inputFile);
      while (myReader.hasNextLine()) {
        String line = myReader.nextLine();
        inputArray.add(line);
      }
      myReader.close();
    }
    catch (FileNotFoundException e){
      System.out.println("An error has occured.");
      e.printStackTrace();
    }

    char firstMatch;
    char lastMatch;
    String workingLine;
    String reversedLine = "";
    char[] reversedArray;
    char[] lineArray;
    int runningTotal = 0;
    String patternString = "[0-9]";
    Pattern pattern = Pattern.compile(patternString);
    
    for(int i = 0; i < inputArray.size(); i++){
      workingLine = inputArray.get(i);

      for (int j = 0; j < workingLine.length(); j++){
        reversedLine = workingLine.charAt(j) + reversedLine;
      }

      Matcher matcher = pattern.matcher(workingLine);
      matcher.find();
      lineArray = workingLine.toCharArray();
      firstMatch = lineArray[matcher.start()];

      Matcher matcher2 = pattern.matcher(reversedLine);
      matcher2.find();
      reversedArray = reversedLine.toCharArray();
      lastMatch = reversedArray[matcher2.start()];

      int firstInt = Character.getNumericValue(firstMatch);
      int lastInt = Character.getNumericValue(lastMatch);

     runningTotal = runningTotal + (firstInt * 10) + lastInt;
    }

  }
}
