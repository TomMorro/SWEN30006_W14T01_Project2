package cribbage.scoring;

public abstract class ValueScoringStrategy implements IScoringStrategy{
	
	protected final String fifteenRule = "FIFTEEN";
	protected final String thirtyoneRule = "THIRTYONE";
	protected int value = 2;
	protected final int fifteen = 15;
	protected final int thirtyone = 31;
}
