package cribbage.scoring;

import ch.aplu.jcardgame.Hand;

public class CompositeShowScoringStrategy extends CompositeScoringStrategy{

    public CompositeShowScoringStrategy() {
        super();
        super.add(new FlushScoringStrategy());

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
