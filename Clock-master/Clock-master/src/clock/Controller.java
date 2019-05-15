package clock;

import java.awt.event.*;
import javax.swing.Timer;

/**
 *This class is responsible for ensuring that Model().update() loops on a set timer
 * @author 07014975
 */
public class Controller {
    
    ActionListener listener;
    Timer timer;
    
    Model model;
    View view;
    
    /**
     *This method is responsible for ensuring that Model().update() loops on the timer set by variable timer
     * @param m is the value to call the Method class
     * @param v is the value to call the View class
     */
    public Controller(Model m, View v) {
        model = m;
        view = v;
        
        listener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.update();
            }
        };
        
        timer = new Timer(100, listener);
        timer.start();
    }
}