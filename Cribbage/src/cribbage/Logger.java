package cribbage;

import cribbage.Cribbage.Rank;
import cribbage.scoring.ScoringInstance;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import ch.aplu.jcardgame.*;

public class Logger{
    private PrintWriter log;
    private Scorer scorer;
    private Cribbage cribbageGame;

    public Logger(Cribbage cribbageGame){
        this.cribbageGame = cribbageGame;
        scorer = new Scorer(cribbageGame);
        try{
            log = new PrintWriter(new FileWriter("cribbage.log"));
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    // update scores
    public void update(ArrayList<ScoringInstance> scoringInstanceList, int playerNumber, String phase) {
        int score;
        for(ScoringInstance scoringInstance : scoringInstanceList){
            System.out.println(scoringInstance.getRule()+ ":" + scoringInstance.getScoringCards());
            score = scorer.update(scoringInstance, playerNumber);
            log.print("score,P" + playerNumber + "," + score + "," + scoringInstance.getPoints()+ ","
                    + scoringInstance.getRule());
            if(phase.equals("SHOW")){
                log.println(cribbageGame.canonical(scoringInstance.getScoringCards()));
            }
            else {
            	log.println();
            }

        }
    }

    public void close(){
        log.close();
    }
    
    public void logPlay(Hand segment, int playerNumber) {
    	int total = 0;
    	for (Card card : segment.getCardList()) {
    		Rank rank = (Cribbage.Rank) card.getRank();
    		total += rank.value;
    	}
    	log.println("play,P" + playerNumber + "," + total + "," + 
    			cribbageGame.canonical(segment.getLast()));
    }
}
