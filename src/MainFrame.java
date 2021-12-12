
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class MainFrame extends JFrame{
    private static final int SQUARE_SIZE = 100;
    private int rows = 8;
    private int columns = 6;
    private Container contentPane;
    private JPanel mainPanel;
    private JPanel toolPanel;
    /**
     * Constructor for objects of class QuiltFrame
     * @param r number of rows
     * @param c number of columns
     * @param random Random object from QuiltController
     */
    public MainFrame(String title){
        this.setTitle(title);
        //rng = App.getRNG(); //good practice to keep random objects with one seed
        
        mainPanel = new JPanel();
        toolPanel = new JPanel();
        
        contentPane = this.getContentPane();
        //contentPane.add(mainPanel);
        //contentPane.add(toolPanel,BorderLayout.WEST);
        
        refreshMain();
        
        //displayModule();
        displayMainMenu();
        //toolPanel.setLayout(new GridLayout(5,1));
    }
    
    /**
     * Gets the preferred size of the QuiltFrame
     * @return Dimension of rows*SQUARE_SIZE & columns*SQUARE_SIZE
     */
    public Dimension getPreferredSize(){
        Dimension dim = new Dimension(rows*SQUARE_SIZE, columns*SQUARE_SIZE);
        return dim;
    }
    
    /**
     * Applies default settings to the contentPane of the QuiltFrame
     */
    public void refreshMain(){
        contentPane.removeAll();
        //this.repaint();
        //mainPanel.setLayout(new GridLayout(rows, columns, 5, 5)); 
        //mainPanel.setBackground(Color.DARK_GRAY);
    }
    public void displayMainMenu(){
        refreshMain();
        MainMenuUI e = new MainMenuUI();
        contentPane.add(e, BorderLayout.CENTER);
        this.pack();
    }
    /**
     * Displays a module
     */
    public void displayModule(){
        refreshMain();
        ModuleUI e = new ModuleUI();
        contentPane.add(e.getSplitPane());
        this.pack();
    }
}
