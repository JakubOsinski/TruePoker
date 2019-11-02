
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;


public class TruePoker  
{
     public String[] hand1 = {"High Card","Pair", "Two Pair", "Trips", "Straight", "Flush", "Full House"
     ,"Quads", "Straight Flush", "Royal Poker"};
    
    private Card []deck; //main deck, always full
    private Card []secondDeck; //used cards indexex turn null as the game goes on
    
    public     ArrayList<Card> playerCards;
    private ArrayList<Card> flop = new ArrayList<Card>();
    private ArrayList<Card> river = new ArrayList<Card>();
    private ArrayList<Card> turn = new ArrayList<Card>();
    //Random random;
    
    public TruePoker() 
    {
        fillDeck();
        secondDeck = deck;
    //    checkDeck();
        Player player = new Player();

        
   //     player.addPlayerCards(getRandomCardsArray(2));
     

 //  int[] handIndexes = {0,13};
  //    player.addPlayerCards(getCardByIndexArray(handIndexes));
    
     // player.checkPlayerCards();
      
    //     checkPlayerPosition(1, player);  

          int handPower = IdentifyHand.getHandPower(player);
       //    System.out.println(handPower + " : power of the hand");

          
             //    int[] flopIndexes = {0,13, 26,39, 2,50, 16}; // quads = quads  y
             //    int[] flopIndexes = {0,13, 26,39, 2,50, 15}; // quads + pair = quads  y
              //   int[] flopIndexes = {0,13, 26,39, 5,18, 31}; // quads + trips = quads y
             
    //       int[] flopIndexes = {0,13, 50,26, 5,18, 31}; // 2x trips = full house   y
     //      int[] flopIndexes = {0,13, 26,5, 2,50, 15}; // trips + pair = full house  y
     //     int[] flopIndexes = {0,13, 26,51, 38,50, 37}; // trips + 2pair = full house  y
          
  //            int[] flopIndexes = {0,13, 6,19, 2,50, 15}; // 3 pairs = two pair  y
//int[] flopIndexes = {0,13, 6,19, 2,50, 16}; // 2 pairs = two pair  y
 //int[] flopIndexes = {0,6, 13,9, 20,50, 3}; // 1 pair =  pair  y
                 
 //    int[] flopIndexes = {0,6, 7,9, 20,33, 3}; // pair/trips + colour =  colour  y           
      //       int[] flopIndexes = {0,43, 14,28, 29,50}; // straight   
 // int[] flopIndexes = {0,16, 14,15, 12,50}; // wheelie straight   
        //   int[] flopIndexes = {0,1, 2,15, 16,50, 17}; // normal  straight   y
        //    int[] flopIndexes = {42,4, 5,6, 7,21, 24,50,29,30,39, 43, 19}; // normal straight straight   y
      //     int[] flopIndexes = {4,13,14,10,11,8,7}; 
     
                   int[] flopIndexes = {33,11,10,21,30,9};      //  highest straight  
            //         int[] flopIndexes = {1,0,2,16,21,20, 25};      //  straight wheel 
             //        int[] flopIndexes = {7,11,10,22,21,20};      //  normal straight
          
            flop = getCardByIndexArray(flopIndexes);//getRandomCardsArray(3);
         player.addPlayerCards(flop);
  //         player.checkPlayerCards();          
//             ArrayList<Card> cardsSORTEDBYINDEX = new ArrayList<Card>();
//       cardsSORTEDBYINDEX =  playerCards;
            System.out.println("///////////////////////////////////////////////////n");     
         Collections.sort(player.getPlayerCards(), new SortbyMODULEDINDEX()); 
          player.checkPlayerCards();
 
             checkPlayerPosition(2, player);  
             handPower = IdentifyHand.getHandPower(player);
           System.out.println(handPower + " : power of the hand");
     
//           int[] riverIndexes = {41};
//                river = getCardByIndexArray(riverIndexes);
//
//            System.out.println("************************************************************************************");
//             player.addPlayerCards(river);
//  
//           player.checkPlayerCards();
           
     //      System.out.println();
     //       player.checkPlayerCards();
               
    } // end TruePoker constructor

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
    
    public void checkPlayerPosition(int gameStatus, Player player) 
    {
        if(gameStatus == 1) 
        { // check playerhand before flop
           int[]cardCounter =  IdentifyHand.checkPairs(player.getPlayerCards());
            IdentifyHand.checkRanks(cardCounter, player, 1);
        }  
          if(gameStatus == 2) 
        { // check playerhand before flop
            System.out.println("after flop checking hand...");
           int[]cardCounter =  IdentifyHand.checkPairs(player.getPlayerCards());
            IdentifyHand.checkRanks(cardCounter, player, 2);
            
              int[]colourCounter =  IdentifyHand.checkSuits(player.getPlayerCards());
             String flushColour =    IdentifyHand.checkFlush(colourCounter, player);
             
                IdentifyHand.checkStraight(player, player.getPlayerHand(), cardCounter);

        }  
          //player.getRealCards();
          System.out.println("real hand\n");
          Collections.sort(player.getRealCards(), new SortbyMODULEDINDEX()); 
               for(int i =0; i < player.getRealCards().size(); i++) 
      {
         System.out.println("["+ i + "] : REAL Index: " + player.getRealCards().get(i).getCardIndex()+ "   ");
          System.out.print(player.getRealCards().get(i).getRank()+ "   ");
           System.out.print(player.getRealCards().get(i).getSuit() + "\n");
           System.out.println();
      }
  //      IdentifyHand.getHandPower(player);
        
//        for(int i = 0; i<player.getPlayerHand().length; i++)
//        {
//            System.out.println(player.getPlayerHand()[i] + " " + i);
//        }
        

       
       
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