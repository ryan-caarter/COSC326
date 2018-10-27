import java.util.*;
/** Smokers Problem
* @author Ryan Carter, James Zhao, Harry Mead
* 27/9/18
* an implementation of the smokers problem solution
*/
public class Smokers{
  private static ArrayList<String> indices;
  private static int[] gridSize = new int[2];
  private static final char nonSmoker = 'N';
  private static char[][] grid;
  private static String startState = "0,0";
  private static String endState;
  private static ArrayList<int[]> nodeList;
  private static String current;
  private static Map<String, String> parentNodes;
  private static HashMap<String,ArrayList<int[]>> currentExpansions;

  public static void main(String[] args){
    Scanner scan = new Scanner(System.in);
    ArrayList<String[]> scenario = new ArrayList<String[]>();
    while(scan.hasNextLine()){
      String input = scan.nextLine();
      if(input.isEmpty()){
        indices = new ArrayList<String>();
        nodeList = new ArrayList<int[]>();
        doScenario(scenario);
        scenario = new ArrayList<String[]>();
      }else{
        String[] split = input.split(" ");
        scenario.add(split);
      }
    }
    indices = new ArrayList<String>();
    nodeList = new ArrayList<int[]>();
    doScenario(scenario);
    scenario = new ArrayList<String[]>();
}

/** doScenario method
* runs the test on a specific scenario
* @param ArrayList<String[]> scenario, the scenario to evaluate
*/
public static void doScenario(ArrayList<String[]> scenario){
  int count = 0;
  while(count < scenario.size()){
    if(count == 0){
      gridSize[0] = Integer.parseInt(scenario.get(count)[0]);
      gridSize[1] = Integer.parseInt(scenario.get(count)[1]);
      endState = Integer.toString(gridSize[0]-1)+","+Integer.toString(gridSize[1]-1);
    }else{
      String result = Integer.parseInt(scenario.get(count)[1])+","+Integer.parseInt(scenario.get(count)[0]);
      indices.add(result);
    }
      count++;
    }

grid = new char[gridSize[0]][gridSize[1]];
for(int i = 0; i < gridSize[0]; i++){
  for(int j = 0; j < gridSize[1]; j++){
      String result = Integer.toString(i)+","+Integer.toString(j);
      if(indices.contains(result)){
        grid[i][j] = nonSmoker;
      }else{
        grid[i][j] = '.';
      }
    }
}
 print(); //uncomment this to see the original grid
currentExpansions = new HashMap<String,ArrayList<int[]>>();
for(int i = 0; i < indices.size(); i ++){
  ArrayList<int[]> result = new ArrayList<int[]>();
  int[] array = {Integer.parseInt(indices.get(i).split(",")[0]),Integer.parseInt(indices.get(i).split(",")[1])};
  result.add(array);
  currentExpansions.put(indices.get(i), result);
}
out:
while(hasPath()){
  for(int i = 0; i < indices.size(); i ++){
    int x = Integer.parseInt(indices.get(i).split(",")[0]);
    int y = Integer.parseInt(indices.get(i).split(",")[1]);
    if(currentExpansions.containsKey(indices.get(i))){
      expandNode(currentExpansions.get(indices.get(i)),i);
    }
    if(!hasPath()){
      reduceNodes();
      currentExpansions.remove(indices.get(i));
    }
    if(currentExpansions.size() == 0){
      hasPath();
      break out;
    }
  }
}
 print(); //uncomment this to see the path
printDistances();
}

/** expandNode method
* expands to the nodes around a given node
* @param i,j the indices of the node to expand
*/
public static void expandNode(ArrayList<int[]> expandThis, int index){
  for(int l = 0; l < gridSize[0]; l++){
    for(int h = 0; h < gridSize[1]; h++){
      if(grid[l][h] == '-'){
        grid[l][h] = nonSmoker;
      }
    }
  }
  ArrayList<int[]> coordinates = new ArrayList<int[]>();
  for(int[] array: expandThis){
    int i = array[0];
    int j = array[1];
    if(grid[i][j] == nonSmoker){
      if((i-1 >= 0) && (i-1 < grid.length)){
        if(grid[i-1][j] == '.'){
          grid[i-1][j] = '-';
          int[] res = {i-1,j};
          coordinates.add(res);
        }else{
          int[] res = {i-1,j};
          coordinates.add(res);
        }
      }
      if((j-1 >= 0) && (j-1 < grid[i].length)){
        if(grid[i][j-1] == '.'){
        grid[i][j-1] = '-';
        int[] res = {i,j-1};
        coordinates.add(res);
      }else{
        int[] res = {i,j-1};
        coordinates.add(res);
      }
    }
      if((i+1 >= 0) && (i+1 < grid.length)){
        if(grid[i+1][j] == '.'){
        grid[i+1][j] = '-';
        int[] res = {i+1,j};
        coordinates.add(res);
      }else{
        int[] res = {i+1,j};
        coordinates.add(res);
      }
    }
      if((j+1 >= 0) && (j+1 < grid[i].length)){
        if(grid[i][j+1] == '.'){
        grid[i][j+1] = '-';
        int[] res = {i,j+1};
        coordinates.add(res);
      }else{
        int[] res = {i,j+1};
        coordinates.add(res);
      }
    }
    }
  }
  for(int[] coords: coordinates){
    expandThis.add(coords);
  }
  currentExpansions.put(indices.get(index),expandThis);
}

/** reduceNodes method
* gets called if the last expansion blocked the path,
* reduces all the nodes
* @param ArrayList<String> coordinates, the coordinates of the expanded nodes
*/
public static void reduceNodes(){
  for(int i = 0; i < gridSize[0]; i++){
    for(int j = 0; j < gridSize[1]; j++){
      if(grid[i][j] == '-'){
        grid[i][j] = '.';
      }
    }
  }
}

/** getChildren method
* returns the children of the node for the DFS algorithm
* @param i,j the index of the node
* @return ArrayList<String>, the children
*/
public static ArrayList<String> getChildren(int i, int j){
    ArrayList<String> childNodes = new ArrayList<>();
    if(((i+1 >= 0) && (i+1 < grid.length)) && grid[i+1][j] == '.'){
      childNodes.add(Integer.toString(i+1)+","+Integer.toString(j));
    }
    if(((j+1 >= 0) && (j+1 < grid[i].length)) && grid[i][j+1] == '.'){
      childNodes.add(Integer.toString(i)+","+Integer.toString(j+1));
    }
    return childNodes;
}
/** hasPath method
* implements DFS to find a path and return if the graph has one
* @return boolean, has a path from start to finish
*/
public static boolean hasPath(){
    if(startState.equals(endState)){
        return true;
    }
    if(grid[0][0] != '.'){
      return false;
    }
    Stack<String> stack = new Stack();
    ArrayList<String> explored = new ArrayList<String>();
    parentNodes = new HashMap();
    current = "";
    stack.push(startState);
    parentNodes.put(startState,startState);

    while(!stack.isEmpty()){
      current = stack.pop();
      if(current.equals(endState)) {
        return true;
      }else{
        ArrayList<String> children = getChildren(Integer.parseInt(current.split(",")[0]),Integer.parseInt(current.split(",")[1]));
        out:
        if(children.isEmpty()){
          break out;
        }else{
          for(String s: children){
            parentNodes.put(s,current);
          }
          stack.addAll(children);
        }
      }
    }
     return false;
  }
/** printDistances method
* calculates and prints the best mininum and total distances from non-Smokers
* using the manhattan distance
*/
public static void printDistances(){
  String backPath = current;
  int min = gridSize[0]*gridSize[1];
  ArrayList<int[]> path = new ArrayList<int[]>();
  HashMap<String,Integer> minDistances = new HashMap();
  int count = 0;
  int[] start = {Integer.parseInt(backPath.split(",")[1]),Integer.parseInt(backPath.split(",")[0])};
  path.add(start);
  for(;;){
    backPath = parentNodes.get(backPath);
    int [] res = {Integer.parseInt(backPath.split(",")[1]),Integer.parseInt(backPath.split(",")[0])};
    path.add(res);
    if(backPath.equals(startState)){
      break;
    }
  }

  for(int i = 0; i < indices.size(); i++){
    for(int[] arr: path){
      String[] res = indices.get(i).split(",");
      int x = Integer.parseInt(res[1]);
      int y = Integer.parseInt(res[0]);
      int distance = Math.abs(arr[0]-x)+Math.abs(arr[1]-y);
      if(minDistances.containsKey(indices.get(i))){
        if(distance < minDistances.get(indices.get(i))){
          minDistances.put(indices.get(i), distance);
        }
      }else{
        minDistances.put(indices.get(i),distance);
      }
    }
  }
  for(String s: minDistances.keySet()){
    int i = minDistances.get(s);
    count += i;
    if(i < min){
      min = i;
    }
  }
  System.out.println("min "+min+", total "+count);
}

/** print method
* prints out the current grid
*/
  public static void print(){
      for(char[] array: grid){
        for(char c: array){
          System.out.print(c+" ");
        }
      System.out.println();
      }
    System.out.println();
  }
}
