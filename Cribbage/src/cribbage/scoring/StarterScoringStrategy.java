package cribbage.scoring;

import ch.aplu.jcardgame.Hand;
import cribbage.Cribbage;

public class StarterScoringStrategy implements IScoringStrategy {
	private int jackScore = 2;
	
	public StarterScoringStrategy() {
	}

	@Override
	public int getPoints(Hand cards) {
		if (cards.get(0).getRank() == Cribbage.Rank.JACK) {
			return jackScore;
		}
		return 0;
	}
	
}
