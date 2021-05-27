package cribbage.scoring;

import ch.aplu.jcardgame.Hand;

import java.util.ArrayList;

public abstract class RunScoringStrategy implements IScoringStrategy{

    protected final String rule = "run";

    @Override
    public abstract ArrayList<ScoringInstance> getScores(Hand cards);

}
