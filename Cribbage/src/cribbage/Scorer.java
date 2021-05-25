package cribbage;


import ch.aplu.jgamegrid.*;
import ch.aplu.jcardgame.*;

import cribbage.scoring.ScoringInstance;

import java.awt.*;
import java.util.ArrayList;

import static cribbage.Cribbage.nPlayers;


public class Scorer implements Subscriber {

    private final int[] scores = new int[nPlayers];
    private final Location[] scoreLocations = {
            new Location(590, 25),
            new Location(590, 675)
    };
    private final Actor[] scoreActors = {null, null}; //, null, null };

    private Cribbage cribbageGame;


    public Scorer(Cribbage cribbageGame) {
        this.cribbageGame = cribbageGame;
        initScore();
    }

    @Override
    public void update(ArrayList<ScoringInstance> scoringInstanceList, int playerNumber) {

        int totalScore = 0;

        for(ScoringInstance curScoringInstance: scoringInstanceList){

            totalScore += curScoringInstance.getPoints();

        }
        scores[playerNumber] += totalScore;
        updateScore(playerNumber);
    }

    private void initScore() {
        for (int i = 0; i < nPlayers; i++) {
            scores[i] = 0;
            scoreActors[i] = new TextActor("0", Color.WHITE, cribbageGame.bgColor, cribbageGame.bigFont);
            Cribbage.cribbage.addActor(scoreActors[i], scoreLocations[i]);
        }
    }

    private void updateScore(int player) {

        Cribbage.cribbage.removeActor(scoreActors[player]);
        scoreActors[player] = new TextActor(String.valueOf(scores[player]), Color.WHITE, cribbageGame.bgColor, cribbageGame.bigFont);
        Cribbage.cribbage.addActor(scoreActors[player], scoreLocations[player]);
    }
}
