package cribbage.scoring;

import ch.aplu.jcardgame.Hand;
import cribbage.Cribbage;
import cribbage.Cribbage.Rank;

import java.util.ArrayList;

import ch.aplu.jcardgame.Card;

public class PlayValueScoringStrategy extends ValueScoringStrategy {
	
	public PlayValueScoringStrategy() {
		
	}

	@Override
	public ArrayList<ScoringInstance> getScores(Hand cards) {
		int total = 0;
		ArrayList<ScoringInstance> valueScores = new ArrayList<>();
		
		// Iterate through the hand, keeping track of total score
		for (Card card : cards.getCardList()) {
			Rank rank = (Cribbage.Rank) card.getRank();
			total += rank.value;
		}
		
		// Check if the score of the hand equals one of the scoring targets
		if (total == fifteen) {
			ScoringInstance curScore = new ScoringInstance(super.fifteenRule, cards, super.value);
			valueScores.add(curScore);
		}
		else if (total == thirtyone) {
			ScoringInstance curScore = new ScoringInstance(super.thirtyoneRule, cards, super.value);
			valueScores.add(curScore);
		}
		
		return valueScores;
	}
}
