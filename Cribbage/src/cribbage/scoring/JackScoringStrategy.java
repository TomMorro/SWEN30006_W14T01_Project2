package cribbage.scoring;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;
import cribbage.Cribbage;

import java.util.ArrayList;

public class JackScoringStrategy implements IScoringStrategy{

//    @Override
//    public int getPoints(Hand cards) {
//        // assume that card at first index is the starter card
//        ArrayList<Card> cardList = cards.getCardList();
//        Cribbage.Suit starterSuit = (Cribbage.Suit) cardList.get(0).getSuit();
//        // assume that jack scoring stacks
//        int total = 0;
//        for(int i = 1; i < cardList.size(); i++){
//            if(cardList.get(i).getRank().equals(Cribbage.Rank.JACK) && cardList.get(i).getSuit().equals(starterSuit) ){
//                total++;
//            }
//        }
//
//        return total;
//    }

    @Override
    public ArrayList<ScoringInstance> getScores(Hand cards) {
        ArrayList<ScoringInstance> scores = new ArrayList<>();
        // assume that card at first index is the starter card
        ArrayList<Card> cardList = cards.getCardList();
        Cribbage.Suit starterSuit = (Cribbage.Suit) cardList.get(0).getSuit();
        ArrayList<Card> jackedCards = new ArrayList<>();
        // assume that jack scoring stacks
        int total = 0;
        for(int i = 1; i < cardList.size(); i++){
            if(cardList.get(i).getRank().equals(Cribbage.Rank.JACK) && cardList.get(i).getSuit().equals(starterSuit) ){
                total++;
                jackedCards.add(cardList.get(i));
            }
        }
        scores.add(new ScoringInstance("JACK", jackedCards, total));
        return scores;
    }
}

