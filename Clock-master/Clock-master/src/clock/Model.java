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
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.PriorityQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.NumberFormatter;
import javax.swing.text.PlainDocument;
//import java.util.GregorianCalendar;

public class Model extends Observable {
    
    JTextArea output;
    JScrollPane scrollPane;
    String newline = "\n";
    
    Alarm alarm =  new Alarm();
    PriorityQueue<Integer> alarmQueue = alarm.alarmMaker();
    PriorityQueue<String> alarmView = alarm.alarmViewMaker();
    final Object[] alarmArray = alarmQueue.toArray();
    final Object[] viewArray = alarmView.toArray();
    
    //PriorityQueue<Integer> alarmQueue = new Alarm().alarmMaker();
    
    
    int hour = 0;
    int minute = 0;
    int second = 0;
    
    int oldSecond = 0;
    
    public Model() {
        
        createContentPane();
        try {
        Load("AlarmList.ics");
        StringLoad("AlarmListString.ics");
        } catch (IOException ex) {
            Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
            }
        update();
    }
    
    //https://stackoverflow.com/questions/11093326/restricting-jtextfield-input-to-integers
    private NumberFormatter setUpFormats() {
        NumberFormat amountFormat = NumberFormat.getInstance();
    NumberFormatter formatter = new NumberFormatter(amountFormat);
    formatter.setAllowsInvalid(false);
    formatter.setMinimum(00);
    formatter.setMaximum(12);
    
    return formatter;
    }
    
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
        
        //final Object[] alarmArray = alarmQueue.toArray();
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
        System.out.println(timeCheck);
        
        //for (int i = 0; i < viewArray.length; i++) {
            String timeTest = String.valueOf(viewArray[0]);
            //System.out.println(timeTest);
            if (timeCheck.matches(timeTest)) {
                System.out.println("MATCH!");
                final JFrame display = new JFrame();
            
            //https://stackoverflow.com/questions/3002787/simple-popup-java-form-with-at-least-two-fields

            JPanel panel = new JPanel(new GridLayout(0, 1));

            final JTextField fieldValue = new JTextField("Your alarm is going off!");
                panel.add(new JLabel("Active Alarm:"));
                panel.add(fieldValue);
                
                
                
                
            
            int result = JOptionPane.showConfirmDialog(null, panel, "Turn Alarm Off",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            }
  
            
        
        //final Object[] alarmArray = alarmQueue.toArray();
        //alarmArray = alarmQueue.toArray();
        /*
        String timeCheck = StringHour + StringMinute + StringSecond;
        int CheckTimeValue = Integer.parseInt(timeCheck);
        System.out.println(CheckTimeValue);
        //for (int i = 0; i < alarmArray.length; i ++) {
        while (alarmQueue.size() > 0) {
        System.out.println(alarmArray.length);
        if (alarm.head() == CheckTimeValue){
            String alarmCheck = String.valueOf(alarmQueue);
            System.out.println(alarmCheck);
            System.out.println("Hello");
            int alarmTimeCheck = Integer.parseInt(alarmCheck);
            //final int x = i;
            //int y = x;
            if (CheckTimeValue == alarmTimeCheck){
                final JFrame showAlarm = new JFrame();
                
                JPanel panel = new JPanel(new GridLayout(0, 1));
                
                int result = JOptionPane.showConfirmDialog(null, panel, "Ok",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            } else {
                
            }
        }
        } */
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
    /*
    public class AddAlarm extends AbstractAction {
        public AddAlarm() {
        super ("Add Alarm");
                } */
        public void actionPerformed(ActionEvent v) {
            
            final JFrame parent = new JFrame();
/*
            //q = new SortedPriorityQueue();
            String b = "Action performed."
                    + newline
                    + "Button pressed";
            output.append(b);
            */
            //https://stackoverflow.com/questions/3002787/simple-popup-java-form-with-at-least-two-fields
            
            //JTextField field1 = new JTextField("");
            //JFormattedTextField field1 = new JFormattedTextField(setUpFormats());
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
            //alarmQueue.add (queueValue);
            alarm.alarmAdder(queueValue);
            alarm.alarmViewAdder(a);
            //output.append("Queue value is: " + alarmQueue);
        } else {
            System.out.println("Cancelled");
               }   

        }
        
        //https://stackoverflow.com/questions/13075564/limiting-length-of-input-in-jtextfield-is-not-working
        public final class LengthRestrictedDocument extends PlainDocument {

  private final int limit;

  public LengthRestrictedDocument(int limit) {
    this.limit = limit;
  }

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
        
        //Class to output stored alarms to an external file
        //public class iCalMaker{
/*
    private String version =    "VERSION:1.0 \n";

    private String prodid =     "PRODID://Elara/lofy/tanare/delp/314sum2015// \n";
    private String calBegin =   "BEGIN:VCALENDAR \n";
    private String calEnd =     "END:VCALENDAR \n";
    private String eventBegin = "BEGIN:VEVENT \n";
    private String eventEnd =   "END:VEVENT \n";

*/
    
        //public void iCal(){
        //}

        public void write( String name ){
            StringBuilder builder = new StringBuilder();
            builder.append(name);
            builder.append(".ics");
            
            String calBegin =   "BEGIN:VCALENDAR\r\n";
            String calEnd =     "END:VCALENDAR";

    //String testExample = "\nDTSTAMP:\r\n";
    //String timeExample = String.valueOf(alarmQueue);
    final Object[] alarmArray = alarmQueue.toArray();
            try {

                File file = new File(builder.toString());

                // if file doesnt exists, then create it
                if (!file.exists()) {
                    file.createNewFile();
                }

                FileWriter fw = new FileWriter(file.getAbsoluteFile());
                BufferedWriter bw = new BufferedWriter(fw);
                /*
                bw.write(calBegin);
                bw.write(version);
                bw.write(prodid);
                bw.write(eventBegin);
                */
                //bw.write(testExample);
                
                String version =    "VERSION:2.0\r\n";
                String prodid =     "PRODID://07014975//JAVA Alarm Clock//EN\r\n";
                
                
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
                
                for (int i = 0; i < alarmArray.length; i ++) {
                    String timeExample = String.valueOf(alarmArray[i]);
                    bw.write(timeExample);
                    //bw.write("\r\n");
                    
                    if (i == alarmArray.length - 1) {
                        bw.write("");
                    } else {
                        bw.write("\r\n");
                    }
                    
                }
                
                /*
                bw.write(eventEnd);
                bw.write(calEnd);
*/
                
                bw.write(eventEnd);
                bw.write(calEnd);
                bw.close();

                System.out.println("Done");
                //System.out.println(testExample);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        public void StringWrite( String name ){
            StringBuilder builder = new StringBuilder();
            builder.append(name);
            builder.append(".ics");
            
            String calBegin =   "BEGIN:VCALENDAR\r\n";
            String calEnd =     "END:VCALENDAR";

    //String testExample = "\nDTSTAMP:\r\n";
    //String timeExample = String.valueOf(alarmQueue);
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
                /*
                bw.write(calBegin);
                bw.write(version);
                bw.write(prodid);
                bw.write(eventBegin);
                */
                //bw.write(testExample);
                
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
                
                /*
                bw.write(eventEnd);
                bw.write(calEnd);
*/
                bw.write(eventEnd);
                bw.write(calEnd);
                bw.close();

                System.out.println("Done");
                //System.out.println(testExample);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        //protected class iCalLoader {
        //Class to load alarms from external file
        public PriorityQueue<Integer> Load(String file) throws FileNotFoundException, IOException {
            
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
                        
                    
                //System.out.println(st);
                /*
                try {
                int queueValue = Integer.parseInt(st);
                alarmQueue.add(queueValue);
                output.append(String.valueOf(alarmQueue));
                }catch(NumberFormatException ex){
                System.out.println("Error occured during conversion");
                } */
                //return data;
                }
                for (int i = 0; i < stList.size() - 2; i ++){
                    int list = Integer.parseInt(stList.get(i));
                alarmQueue.add(list);
                //System.out.println(alarmQueue);
                }
                } 
         return alarmQueue;
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
                //System.out.println(st);
                /*
                try {
                //int queueValue = Integer.parseInt(st);
                alarmView.add(st);
                output.append(String.valueOf(alarmView));
                }catch(NumberFormatException ex){
                System.out.println("Error occured with during conversion");
                }*/
                //return data;
                }
                for (int i = 0; i < stList.size() - 2; i ++) {
                    String list = stList.get(i);
                    alarmView.add(list);
                    //System.out.println(alarmView);
                }
                } 
         return alarmView;
         }
        
        //Class to edit/delete alarms
        public void ViewAlarm(ActionEvent n) {
            final JFrame editvalue = new JFrame();
            
            //https://stackoverflow.com/questions/3002787/simple-popup-java-form-with-at-least-two-fields
            
            final Object[] alarmArray = alarmQueue.toArray();
            final Object[] viewArray = alarmView.toArray();
            
            JTextField field1 = new JTextField("");
            JTextField field2 = new JTextField("");
            JTextField field3 = new JTextField("");
            //int i = 0;
            //JTextField fieldValue = new JTextField((String) alarmArray[i]);
            
            JPanel panel = new JPanel(new GridLayout(0, 1));
            
            
            for (int i = 0; i < alarmArray.length; i ++) {
                JButton buttonV4 = new JButton("Edit Alarm");
                JButton buttonV5 = new JButton("Delete Alarm");
                final int x = i;
                final JTextField fieldValue = new JTextField(String.valueOf(viewArray[i]));
                panel.add(new JLabel("Stored Alarm:"));
                panel.add(fieldValue);
                panel.add(buttonV4);
                panel.add(buttonV5);
                //editValueString = String.valueOf(fieldValue);
        //panel.add(combo);
        //https://stackoverflow.com/questions/329118/how-do-i-activate-jbutton-actionlistener-inside-code-unit-testing-purposes
                buttonV4.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent b) {
                        final JFrame editAlarm = new JFrame();
            
                    //https://stackoverflow.com/questions/3002787/simple-popup-java-form-with-at-least-two-fields
                    //editValueString = 

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
                    //JButton button = new JButton("Button 1 (PAGE_START)");
                        int y = x;
                    
                    //String valueField = String.valueOf(fieldValue);
                    JTextField fieldValue2 = new JTextField(String.valueOf(viewArray[y]));
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
                    if (alarmQueue.contains(alarmArray[y])){
                        //String removeValueString = String.valueOf(alarmArray[y]);
                        //int removeValue = Integer.parseInt(removeValueString);
                        int removeObject = (int) alarmArray[y];
                        String removeView = (String) viewArray[y];
                    int queueValue = Integer.parseInt(s);
                    //alarmQueue.remove(alarmArray[y]);
                    alarm.alarmRemover(removeObject);
                    alarm.alarmViewRemover(removeView);
                    output.append(alarmArray[y] + " removed.");
                    //alarmQueue.add (queueValue);
                    alarm.alarmAdder(queueValue);
                    alarm.alarmViewAdder(a);
                    output.append("New Queue value is: " + alarmQueue);
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
                    panel.add(fieldValue2);
                        
                int result = JOptionPane.showConfirmDialog(null, panel, "Delete Alarm",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                    
                    if (result == JOptionPane.OK_OPTION) {
                        int removeObject = (int) alarmArray[y];
                        String removeView = (String) viewArray[y];
                    alarm.alarmRemover(removeObject);
                    alarm.alarmViewRemover(removeView);
                    output.append("New Queue value is: " + alarmQueue);
                        }
                    }
                    });
                
                
            }
            int result = JOptionPane.showConfirmDialog(null, panel, "View Alarms",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        }
         
        //}
    //}
        
        public void SaveAlarm(String file, String file2) {
            String fileName1 = file;
            String fileName2 = file2;
            final JFrame editvalue = new JFrame();
            
            //https://stackoverflow.com/questions/3002787/simple-popup-java-form-with-at-least-two-fields
            
            final Object[] alarmArray = alarmQueue.toArray();
            final Object[] viewArray = alarmView.toArray();
            
            
            JPanel panel = new JPanel(new GridLayout(0, 1));
            
            
            for (int i = 0; i < alarmArray.length; i ++) {
                final int x = i;
                final JTextField fieldValue = new JTextField(String.valueOf(viewArray[i]));
                panel.add(new JLabel("Stored Alarm:"));
                panel.add(fieldValue);
                
                
                
                
            }
            int result = JOptionPane.showConfirmDialog(null, panel, "Save Alarms?",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            
            if (result == JOptionPane.OK_OPTION) {
                        write(fileName1);
                        StringWrite(fileName2);
                        }
                    }
                    
        
    }
    