import java.util.ArrayList;
import java.util.HashMap;
public class Day5Part1{
  static HashMap<Long, Long> createHash(ArrayList<String> takeArray){
    // Function to create a hashmap for each mapping type
    HashMap<Long, Long> funcHash = new HashMap<Long, Long>();
    for (int k = 1; k < takeArray.size(); k++){
      // Index at 1 because I want to skip the name of the map
      String workingString = takeArray.get(k);
      String[] stringMaps = workingString.split(" ");

      Long destStart = Long.parseLong(stringMaps[0]);
      Long sourceStart = Long.parseLong(stringMaps[1]);
      Long range = Long.parseLong(stringMaps[2]);

      for (int l = 0; l < range; l++){
        funcHash.put(sourceStart + l, destStart + l);
      }
    }
    return(funcHash);
  }

  public static void main(String[] args){
    // Read in the input
    ReadFile rf = new ReadFile();
    ArrayList<String> inputArray = rf.readFile("./testInput.txt");

    // Setup a hashmap for the different lists
    HashMap<Integer, ArrayList<String>> stringMappings = 
      new HashMap<Integer, ArrayList<String>>();

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

    HashMap<Long, Long> seedSoil = createHash(stringMappings.get(1));
    HashMap<Long, Long> soilFert = createHash(stringMappings.get(2));
    HashMap<Long, Long> fertWater = createHash(stringMappings.get(3));
    HashMap<Long, Long> waterLight = createHash(stringMappings.get(4));
    HashMap<Long, Long> lightTemp = createHash(stringMappings.get(5));
    HashMap<Long, Long> tempHumid = createHash(stringMappings.get(6));
    HashMap<Long, Long> humidLoc = createHash(stringMappings.get(7));

    // Find the seeds 
    String seedString = stringMappings.get(0).get(0);
    String[] prefixSplit = seedString.split(":");
    String[] seedsArray = prefixSplit[1].split(" ");
    ArrayList<Long> seedIntList = new ArrayList<Long>();

    // Convert the seeds to ints 
    for (int n = 0; n < seedsArray.length; n++){
      if (seedsArray[n] == ""){
        continue;
      }
      seedIntList.add(Long.parseLong(seedsArray[n]));
    }

    // Make a list for the results, then run the numbers through the maps
    ArrayList<Long> locationList = new ArrayList<Long>();
    for (int o = 0; o < seedIntList.size(); o++){
      Long workingInt = seedIntList.get(o);
      workingInt = seedSoil.getOrDefault(workingInt, workingInt);
      workingInt = soilFert.getOrDefault(workingInt, workingInt);
      workingInt = fertWater.getOrDefault(workingInt, workingInt);
      workingInt = waterLight.getOrDefault(workingInt, workingInt);
      workingInt = lightTemp.getOrDefault(workingInt, workingInt);
      workingInt = tempHumid.getOrDefault(workingInt, workingInt);
      workingInt = humidLoc.getOrDefault(workingInt, workingInt);
      locationList.add(workingInt);
    }

    // Find the smallest in the list
    Long minInt = 100000000000000000L;
    int minIndex;
    for (int p = 0; p < locationList.size(); p++){
      if (locationList.get(p) < minInt){
        minInt = locationList.get(p);
        minIndex = p;
      }
    }
  }
}
