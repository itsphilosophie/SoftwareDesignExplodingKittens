package softwaredesign;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public abstract class Deck {
    private ArrayList<Card> deck;
    Random generator = new Random();

    /*
     * This is a 1 argument constructor that passes in a collection of Card objects
     */
    protected Deck(ArrayList<Card> deck) {
        this.deck = deck;
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public void setDeck(ArrayList<Card> deck) {
        this.deck = deck;
    }

    // This method will remove the top card of the deck
    public void removeCard() {
        try {
            if (!deck.isEmpty())
                deck.remove(0);
        } catch (Exception e) {
            throw new IllegalArgumentException("Deck is empty, can't remove.");
        }
    }

    // This method will remove the specific card of the deck
    public void removeSpecificCard(int cardIndex) {
        try {
            if (!deck.isEmpty())
                deck.remove(cardIndex);
        } catch (Exception e) {
            throw new IllegalArgumentException("Deck is empty, can't remove.");
        }
    }

    // This method will return the size of the deck
    public int getDeckSize() {
        return deck.size();
    }

    // This method will shuffle the deck
    public void shuffleDeck() {
        try {
            Collections.shuffle(deck);
        } catch (Exception e) {
            throw new IllegalArgumentException("Deck is empty, can't shuffle.");
        }
    }

    public boolean isEmpty() {
        return deck.isEmpty();
    }

    public void addCardDeck(int index, Card card) {
        if (!deck.isEmpty()) {
            deck.add(index, card);
        } else {
            addCardDeck(deck.size() - 1, card);
        }
    }

    /*
     * This method deal a hand for a given player after the talon is initialized.
     * A random card (that isn't an exploding kitten nor defuse card) will be added to hand and removed from talon
     * then add a defuse card in the end
     * @param player is the player for which the hand is being initialized
     */
    public void deal(Player player) {
        List<String> validCardTypes = Card.getValidCardTypes();
        for (int i = 0; i < 7; ) {
            int randomCardIndex = generator.nextInt(validCardTypes.size());
            String cardValue = validCardTypes.get(randomCardIndex);
            if (!cardValue.equals("ExplodingKitten") && !cardValue.equals("Defuse")) {
                player.getPlayerHand().addCardHand(new Card(cardValue));
                removeSpecificCard(randomCardIndex);
                i++;
            }
        }
        player.getPlayerHand().addCardHand(new Card("Defuse"));
    }
}

class Talon extends Deck {
    private static ArrayList<Card> talonPile;
    private Image backOfCardImage;
    private static final int TOTAL_DEFUSE_CARDS = 6;

    protected Talon(int numPlayers) {
        super(talonPile);

        List<String> fourCards = Card.fourCardTypes();
        List<String> fiveCards = Card.fiveCardTypes();

        for (String fourCard : fourCards) {
            // add 4 of each card from the fourCards collection to the deck
            for (int i = 0; i < 4; i++) {
                talonPile.add(new Card(fourCard));
            }
        }
        for (String fiveCard : fiveCards) {
            // add 5 of each card from the fiveCards collection to the deck
            for (int i = 0; i < 5; i++) {
                talonPile.add(new Card(fiveCard));
            }
        }

        // if number players < 4 then only add 2 defuse cards in the deck, else add 6-n cards
        if (numPlayers < 4) {
            talonPile.add(new Card("Defuse"));
            talonPile.add(new Card("Defuse"));
        } else {
            int remainDefuse = TOTAL_DEFUSE_CARDS - numPlayers;
            if (remainDefuse > 1) {
                // add remaining defuse cards to the deck
                for (int i = 0; i < remainDefuse; i++) {
                    talonPile.add(new Card("Defuse"));
                }
            }
        }

        // add exploding kitten cards based on number of players
        for (int i = 0; i < (numPlayers - 1); i++) {
            talonPile.add(new Card("ExplodingKitten"));
        }

        backOfCardImage = new Image("resources/softwaredesign/softwaredesign.GUI/backCard.jpg");
    }

    public static void setTalonPile(ArrayList<Card> talonPile) {
        Talon.talonPile = talonPile;
    }

    public ArrayList<Card> getTalonPile() {
        return talonPile;
    }

    public Image getBackOfCardImage() {
        return backOfCardImage;
    }

    public void setBackOfCardImage(Image backOfCardImage) {
        this.backOfCardImage = backOfCardImage;
    }

    // This method will "deal" the top card off the talon
    public Card drawTopCard() {
        if (!talonPile.isEmpty()) {
            return talonPile.remove(0);
        } else {
            throw new IllegalArgumentException("Talon pile is empty");
        }
    }

    /*
     * This method will put the exploding kitten back into a specific place in the talon after defuse is used
     * if the index is bigger than the talon pile the card gets added to the end
     * @param card is the specific exploding kitten
     * @param index is the place in the talon where the player chose to add the card
     */
    public void hideKitten(Card card, int index) {
        if (index <= talonPile.size()) {
            addCardDeck(index, card);
        } else {
            addCardDeck(talonPile.size() - 1, card);
        }
    }

    /*
     * This method will show the top 3 card of the talon
     * if there's less than 3 cards it shows the remaining cards
     */
    public static ArrayList<Card> seeTop3Cards() {
        ArrayList<Card> top3Cards = new ArrayList<>();
        if (talonPile.size() >= 3) {
            top3Cards.add(talonPile.get(0));
            top3Cards.add(talonPile.get(1));
            top3Cards.add(talonPile.get(2));
            return top3Cards;
        } else if (talonPile.size() >= 2) {
            top3Cards.add(talonPile.get(0));
            top3Cards.add(talonPile.get(1));
            return top3Cards;
        } else if (!talonPile.isEmpty()) {
            top3Cards.add(talonPile.get(0));
            return top3Cards;
        } else {
            throw new IllegalArgumentException("Talon pile doesn't contain any cards to view");
        }
    }

}

class DiscardPile extends Deck {
    private static ArrayList<Card> discard;

    protected DiscardPile() {
        super(discard);
    }

    public static ArrayList<Card> getDiscardPile() {
        return discard;
    }

    public static void setDiscardPile(ArrayList<Card> discard) {
        DiscardPile.discard = discard;
    }

    public void removeAllCards() {
        discard.clear();
    }

    public ArrayList<String> seeAllCards() {
        if (!discard.isEmpty()) {
            ArrayList<String> allCards = new ArrayList();
            for (Card card : discard) {
                allCards.add(card.toString());
            }
            return allCards;
        } else {
            throw new IllegalArgumentException("Discard pile is empty");
        }
    }

    /*
     * Method that allows the player to pick a card from the talon
     * It returns the card that they picked and removes it from the discard pile
     * @param card is the specific card the player picked
     */
    public Card pickCard(Card card) {
        if (discard.contains(card)) {
            return discard.remove(discard.indexOf(card));
        } else {
            throw new IllegalArgumentException("This card is not in the discard pile.");
        }
    }
}