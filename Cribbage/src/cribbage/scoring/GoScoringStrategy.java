package cribbage.scoring;

import ch.aplu.jcardgame.Hand;

import java.util.ArrayList;

public class GoScoringStrategy implements IScoringStrategy{

    protected final String rule = "go";

    @Override
    public ArrayList<ScoringInstance> getScores(Hand cards) {

        ScoringInstance scoringInstance = new ScoringInstance(rule, cards, 1);

        ArrayList<ScoringInstance> retList = new ArrayList<>();
        retList.add(scoringInstance);

        return retList;
    }
}

