package cribbage.scoring;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;
import cribbage.Cribbage;

import java.util.ArrayList;

public class PairScoringStrategy implements  IScoringStrategy{
    public enum Type {
        // Order of cards is tied to card images
        TWO(2, 2), THREE(3, 6), FOUR(4, 12);
        public final int type;
        public final int points;
        Type(int type, int points) {
            this.type = type;
            this.points = points;
        }
    }
    private Type type;

    public PairScoringStrategy(int type){
        if(type == 2){
            this.type = Type.TWO;
        } else if (type == 4){
            this.type = Type.FOUR;
        } else if (type == 3){
            this.type = Type.THREE;
        }
    }

    @Override
    public int getPoints(Hand cards) {
        if(isValidPair(cards)){
            return type.points;
        }
        return 0;
    }

    private boolean isValidPair(Hand cards){
        ArrayList<Card> cardList = cards.getCardList();
        Cribbage.Rank rank= (Cribbage.Rank) cardList.get(cardList.size()-1).getRank();
        for(int i = cardList.size() - 2; i > cardList.size() - type.type - 1; i--){
            Cribbage.Rank curRank= (Cribbage.Rank) cardList.get(cardList.size()-1).getRank();
            if(rank.order != curRank.order){
                return false;
            }
        }
        return true;
    }
}
