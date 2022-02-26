/*
 * This is the PlayerHand class
 * Objects create from this class contain necessary data which crucial for determine who is the winner of a game of three-card holder.
 */
import java.util.Arrays;
public class PlayerHand 
{
	private String flush;
	private int straight;
	private int pairNum;
	private int threeOfAKind;
	private int [] lowestToHighest;
	
	/*
	 * Constructor
	 */
	public PlayerHand()
	{
		flush = null;
		straight = -1;
		pairNum = -1;
		threeOfAKind = -1;
		lowestToHighest = null;
	}
	
	/*
	 * Overload constructor
	 */
	public PlayerHand(String[] setOfSuit, int[] setOfRank)
	{
		flush = null;
		straight = -1;
		pairNum = -1;
		threeOfAKind = -1;
		lowestToHighest = null;
		parseHand(setOfSuit, setOfRank);
	}
	

	/**
	 * This function initialize all the crucial detail of cards that player is holding
	 * 
	 * @param setOfSuit is array of card suit that player is holding
	 * @param setOfRank is array of card rank that player is holding
	 * Same index array of the setOfSuit and setOfRank are referring to the same class
	 * (i.e) setOfSuit[0] is 4 and setOfRank[0] is c -> this card at index zero is 4c.
	 */
	public void parseHand(String[] setOfSuit, int[] setOfRank)
	{
		Arrays.sort(setOfRank);
		lowestToHighest = setOfRank;
		
		/*
		 * Check for Three of a kind
		 */
		if((setOfRank[0] == setOfRank[1]) && (setOfRank[1] == setOfRank[2])) this.threeOfAKind = setOfRank[0];
		/*
		 * Check for flush
		 */
		if((setOfSuit[0].equalsIgnoreCase(setOfSuit[1])) && (setOfSuit[0].equalsIgnoreCase(setOfSuit[2]))) this.flush = setOfSuit[0];
		
		/*
		 * Check for straight
		 */

		if((lowestToHighest[0]+1 == lowestToHighest[1]) && (lowestToHighest[1]+1 == lowestToHighest[2])) this.straight = this.lowestToHighest[2];
		/*
		 * Check for pair
		 */
		if((setOfRank[0] == setOfRank[1]) || (setOfRank[0] == setOfRank[2])) pairNum = setOfRank[0];
		else if(setOfRank[1] == setOfRank[2]) pairNum = setOfRank[1];
		/*
		 * Corner case: lowest possible is `A-2-3`, 
		 * Since A is 14, if (14 + 2 + 3) - 14 always = to 5.
		 */
		if((this.lowestToHighest[2] == 14) && (((setOfRank[0] + setOfRank[1] + setOfRank[2]) - this.lowestToHighest[2]) == 5 ))
		{
			this.lowestToHighest[2] = 3; 
			this.lowestToHighest[0] = 1; 
			this.straight = 3;
		}
	}
	public boolean isStraightFlush()
	{
		if((this.isFlush()) && (this.isStraight())) return true;
		return false;
	}
	
	public boolean isStraight()
	{
		if(this.straight != -1) return true;
		return false;
	}
	
	public boolean isFlush()
	{
		if(this.flush != null) return true;
		return false;
	}
	
	public boolean isThreeOfAKind()
	{
		if(this.threeOfAKind != -1) return true;
		return false;
	}
	
	public boolean isPair()
	{
		if(this.pairNum!= -1) return true;
		return false;
	}
	
	public int getPair()
	{
		return this.pairNum;
	}
	
	public int getThreeOfAKind()
	{
		return this.threeOfAKind;
	}
	
	
	public int [] getCards()
	{
		return this.lowestToHighest;
	}
	
	
}

