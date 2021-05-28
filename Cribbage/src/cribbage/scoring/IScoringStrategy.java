package cribbage.scoring;

import ch.aplu.jcardgame.Hand;

import java.util.ArrayList;

public interface IScoringStrategy {
	// Get the scores of the particular scoring strategy
    public ArrayList<ScoringInstance> getScores(Hand cards);

}
