package cribbage.scoring;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;
import cribbage.Cribbage;

import java.util.ArrayList;

public class FlushScoringStrategy implements IScoringStrategy{

    private final String rule = "flush";

    @Override
    public ArrayList<ScoringInstance> getScores(Hand cards) {

        ArrayList<Card> cardList = cards.getCardList();

        ArrayList<ScoringInstance> retList = new ArrayList<>();
        ScoringInstance scoringInstance;

        // suitID of second card that will be compared to
        int suitID = cardList.get(1).getSuitId();

        // Iterate through all of the cards except the 1st card (starter card) and the 2nd card (card that we are comparing to)
        for(int i = 2; i < cards.getNumberOfCards(); i++){
            Card curCard = cardList.get(i);

            // If the suitID is not the same as the second card, there is no flush
            if (curCard.getSuitId() != suitID){
                return retList;
            }
        }

        // If the starter card is the same suit
        if(cardList.get(0).getSuitId() == suitID){
            scoringInstance = new ScoringInstance(rule + 5, cardList, 5);

        }
        else {
            cardList.remove(0);
            
            scoringInstance = new ScoringInstance(rule + 4, cardList, 4);
        }
        retList.add(scoringInstance);
        return retList;
    }
}
