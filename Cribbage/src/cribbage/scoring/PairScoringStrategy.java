package cribbage.scoring;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;
import cribbage.Cribbage;

import java.util.ArrayList;
import java.util.HashMap;
import java.lang.Math;

public class PairScoringStrategy implements  IScoringStrategy{
    
    public PairScoringStrategy(){
    }

    @Override
    public int getPoints(Hand cards) {
    	int i = Math.min(cards.getNumberOfCards(), 4);

    	while (i > 1) {
    		if (isValidPair(cards, i)){
    			// Pair scoring strategy conversion
                return i*i - i;
            }
    		i--;
    	}
    	
        return 0;
    }

    private boolean isValidPair(Hand cards, int length){
        ArrayList<Card> cardList = cards.getCardList();
        Cribbage.Rank rank= (Cribbage.Rank) cardList.get(cardList.size() - 1).getRank();
        
        for(int i = cardList.size() - 2; i > cardList.size() - length - 1; i--){
            Cribbage.Rank curRank= (Cribbage.Rank) cardList.get(i).getRank();
            if(rank.order != curRank.order){
                return false;
            }
        }
        return true;
    }
}
