package cribbage.scoring;

import ch.aplu.jcardgame.Hand;

public class ScoringInstance implements Comparable<ScoringInstance> {

    private final String rule;
    private Hand scoringHand;
    private int points;

    public ScoringInstance(String rule, Hand scoringCards, int points) {
        this.rule = rule;
        this.scoringHand = scoringCards;
        this.points = points;
    }

    public String getRule() {
        return rule;
    }

    public Hand getScoringHand() {
        return scoringHand;
    }


    public int getPoints() {
        return points;
    }

    @Override
    public int compareTo(ScoringInstance o) {
    	
    	// First compare the category
        if (Rule.valueOf(this.rule).ordinal() != Rule.valueOf(o.rule).ordinal()){

            System.out.println(this.rule + ": " + o.rule);

            return Rule.valueOf(this.rule).ordinal() - Rule.valueOf(o.rule).ordinal();
        }
        
        // If category the same, compare the hands in alphabetical order
        this.scoringHand.sort(Hand.SortType.POINTPRIORITY, false);
        o.scoringHand.sort(Hand.SortType.POINTPRIORITY, false);

        int i = 0;
        int res;

        while(i < Math.min(this.scoringHand.getNumberOfCards(), o.scoringHand.getNumberOfCards())){

            if ((res = this.scoringHand.get(i).compareTo(o.scoringHand.get(i))) != 0){

                return res;

            }
            i++;
        }


        return 0;
    }
}
