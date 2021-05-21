package cribbage.scoring;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;
import cribbage.Cribbage;

import java.util.ArrayList;

public class JackScoringStrategy implements IScoringStrategy{

    @Override
    public int getPoints(Hand cards) {
        // assume that card at first index is the starter card
        ArrayList<Card> cardList = cards.getCardList();
        Cribbage.Suit starterSuit = (Cribbage.Suit) cardList.get(0).getSuit();
        // assume that jack scoring stacks
        int total = 0;
        for(int i = 1; 1 < cardList.size(); i++){
            if(cardList.get(i).getRank().equals(Cribbage.Rank.JACK) && cardList.get(i).getSuit().equals(starterSuit) ){
                total++;
            }
        }

        return total;
    }
}
