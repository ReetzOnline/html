/**Lauritta Burrows 5th September 2020
Text-based game written in Java.
Enjoy!

DEVELOPMENT IDEAS

- more interaction between player and words
- more description about rooms when 'look'
- include a couple hidden codes within the rooms
- give player a maximum of 50 moves to reach the end of the game
- make padlock a clue which can be attached to different objects
- include input request for age, terminate game for those under the age of 18
- make player information private and separate
- develop responses to match player's attributes, e.g. playing style
- word wrapping to improve output
*/

import java.util.ArrayList;
import java.util.Scanner;

public class Game1 {

  static int row = 0;
  static boolean quit = false;
  static String directions = "You are now in the %s. You can see %s. %n";
  static String distanceView = "You're looking at the %s from a distance. ";
  static String nonSpecial = "There is nothing special to see here.\n";
  static String ofInterest = "Hmmmm, there is more to see here.\n";
  static String findings = "You find a %s in the %s. %n";
  static String sense = "You sense something about this clue.\n";
  static String noMagic = "This clue doesn't appear to have any magical qualities.\n";
  static String toBag = "You added the %s to your bag.\n";
  static String fromBag = "You no longer have the %s in your bag.\n";
  static boolean shootPad = false;
  static boolean partTwo = false;
  static ArrayList<String> bag;
  static final String quitA = "quit";
  static final String quitB = "q";

  public static void main(String[] args) {

    Scanner start = new Scanner(System.in);

    System.out.println(" ");
    System.out.println("Welcome wanderer. You've entered the quest to reach Glandor Castle. Type 'quit' or 'q' to end the game at any time.\n\nWhat do you call yourself?");

    String playerName = start.nextLine();

    if (playerName.equals(quitA)){
      quit();return;
    }
    if (playerName.equals(quitB)){
      quit();return;
    }

    System.out.println(" ");
    System.out.println("How old are you? Enter your age in numbers, e.g. 22.");


    int playerAge = testAge();
    if (playerAge == 0){
      quit();return;
    }
    if (playerAge < 18){
      System.out.print("Sorry. You need to be 18 or over to play this game.");
      return;
    }

    Player wanderer = new Player(playerName, playerAge);

    Location lounge = new Location("North", "lounge", "a sofa and table");
    Location bathroom = new Location("South", "bathroom", "a cabinet unit");
    Location bedroom = new Location("East", "bedroom", "a bed and chest");
    Location kitchen = new Location("West", "kitchen", "a fridge and oven");

    Furnature sofa = new Furnature(false, "sofa");
    Furnature table = new Furnature(false, "table");
    Furnature cabinet = new Furnature(false, "cabinet");
    Furnature bed = new Furnature(true, "bed");
    Furnature chest = new Furnature(false, "chest");
    Furnature fridge = new Furnature(false, "fridge");
    Furnature oven = new Furnature(true, "oven");

    Clue key = new Clue(false, "key", "sofa");
    Clue gun = new Clue(false, "gun", "table");
    Clue letter = new Clue(true, "letter", "cabinet");
    Clue mirror = new Clue(true, "mirror", "chest");

    bag = new ArrayList<String>();

    System.out.printf("%n***********%n");
    System.out.printf("%nHi %s. %n%nYou are currently floating above a mystical cottage. On your back is an empty bag which acts as your inventory.%nFind the clues to take you to your destiny. %n%n"
      + "Type 'help' (no quotation marks) at any time for assistance.%nType 'quit' or 'q' to end the game.%n%n", wanderer.getName());
    System.out.printf("***********%n");

    Scanner input = new Scanner(System.in);

    bridge://label to be used in furture development of game
    while (quit!=true){
        System.out.println("\nWhat would you like to do now?"+"\n");
        String command = input.nextLine();

        switch(command){

            case quitA: case quitB: quit(); quit=true; break;
            case "help": help(); break;
            case "hint": hint(); break;
            case "inv": inventory(); break;
            //|Lounge
            case "north": case "lounge": System.out.printf(directions, lounge.getRoom(), lounge.getFurnishings()); break;
            case "look lounge": System.out.printf(lounge.getDirection() + " - " + directions, lounge.getRoom(), lounge.getFurnishings()); break;
            case "look sofa": System.out.printf(distanceView, sofa.getType());
              if (sofa.getEmptiness()){System.out.println(nonSpecial);} else {System.out.println(ofInterest);}; break;
            case "search sofa": System.out.printf(findings, key.getItem(), key.getHidingSpot());
              if (key.getMagical()){System.out.println(sense);} else {System.out.println(noMagic);}; break;
            case "take key": bag.add("key"); System.out.printf(toBag, key.getItem()); inventory(); break;
            case "drop key": bag.remove("key"); System.out.printf(fromBag, key.getItem()); inventory(); break;
            case "look table": System.out.printf(distanceView, table.getType());
              if (table.getEmptiness()){System.out.println(nonSpecial);} else {System.out.println(ofInterest);}; break;
            case "search table": System.out.printf(findings, gun.getItem(), gun.getHidingSpot());
              if (gun.getMagical()){System.out.println(sense);} else {System.out.println(noMagic);}; break;
            case "take gun": bag.add("gun"); System.out.printf(toBag, gun.getItem()); inventory(); break;
            case "drop gun": bag.remove("gun"); System.out.printf(fromBag, gun.getItem()); inventory(); break;
            //bathroom
            case "south": case "bathroom": System.out.printf(directions, bathroom.getRoom(), bathroom.getFurnishings()); break;
            case "look bathroom": System.out.printf(bathroom.getDirection() + " - " + directions, bathroom.getRoom(), bathroom.getFurnishings()); break;
            case "look cabinet": System.out.printf(distanceView, cabinet.getType());
              if (cabinet.getEmptiness()){System.out.println(nonSpecial);} else {System.out.println(ofInterest);}; break;
            case "search cabinet": case "open cabinet": System.out.printf(findings, letter.getItem(), letter.getHidingSpot());
              if (letter.getMagical()){System.out.println(sense);} else {System.out.println(noMagic);}; break;
            case "take letter": bag.add("letter"); System.out.printf(toBag, letter.getItem()); inventory(); break;
            case "drop letter": bag.remove("letter"); System.out.printf(fromBag, letter.getItem()); inventory(); break;
            //bedroom
            case "east": case "bedroom": System.out.printf(directions, bedroom.getRoom(), bedroom.getFurnishings()); break;
            case "look bedroom": System.out.printf(bedroom.getDirection() + " - " + directions, bedroom.getRoom(), bedroom.getFurnishings()); break;
            case "look bed": System.out.printf(distanceView, bed.getType());
              if (bed.getEmptiness()){System.out.println(nonSpecial);} else {System.out.println(ofInterest);}; break;
            case "search bed": System.out.println(nonSpecial); break;
            case "look chest": case "open chest": System.out.printf(distanceView, chest.getType());
              if (chest.getEmptiness()){System.out.println(nonSpecial);} else {System.out.println(ofInterest);}; break;
            case "search chest": System.out.printf(findings, mirror.getItem(), mirror.getHidingSpot());
              if (mirror.getMagical()){System.out.println(sense);} else {System.out.println(noMagic);}; break;
            case "take mirror": bag.add("mirror"); System.out.printf(toBag, mirror.getItem()); inventory(); break;
            case "drop mirror": bag.remove("mirror"); System.out.printf(fromBag, mirror.getItem()); inventory(); break;
            //Kitchen
            case "west": case "kitchen": System.out.printf(directions, kitchen.getRoom(), kitchen.getFurnishings()); break;
            case "look kitchen": System.out.printf(kitchen.getDirection() + " - " + directions, kitchen.getRoom(), kitchen.getFurnishings()); break;
            case "look oven": System.out.printf(distanceView, oven.getType());
              if (oven.getEmptiness()){System.out.println(nonSpecial);} else {System.out.println(ofInterest);}; break;
            case "search oven": case "open oven": System.out.println(nonSpecial); break;
            case "look fridge": System.out.printf(distanceView, fridge.getType() + " (it's padlocked)"); break;
            case "shoot padlock": case "shoot lock": shootPad = true; System.out.println("The padlock falls away and the fridge opens.\n"); break;
            case "search fridge": case "open fridge": partTwo = true; if(shootPad){System.out.println("You notice a secret passage leading down into the earth. You lean against the wall of the passage way entrance. It's coolness has a calming effect, as distant sounds hum and vibrate through the surface. You can see a soft glow in the distance inviting you in.\n"
              + "Treasures await but there's no need to rush. Take a rest and check your inventory. "); inventory();
                if (Clue.countObjects > bag.size()) {System.out.printf("You're missing %d clue(s). Head back, collect more clues, and then search here again. %n", (Clue.countObjects - bag.size()));}
                else {System.out.println("You have all the clues you need to enter Glandor Castle. Instructions will follow in Part 2 of this game.");}; break;}
              else {System.out.println("You can't open the fridge to search it.");}; break;
            case "look part 2": System.out.println("Baby there ain't no part 2. Go do some workplace activities."); break;
            case "look": System.out.println("There are four rooms in this cottage. What would you like to see?"); break;
            case "secrets" : bag.add("key"); bag.add("gun"); bag.add("letter"); bag.add("mirror"); shootPad = true; partTwo = true;
            case "part 2": case "part two": if (partTwo){System.out.println("The Silent Mage waits for you on the other side. You take a few steps into the passage and find you're suddenly frozen where you stand.\nYour breathing rate begins to rise. Anxiety threatens to rear its ugly head, until you realise you can still step backwards. \nWhen you do, a message fills your head, as if sent telepathically:\n \nDo not come now. Return to me with the blood moon.\n \nYou find yourself leaning on the wall once more. This is impossible, and yet so real.\nYou sense this mage somehow holds all the secrets of the castle. You want to meet her; you need to find out more.\nBut the next blood moon is so far away.  Surely instructions will come sooner. Surely!\n\nYou leave the cottage, determined to find a way to meet the mage and enter Glandor Castle..."); break bridge;}
              else {System.out.println("You aren't ready yet. You need to complete the game to reach Part 2.");}; break;
            default: System.out.println("You can't do that here."); break ;
        }//end of switch
    }//end of while loop

  }//end of main function


  public static int testAge(){
    int i;
    String tryAge;
    int validAge = 0;

    Scanner age = new Scanner(System.in);

    for (i = 1; i > 0; i++){
      System.out.println("Enter your age");
      tryAge = age.nextLine();
      try {validAge = Integer.parseInt(tryAge); return validAge;
      } catch (NumberFormatException e){System.out.println("You need to enter a valid number. Press 0 to quit");
      }
    } return validAge;
  }//end of test age method


public static void quit(){
  System.out.println("Thank you for playing, good bye.");
}//end of quit method


public static void help(){
  String direction = "to teleport to the north of the building (same applies for other directions)";
  String look = "to look at rooms, objects, etc. from a distance, e.g. look garage";
  String search = "to find clues, e.g. search car";
  String take = "to place item in your bag, e.g. take shoe";
  String drop = "to remove item from your bag, e.g. drop shoe";
  String inv = "to see what's in your inventory";
  String hint = "to get an assist, if you're stuck, but only if you really need it";
  String part2 = "to find out what happens next";
  String quit = "to end the game";

  System.out.printf("Commands here are case sensitive. Use any of the following commands to help you on your journey:%n%nnorth\t%s %nlook\t%s %nsearch\t%s %ntake\t%s %ndrop\t%s %ninv\t%s %nhint\t%s %npart 2\t%s %nquit\t%s %n%n", direction, look, search, take, drop, inv, hint, part2, quit);
    }//end of help method


public static void inventory() {
  System.out.printf("You have %d items in your bag: ", bag.size());
  for (String i: bag){
    System.out.print(i+" ");}
    System.out.println("\n");
  }//end of inventory method


  public static void hint() {
    try{
      String [][] gameHints = { {"1st hint ", "spelling is important."}, {"2nd hint ", "start in the North, can't go wrong."}, {"3rd hint ", "sometimes it helps to step back and take a look a things."}, {"4th hint ", "stick to those commands at your disposal."}, {"5th hint ", "search through items when you feel the need."}, {"6th hint", "silly, you can't carry furnature in your bag."}, {"7th hint", "serious? You need another hint?"} };
      for (int column=0; column < gameHints[row].length; column++){
        System.out.println(gameHints[row][column]);
      }
      row++;
    }
    catch(Exception e){
        System.out.println("You're out of hints.");
    }
  }//end of hint method


  //PLAYER
  public static class Player {
    String name;
    int age;

    Player(String name, int age) {
        this.setName(name);
        this.setAge(age);}

    public void setName(String name) {
        this.name = name;}

    public void setAge(int age) {
        this.age = age;}

      public String getName() {
        return this.name;}

    public int getAge() {
        return this.age;}
  }//end of player class


  //LOCATION
  public static class Location {
      String room;
      String direction;
      String furnishings;

      public Location(String direction, String room, String furnishings) {
          this.setDirection(direction);
          this.setRoom(room);
          this.setFurnishings(furnishings);}

      public void setDirection(String direction) {
          this.direction = direction;}

      public void setRoom(String room) {
          this.room = room;}

      public void setFurnishings(String furnishings) {
          this.furnishings = furnishings;}

      public String getDirection() {
          return this.direction;}

      public String getRoom() {
          return this.room;}

      public String getFurnishings() {
          return this.furnishings;}
  }//end of location class


  //FURNATURE
  public static class Furnature {
     boolean emptiness;
     String type; //change to ArrayLiist

     Furnature(boolean emptiness, String type) {
          this.setEmptiness(emptiness);
          this.setType(type);}

      public void setEmptiness(boolean emptiness) {
          this.emptiness = emptiness;}

      public void setType(String type) {
          this.type = type;}

      public boolean getEmptiness() {
          return this.emptiness;}

      public String getType() {
          return this.type;}
  }//end of furnature class


  //CLUE
  public static class Clue {
      String item;
      boolean magical;
      String hidingSpot;
      static int countObjects = 0;

      Clue(boolean magical, String item, String hidingSpot) {
          this.setMagical(magical);
          this.setItem(item);
          this.setHidingSpot(hidingSpot);
          countObjects ++;}

      public void setMagical(boolean magical) {
          this.magical = magical;}

      public void setItem(String item) {
          this.item = item;}

      public void setHidingSpot(String hidingSpot) {
          this.hidingSpot = hidingSpot;}

      public boolean getMagical() {
          return this.magical;}

      public String getItem() {
          return this.item;}

      public String getHidingSpot() {
          return this.hidingSpot;}
  }//end of furnature class


}//end of game class
