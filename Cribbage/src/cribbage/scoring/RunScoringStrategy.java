package cribbage.scoring;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;
import cribbage.Cribbage;
import cribbage.Cribbage.Rank;

import java.util.ArrayList;
import java.util.Collections;

public abstract class RunScoringStrategy implements IScoringStrategy{

    protected final String rule = "RUN";

    @Override
    public abstract ArrayList<ScoringInstance> getScores(Hand cards);


    /* Returns score for that length */
//    int isValidRun(Hand cards, int length) {
//
//        Hand[] sequence = cards.extractSequences(length);
//        if (sequence.length > 0)  System.out.println(sequence.length + ": " + length + " Sequences");
//        for (Hand h: sequence){
//            System.out.println(h);
//        }
//
//        return sequence.length * length ;
//    }

}
