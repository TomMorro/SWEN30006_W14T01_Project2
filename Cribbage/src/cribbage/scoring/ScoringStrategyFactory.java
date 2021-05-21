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
    
    public IScoringStrategy getCompositeScoringStrategy(String phase) {
    	IScoringStrategy applicableStrategy = null;
    	switch(phase) {
    		case "play":
    			applicableStrategy = new CompositePlayScoringStrategy();
    			break;
    	}
    	return applicableStrategy;
    }
    
}
