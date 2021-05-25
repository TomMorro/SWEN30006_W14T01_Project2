package cribbage.scoring;

import ch.aplu.jcardgame.Hand;

public class CompositePlayScoringStrategy extends CompositeScoringStrategy {
	
	public CompositePlayScoringStrategy() {
		super();
		super.add(new PlayValueScoringStrategy());
		super.add(new PlayRunScoringStrategy());
		super.add(new PlayPairScoringStrategy());
	}

	@Override
	public int getPoints(Hand cards) {
		int totalScore = 0;
		
		for (IScoringStrategy strategy : scoringStrategies) {
			totalScore += strategy.getPoints(cards);
		}
		
		return totalScore;
	}
}
