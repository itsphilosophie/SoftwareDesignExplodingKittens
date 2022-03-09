package softwaredesign;

import java.util.ArrayList;

// PLACEHOLDER

public class HandCards {
    public int size() {
        return hand.size();
    }

    public Card getCard(int i) {
        return hand.get(i);
    }
    private ArrayList<Card> hand;

    public ArrayList<Card> getHand() {
        return hand;
    }

    public boolean isEmpty() {
        return hand.isEmpty();
    }

    public void addCardHand(Card card) {
        hand.add(card);
    }

    public void removeCard(Card card) {
        hand.remove(card);
    }

    public boolean hasCard(String cardName) {
        for(Card card: hand){
            if (card.getCardType().equals(cardName)){
                return true;
            }
        }
        return false;
    }
    public boolean hasCard(Card card) {
        return hand.contains(card);
    }

    public void drawCard() {
        GameState.drawPile.drawTopCard();
    }

}
