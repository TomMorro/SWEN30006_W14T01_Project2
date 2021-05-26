package cribbage.scoring;

import ch.aplu.jcardgame.Hand;

import java.util.ArrayList;

public class ShowRunScoringStrategy extends RunScoringStrategy{

    @Override
    public ArrayList<ScoringInstance> getScores(Hand cards) {

        ArrayList<ScoringInstance> runScores = new ArrayList<>();

        // number of 5 sequences
        Hand[] fiveSequence = cards.extractSequences(5);
        for (Hand h: fiveSequence){

            ScoringInstance curScore = new ScoringInstance(super.rule + 5, h.getCardList(), 5);
            runScores.add(curScore);
        }


        Hand[] fourSequence = cards.extractSequences(4);
        for (Hand h: fourSequence){
            ScoringInstance curScore = new ScoringInstance(super.rule + 4, h.getCardList(), 4);
            runScores.add(curScore);

        }

        Hand[] threeSequence = cards.extractSequences(3);
        for (Hand h: threeSequence){
            ScoringInstance curScore = new ScoringInstance(super.rule + 3, h.getCardList(), 3);
            runScores.add(curScore);
        }

        return runScores;
    }
}
