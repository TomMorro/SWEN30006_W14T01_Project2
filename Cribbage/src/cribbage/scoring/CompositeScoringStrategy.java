package cribbage.scoring;

import java.util.ArrayList;
import ch.aplu.jcardgame.Hand;

public abstract class CompositeScoringStrategy implements IScoringStrategy {
	protected ArrayList<IScoringStrategy> scoringStrategies = new ArrayList<IScoringStrategy>();
	
	// Add a new strategy to this compoiste strategy
	public void add(IScoringStrategy strategy) {
		scoringStrategies.add(strategy);
	}

	public abstract ArrayList<ScoringInstance> getScores(Hand cards);




}
