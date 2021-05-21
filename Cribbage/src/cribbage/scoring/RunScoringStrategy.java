package cribbage.scoring;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;
import cribbage.Cribbage;
import cribbage.Cribbage.Rank;

import java.util.ArrayList;
import java.util.Collections;

public class RunScoringStrategy implements IScoringStrategy{

    public RunScoringStrategy(){
    }


    @Override
    public int getPoints(Hand cards) {
    	int i = cards.getNumberOfCards();
    	
    	while (i > 2) {
    		if (isValidRun(cards, i)){
                return i;
            }
    		i--;
    	}

        return 0;
    }

    private boolean isValidRun(Hand cards, int length){
        ArrayList<Card> cardList = cards.getCardList();
        ArrayList<Integer> buf = new ArrayList<>();
        int i;
        for(i = cardList.size() - 1;i > cardList.size() - length - 1;i--){
            Rank rank = (Cribbage.Rank) cardList.get(i).getRank();
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
