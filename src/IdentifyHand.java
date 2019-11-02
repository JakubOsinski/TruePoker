
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class IdentifyHand {

    public static int[] checkPairs(ArrayList<Card> playerCards) {
        int[] cardCounter = new int[13];
        for (int i = 0; i < playerCards.size(); i++) {
            switch (playerCards.get(i).getRank()) {

                case "2":
                    cardCounter[0]++;
                    break;
                case "3":
                    cardCounter[1]++;
                    break;
                case "4":
                    cardCounter[2]++;
                    break;
                case "5":
                    cardCounter[3]++;
                    break;
                case "6":
                    cardCounter[4]++;
                    break;
                case "7":
                    cardCounter[5]++;
                    break;
                case "8":
                    cardCounter[6]++;
                    break;
                case "9":
                    cardCounter[7]++;
                    break;
                case "10":
                    cardCounter[8]++;
                    break;
                case "Jack":
                    cardCounter[9]++;
                    break;
                case "Queen":
                    cardCounter[10]++;
                    break;
                case "King":
                    cardCounter[11]++;
                    break;
                case "Ace":
                    cardCounter[12]++;
                    break;
            }
        }
        return cardCounter;
    }

    public static void checkRanks(int[] cardCounter, Player player, int gameStatus) {//jaki jest system mocy?
        int pair = 0;
        int trips = 0;
        int quads = 0;

        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
        String rank = "";
        ArrayList<String> ranksToGet = new ArrayList<String>();

                  boolean[] hand = new boolean[10];

        for (int i = 0; i < cardCounter.length; i++) {
            if (cardCounter[i] == 4) // if 4 of the same kind
            {
                System.out.println(" quads");
                ranksToGet.add(ranks[i]);
                
                  quads++;
                System.out.println("there are now : " + quads + " quads.");
                hand[7] = true;        
            }
        }

        for (int i = 0; i < cardCounter.length; i++) {
            if (cardCounter[i] == 3) // if 4 of the same kind
            {
                System.out.println(" trips");
                ranksToGet.add(ranks[i]);
                
                  trips++;
                hand[3] = true;
            }
        }
        for (int i = 0; i < cardCounter.length; i++) {
            if (cardCounter[i] == 2 && quads  == 0) // if 4 of the same kind
            {
                System.out.println(" pair ");
                ranksToGet.add(ranks[i]);
                
                pair++;
                hand[1] = true;
            }
        }
        
        
        if(pair == 3) 
        {
        System.out.println("three pairs") ;
        ranksToGet.remove(0); // remove the lowest pair
        }
  if(pair == 2 && trips == 1) 
        { // first index[0] is always trips, last is (always???) the highest
        System.out.println("pairs = 2 + trips = 1") ; 
        System.out.println(ranksToGet.get(0));
        System.out.println(ranksToGet.get(1));
        System.out.println(ranksToGet.get(2));
        ranksToGet.remove(1); // remove the lowest pair
        }
   
        boolean check = false; // separate array with all cards that appear more than once
        ArrayList<Card> cards = new ArrayList<Card>();
        for (int i = 0; i < ranksToGet.size(); i++) {
            for (int j = 0; j < player.getPlayerCards().size(); j++) {
                if (ranksToGet.get(i).equals(player.getPlayerCards().get(j).getRank())) {
                    System.out.println("   adding card with index : " + player.getPlayerCards().get(j).getCardIndex());
                    cards.add(player.getPlayerCards().get(j));
                    check = true;
                }
            }
        }
        if (check) {
            player.setRealCards(cards);
        }

      
        int count = 0; // how many high cards did you get?
        //fill with highcards
        if (player.getRealCards().size() < 5) {
            for (int i = 0; i < player.getPlayerCards().size(); i++) {
                if (!cards.contains(player.getPlayerCards().get(i)) && cards.size() < 5) {
                    cards.add(player.getPlayerCards().get(i));
                    count++;
                }
            }
        }
        System.out.println(count + " : is the count of high cards you got");


        if (count == 5) {
          player.setRealCards(cards);
            hand[0] = true;
        }



        if (trips >= 1 && pair >= 1) { // start full housec
            System.out.println(" full house");
            hand[6] = true;
        }
        if (trips > 1 && quads == 0) {
            System.out.println(" full house  = trips x2");
            hand[6] = true;
            cards.remove(0);
        }   
          System.out.println(pair + " : pairs n.");
       System.out.println(trips + " : trips n.");
         System.out.println(quads + " : quads n.");
        if(quads == 1 && trips == 1) {
                cards.remove(cards.size()-1);
            cards.remove(cards.size()-2);
             hand[7] = true;
            }
        

        if (pair > 1) { // two pair
            hand[2] = true;
        }

        if (pair == 0) {
            hand[0] = true;
        }

        player.setPlayerHand(hand);
    }


    public static int getHandPower(Player player) {
        if (player.getPlayerHand()[9]) {
            System.out.println("royal poker");
            return 100000+ getPoints(player);
        }
        if (player.getPlayerHand()[8]) {
            System.out.println("straight flush");
            return 50000+ getPoints(player);
        }
          if (player.getPlayerHand()[7]) {
            System.out.println("quads");
            return 30000 + getPoints(player);
        }
        
  if (player.getPlayerHand()[6]) {
            System.out.println("full house is here");
            return 20000 + getPoints(player);
        }
        
        if (player.getPlayerHand()[5]) {
            System.out.println("color");
            return 10000 + getPoints(player);
        }
        if (player.getPlayerHand()[4]) {
            System.out.println("strit");
            return 5000 + getPoints(player);
        }

        if (player.getPlayerHand()[3]) {
            System.out.println("TRIPS");
            return 600 + getPoints(player);
        }

        if (player.getPlayerHand()[2]) {
            System.out.println("two pair");
            return 300 + getPoints(player);
        }

        if (player.getPlayerHand()[1]) {
            System.out.println("pair");
            return 100 + getPoints(player);
        }

        if (player.getPlayerHand()[0]) {
            System.out.println("high card");
            return 0 + getPoints(player);
        }

        return 0;
    }

    public static int getPoints(Player player) {
        int points = 0;
        for (int i = 0; i < player.getRealCards().size(); i++) {
            for (int j = Card.ranks.length - 1; j >= 0; j--) { //2 = 1p. 3 = 2p. 4=3p, 5 .
                //       System.out.println(Card.ranks[j]);
                if (player.getRealCards().get(i).getRank().equals(Card.ranks[j])) {
                    //       System.out.println(player.getPlayerCards().get(i).getRank() + " j : " + (j+1));
                    points = points + (j + 1);
                }
            }
        }
        return points;
    }

    public static int[] checkSuits(ArrayList<Card> playerCards) {
        int[] colourCounter = new int[4];
        for (int i = 0; i < playerCards.size(); i++) {
            switch (playerCards.get(i).getSuit()) {
                case "clubs":
                    colourCounter[0]++;
                    break;
                case "hearts":
                    colourCounter[1]++;
                    break;
                case "diamonds":
                    colourCounter[2]++;
                    break;
                case "spades":
                    colourCounter[3]++;
                    break;
            }

        }
        return colourCounter;
    }

    public static String checkFlush(int[] colourCounter, Player player) {
        boolean[] hand = player.getPlayerHand();
        String[] color = {"clubs", "hearts", "diamonds", "spades"};
        String suit = "";
        String flushColour = "";

        for (int i = 0; i < colourCounter.length; i++) {
            if (colourCounter[i] > 4) {
                System.out.println("checkFlush() flush detected");
                suit = color[i];
                flushColour = suit;
                hand[5] = true;
            }
        }
        ArrayList<Card> flush = new ArrayList<Card>();
        ArrayList<Card> playerCards = player.getPlayerCards();
        for (int i = 0; i < playerCards.size(); i++) {
            //    System.out.println("does '" + playerCards.get(i).getSuit() + "' equal : '" + suit + "'");
            if (playerCards.get(i).getSuit().equals(suit)) {
                if (flush.size() < 5) {
                    flush.add(playerCards.get(i));
                }
            }
        }

//         
        if (hand[5]) {
            if(!hand[7] && !hand[6]) 
            {
            player.setRealCards(flush);
              doesFlushContainStraight(player,hand,flushColour);  // if there is flush and no fullhouse/quads there is a chance it might be straight flush
            }
        } 
        player.setPlayerHand(hand);
        
   
        
         return flushColour;
    }

    public static  boolean doesFlushContainStraight(Player player, boolean[]hand, String flushColour)
    { 
      //   boolean flushContainsStraight = false;
             ArrayList<Card> playerCards = player.getPlayerCards();
               ArrayList<Card> flushCards = new ArrayList<Card>();
                ArrayList<Card> straightFlush = new ArrayList<Card>(); //should be checked 1st
          
       for(int i = 0; i < playerCards.size(); i++) 
       {
           if(playerCards.get(i).getSuit().equals(flushColour)) 
           {
          flushCards.add(playerCards.get(i));
           }
       }
         // check if flush is straight
         try{
          for(int i = 0; i < flushCards.size(); i++) 
          {
               if ((flushCards.get(i).getModuledIndex() - 4) == (flushCards.get(i+4).getModuledIndex())) //detect straight
                        {
                            for(int j = i; j < 5; j++)
                           {//get straight
                               straightFlush.add(flushCards.get(j));
                           }
                            
                            if(straightFlush.get(0).getRank().equals("Ace"))  //royal poker or just poker?
                            { 
                                  player.setRealCards(straightFlush);
                                    hand[9] = true; // royal flush
                                   return true;
                            } else {
                              player.setRealCards(straightFlush);
                                   hand[8] = true; // straight flush
                                   return true;
                            }
                        }
          }
         }catch(Exception e){
             System.out.println("excepion in doesFlushContainStraight() : 'check if flush is normal straight'");
         }
       
            // if normal straight flush was not detected above, check if flush contains a wheel
          ArrayList<Card> straightFlushWheel = new ArrayList<Card>();
       
         for(int i = 0; i < flushCards.size(); i++) 
           { // check if flush is wheel first? wheelie check should be at the end btw jacob
                if(flushCards.get(i).getModuledIndex() == 12 || flushCards.get(i).getModuledIndex() == 0 || flushCards.get(i).getModuledIndex() == 1
                        || flushCards.get(i).getModuledIndex() == 2 || flushCards.get(i).getModuledIndex() == 3) 
                        {
                    straightFlushWheel.add(flushCards.get(i));
                }
           }
         if(straightFlushWheel.size() == 5)
         {
           player.setRealCards(straightFlushWheel);
                                   hand[8] = true;
                                   return true;
                           //        flushContainsStraight = true;
         }
        return false;
    }
    
    public static void checkStraight(Player player, boolean[] hand, int[] cardCounter) 
    {//straight flush already checked after flush has been detected. Only checking straight now.
           ArrayList<Card> playerCards = player.getPlayerCards();
            ArrayList<Integer> modulos = new ArrayList<Integer>();
         
       for(int i = 0; i < playerCards.size(); i++) 
       {
        modulos.add(playerCards.get(i).getModuledIndex());
       }
        removeDuplicates(modulos, cardCounter);
        
 
         for (int i = 0; modulos.size() > i; i++) 
         {
                try {
              //      System.out.println("first : " + (modulos.get(i) - 4) + " second : " + modulos.get(i+4));
             //   if (cardsSORTEDBYINDEX.get(i).getModuledIndex() - 4 == cardsSORTEDBYINDEX.get(i - 4).getModuledIndex()) 
                    if (modulos.get(i) - 4 == modulos.get(i+4) || modulos.get(0) == 12) 
                {
                    System.out.println("straight detected v2v2v2v2v2v2v2v2v2");
                  getStraight1(player, hand, modulos.get(i));            
                 return ;
            ////        checkStraight(cardsSORTEDBYINDEX, hand); // starting index, end index
                }
            } catch (Exception e) {
                System.out.println("exception");
            }
         }
 
    }
    
    public static void getStraight1(Player player, boolean[] hand, int firstModulo) 
    {//firstModulo = first modulo of straight
        ArrayList<Card> playerCards = player.getPlayerCards();
           ArrayList<Card> straightCards = new ArrayList<Card>();
           int index = 0;
          //finish \/
           
           for(int i = 0; i < playerCards.size();i++)
           {
               if(playerCards.get(i).getModuledIndex() == firstModulo)
               {
                   straightCards.add(playerCards.get(i));
                   index = i;
               }
           }
           
              try{
        for(int i = index+1; i < playerCards.size();i++)
        {
              System.out.println(player.getPlayerCards().get(i).getModuledIndex() -1 + " elo " + player.getPlayerCards().get(i+1).getModuledIndex());             

             if(straightCards.get(straightCards.size()-1).getModuledIndex()-1 == straightCards.get(i).getModuledIndex()) 
            {
                  straightCards.add(playerCards.get(i));

            }
        }
    }catch(Exception e) 
    {
        System.out.println("error v2");
    }
           
           

    
        System.out.println("size of cards that match straight algorithm : " + straightCards.size()); // 5 needed for straight
        if(straightCards.size() == 5) 
        {
            player.setRealCards(straightCards);
             hand[4] = true;
              return;
        }
        //finish ^^^
        
        //wheel 
         ArrayList<Card> wheel = new ArrayList<Card>();
          ArrayList<Integer> usedWheelIndex = new ArrayList<Integer>();
         for(int i = 0; i < playerCards.size(); i++) 
           { 
                if(playerCards.get(i).getModuledIndex() == 12 || playerCards.get(i).getModuledIndex() == 0 || playerCards.get(i).getModuledIndex() == 1
                        || playerCards.get(i).getModuledIndex() == 2 || playerCards.get(i).getModuledIndex() == 3) 
                        {
                       // making sure NO DUPLICATES GO IN HERE
                            if(!usedWheelIndex.contains(playerCards.get(i).getModuledIndex())) 
                            {
                    usedWheelIndex.add(playerCards.get(i).getModuledIndex());
                    wheel.add(playerCards.get(i));
                            }
                }
           }
         if(wheel.size() == 5)
         {
           player.setRealCards(wheel);
                                   hand[4] = true;
                                   return;
         }
    }
    

  public static void removeDuplicates(ArrayList<Integer> modulos, int[] cardCounter)
    { //remove modulo duplicates so that straight can be detected (11,7,5,5,4,3,2, 2) - > (11,7,5,4,3,2)
         System.out.print("checking modulos before removing duplicates...");
          for(int i = 0; i < modulos.size(); i++)
       {
           System.out.print(modulos.get(i) + ", ");
       }
       HashMap<Integer, Integer> hm = new HashMap<>();  // map containing repeating modulos of card, key = card appearing more than once, 
       //value = how many of this card do you need to deleted for it to be 1.                
       for(int i =0; i < cardCounter.length; i++) 
       {
           if(cardCounter[i] > 1)
           {// What rank of card appears more than once? = key. How many of that rank is there? = value
               hm.put(i, cardCounter[i]);
           }
       } 
         System.out.println("\nCreated hashmap is" + hm); 
       for(int i = 0; i < modulos.size(); i++) 
       { // 0,1,2,3,4,4,5, 12
           if(hm.containsKey(modulos.get(i))) 
           {
               System.out.println(modulos.get(i) + " is going to be deleted");
               int c = 1;
               while(c != hm.get(modulos.get(i))) 
               {
                    modulos.remove(i);
                   c++;
               }
               hm.remove(modulos.get(i));    
           }
       }
              System.out.print("checking modulos after the duplicates have been removed"); 
       for(int i = 0; i < modulos.size(); i++)
       {
           System.out.print(modulos.get(i) + ", ");
       }
       System.out.println();
    } // end removeDuplicates() method
} // end class