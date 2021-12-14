import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * ModuleLoader
 * Handles loading modules and compiling lists of said modules.
 */
public class ModuleLoader {
    
    /**
     * 
     * Internal ModuleReader Class
     * Reads from a mod folder and populates a Module
     * 
     * Each file is a category; ? delineates a new card
     * " : " separates a key value pair for each card - but can also be used to give answers to questions - depends on type indicated in 2nd line from ?
     */
    public class ModuleReader {
        // for each file in folder - module
        //// read each file - category
        ////// read each card - card
        String moduleInput;
        Module moduleOutput;

        /**
         * Constructor for ModReader class
         * @param m Module name
         */
        public ModuleReader(String m){
            moduleOutput = new Module(m);
            moduleInput = m;
        }

        public void assembleCardSet(String name){
            CardSet set = moduleOutput.addCardSet();
            set.setName(name);

            //read through list - goal: add cards to cardset
            //when ? detected, start new card
            try {
                File cardSetFile = new File("./modules/"+moduleInput+"/"+name);
                Scanner reader = new Scanner(cardSetFile);
                boolean makeNewCard = false;
                Card current = null;
                while (reader.hasNextLine()) {
                    String line = reader.nextLine();

                    //If ?, creates a new card and adds to same card until ? encountered again - where a new card will be created - until the end of the document.
                    if (line.equals("?")){
                        current = new Card();
                        current.setTitle(reader.nextLine());
                        current.setType(reader.nextLine());
                        set.addCard(current);
                        continue;
                    }else{
                        String[] arr = line.split(" : ", 2);
                        current.setEntry(arr[0], arr[1]);
                    }
            }   
            reader.close();
            } catch (FileNotFoundException e) {
                System.out.println("Error when attempting to read file: " + name);
                e.printStackTrace();
            }
        }

        public void listFiles(File folder) {
            for (File fileEntry : folder.listFiles()) {
                if (!fileEntry.isDirectory()) {
                    assembleCardSet(fileEntry.getName());
                }
            }
        }
        
        public void scan(){
            moduleOutput = new Module(moduleInput);
            final File folder = new File("./modules/" + moduleInput);
            listFiles(folder);
        }

        public Module getModule(){
            return moduleOutput;
        }
    }
    
    ArrayList<Module> moduleList = new ArrayList<Module>();


    /**
     * Default Constructor for ModuleLoader
     */
    public ModuleLoader(){

    }

    public Module scanModule(String m){
        ModuleReader r = new ModuleReader(m);
        r.scan();
        return r.getModule();
    }
    public void addModule(Module e){
        moduleList.add(e);
    }
    public ArrayList<Module> getModules(){
        return moduleList;
    }

    public void scanModules(){
        File folder = new File("./modules/");
        for (File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                addModule(scanModule(fileEntry.getName()));
            }
        }
    }
}
