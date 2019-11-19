
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

    public static ArrayList<String>  checkRanks(int[] cardCounter, Player player, int gameStatus) {//jaki jest system mocy?
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
              System.out.println("two pairs + trips") ;
//        System.out.println("pairs = 2 + trips = 1") ; 
//        System.out.println(ranksToGet.get(0));
//        System.out.println(ranksToGet.get(1));
//        System.out.println(ranksToGet.get(2));
        ranksToGet.remove(1); // remove the lowest pair
        }
   
        boolean check = false; // separate array with all cards that appear more than once
        ArrayList<Card> cards = new ArrayList<Card>();
        for (int i = 0; i < ranksToGet.size(); i++) {
            for (int j = 0; j < player.getPlayerCards().size(); j++) {
                if (ranksToGet.get(i).equals(player.getPlayerCards().get(j).getRank())) {
                //    System.out.println("   adding card with index : " + player.getPlayerCards().get(j).getCardIndex());
                    cards.add(player.getPlayerCards().get(j));
                    check = true;
                }
            }
        }
      //  if (check) {
            player.setRealCards(cards);
     //   }

    
        int count = 0; // how many high cards did you get?
        //fill with highcards
//          System.out.println("TH");
//        System.out.println(hand[0] + " player name : " + player.getName() + " " + player.getRealCards().size());
//        if(hand[0] && player.getRealCards().size() == 5) 
//        {
//           for(int i = 0; i < 5; i++) 
//           {
//               player.getRealCards().remove(i);
//           }
//        }
        if (player.getRealCards().size() < 5) {
            for (int i = 0; i < player.getPlayerCards().size(); i++) {
                if (!cards.contains(player.getPlayerCards().get(i)) && cards.size() < 5) {
                    cards.add(player.getPlayerCards().get(i));
                    count++;
                }
            }
    }
        System.out.println(count + " : is the amount of high cards you got : " + player.getName());


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
   //       System.out.println(pair + " : pairs n.");
  //     System.out.println(trips + " : trips n.");
   //      System.out.println(quads + " : quads n.");
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
for(int i = 0; i < ranksToGet.size(); i++) 
{
  //  System.out.println("ranksToGet adding : " + i + " :" + ranksToGet.get(i));
}
        player.setPlayerHand(hand);
        player.setRanksToGet(ranksToGet);
        return ranksToGet;
    }


    public static int getHandPower(Player player,  ArrayList<String> ranksToGet) {
        if (player.getPlayerHand()[9]) {
            System.out.println("royal poker");
            player.setHandPower(10000000);
              return 0;
        }
        if (player.getPlayerHand()[8]) {
            System.out.println("straight flush");
         //   getRealPoints(player, 8, 1000000);
            player.setHandPower( getRealPoints(player, 8, 1000000, ranksToGet));
         return 0;
        }
          if (player.getPlayerHand()[7]) {
            System.out.println("quads");
            player.setHandPower( getRealPoints(player, 7, 500000, ranksToGet));
         return 0;
        }
        
  if (player.getPlayerHand()[6]) {
            System.out.println("full house is here");
            player.setHandPower( getRealPoints(player, 6, 300000, ranksToGet));
            return 0;
        }
   
        if (player.getPlayerHand()[5]) 
        {
                 System.out.println("flush is here");
 player.setHandPower( getRealPoints(player, 5, 250000, ranksToGet));
          return 0;
        }
        
        if (player.getPlayerHand()[4]) {
             System.out.println("straight is here");
         player.setHandPower( getRealPoints(player, 4, 200000, ranksToGet));
           return 0;
        }
  
        if (player.getPlayerHand()[3]) {
                     System.out.println("trips are here");
          player.setHandPower( getRealPoints(player, 3, 150000, ranksToGet));
            return 0;
        }

        if (player.getPlayerHand()[2]) {
            System.out.println("two pair");
                    player.setHandPower( getRealPoints(player, 2, 100000, ranksToGet));
            return 0;
        }

        if (player.getPlayerHand()[1]) {
            System.out.println("pair");
               player.setHandPower( getRealPoints(player, 1, 50000, ranksToGet));
            return 0;
        }

        if (player.getPlayerHand()[0]) {
            System.out.println("high card");
              player.setHandPower( getRealPoints(player, 0, 100, ranksToGet));
            return 0;
        }
        return 0;
    }

    
    public static ArrayList<Player>  getRealPoints2(ArrayList<Player> players) 
    {//highcard     // get winner
        
        try {
        while(players.size() != 1)    
        {
            
 int highestModuloHighCard = 0;
        for(int i =0; i < players.size(); i++) 
        {
            if(players.get(i).getRealCards().get(0).getModuledIndex() > highestModuloHighCard) 
            {
                highestModuloHighCard = players.get(i).getRealCards().get(0).getModuledIndex();
            }
        }
        
       for(int i =0; i < players.size(); i++)   
       {
           if(players.get(i).getRealCards().get(0).getModuledIndex() != highestModuloHighCard) 
           {
               players.remove(i);
           }
       }
       
       for(int i = 0; i < players.size(); i++) 
       {
           players.get(i).getRealCards().remove(0);
       }
     
        }
        }catch(Exception e) {
            System.out.println("high card draw");
            return players;
        }
        System.out.println(players.size() );
           System.out.println(  players.get(0).getName() + " has the best high card");
        return players;
    }
    
    public static int getRealPoints(Player player, int hand, int points, ArrayList<String> ranksToGet)
    {    
        ArrayList<Card> realCards = player.getRealCards();
        //y  = works, n = not working
        if(hand==8) //y
        {// System.out.println("straight flush");
            if(realCards.get(0).getModuledIndex() == 12 &&realCards.get(4).getModuledIndex() == 0)
            {//wheel
                return points;
            } else {
                 points = points + (realCards.get(0).getModuledIndex() + 1);
            }
            return points;
        }//here
      //      System.out.println(points + " points before quads");
         if(hand==7) //y
        {// System.out.println("quads");
             for(int i = 0; i < realCards.size();i++) 
             {
                    if(realCards.get(2).getRank().equals(realCards.get(i).getRank())) 
                 { // can just find quad Rank by getting middle index from realcards
                 //    System.out.println(points + " adding quad card points");
                     points = points + ((realCards.get(i).getModuledIndex()+1) * 20);
                 } else {
                     points = points + realCards.get(i).getModuledIndex()+1;
                 }
             }
              return points;
         }
         
          if(hand==6) 
        {// System.out.println("full house");
           // realCards index 2 is always trips card
            String tripsRank = realCards.get(2).getRank();
             for(int i = 0; i < realCards.size();i++) 
             {
                 if(realCards.get(i).getRank().equals(tripsRank))
                  {
                       System.out.println(points + " adding full card points");
                      points = points + ((realCards.get(i).getModuledIndex()+1) * 20);
                  } else {
                                System.out.println(points + " adding full card points");
                      points = points + realCards.get(i).getModuledIndex()+1;
                 }
             }
               return points;
        }
          
          
          if(hand==5) 
          {//flush
              points = points + (realCards.get(0).getModuledIndex()+1); // highest flush card is your power
              return points;
          }
          
            if(hand==4) 
        {// System.out.println("strit");
                  if(realCards.get(0).getModuledIndex() == 12 &&realCards.get(4).getModuledIndex() == 0)
            {//wheel
                return points;
            } else {
                 points = points + (realCards.get(0).getModuledIndex() + 1);
            
             }
               return points;
        }   
            
               if(hand==3) 
        {// System.out.println("trips");
             String tripsRank = realCards.get(2).getRank();
              for(int i = 0; i < realCards.size();i++) 
             {
                   if(realCards.get(i).getRank().equals(tripsRank))
                  {
                       System.out.println(points + " adding trips card points");
                      points = points + ((realCards.get(i).getModuledIndex()+1) * 20);
                  } 
             }
               return points;
             }
     
           if(hand==2) // make it so only the highest pair gives good points
                   {// System.out.println("two pair");
                         for(int i = 0; i < realCards.size();i++)  // either this or if cardsToGet's rank = card's rank but then it's bad because 3x pairs still = 2 pairs
                         {
                             if(ranksToGet.get(1).equals(realCards.get(i).getRank())) 
                             {
                                   points = points + ((realCards.get(i).getModuledIndex()+1) * 20);
                          
                            }
     //                               else {
//                                   points = points + realCards.get(i).getModuledIndex()+1;
//                                         System.out.println((i+1) + ": kurwa : " + points);
//                             }
                         } 
                          return points;
                   }
           
           if(hand==1) 
           {//PAIR
                 for(int i = 0; i < realCards.size();i++)
                 {
               if(ranksToGet.get(0).equals(realCards.get(i).getRank()))
               {
                     points = points + ((realCards.get(i).getModuledIndex()+1) * 20);
                 }
                 }
               return points;
           }

             if(hand==0) 
             {
                 points = points + (realCards.get(0).getModuledIndex() + 1);
                  return points;
             }
        return -999;
    }
    
    public static int getPoints(Player player, int points) {

        int multiplier = 200;
          for (int i = 0; i < player.getRealCards().size(); i++) 
          {
       //       System.out.println("getPoints() getting points : " + player.getRealCards().get(i).getModuledIndex()+ 1);
           //    points = points + (player.getRealCards().get(i).getModuledIndex()+ 1);
              int cardPower = (player.getRealCards().get(i).getModuledIndex()+1) * multiplier;
                 points = points + cardPower;
              System.out.println(i + " : " + player.getRealCards().get(i).getRank() + " is worth : " + cardPower + " points.");
               multiplier =  multiplier - 50;
//                     if (multiplier - 20 == -20) 
//                     {
//                         multiplier = 0;
//                     } else {
//                        multiplier =  multiplier - 20;
//                     }
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

   // public static String checkFlush(int[] colourCounter, Player player) {
     public static void checkFlush(int[] colourCounter, Player player) {
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
              return;
            }
        } 
        player.setPlayerHand(hand);
        
   
        
     //    return flushColour;
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
//       System.out.println(flushColour + " : flushColour");
//       for(int i =0; i < flushCards.size();i++)
//       {
//              System.out.println("["+ i + "] : flush cards " + flushCards.get(i).getCardIndex()+ "   ");
//          System.out.print(flushCards.get(i).getRank()+ "   ");
//           System.out.print(flushCards.get(i).getSuit() + "\n");
//           System.out.println();
//       }
     
         // check if flush is straight by checking flush cards
         try{
          for(int i = 0; i < flushCards.size(); i++) 
          {
               if ((flushCards.get(i).getModuledIndex() - 4) == (flushCards.get(i+4).getModuledIndex())) //detect straight
                        {
             //               for(int j = i; j < 5; j++) //WRONG WRONG WRONG
                  for(int j = i; j < (i+5); j++)
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

//              for(int i = 0; i < flushCards.size();i++)
//             {
//                  System.out.println((1+i) + " : : : : : " + flushCards.get(i).getSuit());
//                   System.out.println((1+i) + " : : : : : " + flushCards.get(i).getRank());
//                    System.out.println((1+i) + " : : : : : " + flushCards.get(i).getCardIndex());
//             }
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
    //   System.out.println("checking modulos of : " + player.getName());
        removeDuplicates(modulos, cardCounter);
        
 
         for (int i = 0; modulos.size() > i; i++) 
         {
                try {
              //      System.out.println("first : " + (modulos.get(i) - 4) + " second : " + modulos.get(i+4));
             //   if (cardsSORTEDBYINDEX.get(i).getModuledIndex() - 4 == cardsSORTEDBYINDEX.get(i - 4).getModuledIndex()) 
                    if (modulos.get(i) - 4 == modulos.get(i+4)) 
                {
                    System.out.println("straight detected v2v2v2v2v2v2v2v2v2");
                  getStraight1(player, hand, modulos.get(i), false);            
                 return ;
                }
            } catch (Exception e) {
                System.out.println("exception");
            }
         }
           if(modulos.contains(12)  && modulos.contains(0) && modulos.contains(1) && modulos.contains(2) && modulos.contains(3)) 
           {
                    System.out.println("wheel detected v2v2v2v2v2v2v2v2v2");
                getStraight1(player, hand, -1, true);  
           }   
   
 
    }
    
    public static void getStraight1(Player player, boolean[] hand, int firstModulo, boolean wheelStraight) 
    {//firstModulo = first modulo of straight
         ArrayList<Card> playerCards = player.getPlayerCards();
        if(!wheelStraight) {
           ArrayList<Card> straightCards = new ArrayList<Card>();

           System.out.println("first modulo " + firstModulo);
           ArrayList<Integer> straightCardsModulos = new ArrayList<Integer>();
             for(int i = 0; i < playerCards.size();i++)
             {
                 if(playerCards.get(i).getModuledIndex() == firstModulo)
                 {
                      for(int j = i;  j < playerCards.size();j++){
                     if(!straightCardsModulos.contains(playerCards.get(j).getModuledIndex()) && straightCardsModulos.size() < 5)
                     {
                         straightCardsModulos.add(playerCards.get(j).getModuledIndex());
                     }
                      }
                 }
             }
             //check straightCardsModulos
              for(int i = 0; i < straightCardsModulos.size();i++)
             {
                  System.out.println((1+i) + " : : : : : " + straightCardsModulos.get(i));
             }
             
              for(int i = 0; i < playerCards.size();i++)
             { // get the straight based on modulos
                 if(straightCardsModulos.contains(playerCards.get(i).getModuledIndex())) 
                 {
                     straightCards.add(playerCards.get(i));
                     for(int j = 0; j < straightCardsModulos.size();j++)
                     {
                         if(playerCards.get(i).getModuledIndex() == straightCardsModulos.get(j)) 
                         {
                             straightCardsModulos.remove(j);
                         }
                     }
                 }
             }
              //check straightCards before it becomes your hand
//                 for(int i = 0; i < straightCards.size();i++)
//             {
//                  System.out.println((1+i) + " ' ' ' ' '  " + straightCards.get(i).getModuledIndex());
//             }
              System.out.println(straightCards.size() + " JSDFJASDASDJAJSD");
              if(straightCards.size() == 5)
              {
               player.setRealCards(straightCards);
             hand[4] = true;
              return;
              }   
        }
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
    
    public static void getHighestPointsPlayers(ArrayList<Player> players)
    { // remove all players that don't match up to best hand power
          int highestPoints = 0;
       for(int i = 0; i < players.size(); i++)
       {
           if (highestPoints < players.get(i).getHandPower()) 
           {
               highestPoints = players.get(i).getHandPower();
           }
       }
        
        for(int i = 0; i < players.size(); i++)
       {
           if(players.get(i).getHandPower() != highestPoints) 
           {
               players.remove(i);
           }
       }
    }
    
    public static ArrayList<Player> findWinner(ArrayList<Player> players, int bestHandIndex) 
    {
    //    int [] pointsOfPlayers = new int[players.size()];

        getHighestPointsPlayers(players); // find highest points player + eliminate weaker points players
     
       if(players.size() == 1) 
       {
        return players; // winner
       } else {
           if(bestHandIndex == 2) 
           {//sort two pair hand
               getBestTwoPair(players, bestHandIndex);
           } else {
                   trincateCards(players, bestHandIndex);
           }
        
       }  
       return players;
    }
    
    public static void deletePairedRealCards(ArrayList<Player> players)
    {//used to determine who is winner of two pair hand if more than 2 players have it, used in getBestTwoPair()
          for(int i = 0; i < players.size();i++) 
            {//this loop deletes all paired cards of  players.getPlayerCards()
                for(int j = 0; j < players.get(i).getRealCards().size(); j++) 
                {
                if(players.get(i).getRanksToGet().contains(players.get(i).getRealCards().get(j).getRank())) 
                   {
                    players.get(i).getRealCards().remove(j);
                  
                   }
                }
            }//at the end of this method realCards only have 1 real card left. this card then gets checked to determine who is the winner of the hand or if the hand is a draw
    }
    
    public static void getBestTwoPair(ArrayList<Player> players, int bestHandIndex) 
    {
     ArrayList<String> allSecondPairs = new ArrayList<String>();
        for(int i = 0; i < players.size(); i++)
        {
allSecondPairs.add(players.get(i).getRanksToGet().get(0)); //first index of player's getRanksToGet() is always the weaker pair
        }
        if(verifyAllEqualUsingFrequency(allSecondPairs))
        { // if second pairs are the same, delete them and check last remaining card.
               //delete paired real cards
         deletePairedRealCards(players);
            System.out.println("checking size of real player cards should all be 1: " );
            for(int i = 0; i < players.size(); i++)
            {
                System.out.println(players.get(i).getRealCards() + " : <size : index> : " + i);
            }
             getRealPoints2(players);
        } else { // second pairs of player are not equal, this means some could be stronger or weaker
            //determine the winners by the second pair, if more than 1 still remaining, -> trincate.
            //find highest second pair
            String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
            int highestSecondPairIndex = 0;
            String highestSecondPair = "2";
            for(int i = 0; i < allSecondPairs.size(); i++) 
                {
                   for(int j = 0; j < ranks.length; j++)
                   {
                     if(allSecondPairs.get(i).equals(ranks[j])) 
                     {
                         if(j > highestSecondPairIndex) 
                         {
                             highestSecondPairIndex = j;
                             highestSecondPair = ranks[j];
                         }
                     }
                   }
                }
            
            //delete players that have no highestSecondPairIndex
            for(int i = 0; i < players.size();i++) 
            {
                if(!players.get(i).getRanksToGet().get(0).equals(highestSecondPair))
                {
                    players.remove(i);
                }
            }
            
            if(players.size() > 1)
            {//only 1 cards left to check, if it's the same then it's a draw
                //delete paired real cards
                //trincate
                 deletePairedRealCards(players); // 5 real cards become 1 (2xtwo pair get deleted)
                     getRealPoints2(players);
            }
            
        }
        
    }
    
    public static boolean verifyAllEqualUsingFrequency(ArrayList<String> list) { //if true = all elements in list are the same
    return list.isEmpty() || Collections.frequency(list, list.get(0)) == list.size();
}
    
    public static ArrayList<Player> trincateCards(ArrayList<Player> players, int bestHandIndex) 
        {//at this points you have 2 or more players that have same cards strength and we need to go further to determine who is the winner
           if(bestHandIndex == 1 || bestHandIndex == 3) 
           {//if pair or trips, remove paired cards first
                    for(int i = 0; i < players.size(); i++)  
          {
              for(int j = 0; j < players.get(i).getPlayerCards().size(); j++) 
              {
                  if(players.get(i).getRanksToGet().contains(players.get(i).getPlayerCards().get(j).getRank())) 
                  {
                      players.get(i).getPlayerCards().remove(j);
                  }
              }
          }
           } else {
               System.out.println("high card or flush detectedm maybe even getting 1 card of two pairs");
           }
             getRealPoints2(players);
          return players;
 
        }
        
 
//used for finding straight
  public static void removeDuplicates(ArrayList<Integer> modulos, int[] cardCounter)
    { //remove modulo duplicates so that straight can be detected (11,7,5,5,4,3,2, 2) - > (11,7,5,4,3,2)
      //   System.out.print("checking modulos before removing duplicates...");
          for(int i = 0; i < modulos.size(); i++)
       {
    //       System.out.print(modulos.get(i) + ", ");
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
   //      System.out.println("\nCreated hashmap is" + hm); 
       for(int i = 0; i < modulos.size(); i++) 
       { // 0,1,2,3,4,4,5, 12
           if(hm.containsKey(modulos.get(i))) 
           {
         //     System.out.println(modulos.get(i) + " is going to be deleted");
               int c = 1;
               while(c != hm.get(modulos.get(i))) 
               {
                    modulos.remove(i);
                   c++;
               }
               hm.remove(modulos.get(i));    
           }
       }
      //       System.out.print("checking modulos after the duplicates have been removed: "); 
       for(int i = 0; i < modulos.size(); i++)
       {
     //      System.out.print(modulos.get(i) + ", ");
       }
       System.out.println();
    } // end removeDuplicates() method
} // end class