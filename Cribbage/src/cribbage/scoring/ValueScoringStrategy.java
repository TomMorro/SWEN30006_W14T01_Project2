package cribbage.scoring;

import ch.aplu.jcardgame.Hand;

public class ValueScoringStrategy implements IScoringStrategy{
	
	private int value = 2;
	
	public ValueScoringStrategy() {
	}

	@Override
	public int getPoints(Hand cards) {
		if (cards.getScore() == 15 || cards.getScore() == 31) {
			return value;
		}
		return 0;
	}
}
