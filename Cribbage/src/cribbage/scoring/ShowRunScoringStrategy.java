package cribbage.scoring;

import ch.aplu.jcardgame.Hand;

import java.util.ArrayList;

public class ShowRunScoringStrategy extends RunScoringStrategy{


    @Override
    public int getPoints(Hand cards) {
        int totalPoints = 0;

        // number of 5 sequences
        Hand[] fiveSequence = cards.extractSequences(5);
        if (fiveSequence.length > 0)  System.out.println(fiveSequence.length + " 5 Sequences");
        for (Hand h: fiveSequence){
            System.out.println(h);
        }
        totalPoints += fiveSequence.length * 5;


        Hand[] fourSequence = cards.extractSequences(4);
        if (fourSequence.length > 0) System.out.println(fourSequence.length + " 4 Sequences");
        for (Hand h: fourSequence){
            System.out.println(h);
        }

        totalPoints += fourSequence.length * 4;

        Hand[] threeSequence = cards.extractSequences(3);
        if (threeSequence.length> 0) System.out.println(threeSequence.length + " 3 Sequences");
        for (Hand h: threeSequence){
            System.out.println(h);
        }

        totalPoints += threeSequence.length * 3;

        return totalPoints;
    }
}
