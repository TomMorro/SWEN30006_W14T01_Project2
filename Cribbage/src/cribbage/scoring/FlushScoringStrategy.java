package cribbage.scoring;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;
import cribbage.Cribbage;

import java.util.ArrayList;

public class FlushScoringStrategy implements IScoringStrategy {

    private final String rule = "flush";

    @Override
    public ArrayList<ScoringInstance> getScores(Hand cards) {

        ArrayList<ScoringInstance> retList = new ArrayList<>();
        ScoringInstance scoringInstance;

        Card starterCard = cards.getFirst();
        Hand newHand = new Hand(starterCard.getDeck());

        for (Card card : cards.getCardList()) {
            newHand.insert(card.clone(), false);
        }

        for (Cribbage.Suit suit : Cribbage.Suit.values()) {

            Hand tempHand = cards.extractCardsWithSuit(suit);

            if (tempHand.getNumberOfCards() >= 4) {
                if (starterCard.getSuit() == tempHand.get(0).getSuit()) {

                    tempHand.reverse(false);
                    tempHand.insert(starterCard, false);
                    tempHand.reverse(false);

                    scoringInstance = new ScoringInstance(rule + 5, tempHand, 5);
                    retList.add(scoringInstance);
                    return retList;

                } else {

                    scoringInstance = new ScoringInstance(rule + 4, tempHand, 4);
                    retList.add(scoringInstance);
                    return retList;
                }
            }
        }

        return retList;
    }
}
