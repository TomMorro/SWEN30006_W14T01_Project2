package cribbage.scoring;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;
import cribbage.Cribbage;
import cribbage.Cribbage.Rank;

import java.util.ArrayList;
import java.util.Collections;

public class PlayRunScoringStrategy extends RunScoringStrategy{

    private boolean isValidRun(Hand cards, int length){
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

    @Override
    public ArrayList<ScoringInstance> getScores(Hand cards) {
        int i = cards.getNumberOfCards();
        ArrayList<ScoringInstance> runScores = new ArrayList<>();
        ArrayList<Card> cloneCards = new ArrayList<>();

        while (i > 2) {
            if (isValidRun(cards, i)){

                for(int j = cards.getNumberOfCards() - 1; j > i; j--){

                    cloneCards.add(cards.getCard(j));
                }
                System.out.println(cloneCards);

                ScoringInstance curScore = new ScoringInstance(super.rule, cloneCards, i);
                runScores.add(curScore);
                return runScores;
            }
            i--;
        }
        return runScores;
    }
}
