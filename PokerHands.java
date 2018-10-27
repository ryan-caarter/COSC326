package poker;
import java.util.*;
/** @author Ryan Carter
* 10/7/18
* PokerHands class
*/
public class PokerHands{
  private static String[] cards;
  private static String originalHand;
  private static String hand;

/** main method, loops over user input and returns if the hand is valid or not
* if valid, it returns the hand sorted, and to the etude specification
* if invalid, returns the submitted hand
*/
  public static void main(String[] args){
    Scanner scan = new Scanner(System.in);
    while(scan.hasNextLine()){
      originalHand = scan.nextLine();
      hand = originalHand.toUpperCase();
      replace();
      if(split() || checkDuplicates() || checkCards()){
        System.out.println("Invalid: "+originalHand);
      }else{
        sort();
        print();
      }
    }
    scan.close();
  }

/** replaces the J, Q, K, A, 10, 11, 12, and 13 cards with special
* values that make the sorting much easier, these cases are then
* replaced in the print() method
*/
  public static void replace(){
    for(int i = 1; i < hand.length(); i++){
      if(hand.substring(i-1,i).equals("A")){
        hand = hand.substring(0,i-1)+"E"+hand.substring(i,hand.length());
      }
    }
    for(int i = 2; i < hand.length(); i++){
      if (hand.substring(i-2,i).equals("13")){
        hand = hand.substring(0,i-2)+"D"+hand.substring(i,hand.length());
      }else if(hand.substring(i-2,i).equals("12")){
        hand = hand.substring(0,i-2)+"C"+hand.substring(i,hand.length());
      }else if(hand.substring(i-2,i).equals("11")){
        hand = hand.substring(0,i-2)+"B"+hand.substring(i,hand.length());
      }else if(hand.substring(i-2,i).equals("10")){
        hand = hand.substring(0,i-2)+"A"+hand.substring(i,hand.length());
      }
    }
      for(int i = 1; i < hand.length(); i++){
        if(hand.substring(i-1,i).equals("1")){
          hand = hand.substring(0,i-1)+"E"+hand.substring(i,hand.length());
        }else if(hand.substring(i-1,i).equals("K")){
          hand = hand.substring(0,i-1)+"D"+hand.substring(i,hand.length());
        }else if(hand.substring(i-1,i).equals("Q")){
          hand = hand.substring(0,i-1)+"C"+hand.substring(i,hand.length());
        }else if(hand.substring(i-1,i).equals("J")){
          hand = hand.substring(0,i-1)+"B"+hand.substring(i,hand.length());
        }else if(hand.substring(i-1,i).equals("T")){
          hand = hand.substring(0,i-1)+"A"+hand.substring(i,hand.length());
        }
      }
    }

/** splits the hand into an array, via "/" "-" or "<space>"
* then stores it in the cards data field
* checks if the separators are the same
* and also checks if the hand is the right length
*/
  public static boolean split(){
    if(hand.length() != 14){
      return true;
    }
    cards = new String[5];
    char firstSeparator = hand.charAt(2);
    int j = 0;

    for(int i = 2; i <= hand.length(); i+=3){
      char sep = (i != hand.length()) ? hand.charAt(i) : firstSeparator;
      if(i != hand.length() && sep != '/' && sep != ' ' && sep != '-' || sep != firstSeparator){
        return true;
      }
        cards[j] = hand.substring(i-2,i);
        j++;
      }
      return false;
    }

/** makes sure there are no duplicate cards in the deck
*/
    public static boolean checkDuplicates(){
      for(int i = 0; i < cards.length; i++){
        for(int j = 0; j < cards.length; j++){
          if(j + 1 == cards.length){
            break;
          }else if(j == i){
            j++;
          }
          if(cards[i].equals(cards[j])){
            return true;
          }
        }
      }
      return false;
    }
/** iterates through the hand given
* and checks that all the cards are legitimate
*/
    public static boolean checkCards(){
      for(int i = 0; i < cards.length; i++){
        boolean flag = true;
        switch(cards[i].charAt(1)){
          case 'C':
            break;
          case 'D':
            break;
          case 'H':
            break;
          case 'S':
            break;
          default:
            return true;
        }
        switch(cards[i].charAt(0)){
          case 'A':
          flag = false;
            break;
          case 'B':
          flag = false;
            break;
          case 'C':
          flag = false;
            break;
          case 'D':
          flag = false;
            break;
          case 'E':
          flag = false;
            break;
          default:
            break;
        }
        if(flag){
          try{
            if(cards[i].charAt(0) > '9'){
              return true;
            }
          }catch(Exception e){
            return true;
          }
        }
      }
      return false;
    }
/** sorts the cards, first by value (charAt(0)), then by suit (charAt(1))
*/
    public static void sort(){
      String swap = "";
      for (int i = 0; i < cards.length; i++){
        for (int j = 1; j < cards.length; j++){
            if(cards[j-1].charAt(0) > cards[j].charAt(0)){
              swap = cards[j];
              cards[j] = cards[j-1];
              cards[j-1] = swap;
            }
        }
      }
      for(int j = 0; j < 5; j++){
        for(int i = 1; i < cards.length; i++){
          if(cards[i-1].charAt(0) == cards[i].charAt(0)){
            char temp = (cards[i-1].charAt(1) > cards[i].charAt(1)) ? cards[i-1].charAt(1) : cards[i].charAt(1);
            if(temp == cards[i-1].charAt(1)){
              swap = cards[i-1];
              cards[i-1] = cards[i];
              cards[i] = swap;
            }
          }
        }
      }
    }

  /** prints out the hand given to standard output,
  * and replaces the special cases replaced earlier
  * with the correct values
  */
    public static void print(){
      for(int i = 0; i < cards.length; i++){
        if(cards[i].charAt(0) == 'A'){
          cards[i] = "10"+cards[i].substring(1,cards[i].length());
        }else if(cards[i].charAt(0) == 'B'){
          cards[i] = "J"+cards[i].substring(1,cards[i].length());
        }else if(cards[i].charAt(0) == 'C'){
          cards[i] = "Q"+cards[i].substring(1,cards[i].length());
        }else if(cards[i].charAt(0) == 'D'){
          cards[i] = "K"+cards[i].substring(1,cards[i].length());
        }else if(cards[i].charAt(0) == 'E'){
          cards[i] = "A"+cards[i].substring(1,cards[i].length());
        }
        if(i != cards.length-1){
        System.out.print(cards[i]+" ");
      }else{
        System.out.print(cards[i]);
      }
    }
    System.out.println();
  }
}
