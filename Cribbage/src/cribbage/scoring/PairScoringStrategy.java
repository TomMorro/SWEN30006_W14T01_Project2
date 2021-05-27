package cribbage.scoring;

import ch.aplu.jcardgame.Hand;

import java.util.ArrayList;

public abstract class PairScoringStrategy implements IScoringStrategy{
    protected final String rule = "pair";
    
    public ArrayList<Hand[]> getAllPairs(Hand cards){
        ArrayList<Hand[]> allPairs = new ArrayList<>();
        boolean isInAllPairs;

        // get pair4
        Hand[] pair4s = cards.extractQuads();
        if(pair4s.length > 0) allPairs.add(pair4s);

        // get pair3
        Hand[] extractedPair3s = cards.extractTrips();
        ArrayList<Hand> finalPair3sList = new ArrayList<>();
        // we want to make sure that these pair3s are not in allPairs already
        for(Hand pair3 : extractedPair3s){
            isInAllPairs = false;
            for(Hand pair4 : pair4s){
                if(pair3.getFirst().getRank().equals(pair4.getFirst().getRank())){
                    isInAllPairs = true;
                    break;
                }
            }
            if (!isInAllPairs) finalPair3sList.add(pair3);
        }
        Hand[] pair3s = new Hand[finalPair3sList.size()];
        pair3s = finalPair3sList.toArray(pair3s);
        allPairs.add(pair3s);


        // get pair2
        Hand[] extractedPair2s = cards.extractPairs();
        ArrayList<Hand> finalPair2sList = new ArrayList<>();
        // make sure that pair2 is not in allPairs already
        for (Hand pair2 : extractedPair2s){
            isInAllPairs = false;
            for(Hand pair4 : pair4s){
                if(pair2.getFirst().getRank().equals(pair4.getFirst().getRank())){
                    isInAllPairs = true;
                    break;
                }
            }
            for(Hand pair3 : pair3s){
                if(pair2.getFirst().getRank().equals(pair3.getFirst().getRank())){
                    isInAllPairs = true;
                    break;
                }
            }
            if (!isInAllPairs) finalPair2sList.add(pair2);
        }
        Hand[] pair2s = new Hand[finalPair2sList.size()];
        pair2s = finalPair2sList.toArray(pair2s);
        allPairs.add(pair2s);


        return allPairs;
    }
}
