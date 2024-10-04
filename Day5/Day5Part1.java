import java.util.ArrayList;
import java.util.HashMap;
public class Day5Part1{
  public static void main(String[] args){
    // Read in the input
    ReadFile rf = new ReadFile();
    ArrayList<String> inputArray = rf.readFile("./gameInput");

    // Setup a hashmap for the different lists
    HashMap<Integer, ArrayList<String>> stringMappings = 
      new HashMap<Integer, ArrayList<String>>();

    // Process into the hashmap
    int j = 0;
    for (int i = 0; i < inputArray.size(); i++){
      if (inputArray.get(i) == ""){
        j += 1;
        continue;
      }
      if (!stringMappings.containsKey(j)){
        stringMappings.put(j, new ArrayList<String>());
      }
      ArrayList<String> workingList = stringMappings.get(j);
      workingList.add(inputArray.get(i));
      stringMappings.put(j, workingList);
    }

    // Turn the seeds into a list of ints
    ArrayList<String> seedString = stringMappings.get(0);
    String[] seedSplit = seedString.get(0).split(" ");
    ArrayList<Long> seedNumList = new ArrayList<Long>();
    for (int k = 1; k < seedSplit.length; k++){
      seedNumList.add(Long.parseLong(seedSplit[k]));
    }

    // Make the new seed list
    ArrayList<Long> processedSeeds = new ArrayList<Long>();

    // Process through the seeds
    for (Long seed : seedNumList){
      // Indexing at one to skip the list of seeds
      for (int l = 1; l < stringMappings.size(); l++){
        // Work through the list of mappings, again skipping the name
        for (int m = 1; m < stringMappings.get(l).size(); m++){
          // Convert the string to a list of ints
          String mappings = stringMappings.get(l).get(m);
          String[] mappingsArray = mappings.split(" ");
          ArrayList<Long> mappingsIntList = new ArrayList<Long>();
          for (String map : mappingsArray){
            mappingsIntList.add(Long.parseLong(map));
          }

          // Now check if the seed is in the range, if it is, add to it
          if (seed >= mappingsIntList.get(1) &&
              seed < mappingsIntList.get(1) + mappingsIntList.get(2)){
              seed += mappingsIntList.get(0) - mappingsIntList.get(1);
              break;
              }
        }

      }
      processedSeeds.add(seed);
    }

    // Find the smallest
    Long minSeed = processedSeeds.get(0);
    for (Long pSeed : processedSeeds){
      if (pSeed < minSeed){
        minSeed = pSeed;
      }
    }
  }
}
