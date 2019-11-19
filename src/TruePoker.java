
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.*;
import java.util.Random;


public class TruePoker  
{
     public static String[] possibleHands = {"High Card","Pair", "Two Pair", "Trips", "Straight", "Flush", "Full House"
     ,"Quads", "Straight Flush", "Royal Poker"};
    
    private Card []deck; //main deck, always full
    private Card []secondDeck; //used cards indexex turn null as the game goes on
    
//    public     ArrayList<Card> playerCards;
    public     ArrayList<Player> players;
     private ArrayList<Card> preFlop = new ArrayList<Card>();
    private ArrayList<Card> flop = new ArrayList<Card>();
     private ArrayList<Card> turn = new ArrayList<Card>();
    private ArrayList<Card> river = new ArrayList<Card>();
   
     private ArrayList<Card> preFlop2 = new ArrayList<Card>();
      private ArrayList<Card> preFlop3 = new ArrayList<Card>();
    //Random random;
    
    public TruePoker() 
    { // pair, two pair + straight points tested
        fillDeck();
        secondDeck = deck;
    //    checkDeck();
        Player player = new Player();
        Player player2 = new Player(); 
        Player player3 = new Player(); 
         
      players = new ArrayList<Player>();
      player.setName("player one");
      player2.setName("player two");
      player3.setName("player three");
      players.add(player);
      players.add(player2);
      players.add(player3);
      
//       int[] preFlopIndexes = {38,50};//,9,10,12}; // 
//            preFlop = getCardByIndexArray(preFlopIndexes);
//           
//              int[] preFlopIndexes2 = {42,28};//,9,10,12}; //  usun 43
//            preFlop2 = getCardByIndexArray(preFlopIndexes2);
//            
//                int[] preFlopIndexes3 = {25,19};//,9,10,12}; // 
//            preFlop3 = getCardByIndexArray(preFlopIndexes3);
//           
//             players.get(0).addPlayerCards(preFlop);
//              players.get(1).addPlayerCards(preFlop2);
//               players.get(2).addPlayerCards(preFlop3);
            
            
            for(int i = 0; i < players.size(); i++) 
            {
                players.get(i).addPlayerCards(getRandomCardsArray(2));
                  Collections.sort(  players.get(i).getPlayerCards(), new SortbyMODULEDINDEX()); 
                    checkPlayerPosition(1,  players.get(i), "preFlop");  
            }
               System.out.println("qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq");
            //betting etc. players that are not going further are out
            /*
             int[] flopIndexes = {37,12,45}; 
               flop = getCardByIndexArray(flopIndexes);
               
                  int[] riverIndexes = {48};
               river = getCardByIndexArray(riverIndexes);
               
                           int[] turnIndexes = {35};
               turn = getCardByIndexArray(turnIndexes);
                    */
            flop = getRandomCardsArray(3); 
               river = getRandomCardsArray(1);
                     turn = getRandomCardsArray(1);
            
            for(int i = 0; i < flop.size();i++) 
            {
                System.out.println(flop.get(i).getCardIndex() + " : " +  i + " is the index of flop");
            }
               System.out.println("\n" + river.get(0).getCardIndex() + " : " +  0 + " is the index of river\n");
             System.out.println("\n" + turn.get(0).getCardIndex() + " : " +  0 + " is the index of turn\n");
               
                for(int i = 0; i < players.size(); i++) 
            {
                players.get(i).addPlayerCards(flop);
                  Collections.sort(  players.get(i).getPlayerCards(), new SortbyMODULEDINDEX()); 
                    checkPlayerPosition(2,  players.get(i), "Flop");  
            }
             //betting etc. players that are not going further are out
        //           int[] riverIndexes = {22};//,9,10,12}; // 
         //      river = getCardByIndexArray(riverIndexes);
         //       river = getRandomCardsArray(1);
                
//                   for(int i = 0; i < river.size();i++) 
//            {
//                System.out.println("\n" + river.get(i).getCardIndex() + " : " +  i + " is the index of river\n");
//            }
                
                 for(int i = 0; i < players.size(); i++) 
            {
                players.get(i).addPlayerCards(river);
                  Collections.sort(  players.get(i).getPlayerCards(), new SortbyMODULEDINDEX()); 
                    checkPlayerPosition(2,  players.get(i), "RIVER");  
            }    
                //betting etc. players that are not going further are out. if only 1 player left - he gets the chips and game is over.
           //           int[] turnIndexes = {45};//,9,10,12}; // 
            //   turn = getCardByIndexArray(turnIndexes);
           //      turn = getRandomCardsArray(1);
              
//                            for(int i = 0; i < turn.size();i++) 
//            {
//                System.out.println("\n" + turn.get(i).getCardIndex() + " : " +  i + " is the index of turn\n");
//            }
                 
                    for(int i = 0; i < players.size(); i++) 
            {
                players.get(i).addPlayerCards(turn);
                  Collections.sort(  players.get(i).getPlayerCards(), new SortbyMODULEDINDEX()); 
                    checkPlayerPosition(2,  players.get(i), "TURN");  
            }    
                   //betting etc. players that are not going further are out. if only 1 player left - he gets the chips and game is over.
                   //if showdown... showdown showdown showdown showdown showdown showdown showdown showdown showdown 
             
              //      System.out.println("asjdiajsdsadjasd");
             //        players.get(1).checkPlayerCards();
          
//                players.get(1).getPlayerCards().remove(1);
//     players.get(2).getPlayerCards().remove(1);
//
//                    int[] special = {25};//,9,10,12}; // 
//             ArrayList<Card> turn2 = getCardByIndexArray(special);
//                 players.get(1).addPlayerCards(turn2);
//                      players.get(2).addPlayerCards(turn2);
//        //           players.get(1).checkPlayerCards();
//                    
//                      Collections.sort(   players.get(1).getPlayerCards(), new SortbyMODULEDINDEX()); 
//                       Collections.sort(   players.get(2).getPlayerCards(), new SortbyMODULEDINDEX()); 
//                   checkPlayerPosition(2,  players.get(1));  
//                        checkPlayerPosition(2,  players.get(2));

                    checkAllPlayerRealCards(players);
                    System.out.println("********************************\n");
                       findWinner(players);  
                       
                       
    }
    
   
     private void findWinner(ArrayList<Player> players) 
    {
        //find the best hand
        int bestHandIndex =  getBestHandIndex(players);
  
        System.out.println("the strongest hand index :" + bestHandIndex);
          System.out.println(possibleHands[bestHandIndex]);
        //j is index of best hand
         for(int i = 0; i < players.size(); i++)  
         {
             if(players.get(i).getPlayerHand()[bestHandIndex] == false) 
             {
                 players.remove(i);
             }
         }
         //if two or more people have the strongest hand...
         //decide which one is the best, in case 1 do it by points, case 2 by card removal
           ArrayList<Integer> caseTwoHands = new ArrayList<Integer>( 
            Arrays.asList(0, 1, 2, 3, 5)); // add 2 here 
         
         if(players.size() > 1) 
         {
                 for(int i = 0; i < players.size(); i++) 
                 {//more points = better hand
               IdentifyHand.getHandPower(players.get(i),  players.get(i).getRanksToGet()); // get each player's hand power
                 }
             if(!caseTwoHands.contains(bestHandIndex))
             {//case one: points system
                   System.out.println("solution one");
                   IdentifyHand.getHighestPointsPlayers(players);
              
             } else 
             {//case two: points system + cutting cards
                 System.out.println("solution two");
                 IdentifyHand.findWinner(players, bestHandIndex);
             }
             
             
         } else {
             System.out.println(players.get(0).getName() + " is the winnter");
       //      return;
         }
         
         System.out.println("\nwinners:");
         for(int i = 0; i < players.size();i++)
         {
           System.out.println(players.get(i).getName());  
         }           
    }


    public ArrayList<Card> getCardByIndexArray(int []indexes) 
    {
            ArrayList<Card> cardsArrayList = new ArrayList<Card>();
          if(indexes.length == 0) {
            return cardsArrayList;
          }
   
      for(int i = 0; i < indexes.length; i++) 
      {
      cardsArrayList.add(deck[indexes[i]]);
      secondDeck[indexes[i]] = null;
      }
    
      
        return cardsArrayList;
    }
    
    public void checkPlayerPosition(int gameStatus, Player player, String position)  //position = where are you? preflop, flop? etc
    {
         ArrayList<String> ranksToGet = new ArrayList<String>();
        if(gameStatus == 1) 
        { // check playerhand before flop
           int[]cardCounter =  IdentifyHand.checkPairs(player.getPlayerCards());
            IdentifyHand.checkRanks(cardCounter, player, 1);
        }  
          if(gameStatus == 2) 
        { // check playerhand before flop
            System.out.println("after " + position + " checking hand...");
           int[]cardCounter =  IdentifyHand.checkPairs(player.getPlayerCards());
        ranksToGet =   IdentifyHand.checkRanks(cardCounter, player, 2);
            
              int[]colourCounter =  IdentifyHand.checkSuits(player.getPlayerCards());
              IdentifyHand.checkFlush(colourCounter, player);
          //   String flushColour =    IdentifyHand.checkFlush(colourCounter, player);
             if(player.getPlayerHand()[9] ||  player.getPlayerHand()[8] ||  player.getPlayerHand()[7] || player.getPlayerHand()[6] || player.getPlayerHand()[5]) {
              System.out.println("y");
             } else {
                   IdentifyHand.checkStraight(player, player.getPlayerHand(), cardCounter);
             }
        }  
      
//         System.out.println("real hand\n");
//          Collections.sort(player.getRealCards(), new SortbyMODULEDINDEX()); 
//               for(int i =0; i < player.getRealCards().size(); i++) 
//      {
//         System.out.println("["+ i + "] : REAL Index: " + player.getRealCards().get(i).getCardIndex()+ "   ");
//          System.out.print(player.getRealCards().get(i).getRank()+ "   ");
//           System.out.print(player.getRealCards().get(i).getSuit() + "\n");
//           System.out.println();
//      }
               
               
//                 System.out.println("hands hands\n");
//                       for(int i =0; i < player.getPlayerHand().length; i++) 
//                {
//           System.out.println(i + " @ " + player.getPlayerHand()[i]);
//      } 
    }
    

    
   public Card getCardByIndex(int i) 
   {
       if(secondDeck[i] == null) 
       {
           System.out.println("card already in use");
           return null;
       }
       System.out.println(deck[i].getRank() + " " + deck[i].getSuit());
        secondDeck[i] = null;
       return deck[i];
   } 
   
    public ArrayList<Card> getRandomCardsArray(int n) 
    {
       //  Card []cards = new Card[n];
            ArrayList<Card> cardsArrayList = new ArrayList<Card>();
          if(n == 0) {
            return cardsArrayList;
          }
              while(n != 0 ) {
             Random random = new Random();
             int i = random.nextInt(52); // (52) = 0 to 51    
      while(secondDeck[i] == null ){
             i = random.nextInt(52);
          
      }
      cardsArrayList.add(deck[i]);
      secondDeck[i] = null;
      n--;
      }
        return cardsArrayList;
    
    }
    
public void getRandomCard1(int n,Player player) 
    { // how many N cards to add to Player
        if(n == 0) {
            return;
        } else {
             Random random = new Random();
             int i = random.nextInt(52); // (52) = 0 to 51    
      while(secondDeck[i] == null ){
             i = random.nextInt(52);
      }
              player.addPlayerCard(deck[i]);
              secondDeck[i] = null;
             getRandomCard1(n-1, player);
        }
    }
  
    public void fillDeck() 
    {
        deck = new Card[52]; 
        	int j = 0; // suits
		int jj = 0;//ranks;
		for(int i = 0; i < 52; i++, jj++) 
		{ 	
			if(i % 13 == 0 && i != 0 ) 
			{ 
				j++;
				jj = 0;
			}
			int moduledIndex = i % 13;// moduled index used for connectivity
			deck[i] = new Card(Card.suits[j], Card.ranks[jj], i, moduledIndex);
                        
		}
    }
    
    public static void main(String [] args) 
    {
        TruePoker TP = new TruePoker();
    }
    
    
    
    
    public void checkAnyDeck(Card[] deck) 
    {
         for(int i =0; i < deck.length; i++) 
        {
            if(deck[i] == null ) {
                System.out.println("null detected  null detected   null detected :    " + i);
            } else {
      System.out.println(deck[i].getSuit() + "  " + deck[i].getRank() + "  " + deck[i].getCardIndex() );
       //     System.out.println(deck[i].getSuit() + " + ");
            }
        }
    }
    
    
public void checkDeck() 
{
    //check whole deck
      for(int i =0; i < deck.length; i++) 
        {
   //   System.out.println(deck[i].getSuit() + "  " + deck[i].getRank() + "  " + deck[i].getCardIndex() );
       //     System.out.println(deck[i].getSuit() + " + ");
        }
      //check specific thing
      String  specificThing ="hearts";
        for(int i =0; i < deck.length; i++) 
        {
            if(deck[i].getSuit().equals(specificThing)) {
                System.out.println("checking for specific thing : " + specificThing);
      System.out.println(deck[i].getRank() + "      :   " + deck[i].getSuit() + "  " + deck[i].getCardIndex() );
            }
       //     System.out.println(deck[i].getSuit() + " + ");
        }
}    
 public static int getBestHandIndex(ArrayList<Player> players)
    {//
          int bestHandIndex = 0; //getBestHandIndex(players);
        for(int i = 0; i < players.size(); i++)  
        {
            for(int j = possibleHands.length-1; j > 0 ;j--) //royal flush not tested
            {
                if(players.get(i).getPlayerHand()[j] == true)
                {
                    if(j > bestHandIndex) {
                        bestHandIndex = j;
                    }
                }
            }
        }
        return bestHandIndex;
    }
  
  public static void checkAllPlayerRealCards(ArrayList<Player> players) 
  {
      //check player hand (5 real cards)
       for(int i = 0; i < players.size(); i++)  
        {
               System.out.println("\n"+players.get(i).getName());
             for(int j = 0; j < players.get(i).getRealCards().size() ;j++)
             {
           System.out.println("["+ i + "] :real cards index: " + players.get(i).getRealCards().get(j).getCardIndex()+ "   ");
          System.out.print(players.get(i).getRealCards().get(j).getRank()+ "   ");
           System.out.print(players.get(i).getRealCards().get(j).getSuit() + "\n");
           System.out.println();
             }
              System.out.println("*********************");
        }
          //check all player cards (7), only works before finding winner as realHand gets cut down
          for(int i = 0; i < players.size(); i++)  
        {
               System.out.println("\n"+players.get(i).getName());
             for(int j = 0; j < players.get(i).getPlayerCards().size() ;j++)
             {
           System.out.println("["+ i + "] :real cards index: " + players.get(i).getPlayerCards().get(j).getCardIndex()+ "   ");
          System.out.print(players.get(i).getPlayerCards().get(j).getRank()+ "   ");
           System.out.print(players.get(i).getPlayerCards().get(j).getSuit() + "\n");
           System.out.println();
             }
              System.out.println("*********************");
        }
  }
    
}//end TruePoker class


class SortbyMODULEDINDEX implements Comparator<Card> 
{ //used for collection.sort
 @Override//sort player cards according to their rank by using moduled index
    public int compare(Card o1, Card o2) {
        if(o1.getModuledIndex() > o2.getModuledIndex())
                {
            return -1;
        } else {
            return 0;
        }
    }
} 