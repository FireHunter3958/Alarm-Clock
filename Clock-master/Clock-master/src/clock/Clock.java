package clock;

import java.awt.event.WindowEvent;

/**
 *This class is the main class, all other classes are run from here.
 * @author 07014975
 */
public class Clock {
    
    /**
     *This method is responsible for starting the other classes 
     * @param args
     */
    public static void main(String[] args) {
        Model model = new Model();
        View view = new View(model);
        model.addObserver(view);
        Controller controller = new Controller(model, view);
        
        
        /*
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                View.createAndShowGUI();
            }
        });
*/
    }
}
