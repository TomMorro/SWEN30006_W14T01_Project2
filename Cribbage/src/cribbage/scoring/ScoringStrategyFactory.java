package cribbage.scoring;

public class ScoringStrategyFactory {
    private static ScoringStrategyFactory instance = null;

    private ScoringStrategyFactory(){

    }

    public static ScoringStrategyFactory getInstance(){
        if(instance==null){
            instance=new ScoringStrategyFactory();
        }
        return instance;
    }

    public RunScoringStrategy getRunScoringStrategy(int type){
        return new RunScoringStrategy(type);
    }

    public PairScoringStrategy getPairScoringStrategy(int type){
        return new PairScoringStrategy(type);
    }

    
}
