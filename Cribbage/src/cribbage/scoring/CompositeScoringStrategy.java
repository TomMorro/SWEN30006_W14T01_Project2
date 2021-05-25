package cribbage.scoring;

import java.util.ArrayList;
import ch.aplu.jcardgame.Hand;

public abstract class CompositeScoringStrategy implements IScoringStrategy {
	protected ArrayList<IScoringStrategy> scoringStrategies = new ArrayList<IScoringStrategy>();
	
	public void add(IScoringStrategy strategy) {
		scoringStrategies.add(strategy);
	}

	public abstract ArrayList<ScoringInstance> getScores(Hand cards, int playerNumber);
}
