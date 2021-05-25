//package cribbage.scoring;
//
//import ch.aplu.jcardgame.Hand;
//import cribbage.Cribbage;
//import cribbage.Cribbage.Rank;
//import ch.aplu.jcardgame.Card;
//
//public class PlayValueScoringStrategy extends ValueScoringStrategy{
//
//	@Override
//	public int getPoints(Hand cards) {
//		int total = 0;
//
//		for (Card card : cards.getCardList()) {
//			Rank rank = (Cribbage.Rank) card.getRank();
//			total += rank.value;
//		}
//		if (total == fifteen || total == thirtyone) {
//			return value;
//		}
//		return 0;
//	}
//
//}
