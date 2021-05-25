package cribbage.scoring;

import ch.aplu.jcardgame.Hand;

import java.util.ArrayList;

public class CompositePlayScoringStrategy extends CompositeScoringStrategy {
	
	public CompositePlayScoringStrategy() {
		super();
		super.add(new PlayRunScoringStrategy());
		super.add(new PlayPairScoringStrategy());
//		super.add(new PlayValueScoringStrategy());
	}

	@Override
	public ArrayList<ScoringInstance> getScores(Hand cards) {

		ArrayList<ScoringInstance> compositeScores = new ArrayList<>();

		for (IScoringStrategy strategy : scoringStrategies) {
			compositeScores.addAll(strategy.getScores(cards));
		}

		return compositeScores;
	}
}
