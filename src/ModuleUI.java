
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

//SplitPaneDemo itself is not a visible component.
public class ModuleUI extends Container implements ListSelectionListener {
    private CardUI cardUIHandler;
    private JList list;
    private JSplitPane splitPane;
    private CardSet[] cardsets;
    private String[] cardsetnames;
    public ModuleUI() {

        populateLists();
        
       
        JScrollPane listScrollPane = new JScrollPane(list);
        cardUIHandler = new CardUI();
        //cardUIHandler.setFont(cardUIHandler.getFont().deriveFont(Font.ITALIC));
        //card.setHorizontalAlignment(JLabel.CENTER);
        
        //JScrollPane pictureScrollPane = new JScrollPane(cardUIHandler);

        //Create a split pane with the two scroll panes in it.
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                                   listScrollPane, cardUIHandler); //to make cardUIHandler scrollable, change cardUIHandler to pictureScrollPane
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(150);

        //Provide minimum sizes for the two components in the split pane.
        Dimension minimumSize = new Dimension(100, 50);
        listScrollPane.setMinimumSize(minimumSize);
        cardUIHandler.setMinimumSize(minimumSize);
        //pictureScrollPane.setMinimumSize(minimumSize);

        //Provide a preferred size for the split pane.
        splitPane.setPreferredSize(new Dimension(400, 200));
        //updateCardUI(CardSetNames[list.getSelectedIndex()]);
        updateCardUI(cardsets[list.getSelectedIndex()]);
    }
    
    //Listens to the list
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()){
            JList list = (JList)e.getSource();
            updateCardUI(cardsets[list.getSelectedIndex()]);
        }
    }
    
    //Changes or refreshes CardUI
    public void updateCardUI (CardSet card) {
        //doing this should reset visited within the selected card
        //and also pass the selected card on to cardUIHandler
        cardUIHandler.displayNewCardSet(card);
    }
    //Used by SplitPaneDemo2
    public JList getCardSetList() {
        return list;
    }

    public JSplitPane getSplitPane() {
        return splitPane;
    }
    
    /**
     * Uses parallel lists to populate the JList column for card selection. Works because we can get the selected index from the JList and know where the actual card is.
     */
    private void populateLists(){
        cardsets = new CardSet[App.activeModule.getCardSets().size()];
        cardsetnames = new String[cardsets.length];
        int i = 0;
        for (CardSet e : App.activeModule.getCardSets()){
            cardsets[i] = e;
            cardsetnames[i] = e.getName();
            i++;
        }
        list = new JList(cardsetnames);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        
    }

}
