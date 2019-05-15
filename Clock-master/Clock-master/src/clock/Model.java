package clock;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Observable;
import java.util.PriorityQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * This class handles the functionality that the View class requires.
 * @author 07014975
 */
public class Model extends Observable {
    
    JTextArea output;
    JScrollPane scrollPane;
    String newline = "\n";
    
    Alarm alarm =  new Alarm();
    PriorityQueue<String> alarmView = alarm.alarmViewMaker();
    final Object[] viewArray = alarmView.toArray();
    
    
    int hour = 0;
    int minute = 0;
    int second = 0;
    
    int oldSecond = 0;
    
    /**
     * This method runs the necessary methods at the start of the program,
     * in order of createContentPane(), the load functions, then the update()
     * method.
     */
    public Model() {
        
        createContentPane();
        try {
        StringLoad("AlarmList.ics");
        } catch (IOException ex) {
            Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
            }
        update();
    }

    /**
     *This method loops constantly, updating the displayed time on the clock,
     * and checking if the head of the queue matches current time, if it does
     * a new frame will be created to inform the user that an alarm is going off
     */
    public void update() {
        Calendar date = Calendar.getInstance();
        hour = date.get(Calendar.HOUR);
        minute = date.get(Calendar.MINUTE);
        oldSecond = second;
        second = date.get(Calendar.SECOND);
        if (oldSecond != second) {
            setChanged();
            notifyObservers();
        }
        
        String StringHour = String.valueOf(hour);
        if (StringHour.length() < 2) {
            StringHour = "0" + StringHour;
        }
        String StringMinute = String.valueOf(minute);
        if (StringMinute.length() < 2) {
            StringMinute = "0" + StringMinute;
        }
        String StringSecond = String.valueOf(second);
        if (StringSecond.length() < 2) {
            StringSecond = "0" + StringSecond;
        }
        
        final Object[] viewArray = alarmView.toArray();
        
        String timeCheck = StringHour + ":" + StringMinute + ":" + StringSecond;
        
            String timeTest = String.valueOf(viewArray[0]);
            if (timeCheck.matches(timeTest)) {
                final JFrame display = new JFrame();
            
            //https://stackoverflow.com/questions/3002787/simple-popup-java-form-with-at-least-two-fields

            JPanel panel = new JPanel(new GridLayout(0, 1));

            final JTextField fieldValue = new JTextField("Your alarm is going off!");
                panel.add(new JLabel("Active Alarm:"));
                panel.add(fieldValue);
                
                
                
                String removeView = (String) viewArray[0];
                
                alarm.alarmViewRemover(removeView);
                
                
            
            int result = JOptionPane.showConfirmDialog(null, panel, "Turn Alarm Off",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            }
  
            
        
        
            }
        
    /**
     *This method is responsible for creating the output field and scrollbar that is
     * used by the methods within the Model class.
     * @return contentPane this is the variable that creates the content pane for the class.
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
     *This method is responsible for adding alarms to the correct alarm queue.
     * Validation has been created to ensure users are not able to input invalid data types, or values outside of the acceptable range.
     * @param v is the value retrieved from the View class
     */

        public void actionPerformed(ActionEvent v) {
            
            final JFrame parent = new JFrame();

            //https://stackoverflow.com/questions/3002787/simple-popup-java-form-with-at-least-two-fields
            
            final JTextField field1 = new JTextField();
            field1.setDocument(new LengthRestrictedDocument(2));
            field1.addKeyListener(new java.awt.event.KeyAdapter() {
                //https://stackoverflow.com/questions/20541230/allow-only-numbers-in-jtextfield
        public void keyReleased(java.awt.event.KeyEvent evt) {
            try {
                long number = Long.parseLong(field1.getText());
                if (number > 12) {
                    field1.setText("");
                } else {
                    
                }
            } catch (Exception e) {
                output.append("Only Numbers Allowed");
                field1.setText("");
            }
        }
            });

            final JTextField field2 = new JTextField();
            field2.setDocument(new LengthRestrictedDocument(2));
            field2.addKeyListener(new java.awt.event.KeyAdapter() {
                //https://stackoverflow.com/questions/20541230/allow-only-numbers-in-jtextfield
        public void keyReleased(java.awt.event.KeyEvent evt) {
            try {
                long number = Long.parseLong(field2.getText());
                if (number > 59) {
                    field2.setText("");
                } else {
                    
                }
            } catch (Exception e) {
                output.append("Only Numbers Allowed");
                field2.setText("");
            }
        }
            });
            
            final JTextField field3 = new JTextField();
            field3.setDocument(new LengthRestrictedDocument(2));
            field3.addKeyListener(new java.awt.event.KeyAdapter() {
                //https://stackoverflow.com/questions/20541230/allow-only-numbers-in-jtextfield
        public void keyReleased(java.awt.event.KeyEvent evt) {
            try {
                long number = Long.parseLong(field3.getText());
                if (number > 59) {
                    field3.setText("");
                } else {
                    
                }
            } catch (Exception e) {
                output.append("Only Numbers Allowed");
                field3.setText("");
            }
        }
            });
            
            
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
            String Sfield1 = field1.getText();
            if (field1.getText().length() < 2) {
                Sfield1 = "0" + field1.getText();
            }
            String Sfield2 = field2.getText();
            if (field2.getText().length() < 2) {
                Sfield2 = "0" + field2.getText();
            }
            String Sfield3 = field3.getText();
            if (field3.getText().length() < 2) {
                Sfield3 = "0" + field3.getText();
            }
            String s = Sfield1 + Sfield2 + Sfield3;
            String a = Sfield1 + ":" + Sfield2 + ":" + Sfield3;
            output.append(" " + field1.getText()
                + ":" + field2.getText() 
                + ":" + field3.getText());
            int queueValue = Integer.parseInt(s);
            alarm.alarmViewAdder(a);
        } else {
            System.out.println("Cancelled");
               }   

        }
        
        //https://stackoverflow.com/questions/13075564/limiting-length-of-input-in-jtextfield-is-not-working

    /**
     *This method is the linked form of validation, that prevents users from entering invalid data types.
     */
        public final class LengthRestrictedDocument extends PlainDocument {

  private final int limit;

        /**
         *
         * @param limit
         */
        public LengthRestrictedDocument(int limit) {
    this.limit = limit;
  }

        /**
         *
         * @param offs
         * @param str
         * @param a
         * @throws BadLocationException
         */
        @Override
  public void insertString(int offs, String str, AttributeSet a)
      throws BadLocationException {
    if (str == null)
      return;

    if ((getLength() + str.length()) <= limit) {
      super.insertString(offs, str, a);
    }
  }
    }
        
    /**
     *This method is responsible for writing the stored alarms to an external file.
     * The file name is retrieved from the method that calls this method.
     * The file is created in the iCal format.
     * @param name is the variable provided that will be used as the name of the external file.
     */
    public void StringWrite( String name ){
            StringBuilder builder = new StringBuilder();
            builder.append(name);
            builder.append(".ics");
            
            String calBegin =   "BEGIN:VCALENDAR\r\n";
            String calEnd =     "END:VCALENDAR";

    
    final Object[] viewArray = alarmView.toArray();
            try {

                File file = new File(builder.toString());

                // if file doesnt exists, then create it
                if (!file.exists()) {
                    file.createNewFile();
                }

                FileWriter fw = new FileWriter(file.getAbsoluteFile());
                BufferedWriter bw = new BufferedWriter(fw);
                
                String version =    "VERSION:2.0\r\n";
                String prodid =     "PRODID:-//07014975//JAVA Alarm Clock//EN\r\n";
                
                
                String eventBegin = "BEGIN:VEVENT\r\n";
                String eventEnd =   "\r\nEND:VEVENT\r\n";

                DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
                Date date = new Date();
                
                String testExample = "UID:07014975@uhi.ac.uk\r\nDTSTAMP:" + dateFormat.format(date) + "\r\nORGANIZER;";
                String testExample2 = "CN=07014975:MAILTO:07014975@uhi.ac.uk\r\nDTSTART:" + dateFormat.format(date) + "";
                String testExample3 = "\r\nDTEND:" + dateFormat.format(date) + "\r\nSUMMARY:Alarm Clock\r\n";

                bw.write(calBegin);
                bw.write(version);
                bw.write(prodid);
                bw.write(eventBegin);
                bw.write(testExample);
                bw.write(testExample2);
                bw.write(testExample3);
                
                
                for (int i = 0; i < viewArray.length; i ++) {
                    String timeExample = String.valueOf(viewArray[i]);
                    bw.write(timeExample);
                    //bw.write("\r\n");
                    
                    if (i == viewArray.length - 1) {
                        bw.write("");
                    } else {
                        bw.write("\r\n");
                    }
                    
                }
                
                
                bw.write(eventEnd);
                bw.write(calEnd);
                bw.close();

                System.out.println("Done");
                
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
    public PriorityQueue<String> StringLoad(String file) throws FileNotFoundException, IOException {
            
         {      
                BufferedReader br = new BufferedReader(new FileReader(file));
                br.readLine();
                br.readLine();
                br.readLine();
                br.readLine();
                br.readLine();
                br.readLine();
                br.readLine();
                br.readLine();
                br.readLine();
                br.readLine();
                ArrayList<String> stList = new ArrayList<String>();
                String st;
                while ((st = br.readLine()) != null){
                    stList.add(st);
               
                }
                for (int i = 0; i < stList.size() - 2; i ++) {
                    String list = stList.get(i);
                    alarmView.add(list);
                    
                }
                } 
         return alarmView;
         }
        
    /**
     *This method is responsible for creating the frame, and displaying all relevant information and buttons.
     * It displays each stored alarm, followed by an edit button, and a delete button.
     * Features the same input validation as the method that creates alarms.
     * The edit button creates a frame with the old alarm, and three fields to alter hours, minutes and seconds.
     * The delete button creates a frame to confirm that deletion is acceptable.
     * @param n is the variable retrieved from the methods that call this method.
     */
        public void ViewAlarm(ActionEvent n) {
            final JFrame editvalue = new JFrame();
            
            //https://stackoverflow.com/questions/3002787/simple-popup-java-form-with-at-least-two-fields
            final Object[] viewArray = alarmView.toArray();
            ActionListener listener;
            Timer timer;
            
            JTextField field1 = new JTextField("");
            JTextField field2 = new JTextField("");
            JTextField field3 = new JTextField("");
            
            final JPanel panel = new JPanel(new GridLayout(0, 1));
            

            for (int i = 0; i < viewArray.length; i ++) {
                JButton buttonV4 = new JButton("Edit Alarm");
                JButton buttonV5 = new JButton("Delete Alarm");
                final int x = i;
                final JTextField fieldValue = new JTextField(String.valueOf(viewArray[i]));
                panel.add(new JLabel("Stored Alarm:"));
                fieldValue.setEditable(false);
                panel.add(fieldValue);
                panel.add(buttonV4);
                panel.add(buttonV5);
        //https://stackoverflow.com/questions/329118/how-do-i-activate-jbutton-actionlistener-inside-code-unit-testing-purposes
                buttonV4.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent b) {
                        final JFrame editAlarm = new JFrame();
            
                    //https://stackoverflow.com/questions/3002787/simple-popup-java-form-with-at-least-two-fields

                    final JTextField field1 = new JTextField();
            field1.setDocument(new LengthRestrictedDocument(2));
            field1.addKeyListener(new java.awt.event.KeyAdapter() {
                //https://stackoverflow.com/questions/20541230/allow-only-numbers-in-jtextfield
        public void keyReleased(java.awt.event.KeyEvent evt) {
            try {
                long number = Long.parseLong(field1.getText());
                if (number > 12) {
                    field1.setText("");
                } else {
                    
                }
            } catch (Exception e) {
                output.append("Only Numbers Allowed");
                field1.setText("");
            }
        }
            });

            final JTextField field2 = new JTextField();
            field2.setDocument(new LengthRestrictedDocument(2));
            field2.addKeyListener(new java.awt.event.KeyAdapter() {
                //https://stackoverflow.com/questions/20541230/allow-only-numbers-in-jtextfield
        public void keyReleased(java.awt.event.KeyEvent evt) {
            try {
                long number = Long.parseLong(field2.getText());
                if (number > 59) {
                    field2.setText("");
                } else {
                    
                }
            } catch (Exception e) {
                output.append("Only Numbers Allowed");
                field2.setText("");
            }
        }
            });
            
            final JTextField field3 = new JTextField();
            field3.setDocument(new LengthRestrictedDocument(2));
            field3.addKeyListener(new java.awt.event.KeyAdapter() {
                //https://stackoverflow.com/questions/20541230/allow-only-numbers-in-jtextfield
        public void keyReleased(java.awt.event.KeyEvent evt) {
            try {
                long number = Long.parseLong(field3.getText());
                if (number > 59) {
                    field3.setText("");
                } else {
                    
                }
            } catch (Exception e) {
                output.append("Only Numbers Allowed");
                field3.setText("");
            }
        }
            });

                    JPanel panel = new JPanel(new GridLayout(0, 1));
                        int y = x;
                    
                    JTextField fieldValue2 = new JTextField(String.valueOf(viewArray[y]));
                    panel.add(new JLabel("Stored Alarm:"));
                    fieldValue2.setEditable(false);
                    panel.add(fieldValue2);

                    panel.add(new JLabel("New Hours:"));
                    panel.add(field1);
                    panel.add(new JLabel("New Minutes:"));
                    panel.add(field2);
                    panel.add(new JLabel("New Seconds:"));
                    panel.add(field3);

                    int result = JOptionPane.showConfirmDialog(null, panel, "Edit Alarm",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                    
                    if (result == JOptionPane.OK_OPTION) {
                    String Sfield1 = field1.getText();
                    if (field1.getText().length() < 2) {
                        Sfield1 = "0" + field1.getText();
                    }
                    String Sfield2 = field2.getText();
                    if (field2.getText().length() < 2) {
                        Sfield2 = "0" + field2.getText();
                    }
                    String Sfield3 = field3.getText();
                    if (field3.getText().length() < 2) {
                        Sfield3 = "0" + field3.getText();
                    }
                    String s = Sfield1 + Sfield2 + Sfield3;
                    String a = Sfield1 + ":" + Sfield2 + ":" + Sfield3;
                    output.append(" " + field1.getText()
                        + ":" + field2.getText() 
                        + ":" + field3.getText());
                    if (alarmView.contains(viewArray[y])){
                        String removeView = (String) viewArray[y];
                    int queueValue = Integer.parseInt(s);
                    alarm.alarmViewRemover(removeView);
                    alarm.alarmViewAdder(a);
                    }
                    } else {
                        output.append("Failed");
                           }
                    }
                    });
                
                buttonV5.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent b) {
                        final JFrame deleteAlarm = new JFrame();
                        
                        JPanel panel = new JPanel(new GridLayout(0, 1));
                        
                        int y = x;
                        
                        JTextField fieldValue2 = new JTextField(String.valueOf(viewArray[y]));
                    panel.add(new JLabel("Stored Alarm:"));
                    fieldValue2.setEditable(false);
                    panel.add(fieldValue2);
                        
                int result = JOptionPane.showConfirmDialog(null, panel, "Delete Alarm",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                    
                    if (result == JOptionPane.OK_OPTION) {
                        String removeView = (String) viewArray[y];
                    alarm.alarmViewRemover(removeView);
                        }
                    }
                    });
                
                
            }
            
            int result = JOptionPane.showConfirmDialog(null, panel, "View Alarms",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        }
    }
    