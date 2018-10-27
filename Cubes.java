package cubes;

/** class Cubes
* @author Ryan Carter, Jaydin McMillan, Gus Auwerda
* @date  5/9/18
* calculates all cube combinations for a 2x2x2 cube with 2 colours,
* these cubes are represented in binary format, with a 0 representing a yellow
* cube, and a 1 represeting a blue cube
* uses a permutation generator to view each binary cube representation
* at different rotations/sides, then compares each of the already unique cubes
* to the new permutations and then adds the cube if it is unique,
* or moves on if any of the permutations match the other permutations
*/

import java.util.*;

public class Cubes{
  // uniqueCubes, an ArrayList to hold all the unique cubes
  private static ArrayList<String> uniqueCubes = new ArrayList<>();

/** main method, generates 256 possible cube combinations,
* calls the checkCube method on each to see if they are unique,
* if unique it adds it to the uniqueCubes ArrayList
* finishes by calling a print method
*/
  public static void main(String[] args){
    String cube = "";
    for(int zero = 0; zero < 2; zero++){
      for(int one = 0; one < 2; one++){
        for(int two = 0; two < 2; two++){
          for(int three = 0; three < 2; three++){
            for(int four = 0; four < 2; four++){
              for(int five = 0; five < 2; five++){
                for(int six = 0; six < 2; six++){
                  for(int seven = 0; seven < 2; seven++){
                    cube = zero+""+one+""+two+""+three+""+four+""+five+""+six+""+seven;
                    if(checkCube(cube)){
                      uniqueCubes.add(cube);
                    }
                    }
                  }
                }
              }
            }
          }
        }
      }
    cubesToString();
  }

/* checkCube method, loops through the uniqueCubes ArrayList,
* generates all the permutations of each and compares them against
* the permutations of the cube being sent to this method
* @return true/false, whether the cube is unique or not
*/
    public static boolean checkCube(String cube){
      List<String> cubePermute = genPermute(cube);
      for(String s: uniqueCubes){
        List<String> uniquePermute = genPermute(s);
        for(int j = 0; j < uniquePermute.size(); j++){
          for(int i = 0; i < cubePermute.size(); i++){
            if(uniquePermute.get(j).equals(cubePermute.get(i))){
              return false;
            }
          }
        }
      }
      return true;
}

/** this method takes the string representation of the cube
* and generates strings from it that are the same as the original cube
* but from different rotations on each axis (x, y, z)
* to a total of 24 permutations per cube
* @param s, a binary string representation of a cube
* @return result, an ArrayList containing the cubes permutations
*/
    public static List<String> genPermute(String s){
      ArrayList<String> result = new ArrayList<String>();

      /** adds the cubes from rotating on the x axis to the result (front, left, back, right)
      */
      result.add(s);
      result.add(s.substring(5,6)+s.substring(0,1)+s.substring(7,8)+s.substring(2,3)+s.substring(1,2)+s.substring(4,5)+s.substring(3,4)+s.substring(6,7));
      result.add(s.substring(4,8)+s.substring(0,4));
      result.add(result.get(1).substring(4,8)+result.get(1).substring(0,4));


      /** adds the y-axis rotations (top, bottom)
      * (minus the second one which is the same as the second x-axis rotation)
      */
      result.add(s.substring(5,6)+s.substring(4,5)+s.substring(0,1)+s.substring(1,2)+s.substring(6,7)+s.substring(7,8)+s.substring(3,4)+s.substring(2,3));
      result.add(result.get(2).substring(4,8)+result.get(2).substring(0,4));

      /** for each rotation added, we add the three z-axis rotations
      * for each of them
      */
      for(int i = 0; i < 6; i++){
        /** rotates the permutation once to the right on the z-axis
        * and adds it to result
        */
        result.add(result.get(i).substring(1,2)+result.get(i).substring(3,4)+result.get(i).substring(0,1)+result.get(i).substring(2,3)+result.get(i).substring(6,7)+result.get(i).substring(4,5)+result.get(i).substring(7,8)+result.get(i).substring(5,6));

        /** rotates the permutation twice to the right on the z-axis
        * and adds it to result
        */
        result.add(result.get(i).substring(2,3)+result.get(i).substring(0,1)+result.get(i).substring(3,4)+result.get(i).substring(1,2)+result.get(i).substring(5,6)+result.get(i).substring(7,8)+result.get(i).substring(4,5)+result.get(i).substring(6,7));

        /** rotates the permutation three times to the right on the z-axis
        * and adds it to result
        */
        result.add(result.get(i).substring(3,4)+result.get(i).substring(2,3)+result.get(i).substring(1,2)+result.get(i).substring(0,1)+result.get(i).substring(7,8)+result.get(i).substring(6,7)+result.get(i).substring(5,6)+result.get(i).substring(4,5));
      }
      return result;
    }

/** Prints each cube in an easier to interpret format with information,
* prints how many of each ratio cube there is and then prints the number
* of unique cubes total
*/
    public static void cubesToString(){
      int[] zeroCubeCount = new int[5];

      System.out.println("\nPrinted in the format *front face* -> *back face*\n");
      for(String cube: uniqueCubes){
          int count = 0;
          for(int i = 0; i < cube.length(); i++){
            if(cube.charAt(i) == '0'){
              count++;
            }
        }
        switch(count){
          case 8:
            zeroCubeCount[0]++;
            break;
          case 0:
            zeroCubeCount[0]++;
            break;
          case 7:
            zeroCubeCount[1]++;
            break;
          case 1:
            zeroCubeCount[1]++;
            break;
          case 6:
            zeroCubeCount[2]++;
            break;
          case 2:
            zeroCubeCount[2]++;
            break;
          case 5:
            zeroCubeCount[3]++;
            break;
          case 3:
            zeroCubeCount[3]++;
            break;
          default:
            zeroCubeCount[4]++;
            break;
          }
          System.out.println(count+":"+(8-count));
          System.out.println(cube.charAt(0)+" "+cube.charAt(1)+" -> "+cube.charAt(4)+" "+cube.charAt(5));
          System.out.println(cube.charAt(2)+" "+cube.charAt(3)+" -> "+cube.charAt(6)+" "+cube.charAt(7));
          System.out.println("-------------\n");
      }

      System.out.print("Number of cubes in 0:1 ratio format:");
      System.out.print("0:8 - "+zeroCubeCount[0]+"  ");
      System.out.print("1:7 - "+zeroCubeCount[1]+"  ");
      System.out.print("2:6 - "+zeroCubeCount[2]+"  \n");
      System.out.print("3:5 - "+zeroCubeCount[3]+"  ");
      System.out.print("4:4 - "+zeroCubeCount[4]+"\n\n");
      System.out.println("Number of unique cubes: "+uniqueCubes.size()+"\n");
    }

}
