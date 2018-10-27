package threes;
import java.math.*;
import java.util.*;
/** class Threes.java
* @author Ryan Carter
* 27/09/18
* prints out the first 70 sets that satisfy the equation
* z < x < y && z^4+1 = y^2 + x^2
* first by increasing x, then increasing z
*/
public class Threes{
  private static int resultCount = 1;
  public static void main(String[] args){
    double i = 0;
    while(resultCount <= 70){
      findX(i);
      i++;
    }
    resultCount = 1;
    i = 0;
    System.out.println();
    while(resultCount <= 70){
      findZ(i);
      i++;
    }
}

/** findX method
* prints out the first 70 sets by increasing x
* @param double z, the increasing x value
*/
public static void findX(double x){
  double y = 1;
  double z = 1;
   while(z < x && resultCount <= 70){
    y = ((z*z*z*z)+1)-(x*x);
    if(((x*x) < y) && isSquare(y)){
      y = Math.sqrt(y);
      if(GCD(y,x) == 1 && GCD(y,z) == 1 && GCD(z,x) == 1) {
        //xResult.add({resultCount,Math.round(x),Math.round(y),Math.round(z)});
        System.out.println(resultCount + " "+ Math.round(x)+ " "+ Math.round(y)+ " "+ Math.round(z));

        resultCount++;
      }
    }
      z++;
  }
}

/** findZ method
* prints out the first 70 sets by increasing z
* @param double z, the increasing z value
*/
public static void findZ(double z){
  double x = z+1;
  double y = ((z*z*z*z)+1)-(x*x);
  int count = 0;
   while(((x*x) < ((z*z*z*z)+1)/2)) {
     y = ((z*z*z*z)+1)-(x*x);
     if(isSquare(y)) {
      y = Math.sqrt(y);
        if(GCD(x,y) == 1 && GCD(z,y) == 1 && GCD(z,x) == 1) {
          System.out.println(resultCount + " "+ Math.round(x)+ " "+ Math.round(y)+ " "+ Math.round(z));
          resultCount++;
          count++;
        }
    }
    x++;
  }
}

/** isSquare method
* calculates if the number given is a perfect square
* @param y, the number to test
* @return boolean, if it is a perfect isSquare
*/
public static boolean isSquare(double y){
  double test = Math.sqrt(y);
  if(test*test == y && test == Math.ceil(test)) {
    return true;
  }
  return false;
  }

/** GCD method
* calculates the greatest common divisor of two numbers
* if there is no common divisor, returns -1
* @param doubles test1 and test2, the numbers to test
* @return double, the GCD
*/
public static double GCD(double test1, double test2) {
  if (test2 == 0){
    return test1;
  }
    return GCD(test2, test1 % test2);
  }
}
