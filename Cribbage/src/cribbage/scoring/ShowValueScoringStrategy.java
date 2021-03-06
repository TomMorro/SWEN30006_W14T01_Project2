package cribbage.scoring;

import ch.aplu.jcardgame.Hand;
import ch.aplu.jcardgame.Card;
import java.util.ArrayList;

import cribbage.Cribbage;
import cribbage.Cribbage.Rank;

public class ShowValueScoringStrategy extends ValueScoringStrategy {

	@Override
	public ArrayList<ScoringInstance> getScores(Hand cards) {
		ArrayList<ScoringInstance> valueScores = new ArrayList<>();
		int counter, i;
		int powerSetSize = (int) Math.pow(2, cards.getNumberOfCards());
		ArrayList<Card> cardList = cards.getCardList();
		
		// Iterate through all the combinations of cards
		for (counter = 0; counter < powerSetSize; counter++) {
			int subtotal = 0;
			ArrayList<Card> possibleFifteen = new ArrayList<>();
			
			// Use a binary mask to find different combinations
			for (i = 0; i < cards.getNumberOfCards(); i++) {
				if (String.format("%5s", Integer.toBinaryString(counter)).replace(" ", "0").charAt(i) == '1') {
					Rank rank = (Cribbage.Rank) cardList.get(i).getRank();
					subtotal += rank.value;
					possibleFifteen.add(cardList.get(i));
				}
			}
			// If subtotal is fifteen, found new scoring instance
			if (subtotal == fifteen) {
				Hand newHand = new Hand(possibleFifteen.get(0).getDeck());
				
				for(Card card : possibleFifteen){
					  newHand.insert(card.clone(), false);
				  }
				ScoringInstance curScore = new ScoringInstance(super.fifteenRule, newHand, super.value);
				valueScores.add(curScore);
			}
		}

		return valueScores;
		
	}
}
