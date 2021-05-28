package cribbage.scoring;

import ch.aplu.jcardgame.Hand;

import java.util.ArrayList;

public class PlayRunScoringStrategy extends RunScoringStrategy{

    @Override
    public ArrayList<ScoringInstance> getScores(Hand cards) {
        int i = cards.getNumberOfCards();
        ArrayList<ScoringInstance> runScores = new ArrayList<>();
        // ArrayList<Card> cloneCards = new ArrayList<>();

        Hand newHand = new Hand(cards.get(0).getDeck());

        while (i > 2) {
            if (isValidRun(cards, i)){

                for(int j = cards.getNumberOfCards() - 1; j > i; j--){

                    newHand.insert(cards.getCard(j).clone(), false);
                }
                ScoringInstance curScore = new ScoringInstance(super.rule + i, newHand, i);
                runScores.add(curScore);
                return runScores;
            }
            i--;
        }
        return runScores;
    }
}
