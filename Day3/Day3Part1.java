import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.HashMap;

public class Day3Part1{
  public static void main(String[] args){

    // Read in the input file
    ReadFile rf = new ReadFile();
    ArrayList<String> inputArray = rf.readFile("./wideTestInput.txt");
    int arrayLength = inputArray.size();

    // Create a hashmap to store the location of the symbols
    HashMap<Integer, ArrayList<Integer>> symbIndex =
      new HashMap<Integer, ArrayList<Integer>>();

    // Iterate through the input array to find all of the symbols
    for (int i = 0; i < inputArray.size(); i++){

      // Create the array list to put the symbol indexes in 
      ArrayList<Integer> symbIndexList = new ArrayList<Integer>();

      // Regex to find the special characters
      Pattern symbPattern = Pattern.compile("[^\\.\\d]");
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

    // Iterate through the input again to check if numbers should be added
    // to the total
    for (int j = 0; j < inputArray.size(); j++){

      // Regex to find the numbers
      Pattern pattern = Pattern.compile("(\\d+)");
      Matcher matcher = pattern.matcher(inputArray.get(j));

      // Find the part numbers
      while (matcher.find()){
        String partNum = matcher.group(0);
        int partStart = matcher.start();
        int partEnd = matcher.end();

        for (int k = j - 1; k < j + 2; k++){

          // Skip lines outside of the range
          if (k < 0){
            continue;
          }
          if (k > arrayLength - 1){
            continue;
          }

          // Get the indices of the symbols in the checkline
          ArrayList<Integer> workingLineSymbols = symbIndex.get(k);

          // If there are no symbols in the working index, skip it
          if (workingLineSymbols.size() == 0){
            continue;
          }

          for (int l = partStart - 1; l < partEnd + 1; l++){

            // Skip if the point in the line is out of bounds
            if (l < 0){
              continue;
            }
            if (l > inputArray.get(j).length()){
              continue;
            }

            if (workingLineSymbols.contains(l)){
              int partInt = Integer.parseInt(partNum);
              // System.out.println("Found: " + partNum);
              runningTotal = runningTotal + partInt;
            }

          }


        }
      }
    }
  }
}
