package cribbage.scoring;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;
import cribbage.Cribbage;
import cribbage.Cribbage.Rank;

import java.util.ArrayList;
import java.util.Collections;

public class RunScoringStrategy implements IScoringStrategy{

    private int type;

    public RunScoringStrategy(int type){
        this.type = type;
    }


    @Override
    public int getPoints(Hand cards) {
        if (isValidRun(cards)){
            return type;
        }
        return 0;
    }

    private boolean isValidRun(Hand cards){
        ArrayList<Card> cardList = cards.getCardList();
        ArrayList<Integer> buf = new ArrayList<>();
        int i;
        for(i = cardList.size() - 1;i > cardList.size() - type - 1;i--){
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
