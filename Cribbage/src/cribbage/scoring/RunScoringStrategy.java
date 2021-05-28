package cribbage.scoring;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;
import cribbage.Cribbage.Rank;

import java.util.ArrayList;
import java.util.Collections;

public abstract class RunScoringStrategy implements IScoringStrategy{

    protected final String rule = "run";

    @Override
    public abstract ArrayList<ScoringInstance> getScores(Hand cards);
    
    // Checks if hand is a valid run for certain length
    protected boolean isValidRun(Hand cards, int length){
        ArrayList<Card> cardList = cards.getCardList();
        ArrayList<Integer> buf = new ArrayList<>();
        int i;
        for(i = cardList.size() - 1;i > cardList.size() - length - 1;i--){
            Rank rank = (Rank) cardList.get(i).getRank();
            buf.add(rank.order);
        }
        Collections.sort(buf);

        for (int j = 0; j < buf.size() - 1; j++) {
            if(buf.get(j) != buf.get(j+1) - 1){
                return false;
            }
        }
        return true;
    }

}
