import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.HashMap;

public class Day3Part2{
  public static void main(String[] args){

    // Read in the input file
    ReadFile rf = new ReadFile();
    ArrayList<String> inputArray = rf.readFile("./gameInput.txt");
    int arrayLength = inputArray.size();

    // Create a hashmap to store the location of the symbols
    HashMap<Integer, ArrayList<Integer>> symbIndex =
      new HashMap<Integer, ArrayList<Integer>>();

    // Iterate through the input array to find all of the symbols
    for (int i = 0; i < inputArray.size(); i++){
      // Create the array list to put the symbol indexes in 
      ArrayList<Integer> symbIndexList = new ArrayList<Integer>();

      // Regex to find the special characters
      Pattern symbPattern = Pattern.compile("\\*");
      Matcher symbMatcher = symbPattern.matcher(inputArray.get(i));

      // Find the index of all of the special characters in the line
      while (symbMatcher.find()){
        symbIndexList.add(symbMatcher.start());
      }

      // Add all of the indices to the hashmap with the line as the key
      symbIndex.put(i, symbIndexList);

    }

    // Set the int to calculate the total
    int runningTotal = 0;
    Pattern numPattern = Pattern.compile("(\\d+)");

    for (int i = 0; i < inputArray.size(); i++){
      // Get the location of the *s on the current line, skip if none
      ArrayList<Integer> workingIndices = symbIndex.get(i);
      if (workingIndices.size() == 0){
        continue;
      }

      // Work through each * in the line individually
      for (int j = 0; j < workingIndices.size(); j++){
        // Grab the index of the current *
        int starIndex = workingIndices.get(j);

        // Setup an arraylist for part numbers next to the *
        ArrayList<Integer> adjNums = new ArrayList<Integer>();

        // Check the surrounding lines for numbers
        for (int k = i - 1; k < i + 2; k++){
          if (k < 0){
            continue;
          }
          if (k > arrayLength){
            continue;
          }

          // Start looking for numbers in the current line
          Matcher numMatcher = numPattern.matcher(inputArray.get(k));
          while (numMatcher.find()){
            if (numMatcher.start() - 2 < starIndex &&
                numMatcher.end() + 1 > starIndex
                ){
              adjNums.add(Integer.parseInt(numMatcher.group()));
            }
          }
        }

        if (adjNums.size() == 2){
          int gearRatio = adjNums.get(0) * adjNums.get(1);
          runningTotal += gearRatio;
        }
      } 

    }

  }
}
