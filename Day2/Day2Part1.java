import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.HashMap;

public class Day2Part1 {

  static String[] findNumCol(String input){

    // Regex to find the nubmer
    Pattern pattern = Pattern.compile("(\\d+)");
    Matcher matcher = pattern.matcher(input);
    matcher.find();
    String numCubes = matcher.group(1);

    // String method to find the color
    String colCubes = "";
    if (input.contains("b")) {
      colCubes = "blue";
    } else if (input.contains("g")) {
      colCubes = "green";
    } else {
      colCubes = "red";
    }

    String[] ret = {numCubes, colCubes};
    return(ret);

  }

  public static void main(String[] args){

    // Read in the input text
    ReadFile rf = new ReadFile();
    ArrayList<String> inputArray = rf.readFile("./gameInput.txt");

    // Define the limits of the cubes
    HashMap<String, Integer> maxColors = new HashMap<String, Integer>();
    maxColors.put("red", 12);
    maxColors.put("green", 13);
    maxColors.put("blue", 14);

    int validTotal = 0;
    for (int i = 0; i < inputArray.size(); i++){
      // Iterate through the games
      // Define a variable to check if game works
      Boolean validGame = true;

      // Split out the game number and the different draws
      String[] splitGames = inputArray.get(i).split(":");
      String[] splitDraws = splitGames[1].split(";");

      for (int j = 0; j < splitDraws.length; j++){
        // Iterate through each of the draws in the game

        // Change the game into colors
        String[] splitColors = splitDraws[j].split(",");

        for (int k = 0; k < splitColors.length; k++){
          // Iterate through the different colors in the draw

          // Get my working variables
          String[] numCol = findNumCol(splitColors[k]);
          int workingInt = Integer.parseInt(numCol[0]);
          String workingString = numCol[1];

          // Evaluate if the game is valid 
          if (maxColors.get(workingString) < workingInt){
            validGame = false;
          }
        }

      }

      if (validGame){

        // Because the games start at 1 and the index starts at 0
        validTotal = validTotal + i + 1;

      }

    }

  }
}
