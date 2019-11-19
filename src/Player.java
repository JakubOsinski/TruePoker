
import java.util.ArrayList;


public class Player 
{
    private String name;
     ArrayList<String> ranksToGet = new ArrayList<String>();
    ArrayList<Card>  playerCards = new ArrayList<Card>();
     ArrayList<Card>  realCards = new ArrayList<Card>(); //5 cards used to represent player hand after the system finds out what the best ones are
       public  boolean[] hand = new boolean[10];
       
       private int handPower;
    
    public Player() {
        
    }
    
    public void setRanksToGet(ArrayList<String> ranksToGet) 
    {
        this.ranksToGet = ranksToGet;
    }
    public  ArrayList<String> getRanksToGet() 
    {
        return ranksToGet;
    } 
    
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    
    
       public ArrayList<Card> getRealCards() 
    {
        return realCards;
    }
     public void setRealCards(ArrayList<Card> realCards) 
    {
        this.realCards = realCards;
    }
    
    
    public int getHandPower() 
    {
        return handPower;
    }
     public void setHandPower(int handPower) 
    {
        this.handPower = handPower;
    }
    
    
    public boolean[] getPlayerHand() 
    {
        return hand;
    }
    
       public void setPlayerHand(boolean[] hand) 
    {
         this.hand = hand;
    }
    
    public Player(ArrayList<Card> playerCards) 
    {
        this.playerCards = playerCards;
    }
    
  public void addPlayerCard(Card card) 
  {
      playerCards.add(card);
  }
  
  public void addPlayerCards(ArrayList<Card> cards) 
  {  
      for(int i = 0;i < cards.size();i++) 
      {
          playerCards.add(cards.get(i));
      }
  }
   public void removePlayerCards() 
  {
      for(int i =0; i < playerCards.size(); i++) 
      {
          playerCards.remove(i);
      }
  }
   
   public void checkPlayerCards() 
   {
             for(int i =0; i < playerCards.size(); i++) 
      {
         System.out.println("["+ i + "] :Index: " + playerCards.get(i).getCardIndex()+ "   ");
          System.out.print(playerCards.get(i).getRank()+ "   ");
           System.out.print(playerCards.get(i).getSuit() + "\n");
           System.out.println();
      }
   }
   
  public ArrayList<Card> getPlayerCards() 
   {
       return playerCards;
   }
    
  
  
}
