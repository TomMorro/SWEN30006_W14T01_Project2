package cribbage.scoring;

public abstract class ValueScoringStrategy implements IScoringStrategy{
	
	protected int value = 2;
	protected final int fifteen = 15;
	protected final int thirtyone = 31;
}
