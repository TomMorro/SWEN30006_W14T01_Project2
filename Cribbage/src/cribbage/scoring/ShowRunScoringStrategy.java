package cribbage.scoring;

import ch.aplu.jcardgame.Hand;

import java.util.ArrayList;

public class ShowRunScoringStrategy extends RunScoringStrategy{

    @Override
    public ArrayList<ScoringInstance> getScores(Hand cards) {

        ArrayList<ScoringInstance> runScores = new ArrayList<>();
        ArrayList<Integer> positions = new ArrayList<>();
        Hand testHand;

        // Check for run5s
        for (int i = 0;i < 5; i++) {
        	positions.add(i);
        }
        testHand = getNewHand(cards, positions);
        if (isValidRun(testHand, testHand.getNumberOfCards())){
            runScores.add(new ScoringInstance(rule+5, testHand, 5));
        }
        if (!runScores.isEmpty()) {
        	return runScores;
        }
        positions.clear();
        
        // Check for run4s
        for (int i = 0;i < 5; i++) {
        	for (int j = 0;j < 5; j++){
        		// Use all cards but one
        		if (j != i) {
        			positions.add(j);
        		}
        	}
        	testHand = getNewHand(cards, positions);
            if (isValidRun(testHand, testHand.getNumberOfCards())){
                runScores.add(new ScoringInstance(rule+4, testHand, 4));
            }
            positions.clear();
        }
        if (!runScores.isEmpty()) {
        	return runScores;
        }
        
        // Check for run3s
        for (int i = 0;i < 3; i++) {
        	for (int j = i + 1;j < 4; j++){
        		for (int k = j + 1;k < 5; k++) {
        			// Check 3 cards at a time
        			positions.add(i);
        			positions.add(j);
        			positions.add(k);
        			testHand = getNewHand(cards, positions);
                    if (isValidRun(testHand, testHand.getNumberOfCards())){
                        runScores.add(new ScoringInstance(rule + 3, testHand, 3));
                    }
                    positions.clear();
        		}
        	}
        }

        return runScores;
    }
    
    
    
    private Hand getNewHand(Hand cards, ArrayList<Integer> positions) {
    	// Create new hand using cards in certain positions from first hand
    	Hand resHand = new Hand(cards.getFirst().getDeck());
        for (Integer position : positions) {
        	resHand.insert(cards.get(position).clone(), false);
        }
        resHand.sort(Hand.SortType.POINTPRIORITY, false);
        return resHand;
    }
    
}
