package cribbage.scoring;

import ch.aplu.jcardgame.Hand;

public class PlayValueScoringStrategy extends ValueScoringStrategy{

	@Override
	public int getPoints(Hand cards) {
		if (cards.getScore() == fifteen || cards.getScore() == thirtyone) {
			return value;
		}
		return 0;
	}

}
