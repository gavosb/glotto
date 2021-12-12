import java.util.HashMap;

/**
 * Holds all data pertaining to a card, such as whether the card has been
 * visited, its question:answer pairs, and its type (for GUI representation).
 */
public class Card {

    //temp note: To get the UI working here, you'll probably have to figure out either layout managers or how ModuleUI is adding its components. how come there is no this.add() usage over there?
    //after this we can add listeners and actual functionality, then populate the mod dropdown before setting up the main menu - then it'll be ready for github with postponed plans for a mod & text editor
    HashMap<String, String> entries = new HashMap<String, String>(); // entries hold all key:value pairs for the card - can be a simple flash card or a multi-answer table
    String type = "flash";
    String title = "untitled";
    boolean visited = false;
    int entryCount = 0;
    /**
     * Default Constructor for Card class
     */
    public Card() {

    }

    public void setTitle(String s) {
        title = s;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public void setType(String t) {
        type = t;
    }

    public void setVisited() {
        visited = true;
    }

    public void setUnVisited() {
        visited = false;
    }

    public boolean getVisited() {
        return visited;
    }

    public HashMap<String, String> getEntries() {
        return entries;
    }

    public void setEntry(String x, String y) {
        entries.put(x, y);
        entryCount++;
    }

    public int getEntryCount(){
        return entryCount;
    }
}