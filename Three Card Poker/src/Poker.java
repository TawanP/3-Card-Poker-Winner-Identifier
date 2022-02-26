import java.util.HashMap;
import java.util.Scanner;
import java.util.*;
/*
 * This is the Poker class
 * This class contain main method
 */
public class Poker {

	public static void main(String args [])
	{
		Scanner key = new Scanner(System.in);;
		try {

			int playerCount = Integer.parseInt(key.nextLine());
			PlayerHand currWinner = null, currPlayer;
			
			String winner = "";
			for(int i = 0; i < playerCount; i++)
			{
				String [] splitHand = key.nextLine().split(" ");
				int firstCard = rankStringtoInt(splitHand[1]);
				int secCard = rankStringtoInt(splitHand[2]);
				int thirdCard = rankStringtoInt(splitHand[3]);
				
				currPlayer = new PlayerHand(new String [] {splitHand[1].substring(1), splitHand[2].substring(1), splitHand[3].substring(1)} , new int [] {firstCard, secCard, thirdCard});
				if(i > 0)
				{
					//Use to find the first current winner
					if(i == 1)
					{
						winner = WinnerChecker.currentWinner(currWinner, currPlayer, 0, 1);
					}
					//Compare the current winner with the current player
					else
					{
						List<Object> winnerDetail = WinnerChecker.findWinner(winner, currWinner,  currPlayer , i);
						winner = (String) winnerDetail.get(0);
						currWinner = (PlayerHand) winnerDetail.get(1);
					}
				}
				else 
					currWinner = currPlayer;
			}
			System.out.println(winner);
		}

		finally {
		    if(key !=null)
		        key.close();
		}
	}
	/*
	 * This function that convert string input into the integer, and if the card is T J Q K A convert them into number, which make them easier to compare.
	 */
	public static int rankStringtoInt(String currCard)
	{
		HashMap <String, Integer> cardValueMap = new HashMap <>();
		cardValueMap.put("T", 10);
		cardValueMap.put("J", 11);
		cardValueMap.put("Q", 12);
		cardValueMap.put("K", 13);
		cardValueMap.put("A", 14);
		
		if(cardValueMap.containsKey(currCard.substring(0,1))) return cardValueMap.get(currCard.substring(0,1));
		else return Integer.parseInt(currCard.substring(0,1));	
	}
	
}
