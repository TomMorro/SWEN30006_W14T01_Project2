package cribbage.scoring;

import ch.aplu.jcardgame.Hand;
import ch.aplu.jcardgame.Card;
import java.util.ArrayList;

import cribbage.Cribbage;
import cribbage.Cribbage.Rank;

//public class ShowValueScoringStrategy extends ValueScoringStrategy{
//
//	@Override
//	public int getPoints(Hand cards) {
//		return checkFifteens(cards, cards.getNumberOfCards());
//	}
//
//	private int checkFifteens(Hand cards, int length) {
//		int total = 0, subtotal = 0, counter, i;
//		int powerSetSize = (int) Math.pow(2, length);
//		ArrayList<Card> cardList = cards.getCardList();
//
//		for (counter = 0; counter < powerSetSize; counter++) {
//			for (i = 0; i < length; i++) {
//				if (String.format("%5s", Integer.toBinaryString(counter)).replace(" ", "0").charAt(i) == '1') {
//					Rank rank = (Cribbage.Rank) cardList.get(i).getRank();
//					subtotal += rank.value;
//				}
//			}
//
//			if (subtotal == fifteen) {
//				total += value;
//			}
//			subtotal = 0;
//		}
//
//		return total;
//	}
//}
