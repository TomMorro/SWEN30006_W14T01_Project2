package cribbage.scoring;


import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;

import java.util.ArrayList;


public class CompositeShowScoringStrategy extends CompositeScoringStrategy{
    public CompositeShowScoringStrategy(){
        super();
        super.add(new ShowRunScoringStrategy());
//        super.add(new ShowPairScoringStrategy());
//        super.add(new FlushScoringStrategy());
//        super.add(new JackScoringStrategy());
//        super.add(new ShowValueScoringStrategy());
    }


    @Override
    public ArrayList<ScoringInstance> getScores(Hand cards) {

        // get starter card located at the first index
        Card starter = cards.getFirst();

        // remove starter before sorting cards
        cards.removeFirst(false);

        // sort
        cards.sort(cards.getSortType(), false);

        // a bit of a hacky way to insert the starter at the first index again
        cards.reverse(false);
        cards.insert(starter, false);
        cards.reverse(false);

        int i = 0;

        String [] names = {"RUN", "PAIR", "FLUSH", "JACK", "VALUE"};

        ArrayList<ScoringInstance> compositeScores = new ArrayList<>();

        System.out.println("--THE SHOW--");
        for (IScoringStrategy strategy : scoringStrategies) {

            compositeScores.addAll(strategy.getScores(cards));

            i++;
        }

        return compositeScores;
    }
}
