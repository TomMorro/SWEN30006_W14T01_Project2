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
        ArrayList<Card> cloneCards = cards.getCardList();
		
		for (Card card : cards.getCardList()) {
			Rank rank = (Cribbage.Rank) card.getRank();
			total += rank.value;
		}
		if (total == fifteen) {
			ScoringInstance curScore = new ScoringInstance(super.fifteenRule, cloneCards, super.value);
			valueScores.add(curScore);
		}
		else if (total == thirtyone) {
			ScoringInstance curScore = new ScoringInstance(super.thirtyoneRule, cloneCards, super.value);
			valueScores.add(curScore);
		}
		
		return valueScores;
	}
}
