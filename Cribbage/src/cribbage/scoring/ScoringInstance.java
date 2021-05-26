package cribbage.scoring;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;

import java.util.ArrayList;

public class ScoringInstance {

    private final String rule;
    private Hand scoringHand;
    private int points;

    public ScoringInstance(String rule, Hand scoringCards, int points) {
        this.rule = rule;
        this.scoringHand = scoringCards;
        this.points = points;
    }

    public String getRule() {
        return rule;
    }

    public Hand getScoringHand() {
        return scoringHand;
    }


    public int getPoints() {
        return points;
    }
}
