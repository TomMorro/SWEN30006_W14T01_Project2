package cribbage.scoring;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;

import java.util.ArrayList;

public class ShowPairScoringStrategy extends PairScoringStrategy{
//    @Override
//    public int getPoints(Hand cards) {
//        ArrayList<Hand[]> allPairs = getAllPairs(cards);
//        int total = 0;
//        for(Hand[] pairs : allPairs){
//            for(Hand pair : pairs){
//                total += pair.getNumberOfCards() * pair.getNumberOfCards() - pair.getNumberOfCards();
//            }
//        }
//        return total;
//    }

    @Override
    public ArrayList<ScoringInstance> getScores(Hand cards) {
        ArrayList<ScoringInstance> scores = new ArrayList<>();
        ArrayList<Hand[]> allPairs = getAllPairs(cards);
        for(Hand[] pairs : allPairs){
            for(Hand pair : pairs){
                scores.add(new ScoringInstance(this.rule + pair.getNumberOfCards(), pair, pair.getNumberOfCards() * pair.getNumberOfCards() - pair.getNumberOfCards()));
            }
        }
        return scores;
    }
}
