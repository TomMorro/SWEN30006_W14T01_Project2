package cribbage.scoring;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;

import java.util.ArrayList;

public class ShowPairScoringStrategy extends PairScoringStrategy{
    @Override
    public int getPoints(Hand cards) {
        ArrayList<Hand[]> allPairs = getAllPairs(cards);
        int total = 0;
        for(Hand[] pairs : allPairs){
            for(Hand pair : pairs){
                total += pair.getNumberOfCards() * pair.getNumberOfCards() - pair.getNumberOfCards();
            }
        }
        return total;
    }
}
