import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

/**
 * Class that handles the GUI for all CardSet class instances. NOT tied to a particular CardSet.
 * Typically displayed as a component of the ModuleUI; has one main panel with a subpanel used as a container for items arranged as indicated by the Card type.
 */
public class CardUI extends JPanel{
    private JPanel buttonPanel;
    private JPanel typePanel;
    private CardSet cardSet;
    private Card selectedCard;
    /**
     * Default Constructor for CardUI
     */
    public CardUI(){
        
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
        typePanel = new JPanel();
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        //typePanel.setLayout(new GridLayout());
        this.add(typePanel);
        this.add(Box.createRigidArea(new Dimension(0,5)));
        this.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
    }

    /**
     * Refreshes the display with a new CardSet
     * @param c
     */
    public void displayNewCardSet(CardSet c){
        this.removeAll();
        buttonPanel.removeAll();
        cardSet = c;
        cardSet.unMarkAll();
        changeCard();
        JButton randomizeButton = new JButton("Randomize");
        JButton nextButton = new JButton("Next");
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.add(randomizeButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonPanel.add(nextButton);
        nextButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JButton btn =  (JButton) e.getSource();
                changeCard();
            }
        });
        this.add(buttonPanel, BorderLayout.SOUTH);
        repaint();
        revalidate();
    }

    public void changeCard(){
        try{
            selectedCard = cardSet.getUnvisitedCard();
            switch (selectedCard.getType()) {
                case "table": 
                    displayTableCard();
                    break;
                default:
                    System.out.println("Card type not supported.");
                    break;
            }
            typePanel.repaint();
            typePanel.revalidate(); 
        }catch (NullPointerException e){
            System.out.println("Out of Cards");
            cardSet.unMarkAll();
        }
        
    }

    /**
     * Displays a table with a certain number of entries
     * Gets data for display from selectedCard
     */
    private void displayTableCard(){
        typePanel.removeAll();
        selectedCard.setVisited();
        //use selectedCard.getEntryCount() for # of entries
        //add # of entries of label:text field entries to typePanel
        //check answers by keeping count of how many are correct?
        //have correct() function that determines closeness to true answer by selected guidelines?
        HashMap<String, String> entries = selectedCard.getEntries();

        typePanel.add(new JLabel(selectedCard.getTitle()));
        JPanel elementContainer = new JPanel();

        elementContainer.setLayout(new GridBagLayout()); //maybe entryCount/2 or something
        //elementContainer.setPreferredSize(new Dimension(800, 200));
        //elementContainer.setAlignmentX(LEFT_ALIGNMENT);
        for (String s : entries.keySet()){
            
            JTextField temp = new JTextField(5);
            JLabel tempLabel = new JLabel(s);
            elementContainer.add(tempLabel);
            elementContainer.add(temp);
            temp.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JTextField btn =  (JTextField) e.getSource();
                    if (btn.getText().equals(entries.get(s))){
                        System.out.println("right");
                    }else{
                        System.out.println("wrong");
                    }
                    
                }
            });
            
        }
        typePanel.add(elementContainer);
        this.add(typePanel);
    }


    /**
     * Gets the preferred size of the QuiltFrame
     * @return Dimension of rows*SQUARE_SIZE & columns*SQUARE_SIZE
     */
    public Dimension getPreferredSize(){
        Dimension dim = new Dimension(100, 100);
        return dim;
    }
}