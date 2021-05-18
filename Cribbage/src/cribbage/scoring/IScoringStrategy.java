package cribbage.scoring;

import ch.aplu.jcardgame.Hand;

public interface IScoringStrategy {
    public int getPoints(Hand cards);
}
