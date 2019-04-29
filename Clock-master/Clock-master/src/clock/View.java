package clock;

import java.awt.*;
import javax.swing.*;
import java.util.Observer;
import java.util.Observable;

import java.util.Comparator;
import java.util.PriorityQueue;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.ButtonGroup;
import javax.swing.JMenuBar;
import javax.swing.KeyStroke;
import javax.swing.ImageIcon;

public class View implements Observer, ActionListener, ItemListener {
    PriorityQueue<Person> q;
    ClockPanel panel;
    //ClockPanel pane2;
    JTextArea output;
    JScrollPane scrollPane;
    String newline = "\n";
    
    //https://stackoverflow.com/questions/683041/how-do-i-use-a-priorityqueue
    //Comparable<Integer> comparator = new IntegerComparator();
    //PriorityQueue<Integer> queue = new PriorityQueue<Integer>(8, comparator);
    PriorityQueue<Integer> alarmQueue = new PriorityQueue<Integer>();
    String editValueString;
    
    public View(Model model) {
        JFrame frame = new JFrame();
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
        menu = new JMenu("A Menu");
        menu.setMnemonic(KeyEvent.VK_A);
        menu.getAccessibleContext().setAccessibleDescription(
                "The only menu in this program that has menu items");
        menuBar.add(menu);

        //a group of JMenuItems
        JMenuItem menuItemV2;
        menuItemV2 = new JMenuItem(new MyAction());
        //menuItem.setMnemonic(KeyEvent.VK_T); //used constructor instead
        menuItemV2.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_1, ActionEvent.ALT_MASK));
        menuItemV2.getAccessibleContext().setAccessibleDescription(
                "This doesn't really do anything");
        //q = new SortedPriorityQueue();
        menuItemV2.addActionListener(this);
        menu.add(menuItemV2);
        
        //a group of JMenuItems
        menuItem = new JMenuItem("A text-only menu item",
                                 KeyEvent.VK_T);
        //menuItem.setMnemonic(KeyEvent.VK_T); //used constructor instead
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_1, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "This doesn't really do anything");
        menuItem.addActionListener(this);
        menu.add(menuItem);
        
        JMenuItem menuItemV3;
        menuItemV3 = new JMenuItem(new ButtonActionv2());
        //menuItem.setMnemonic(KeyEvent.VK_T); //used constructor instead
        menuItemV3.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_1, ActionEvent.ALT_MASK));
        menuItemV3.getAccessibleContext().setAccessibleDescription(
                "This doesn't really do anything");
        //q = new SortedPriorityQueue();
        menuItemV3.addActionListener(this);
        menu.add(menuItemV3);
        
        
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
         
        button = new JButton(new ButtonActionv1());
        pane.add(button, BorderLayout.LINE_START);
        //q = new SortedLinkedPriorityQueue<>();
        
        output = new JTextArea(5, 30);
        output.setEditable(false);
        JScrollPane paneScroll = new JScrollPane(output);
        
        button = new JButton(new ButtonActionv2());
        pane.add(button, BorderLayout.PAGE_START);
        //q = new SortedLinkedPriorityQueue<>();

        //Add the text area to the content pane.

        //Add the text area to the content pane.
        pane.add(paneScroll, BorderLayout.PAGE_END);
         /*
        button = new JButton("End Program (End Program)");
        pane.add(button, BorderLayout.PAGE_END);
        
         */
         
        button = new JButton(new ButtonActionv3());
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
    }
    
    public void update(Observable o, Object arg) {
        panel.repaint();
    }
    
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
    
    protected class MyAction extends AbstractAction {
        public MyAction() {
        super ("My Menu Item");
                }
        public void actionPerformed(ActionEvent e) {
            //q = new SortedPriorityQueue();
        }
    }
    
    protected class ButtonActionv1 extends AbstractAction {
        public ButtonActionv1() {
        super ("Add Alarm");
                }
        public void actionPerformed(ActionEvent v) {
            final JFrame parent = new JFrame();

            //q = new SortedPriorityQueue();
            String b = "Action performed."
                    + newline
                    + "Button pressed";
            output.append(b);
            
            //https://stackoverflow.com/questions/3002787/simple-popup-java-form-with-at-least-two-fields
            
            JTextField field1 = new JTextField("");
            JTextField field2 = new JTextField("");
            JTextField field3 = new JTextField("");
            
            JPanel panel = new JPanel(new GridLayout(0, 1));
        //panel.add(combo);
        panel.add(new JLabel("Hours:"));
        panel.add(field1);
        panel.add(new JLabel("Minutes:"));
        panel.add(field2);
        panel.add(new JLabel("Seconds:"));
        panel.add(field3);

            int result = JOptionPane.showConfirmDialog(null, panel, "Add Alarm",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            String s = field1.getText() + field2.getText() + field3.getText();
            output.append(" " + field1.getText()
                + ":" + field2.getText() 
                + ":" + field3.getText());
            int queueValue = Integer.parseInt(s);
            alarmQueue.add (queueValue);
            output.append("Queue value is: " + alarmQueue);
        } else {
            System.out.println("Cancelled");
        }

            /*
            String hours = JOptionPane.showInputDialog(parent,
                        "Add an Alarm:", null);
            String minutes = JOptionPane.showInputDialog(parent,
                        "Add an Alarm:", null);
            String seconds = JOptionPane.showInputDialog(parent,
                        "Add an Alarm:", null);
            String alarmValue = hours.getText() + ":" + minutes.getText() + ":" + seconds.getText();
*/
        }
    }
    
    protected class ButtonActionv2 extends AbstractAction {
        public ButtonActionv2() {
        super ("View Alarms");
                }
        public void actionPerformed(ActionEvent v) {
            String queueView = String.valueOf(alarmQueue);
            output.append(queueView);
        }
    }
    
    protected class ButtonActionv3 extends AbstractAction {
        public ButtonActionv3() {
        super ("Edit Alarms");
                }
        public void actionPerformed(ActionEvent n) {
            final JFrame editvalue = new JFrame();
            
            //https://stackoverflow.com/questions/3002787/simple-popup-java-form-with-at-least-two-fields
            
            final Object[] alarmArray = alarmQueue.toArray();
            
            JTextField field1 = new JTextField("");
            JTextField field2 = new JTextField("");
            JTextField field3 = new JTextField("");
            //int i = 0;
            //JTextField fieldValue = new JTextField((String) alarmArray[i]);
            
            JPanel panel = new JPanel(new GridLayout(0, 1));
            
            
            for (int i = 0; i < alarmArray.length; i ++) {
                JButton buttonV4 = new JButton("Edit Alarm");
                final int x = i;
                final JTextField fieldValue = new JTextField(String.valueOf(alarmArray[i]));
                panel.add(new JLabel("Stored Alarm:"));
                panel.add(fieldValue);
                panel.add(buttonV4);
                //editValueString = String.valueOf(fieldValue);
        //panel.add(combo);
        //https://stackoverflow.com/questions/329118/how-do-i-activate-jbutton-actionlistener-inside-code-unit-testing-purposes
                buttonV4.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent b) {
                        final JFrame editAlarm = new JFrame();
            
                    //https://stackoverflow.com/questions/3002787/simple-popup-java-form-with-at-least-two-fields
                    //editValueString = 

                    JTextField field1 = new JTextField("");
                    JTextField field2 = new JTextField("");
                    JTextField field3 = new JTextField("");

                    JPanel panel = new JPanel(new GridLayout(0, 1));
                    //JButton button = new JButton("Button 1 (PAGE_START)");
                        int y = x;
                    
                    //String valueField = String.valueOf(fieldValue);
                    JTextField fieldValue2 = new JTextField(String.valueOf(alarmArray[y]));
                    panel.add(new JLabel("Stored Alarm:"));
                    panel.add(fieldValue2);

                    panel.add(new JLabel("New Hours:"));
                    panel.add(field1);
                    panel.add(new JLabel("New Minutes:"));
                    panel.add(field2);
                    panel.add(new JLabel("New Seconds:"));
                    panel.add(field3);

                    int result = JOptionPane.showConfirmDialog(null, panel, "Edit Alarm",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                    }
                    
                    });
                
                
                
            }
            int result = JOptionPane.showConfirmDialog(null, panel, "View Alarms",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        }
    }
    /*
    protected class EditAlarmV1 extends AbstractAction {

        //JFrame pane3 = new JFrame();
        public EditAlarmV1() {
        super ("Edit Alarm");
                }
        public void actionPerformed(ActionEvent b) {
            final JFrame editAlarm = new JFrame();
            
            //https://stackoverflow.com/questions/3002787/simple-popup-java-form-with-at-least-two-fields
            //editValueString = 
                    
            JTextField field1 = new JTextField("");
            JTextField field2 = new JTextField("");
            JTextField field3 = new JTextField("");
            
            JPanel panel = new JPanel(new GridLayout(0, 1));
            //JButton button = new JButton("Button 1 (PAGE_START)");
            
            JTextField fieldValue2 = new JTextField(String.valueOf(editValueString));
            panel.add(new JLabel("Stored Alarm:"));
            panel.add(fieldValue2);
            
            panel.add(new JLabel("New Hours:"));
            panel.add(field1);
            panel.add(new JLabel("New Minutes:"));
            panel.add(field2);
            panel.add(new JLabel("New Seconds:"));
            panel.add(field3);
            
            }
            int result = JOptionPane.showConfirmDialog(null, panel, "Edit Alarm",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        }
    
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
    protected String getClassName(Object o) {
        String classString = o.getClass().getName();
        int dotIndex = classString.lastIndexOf(".");
        return classString.substring(dotIndex+1);
    }
    
    protected static void createAndShowGUI() {
        //Create and set up the window.
        /*JFrame frame = new JFrame("MenuDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        menuBar demo = new menuBar();
        frame.setJMenuBar(demo.createMenuBar());
        frame.setContentPane(demo.createContentPane());

        //Display the window.
        frame.setSize(450, 260);
        frame.setVisible(true);
        */
    }
    /*
    protected class SortedLinkedPriorityQueue<T> implements PriorityQueue<T> {
    
    private ListNode<T> top;
    private ListNode<T> temp;
    private ListNode<T> head;
    private ListNode<T> tail;
    
    public SortedLinkedPriorityQueue() {
        top = null;
    }
    
    @Override
    public boolean isEmpty() {
        return top == null;
    }
    
    @Override
    public T head() throws QueueUnderflowException{
        if (isEmpty()) {
            throw new QueueUnderflowException();
        } else {
        return head.getItem();
        }
    }
    
    @Override
    public void remove() throws QueueUnderflowException{
        if (isEmpty()) {
            throw new QueueUnderflowException();
        }
        head = head.getNext();
    }
    
    public void add(T item, int priority) throws QueueOverflowException {
        { 
         ListNode current; 
         ListNode new_node = new ListNode(item, head, priority);
  
         /* Special case for head node *//*
         if (head == null || head.priority <= new_node.priority) 
         { 
            new_node.next = head; 
            head = new_node; 
            top = head;
         } 
         else { 
  
            /* Locate the node before point of insertion. */ /*
            current = head; 
  
            while (current.next != null && 
                   current.next.priority > new_node.priority) 
                  current = current.next; 
  
            new_node.next = current.next; 
            current.next = new_node; 
         } 
     } 
    }
    @Override
    public String toString() {
        String result = "";
         ListNode temp = head; 
         while (temp != null) 
         { 
            //System.out.print(temp.priority+" "); 
             result += temp.getItem();
             if(temp.next != null){
                     result += ", ";
                } 
            temp = temp.next; 
         } /*
         if (temp.next == null){
                result += temp.getItem();
            }*/ /*
            return "List: " + result;
     }
    
    private int size() {
        ListNode<T> node = top;
        int result = 0;
        while (node != null) {
            result = result + 1;
            node = node.getNext();
        }
        return result;
    }
}
*/
}
