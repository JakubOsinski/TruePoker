import java.util.Random;


public class Card
{
    Random random;
	//public static String []suits;
public static String []suits = {"clubs", "hearts", "diamonds", "spades"};
public static String []ranks = { "2", "3", "4","5", "6", "7", "8","9", "10", "Jack", "Queen", "King", "Ace"};
//	public static String []ranks;
	
	public String suit, rank;
	public int index;
	int indexCounter;
        private int moduledIndex;
	
	public Card() 
	{	
		random = new Random();
		suit = "";//getRandomSuit();
		rank = "";//getRandomRank();
		index = 0;//getCardIndex();
                moduledIndex = 0;
	}
	
	public Card(String suit, String rank, int index, int moduledIndex) 
	{
        this.suit = suit;
        this.rank = rank; 
        this.index = index;
        this.moduledIndex = moduledIndex;
	}
        
        public int getModuledIndex() 
        {
            return moduledIndex;
        }
         public void setModuledIndex(int moduledIndex) 
        {
               this.moduledIndex = moduledIndex;
        }
	
//	 public String getRandomSuit()
//	  {
//			int n = random.nextInt(4); // (50) = 0 to 49    
//			index = index + (n+1);
//			 return suits[n];
//	  }
//	  
        
//    public String getRandomRank()
//     {
//    	
//		int n = random.nextInt(13); // (50) = 0 to 49   
//		index = index + (n+1);
//		  return ranks[n];
//     }
    
    public int getCardIndex() 
    {
   	return index;
    }
//public void setSuitValues() 
//{
//	suits = new String[4];
//	suits[0] = "clubs";
//	suits[1] = "hearts";
//	suits[2] = "diamonds";
//	suits[3] = "spades";
//}

//public void setRankValues() 
//{
//	ranks = new String[13];
//	ranks[0] = "Ace";
//	ranks[1] = "2";
//	ranks[2] = "3";
//	ranks[3] = "4";
//	ranks[4] = "5";
//	ranks[5] = "6";
//	ranks[6] = "7";
//	ranks[7] = "8";
//	ranks[8] = "9";
//	ranks[9] = "10";
//	ranks[10] = "Jack";
//	ranks[11] = "Queen";
//	ranks[12] = "King";
//	
//}

// getters & setters
public void setSuit(String suit) 
{ 
	this.suit = suit;
}

//get suit
public String getSuit() 
{ 
	return suit;
}
public void setRank(String rank) 
{
	this.rank = rank;
}

public String getRank()
{ 
	return rank;
}

public void setIndex(int index) 
{
	this.index = index;
}

//public int getIndex() 
//{
//	return index;
//}

// end gettes & settesrs


	
} // end class card
