import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * App - The driver class for Glotto - the linguistics center & language proficiency trainer
 * 
 * @author Gavin Osborn
 * @version 12.11.2021
 */
public class App {

    public static MainFrame frame;

    public static Module activeModule;

    private ModuleLoader loader;

    public App(){
        loader = new ModuleLoader();
        loader.scanModules();
        activeModule = loader.getModules().get(0);
    }

    private void createGUI() {
        frame = new MainFrame("Glotto");
        ((JComponent) frame.getContentPane()).setBorder(BorderFactory.createLineBorder(Color.BLACK));
        Container contentPane = frame.getContentPane();
        
        //create primary menu bar
        JMenuBar menubar = new JMenuBar();
        frame.setJMenuBar(menubar);
        //create "Menu" dropdown on menu bar
        JMenu fileMenu = new JMenu("Menu");
        JMenu moduleMenu = new JMenu("Modules"); //menu for patterns
        JMenu toolbar = new JMenu("Tools"); //menu for tools
        menubar.add(fileMenu);
        menubar.add(moduleMenu);
        menubar.add(toolbar);
        //create menu dropdown buttons
        JMenuItem quitItem = new JMenuItem("Exit");
        JMenuItem mainItem = new JMenuItem("Main");
        JMenuItem aboutItem  = new JMenuItem("About");
        fileMenu.add(mainItem);
        fileMenu.add(aboutItem);
        fileMenu.add(quitItem);

        mainItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                frame.displayMainMenu();
            }
        });

        aboutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                JDialog z = new JDialog(frame, "About");
                JLabel l = new JLabel("<html>Glotto: The Modular Flashcard Program<br>Copyright 2021 Gavin Osborn<br>github.com/gavosb | gavosb.com</html>", SwingConstants.CENTER);
                z.setLayout(new BorderLayout());
                z.add(l, BorderLayout.CENTER);
                z.setSize((int)frame.getSize().getWidth()/2, (int)frame.getSize().getHeight()/2);
                z.setLocationRelativeTo(frame);
                ((JComponent) z.getContentPane()).setBorder(BorderFactory.createLineBorder(Color.BLACK));
                z.setVisible(true);
            }
        });

        quitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                frame.dispose();
                System.exit(0);
            }
        });
        //create module dropdown buttons
        for (Module s : loader.getModules()){
            JMenuItem e = new JMenuItem(s.getName());
            moduleMenu.add(e);
            e.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){
                    JMenuItem z = (JMenuItem) e.getSource();
                    activeModule = loader.getModules().get(moduleMenu.getComponentZOrder(z)+1); //idea is that the zOrder should be the same as the index in the moduleList
                    frame.displayModule();
                }
            });
        }
        frame.pack(); //update frame layout and set to visible
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public static void main(String[] args) throws Exception {

        App app = new App();
        app.createGUI();
    }
}
