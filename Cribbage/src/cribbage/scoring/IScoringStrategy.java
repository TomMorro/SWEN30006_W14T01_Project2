package cribbage.scoring;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;

import java.util.ArrayList;

public interface IScoringStrategy {
    public ArrayList<ScoringInstance> getScores(Hand cards);



}
