import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;

public class Day2Part1 {

  static String[] findNumCol(String input){

    // Regex to find the nubmer
    Pattern pattern = Pattern.compile("(\\d+)");
    Matcher matcher = pattern.matcher(input);
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
    ArrayList<String> inputArray = rf.readFile("./testInput.txt");

    // Define the limits of the cubes
    int red = 12;
    int green = 13;
    int blue = 14;

    for (int i = 0; i < inputArray.size(); i++){
      // Iterate through the games

      // Split out the game number and the different draws
      String[] splitGames = inputArray.get(i).split(":");
      String[] splitDraws = splitGames[1].split(";");

      for (int j = 0; j < splitDraws.length; j++){
        // Iterate through each of the draws in the game

        // Change the game into colors
        String[] splitColors = splitDraws[j].split(",");

        for (int k = 0; k < splitColors.length; k++){
          // Iterate through the different colors in the draw

          String[] numCol = findNumCol(splitColors[k]);
          // To be continued

        }

      }

    }

  }
}
