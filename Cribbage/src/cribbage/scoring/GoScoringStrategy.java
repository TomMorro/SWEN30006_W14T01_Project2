package cribbage.scoring;

import ch.aplu.jcardgame.Hand;

import java.util.ArrayList;

public class GoScoringStrategy implements IScoringStrategy{

    private final String rule = "go";

    @Override
    public ArrayList<ScoringInstance> getScores(Hand cards) {

        ScoringInstance scoringInstance = new ScoringInstance(rule, new Hand(cards.getFirst().getDeck()), 1);

        ArrayList<ScoringInstance> retList = new ArrayList<>();
        retList.add(scoringInstance);

        return retList;
    }
}

