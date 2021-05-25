package cribbage.scoring;

import ch.aplu.jcardgame.Card;

import java.util.ArrayList;

public class ScoringInstance {

    private final String rule;
    private ArrayList<Card> scoringCards;
    private int player;

    public ScoringInstance(String rule, ArrayList<Card> scoringCards, int player) {
        this.rule = rule;
        this.scoringCards = scoringCards;
        this.player = player;
    }

    public String getRule() {
        return rule;
    }

    public ArrayList<Card> getScoringCards() {
        return scoringCards;
    }

    public int getPlayer() {
        return player;
    }
}
