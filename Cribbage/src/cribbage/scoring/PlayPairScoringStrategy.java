//package cribbage.scoring;
//
//import ch.aplu.jcardgame.Card;
//import ch.aplu.jcardgame.Hand;
//
//import java.util.ArrayList;
//
//public class PlayPairScoringStrategy extends PairScoringStrategy{
//
//    public boolean isValidPair(Hand cards, Hand pair){
//        ArrayList<Card> cardList = cards.getCardList();
//
//        // iterate through cards
//        for(int i = 0; i < cardList.size(); i++){
//            // if this card is a part of pair, look forward to see if the next cards are also
//            // in pair
//            if(pair.contains(cardList.get(i))){
//                for (int j = i; j < i + pair.getNumberOfCards() && j < cardList.size();j++){
//                    if(!pair.contains(cardList.get(j))){
//                        return false;
//                    }
//                }
//                return true;
//            }
//        }
//
//        return false;
//    }
//
//    @Override
//    public int getPoints(Hand cards) {
//        ArrayList<Hand[]> allPairs = getAllPairs(cards);
//        Card lastCard = cards.getLast();
//        if(allPairs.size()>0){
//            // works backward since we want to highest score to be returned
//            for (int i = allPairs.size()-1; i > -1; i--) {
//                // iterate through each list of pairs
//                for(Hand pair : allPairs.get(i)){
//                    // if the lastCard forms a pair, then we return
//                    if(pair.contains(lastCard) && isValidPair(cards, pair)){
//                        return pair.getNumberOfCards() * pair.getNumberOfCards() - pair.getNumberOfCards();
//                    }
//                }
//            }
//        }
//        return 0;
//    }
//}
