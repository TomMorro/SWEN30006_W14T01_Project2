package cribbage;

import cribbage.scoring.ScoringInstance;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

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
            if(scoringInstance.getScoringCards().size() >0){
                log.println(cribbageGame.canonical(scoringInstance.getScoringCards()));
            }

        }
    }

    public void close(){
        log.close();
    }
}
