package cribbage.scoring;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;

import java.util.ArrayList;

public class GoScoringStrategy implements IScoringStrategy{

    private final String rule = "GO";

    @Override
    public ArrayList<ScoringInstance> getScores(Hand cards) {

        ScoringInstance scoringInstance = new ScoringInstance(rule, new ArrayList<Card>(), 1);

        ArrayList<ScoringInstance> retList = new ArrayList<>();
        retList.add(scoringInstance);

        return retList;
    }
}
