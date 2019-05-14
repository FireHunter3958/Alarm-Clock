package clock;

import java.awt.event.WindowEvent;

public class Clock {
    
    public static void main(String[] args) {
        Model model = new Model();
        View view = new View(model);
        model.addObserver(view);
        Controller controller = new Controller(model, view);
        
        
        
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                View.createAndShowGUI();
            }
        });
    }
}
