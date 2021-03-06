package cribbage.scoring;

import java.util.ArrayList;

import ch.aplu.jcardgame.Hand;
import cribbage.Cribbage;

public class StarterScoringStrategy implements IScoringStrategy {
	protected int jackScore = 2;
	protected final String rule = "starter";

	public StarterScoringStrategy() {
	}


	@Override
	public ArrayList<ScoringInstance> getScores(Hand cards) {
		ArrayList<ScoringInstance> starterScore = new ArrayList<>();

		// Check if card in starter hand is Jack
		if (cards.get(0).getRank() == Cribbage.Rank.JACK) {
			ScoringInstance curScore = new ScoringInstance(rule, cards, jackScore);
			starterScore.add(curScore);
		}
		return starterScore;
	}

}
