package cribbage;

import cribbage.scoring.ScoringInstance;

import java.util.ArrayList;

public interface Subscriber {


    public void update(ArrayList<ScoringInstance> scoringInstanceList, int playerNumber);
}
