
import java.util.ArrayList;
import java.util.HashMap; 


/**
 * Holds all data for a specific module. This can include an arbitrary number of categories each with their own CardSet.
 * The Card and ModuleUI classes interact with this. Card displays "flashcards" of each category stored herein. ModuleUI displays cards within itself.
 */
public class Module {
    private ArrayList<CardSet> cardsets = new ArrayList<CardSet>();
    private String name;
    public Module(String n){
        name = n;
    }

    public void addCardSet(CardSet e){
        cardsets.add(e);
    }

    public CardSet addCardSet(){
        CardSet set = new CardSet();
        addCardSet(set);
        return set;
    }

    public ArrayList<CardSet> getCardSets(){
        return cardsets;
    }

    public String getName(){
        return name;
    }
}
