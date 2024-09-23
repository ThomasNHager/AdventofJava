// /open ReadFile.java
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

public class Part2 {
  public static void main(String[] args){

    ReadFile rf = new ReadFile();
    ArrayList<String> inputArray = rf.readFile("./puzzleInput.txt");

    String[] searchArray = {
      "one", "two", "three", "four", "five", "six", "seven", "eight", "nine",
      "1", "2", "3", "4", "5", "6", "7", "8", "9"
    };

    HashMap<String, Integer> conversionMap = new HashMap<String, Integer>();

    conversionMap.put("one", 1);
    conversionMap.put("two", 2);
    conversionMap.put("three", 3);
    conversionMap.put("four", 4);
    conversionMap.put("five", 5);
    conversionMap.put("six", 6);
    conversionMap.put("seven", 7);
    conversionMap.put("eight", 8);
    conversionMap.put("nine", 9);

    conversionMap.put("1", 1);
    conversionMap.put("2", 2);
    conversionMap.put("3", 3);
    conversionMap.put("4", 4);
    conversionMap.put("5", 5);
    conversionMap.put("6", 6);
    conversionMap.put("7", 7);
    conversionMap.put("8", 8);
    conversionMap.put("9", 9);

    HashMap<Integer, String> numIndex = new HashMap<Integer, String>();
    HashMap<Integer, String> revNumIndex = new HashMap<Integer, String>();
    int runningTotal = 0;

    for (int i = 0; i < inputArray.size(); i++){
      String workingLine = inputArray.get(i);

      for (int j = 0; j < searchArray.length; j++){
        String workingSearch = searchArray[j];
        numIndex.put(workingLine.indexOf(workingSearch), workingSearch);
        revNumIndex.put(workingLine.lastIndexOf(workingSearch), workingSearch);
      }

      int indexMin = 1000;
      int indexMax = 0;

      for (int k : numIndex.keySet()){
        if (k == -1){continue;}
        if (k < indexMin){
          indexMin = k;
        }
      }

      for (int k : revNumIndex.keySet()){
        if (k == -1){continue;}
        if (k > indexMax){
          indexMax = k;
        }
      }

      String firstString = numIndex.get(indexMin);
      String lastString = revNumIndex.get(indexMax);

      int firstInt = conversionMap.get(firstString);
      int lastInt = conversionMap.get(lastString);

      runningTotal = runningTotal + (10 * firstInt) + lastInt;

      numIndex.clear();
      revNumIndex.clear();

    }


  }
}
