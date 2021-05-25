package cribbage.scoring;

import ch.aplu.jcardgame.Card;

import java.util.ArrayList;

public class ScoringInstance {

    private final String rule;
    private ArrayList<Card> scoringCards;
    private int points;

    public ScoringInstance(String rule, ArrayList<Card> scoringCards, int points) {
        this.rule = rule;
        this.scoringCards = scoringCards;
        this.points = points;
    }

    public String getRule() {
        return rule;
    }

    public ArrayList<Card> getScoringCards() {
        return scoringCards;
    }


    public int getPoints() {
        return points;
    }
}
