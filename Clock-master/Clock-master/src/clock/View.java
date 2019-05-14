package clock;

import java.awt.*;
import javax.swing.*;
import java.util.Observer;
import java.util.Observable;

import java.util.Comparator;
import java.util.PriorityQueue;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.ButtonGroup;
import javax.swing.JMenuBar;
import javax.swing.KeyStroke;
import javax.swing.ImageIcon;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

//https://www.javaxp.com/2012/05/java-simple-icalendar-ical4j-example.html

/**
 *
 * @author FireHunter3958
 */
public class View implements Observer, ActionListener, ItemListener {
    ClockPanel panel;
    //ClockPanel pane2;
    JTextArea output;
    JScrollPane scrollPane;
    String newline = "\n";
    
    //https://stackoverflow.com/questions/683041/how-do-i-use-a-priorityqueue
    //Comparable<Integer> comparator = new IntegerComparator();
    //PriorityQueue<Integer> queue = new PriorityQueue<Integer>(8, comparator);
    //PriorityQueue<Integer> alarmQueue = new PriorityQueue<Integer>();
    Alarm alarm =  new Alarm();
    String editValueString;
    
    Model Model = new Model();
    PriorityQueue<Integer> alarmQueue = Model.alarmQueue;
    PriorityQueue<String> alarmView = Model.alarmView;

    /**
     *
     * @param model
     */
    public View(final Model model) {
        final JFrame frame = new JFrame();
        panel = new ClockPanel(model);
        //pane2 = new ClockPanel(model);
        JMenuBar menuBar;
        JMenu menu, submenu;
        JMenuItem menuItem;
        JRadioButtonMenuItem rbMenuItem;
        JCheckBoxMenuItem cbMenuItem;

        //frame.setContentPane(panel);
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
        //menuItem.setMnemonic(KeyEvent.VK_T); //used constructor instead
        menuItemV2.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_1, ActionEvent.ALT_MASK));
        menuItemV2.getAccessibleContext().setAccessibleDescription(
                "This doesn't really do anything");
        //q = new SortedPriorityQueue();
        menuItemV2.addActionListener(this);
        menu.add(menuItemV2);
        
        JMenuItem menuItemV3;
        menuItemV3 = new JMenuItem(new ViewAlarm());
        //menuItem.setMnemonic(KeyEvent.VK_T); //used constructor instead
        menuItemV3.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_1, ActionEvent.ALT_MASK));
        menuItemV3.getAccessibleContext().setAccessibleDescription(
                "This doesn't really do anything");
        //q = new SortedPriorityQueue();
        menuItemV3.addActionListener(this);
        menu.add(menuItemV3);
        
        JMenuItem menuSave;
        menuSave = new JMenuItem(new ButtonLoad());
        //menuItem.setMnemonic(KeyEvent.VK_T); //used constructor instead
        menuSave.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_1, ActionEvent.ALT_MASK));
        menuSave.getAccessibleContext().setAccessibleDescription(
                "This doesn't really do anything");
        //q = new SortedPriorityQueue();
        menuSave.addActionListener(this);
        menu.add(menuSave);
        
        
        frame.setJMenuBar(menuBar);
        // Start of border layout code
        
        // I've just put a single button in each of the border positions:
        // PAGE_START (i.e. top), PAGE_END (bottom), LINE_START (left) and
        // LINE_END (right). You can omit any of these, or replace the button
        // with something else like a label or a menu bar. Or maybe you can
        // figure out how to pack more than one thing into one of those
        // positions. This is the very simplest border layout possible, just
        // to help you get started.
        
        Container pane = frame.getContentPane();
        
        JButton button = new JButton("Button 1 (PAGE_START)");
        /*
        pane.add(button, BorderLayout.PAGE_START);
         */
        panel.setPreferredSize(new Dimension(200, 200));
        pane.add(panel, BorderLayout.CENTER);
         
        button = new JButton(new AddAlarm());
        pane.add(button, BorderLayout.LINE_START);
        //q = new SortedLinkedPriorityQueue<>();
        
        output = new JTextArea(5, 30);
        output.setEditable(false);
        JScrollPane paneScroll = new JScrollPane(output);
        
        button = new JButton(new ViewAlarm());
        pane.add(button, BorderLayout.PAGE_START);
        //q = new SortedLinkedPriorityQueue<>();

        //Add the text area to the content pane.

        //Add the text area to the content pane.
        pane.add(paneScroll, BorderLayout.PAGE_END);
         /*
        button = new JButton("End Program (End Program)");
        pane.add(button, BorderLayout.PAGE_END);
        
         */
         
        button = new JButton(new EditAlarm());
        pane.add(button, BorderLayout.LINE_END);
        //button = new JButton("5 Add Alarm(Edit Alarm)");
        //pane.add(button, BorderLayout.LINE_END);
        /*
        button = new JButton("5 Add Alarm(LINE_END)");
        pane.add(button, BorderLayout.BEFORE_FIRST_LINE);
        */
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
                    Model.write("AlarmList");
                    Model.StringWrite("AlarmListString");
                    System.exit(0);
                    
                }
            }
});
    }
    
    /**
     *
     * @param o
     * @param arg
     */
    public void update(Observable o, Object arg) {
        panel.repaint();
    }
    
    /**
     *
     * @return contentPane
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
     *
     */
    protected class AddAlarm extends AbstractAction {

        /**
         *
         */
        public AddAlarm() {
        super ("Add Alarm");
                }

        /**
         *
         * @param v
         */
        public void actionPerformed(ActionEvent v) {
            Model.actionPerformed(v);
            output.append("Queue value is: " + alarmView);
            output.append("\n");
            
          
        }
    }
    
    /**
     *
     */
    protected class ViewAlarm extends AbstractAction {

        /**
         *
         */
        public ViewAlarm() {
        super ("View Alarms");
                }

        /**
         *
         * @param v
         */
        public void actionPerformed(ActionEvent v) {
            Model.ViewAlarm(v);
            String queueView = String.valueOf(alarmView);
            //output.append(alarmView);
            output.append("Current queue is: " + alarmView);
        }
    }
    
    /**
     *
     */
    protected class FileAlarm extends AbstractAction {

        /**
         *
         */
        public FileAlarm() {
        super ("Add Alarm to File");
                }
        //@Override

        /**
         *
         * @param r
         */
        @Override
        public void actionPerformed(ActionEvent r)  {
            Model.write("AlarmList");
            Model.StringWrite("AlarmListString");

        }
    }
    
    /**
     *
     */
    protected class ButtonLoad extends AbstractAction {

        /**
         *
         */
        public ButtonLoad() {
            super ("Load Alarms from File");
        }

        /**
         *
         * @param s
         */
        public void actionPerformed(ActionEvent s) {
            try {
                Model.Load("AlarmList.ics");
                Model.StringLoad("AlarmListString.ics");
            } catch (IOException ex) {
                Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
            } 
            } 
    }
    
    //https://stackoverflow.com/questions/31238492/writing-ics-ical-file-using-java
    
    /**
     *
     */
    protected class EditAlarm extends AbstractAction {

        /**
         *
         */
        public EditAlarm() {
        super ("View Alarms");
                }

        /**
         *
         * @param n
         */
        public void actionPerformed(ActionEvent n) {
            Model.ViewAlarm(n);
            output.append("\n");
            output.append("Queue value is: " + alarmView);
            
        }
    }
    
    /**
     *
     * @param e
     */
    public void actionPerformed(ActionEvent e) {
        JMenuItem source = (JMenuItem)(e.getSource());
        String s = "Action event detected."
                   + newline
                   + "    Event source: " + source.getText()
                   + " (an instance of " + getClassName(source) + ")";
        output.append(s + newline);
        output.setCaretPosition(output.getDocument().getLength());
    }

    /**
     *
     * @param e
     */
    public void itemStateChanged(ItemEvent e) {
        JMenuItem source = (JMenuItem)(e.getSource());
        String s = "Item event detected."
                   + newline
                   + "    Event source: " + source.getText()
                   + " (an instance of " + getClassName(source) + ")"
                   + newline
                   + "    New state: "
                   + ((e.getStateChange() == ItemEvent.SELECTED) ?
                     "selected":"unselected");
        output.append(s + newline);
        output.setCaretPosition(output.getDocument().getLength());
    }

    // Returns just the class name -- no package info.

    /**
     *
     * @param o
     * @return
     */
    protected String getClassName(Object o) {
        String classString = o.getClass().getName();
        int dotIndex = classString.lastIndexOf(".");
        return classString.substring(dotIndex+1);
    }
    
    /**
     *
     */
    protected static void createAndShowGUI() {
        
    }
    
}
