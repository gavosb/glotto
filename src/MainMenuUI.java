import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;

public class MainMenuUI extends JPanel {
    JLabel text = new JLabel("Select a module to begin");

    public MainMenuUI(){
        this.add(text);
    }
    
}