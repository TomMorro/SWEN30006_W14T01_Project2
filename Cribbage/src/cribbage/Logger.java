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
            System.out.println(scoringInstance.getRule()+ ":" + scoringInstance.getScoringHand());
            score = scorer.update(scoringInstance, playerNumber);
            log.print("score,P" + playerNumber + "," + score + "," + scoringInstance.getPoints()+ ","
                    + scoringInstance.getRule());
            if(phase.equals("SHOW")){
                log.println("," + cribbageGame.canonical(scoringInstance.getScoringHand()));
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

    public void logGameStart(String player0, String player1){

        log.println("seed," + Cribbage.SEED);

        log.println(player0 + ",P0");
        log.println(player1 + ",P1");

    }
    
    public void logStarter(Card starter) {
    	log.println("starter," + cribbageGame.canonical(starter));
    }

    public void logDeal(Hand hand, int playerNumber){
        log.println("deal,P" + playerNumber +"," + cribbageGame.canonical(hand));
    }

    public void logDiscard(ArrayList<Card> discards, int playerNumber){
        log.println("discard,P" + playerNumber +"," + cribbageGame.canonical(discards));
    }
}
