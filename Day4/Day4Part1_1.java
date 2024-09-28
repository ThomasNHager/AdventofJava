import java.lang.Math;
import java.util.ArrayList;

public class Day4Part1{
  public static void main(String[] args){

    // Read in the input file
    ReadFile rf = new ReadFile();
    ArrayList<String> inputArray = rf.readFile("./gameInput.txt");

    int runningTotal = 0;
    // Run through the lines to separate out the different cards
    for (int i = 0; i < inputArray.size(); i++){
      // Find lists of the numbers before and after |
      String workingLine = inputArray.get(i);
      String[] cardSplit = workingLine.split(":");
      String[] numSplit = cardSplit[1].split("\\|");
      String[] winList = numSplit[0].split(" ");
      String[] drawList = numSplit[1].split(" ");

      ArrayList<Integer> filteredWin = new ArrayList<Integer>();
      for (int j = 0; j < winList.length; j++){
        if (winList[j] != ""){
          filteredWin.add(Integer.parseInt(winList[j]));
        }
      }

      ArrayList<Integer> intDraw = new ArrayList<Integer>();
      for (int k = 0; k < drawList.length; k++){
        if (drawList[k] != ""){
          intDraw.add(Integer.parseInt(drawList[k]));
        }
      }

      int numHits = -1;
      boolean foundHit = false;
      for (int l = 0; l < intDraw.size(); l++){
        if (filteredWin.contains(intDraw.get(l))){
          numHits++;
          foundHit = true;
        }
      }

      if (foundHit){
        runningTotal += Math.pow(2, numHits);
      }

    }
  }
}
