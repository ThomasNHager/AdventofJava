import java.util.ArrayList;

public class Day4Part2{
  public static void main(String[] args){

    // Read in the input file
    // Make a copy to add on to
    ReadFile rf = new ReadFile();
    ArrayList<String> inputArray = rf.readFile("./gameInput.txt");
    ArrayList<String> workingArray = new ArrayList<String>(inputArray);

    // Run through the lines to separate out the different cards
    for (int i = 0; i < workingArray.size(); i++){
      System.out.println("Working on line: " + i);
      // Find lists of the numbers before and after |
      String workingLine = workingArray.get(i);
      String[] cardSplit = workingLine.split(":");
      String[] gameSplit = cardSplit[0].split(" ");
      String strGame = gameSplit[gameSplit.length - 1];
      int workingGame = Integer.parseInt(strGame);
      int workingGameIndexOnInputArray = workingGame - 1;

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

      int numHits = 0;
      boolean foundHit = false;
      for (int l = 0; l < intDraw.size(); l++){
        if (filteredWin.contains(intDraw.get(l))){
          numHits++;
          foundHit = true;
        }
      }

      if (foundHit){
        for (int m = 0; m < numHits; m++){
          String toAdd = inputArray.get(
              workingGameIndexOnInputArray + m + 1
              );
          workingArray.add(toAdd);
        }
      }

    }
  }
}
