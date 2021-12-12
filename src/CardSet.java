import java.util.ArrayList;

/**
 * Holds all data pertaining to the CardSet, including the ArrayList of cards
 * and methods for dealing with a set of Card objects.
 */
public class CardSet {
    ArrayList<Card> cards = new ArrayList<Card>();
    String name = "";

    /**
     * Default Constructor for CardSet class
     */
    public CardSet() {

    }

    public void addCard(Card current) {
        cards.add(current);
    }

    public Card getUnvisitedCard() {
        for (Card e : cards) {
            if (!e.getVisited()) {
                return e;
            }
        }
        return null;
    }

    public void unMarkAll(){
        for (Card e : cards) {
            e.setUnVisited();
        }
    }

    public void setName(String m) {
        name = m;
    }

    public String getName() {
        return name;
    }

}