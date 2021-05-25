package cribbage.scoring;

import ch.aplu.jcardgame.Hand;

import java.util.ArrayList;

public class ShowRunScoringStrategy extends RunScoringStrategy{

    @Override
    public ArrayList<ScoringInstance> getScores(Hand cards, int playerNumber) {

        ArrayList<ScoringInstance> runScores = new ArrayList<>();

        // number of 5 sequences
        Hand[] fiveSequence = cards.extractSequences(5);
        if (fiveSequence.length > 0)  System.out.println(fiveSequence.length + " 5 Sequences");
        for (Hand h: fiveSequence){

            ScoringInstance curScore = new ScoringInstance(super.rule, h.getCardList(), playerNumber);
            runScores.add(curScore);
        }


        Hand[] fourSequence = cards.extractSequences(4);
        if (fourSequence.length > 0) System.out.println(fourSequence.length + " 4 Sequences");
        for (Hand h: fourSequence){
            ScoringInstance curScore = new ScoringInstance(super.rule, h.getCardList(), playerNumber);
            runScores.add(curScore);

        }

        Hand[] threeSequence = cards.extractSequences(3);
        if (threeSequence.length> 0) System.out.println(threeSequence.length + " 3 Sequences");
        for (Hand h: threeSequence){
            ScoringInstance curScore = new ScoringInstance(super.rule, h.getCardList(), playerNumber);
            runScores.add(curScore);
        }

        return runScores;
    }
}
