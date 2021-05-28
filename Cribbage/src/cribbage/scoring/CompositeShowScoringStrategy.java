package cribbage.scoring;


import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;

import java.util.ArrayList;


public class CompositeShowScoringStrategy extends CompositeScoringStrategy{
    public CompositeShowScoringStrategy(){
        super();
        super.add(new ShowValueScoringStrategy());
        super.add(new ShowRunScoringStrategy());
        super.add(new ShowPairScoringStrategy());
        super.add(new JackScoringStrategy());
        super.add(new FlushScoringStrategy());
    }


    @Override
    public ArrayList<ScoringInstance> getScores(Hand cards) {
        // Remove starter before sorting cards
    	Card starter = cards.getFirst();
        cards.removeFirst(false);
        cards.sort(cards.getSortType(), false);

        // Re-insert the starter card
        cards.reverse(false);
        cards.insert(starter, false);
        cards.reverse(false);

        ArrayList<ScoringInstance> compositeScores = new ArrayList<>();

        for (IScoringStrategy strategy : scoringStrategies) {
            compositeScores.addAll(strategy.getScores(cards));
        }

        return compositeScores;
    }
}
