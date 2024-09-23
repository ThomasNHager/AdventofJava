import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.HashMap;

public class Day2Part2 {

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

    int powerTotal = 0;
    for (int i = 0; i < inputArray.size(); i++){
      // Iterate through the games
      // Define the minimum number of cubes for each games
      HashMap<String, Integer> minCubes = new HashMap<String, Integer>();
      minCubes.put("green", 0);
      minCubes.put("blue", 0);
      minCubes.put("red", 0);

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

          // Evaluate if there are more cubes this time than the previous max
          if (minCubes.get(workingString) < workingInt){
              minCubes.put(workingString, workingInt);
          }
        }

      }

      // Calculate the total power value for the game
      powerTotal = powerTotal + (
              minCubes.get("blue") * minCubes.get("green") * minCubes.get("red")
              );

    }

  }
}
