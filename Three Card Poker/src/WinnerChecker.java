import java.util.Arrays;
import java.util.List;

/*
 * This is WinnerChecker class.
 * This class have functions that help determine the winner of the game.
 */
public class WinnerChecker {
	/*
	 * This function compare card between two player and determine who is the winner between the two.
	 * return both if draw.
	 */
	public static String currentWinner( PlayerHand playerOne, PlayerHand playerTwo, int playerOneID, int playerTwoID)
	{
		
		/*
		 * Straight flush check
		 * Both player have straight flush, whoever has the top card wins, if all the rank of the card are exactly same then draw.
		 */
		if((playerOne.isStraightFlush()) && (playerTwo.isStraightFlush()))
		{
				if(playerOne.getCards()[2] > playerTwo.getCards()[2])
				{
					return Integer.toString(playerOneID);
				}
				else if(playerOne.getCards()[2] < playerTwo.getCards()[2]) return Integer.toString(playerTwoID);
				return Integer.toString(playerOneID) +" "+  Integer.toString(playerTwoID);
		}
		else if(playerOne.isStraightFlush())
		{
			return Integer.toString(playerOneID);
		}
		else if(playerTwo.isStraightFlush())
		{
			return Integer.toString(playerTwoID);
		}
		
		/*
		 * Three of a Kind check, if both player have three of a kind, ties are broken by the rank of the three cards. (No draw possible)
		 */
		else if((playerOne.isThreeOfAKind()) && (playerTwo.isThreeOfAKind()))
		{
			if(playerOne.getThreeOfAKind() > playerTwo.getThreeOfAKind()) return   Integer.toString(playerOneID);
			return Integer.toString(playerTwoID);
		}
		else if(playerOne.isThreeOfAKind()) return  Integer.toString(playerOneID);
		else if (playerTwo.isThreeOfAKind()) return Integer.toString(playerTwoID);
		
		/*
		 * Straight check
		 * Ties are broken by the highest ranked card.
		 */
		else if((playerOne.isStraight()) && (playerTwo.isStraight()))
		{
			if(playerOne.getCards()[2] > playerTwo.getCards()[2]) return Integer.toString(playerOneID);
			else if(playerOne.getCards()[2] < playerTwo.getCards()[2]) return Integer.toString(playerTwoID);
			//Draw
			return Integer.toString(playerOneID) + " " + Integer.toString(playerTwoID);
		}
		else if(playerOne.isStraight()) return Integer.toString(playerOneID);
		else if(playerTwo.isStraight()) return Integer.toString(playerTwoID);
		
		/*
		 * Flush check
		 */
		else if((playerOne.isFlush()) && (playerTwo.isFlush()))
		{
			return haveHighest(playerOne, playerTwo, playerOneID, playerTwoID);
		}
		else if (playerOne.isFlush()) return Integer.toString(playerOneID);
		else if (playerTwo.isFlush()) return Integer.toString(playerTwoID);
		
		/*
		 * Pair check
		 */
		if((playerOne.isPair()) && (playerTwo.isPair()))
		{
			if(playerOne.getPair() > playerTwo.getPair()) return Integer.toString(playerOneID);
			else if(playerOne.getPair() > playerTwo.getPair()) return Integer.toString(playerTwoID);
			else return haveHighest(playerOne, playerTwo, playerOneID, playerTwoID);
		}
		else if (playerOne.isPair()) return Integer.toString(playerOneID);
		else if (playerTwo.isPair()) return Integer.toString(playerTwoID);
		/*
		 * Nothing
		 */
		return haveHighest(playerOne, playerTwo, playerOneID, playerTwoID);
	}
	
	/*
	 * This function determine which player have higher rank card on their hand, if all three card of player have exactly same rank return both Player.
	 */
	public static String haveHighest(PlayerHand playerOne, PlayerHand playerTwo, int playerOneID, int playerTwoID)
	{
		for(int i = playerOne.getCards().length -1; i >= 0; i--)
		{
			if(playerOne.getCards()[i] > playerTwo.getCards()[i])
			{
				return Integer.toString(playerOneID);
			}
			else if(playerOne.getCards()[i] < playerTwo.getCards()[i])
			{
				return Integer.toString(playerTwoID);
			}
		}
		return Integer.toString(playerOneID) +" "+  Integer.toString(playerTwoID);
	}
	
	public static List<Object> findWinner(String winner, PlayerHand currWinner, PlayerHand contender, int contenderID)
	{
		int currWinnerID = -1;
		if(winner.length() > 1)  currWinnerID = Integer.parseInt(winner.substring(0,1));
		else currWinnerID = Integer.parseInt(winner);
		//Store winner temporary here because the winner string might currently have two winner (winner A and B) 
		//and B against C (Which B win once again) we don't want to overwrite the string winner but keep it the way it is.
		String winnerTemp = currentWinner(currWinner, contender, currWinnerID, contenderID);
		
		//Found another draw, combine them up together.
		if(winnerTemp.length() > 1)
		{
			winner = winner.concat(" " + winnerTemp.substring(2));
		}
		//new Winner overwrite the old one.
		else if(!winnerTemp.equals(Integer.toString(currWinnerID)))
			{
				winner = winnerTemp;
				currWinner = contender;
			}
		return Arrays.asList(winner, currWinner);
	}

}
