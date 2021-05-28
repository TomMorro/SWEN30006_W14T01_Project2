package cribbage.scoring;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;
import cribbage.Cribbage;

import java.util.ArrayList;

public class FlushScoringStrategy implements IScoringStrategy {

    protected final String rule = "flush";

    @Override
    public ArrayList<ScoringInstance> getScores(Hand cards) {

        ArrayList<ScoringInstance> retList = new ArrayList<>();
        ScoringInstance scoringInstance;

        Card starterCard = cards.getFirst();
        
        // Get a new hand for storing the flush
        Hand newHand = new Hand(starterCard.getDeck());
        for (int i = 1; i < cards.getNumberOfCards(); i++) {
            newHand.insert(cards.get(i).clone(), false);
        }

        for (Cribbage.Suit suit : Cribbage.Suit.values()) {

            Hand tempHand = newHand.extractCardsWithSuit(suit);

            if (tempHand.getNumberOfCards() >= 4) {
            	// Deal with a flush 5
                if (starterCard.getSuit() == tempHand.get(0).getSuit()) {

                    tempHand.reverse(false);
                    tempHand.insert(starterCard.clone(), false);
                    tempHand.reverse(false);

                    scoringInstance = new ScoringInstance(rule + 5, tempHand, 5);
                    retList.add(scoringInstance);
                    return retList;

                } 
                // Deal with a flush 4
                else {

                    scoringInstance = new ScoringInstance(rule + 4, tempHand, 4);
                    retList.add(scoringInstance);
                    return retList;
                }
            }
        }

        return retList;
    }
}
