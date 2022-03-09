/*
 * This class creates card objects with their names
 * getCardType method returns the value of the card such that the indices correspond to the values:
 * 0 = TacoCat                  * 7 = Favor
 * 1 = Cattermelon              * 8 = Skip
 * 2 = BeardCat                 * 9 = Attack
 * 3 = RainbowRalphingCat       * 10 = Nope
 * 4 = HairyPotatoCat           * 11 = Defuse
 * 5 = SeeFuture                * 12 = ExplodingKitten
 * 6 = Shuffle
 */
package softwaredesign;

import java.util.Arrays;
import java.util.List;

public class Card {
    private String cardType;
    protected Integer cardID;

    public Card(String cardType) {
        setCardType(cardType);
    }

    public String getCardType() {
        return cardType;
    }

    /*
     * This method returns a list of names that are valid for Card objects
     */
    public static List<String> getValidCardTypes() {
        return Arrays.asList("TacoCat", "Cattermelon", "BeardCat", "RainbowRalphingCat", "HairyPotatoCat", "SeeFuture",
                "Shuffle", "Favor", "Skip", "Attack", "Nope", "Defuse", "ExplodingKitten");
    }

    /*
     * @return card types that have 4 of each in the deck
     */
    public static List<String> fourCardTypes() {
        return Arrays.asList("TacoCat", "Cattermelon", "BeardCat", "RainbowRalphingCat", "HairyPotatoCat",
                "Shuffle", "Favor", "Skip", "Attack");
    }

    /*
     * @return card types that have 5 of each in the deck
     */
    public static List<String> fiveCardTypes() {
        return Arrays.asList("SeeFuture", "Nope");
    }

    /*
     * This method will validate the argument and set the instance variable
     * @param cardType TacoCat, CaterMelon, BeardCat, RainbowRalphingCat, HairyPotatoCat, SeeFuture, Shuffle, Favor,
     *             Skip, Attack, Nope, Defuse, ExplodingKitten
     */
    public void setCardType(String cardType) {
        List<String> validCardTypes = getValidCardTypes();
        if (validCardTypes.contains(cardType)) {
            this.cardType = cardType;
        } else {
            throw new IllegalArgumentException("This is not a card. Valid names are: " + validCardTypes);
        }
    }

    public static String getCardID(List<String> validCardTypes, String cardType) {
        for (int i = 0; i < validCardTypes.size(); i++) {
            if (validCardTypes.get(i).equals(cardType))
                return i + "";
        }
        return "No valid ID";
    }

    public String toString() {
        return cardType;
    }

}
