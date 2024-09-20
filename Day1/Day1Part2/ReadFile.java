import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class ReadFile{
  public ArrayList<String> readFile(String path){
    ArrayList<String> inputArray = new ArrayList<String>();
    try {
      File inputFile = new File(path);
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
    return(inputArray);
  }
}
