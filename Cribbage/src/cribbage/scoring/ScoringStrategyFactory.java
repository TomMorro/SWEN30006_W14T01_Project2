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
    		case "PLAY":
    			applicableStrategy = new CompositePlayScoringStrategy();
    			break;
            case "SHOW":
                applicableStrategy = new CompositeShowScoringStrategy();
                break;
    	}
    	return applicableStrategy;
    }
    
    public IScoringStrategy getStarterScoringStrategy() {
    	return new StarterScoringStrategy();
    }
//
    public IScoringStrategy getGoScoringStrategy() {
        return new GoScoringStrategy();
    }
    
}
