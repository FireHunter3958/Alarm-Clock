package clock;

import java.awt.*;
import javax.swing.*;
import java.util.Observer;
import java.util.Observable;

import java.util.PriorityQueue;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import javax.swing.JMenuBar;
import javax.swing.KeyStroke;

import java.util.logging.Level;
import java.util.logging.Logger;

//https://www.javaxp.com/2012/05/java-simple-icalendar-ical4j-example.html

/**
 *This class handles the GUI, and all related functions. Any extra functionality is linked within the Model class
 * @author 07014975
 */
public class View implements Observer {
    ClockPanel panel;
    JTextArea output;
    JScrollPane scrollPane;
    String newline = "\n";
    
    //https://stackoverflow.com/questions/683041/how-do-i-use-a-priorityqueue
    Alarm alarm =  new Alarm();
    String editValueString;
    
    Model Model = new Model();
    PriorityQueue<String> alarmView = Model.alarmView;

    /**
     *Basic GUI frame is created here.
     * GUI frame consists of buttons, an alarm clock and a menu system.
     * Section of code dedicated to prompting user to save on close included.
     * @param model is required for functionality
     */
    public View(final Model model) {
        final JFrame frame = new JFrame();
        panel = new ClockPanel(model);
        JMenuBar menuBar;
        JMenu menu;

        
        frame.setTitle("Java Clock");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        //Create the menu bar.
        menuBar = new JMenuBar();

        //Build the first menu.
        menu = new JMenu("Menu");
        menu.setMnemonic(KeyEvent.VK_A);
        menu.getAccessibleContext().setAccessibleDescription(
                "The only menu in this program that has menu items");
        menuBar.add(menu);
        
        JMenuItem menuItemV2;
        menuItemV2 = new JMenuItem(new FileAlarm());
        
        menuItemV2.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_1, ActionEvent.ALT_MASK));
        menuItemV2.getAccessibleContext().setAccessibleDescription(
                "This doesn't really do anything");
        
        menu.add(menuItemV2);
        
        JMenuItem menuItemV3;
        menuItemV3 = new JMenuItem(new ViewAlarm());
        
        menuItemV3.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_1, ActionEvent.ALT_MASK));
        menuItemV3.getAccessibleContext().setAccessibleDescription(
                "This doesn't really do anything");
        
        menu.add(menuItemV3);
        
        JMenuItem menuSave;
        menuSave = new JMenuItem(new ButtonLoad());
       
        menuSave.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_1, ActionEvent.ALT_MASK));
        menuSave.getAccessibleContext().setAccessibleDescription(
                "This doesn't really do anything");
        
        menu.add(menuSave);
        
        
        frame.setJMenuBar(menuBar);
        
        
        Container pane = frame.getContentPane();
        
        JButton button = new JButton("Button 1 (PAGE_START)");
       
        panel.setPreferredSize(new Dimension(200, 200));
        pane.add(panel, BorderLayout.CENTER);
         
        button = new JButton(new AddAlarm());
        pane.add(button, BorderLayout.LINE_START);
        
        
        output = new JTextArea(5, 30);
        output.setEditable(false);
        JScrollPane paneScroll = new JScrollPane(output);
        
        button = new JButton(new ViewAlarm());
        pane.add(button, BorderLayout.PAGE_START);
        

        //Add the text area to the content pane.
        pane.add(paneScroll, BorderLayout.PAGE_END);
        
         
        button = new JButton(new EditAlarm());
        pane.add(button, BorderLayout.LINE_END);
        
        pane.add(menuBar, BorderLayout.PAGE_START);
        
        // End of borderlayout code
        
        frame.pack();
        frame.setVisible(true);
        
        //https://stackoverflow.com/questions/9093448/how-to-capture-a-jframes-close-button-click-event
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(frame,
                        "Save Alarms Before Exit?", "Save Alarms?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
                    Model.StringWrite("AlarmList");
                    System.exit(0);
                    
                }
            }
});
    }
    
    /**
     *This method is responsible for repainting the GUI.
     * @param o is the observable
     * @param arg is the object
     */
    public void update(Observable o, Object arg) {
        panel.repaint();
    }
    
    /**
     *This method creates an output field with a scrollbar. User feedback will be displayed here.
     * @return contentPane this is the variable that is responsible for the output field below the alarm.
     */
    public Container createContentPane() {
        //Create the content-pane-to-be.
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setOpaque(true);

        //Create a scrolled text area.
        output = new JTextArea(5, 30);
        output.setEditable(false);
        scrollPane = new JScrollPane(output);

        //Add the text area to the content pane.
        contentPane.add(scrollPane, BorderLayout.CENTER);

        return contentPane;
    }
    
    /**
     *This method is responsible for manually adding an alarm to the queue
     */
    protected class AddAlarm extends AbstractAction {

        /**
         *This section of code is responsible for creating the method, and providing
         * a title for the button.
         */
        public AddAlarm() {
        super ("Add Alarm");
                }

        /**
         *This section of code sends the variable v to the Model class, where the 
         * alarm value is added to the queue.
         * The alarm queue is then displayed in the output field.
         * @param v is the variable used to trigger the ActionEvent
         * within the Model class.
         */
        public void actionPerformed(ActionEvent v) {
            Model.actionPerformed(v);
            output.append("\nQueue value is: " + alarmView);
            //output.append("\n");
            
          
        }
    }
    
    /**
     *This method is responsible for creating a list of stored alarms, along
     * with edit/delete buttons
     */
    protected class ViewAlarm extends AbstractAction {

        /**
         *This section of code is responsible for creating the method
         * and providing a title for the button.
         */
        public ViewAlarm() {
        super ("View Alarms");
                }

        /**
         *This section of code sends the variable v to the Model class, where the
         * stored alarms are retrieved and added to the new frame, an edit and a
         * delete button are generated under each alarm.
         * @param v is the variable used to trigger the ActionEvent in the Model
         * class.
         */
        public void actionPerformed(ActionEvent v) {
            Model.ViewAlarm(v);
            String queueView = String.valueOf(alarmView);
            //output.append(alarmView);
            output.append("Current queue is: " + alarmView);
        }
    }
    
    /**
     *This method is responsible for saving stored alarms to external files.
     */
    protected class FileAlarm extends AbstractAction {

        /**
         *This section of code is responsible for creating the method and 
         * providing a title for the button.
         */
        public FileAlarm() {
        super ("Add Alarm to File");
                }
        //@Override

        /**
         *This section of code sends the variable v to the Model class, where the
         * stored alarms are retrieved and saved into an external file.
         * @param r is the variable used to trigger the ActionEvent within the
         * Model class.
         */
        @Override
        public void actionPerformed(ActionEvent r)  {
           // Model.write("AlarmList");
            Model.StringWrite("AlarmList");

        }
    }
    
    /**
     *This method is responsible for loading saved alarms from an external file.
     */
    protected class ButtonLoad extends AbstractAction {

        /**
         *This section of code is responsible for creating the method and 
         * providing a title for the button.
         */
        public ButtonLoad() {
            super ("Load Alarms from File");
        }

        /**
         *This section of code sends the variable s to the Model class, where the
         * external file is retrieved, and the stored alarms within the external file
         * are saved into the alarm queue.
         * @param s is the variable used to trigger the ActionEvent within the
         * Model class.
         */
        public void actionPerformed(ActionEvent s) {
            try {
                //Model.Load("AlarmList.ics");
                Model.StringLoad("AlarmList.ics");
            } catch (IOException ex) {
                Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
            } 
            } 
    }
    
    //https://stackoverflow.com/questions/31238492/writing-ics-ical-file-using-java
    
    /**
     *This method is responsible for creating an easier to access version of the
     * view alarms button.
     */
    protected class EditAlarm extends AbstractAction {

        /**
         *This section of code is responsible for creating the method and for
         * providing a title for the button.
         */
        public EditAlarm() {
        super ("View Alarms");
                }

        /**
         *This section of code sends the variable n to the Model class, where the
         * stored alarms are retrieved and added to the new frame, an edit and a
         * delete button are generated under each alarm.
         * @param n is the variable used to trigger the ActionEvent in the Model
         * class.
         */
        public void actionPerformed(ActionEvent n) {
            Model.ViewAlarm(n);
            output.append("\n");
            output.append("Queue value is: " + alarmView);
            
        }
    }
}
