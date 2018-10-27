  import java.util.*;

public class GoldenHour {
  private static int[][] memoize = new int[367][367];
  public static void main(String[] args) {
      Calendar calendar = Calendar.getInstance();
      int dayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
      Scanner sc = new Scanner(System.in);
      System.out.println("Enter the day which you want to know the golden hour: ");
      int day = dayOfYear;
      String check = sc.nextLine();
      if(check.equals("")){
        day = dayOfYear;
        System.out.println("Current day of year: "+dayOfYear);
      }else if(Integer.parseInt(check) > 366 || Integer.parseInt(check) < 0){
        System.out.println("Please enter a valid day of the year");
        return;
      }else{
        day = Integer.parseInt(check);
      }
      sc.close();
      int bronze = day;
      int copper = day;

    for (int[] items: memoize) {
      for (int item: items) {
        item = 0;
      }
    }
      int goldenHour = goldHour(copper, bronze);
      System.out.println("Golden hour: "+goldenHour);
  }

  public static int goldHour(int copper, int bronze) {
    if (memoize[copper][bronze] != 0) {
      return memoize[copper][bronze];
    }
    if (copper == 1) {
      return 1;
    }else if (copper == bronze){
      int result = goldHour(copper-1, bronze) + 1;
      while (result > 12) {
        result -= 12;
      }
      if (memoize[copper][bronze] == 0) {
        memoize[copper][bronze] = result;
      }
      return result;
    } else if (copper > bronze){
      int result = goldHour(bronze, bronze);
      while (result > 12) {
        result -= 12;
      }
      if (memoize[copper][bronze] == 0) {
        memoize[copper][bronze] = result;
      }
      return result;
    } else if (bronze > copper){
      int result = goldHour(copper, (bronze-copper)) + goldHour(copper-1 ,bronze);
      while (result > 12) {
        result -= 12;
      }
      if (memoize[copper][bronze] == 0) {
        memoize[copper][bronze] = result;
      }
      return result;
    }
    return 0;
  }
}
