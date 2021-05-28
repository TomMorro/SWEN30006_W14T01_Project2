package cribbage.scoring;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;
import cribbage.Cribbage;

import java.util.ArrayList;

public class JackScoringStrategy implements IScoringStrategy{
    protected final String rule = "jack";

    @Override
    public ArrayList<ScoringInstance> getScores(Hand cards) {
        ArrayList<ScoringInstance> scores = new ArrayList<>();
        
        // Get the suit of the starter card
        ArrayList<Card> cardList = cards.getCardList();
        Cribbage.Suit starterSuit = (Cribbage.Suit) cardList.get(0).getSuit();
        ArrayList<Card> jackedCards = new ArrayList<>();
        
        // Find Jacks with same suit as starter card
        int total = 0;
        for(int i = 1; i < cardList.size(); i++){
            if(cardList.get(i).getRank().equals(Cribbage.Rank.JACK) && cardList.get(i).getSuit().equals(starterSuit) ){
                total++;
                jackedCards.add(cardList.get(i));
            }
        }
        
        // If any such scoring exists, add it to scores
        if(total>0){
            Hand h = new Hand(jackedCards.get(0).getDeck());
            for (Card card: jackedCards) {
                h.insert(card, false);
            }
            scores.add(new ScoringInstance(rule, h, total));
        }
        return scores;
    }
}

