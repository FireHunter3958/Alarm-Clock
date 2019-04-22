package clock;

import java.util.Calendar;
import java.util.Observable;
//import java.util.GregorianCalendar;

public class Model extends Observable {
    
    int hour = 0;
    int minute = 0;
    int second = 0;
    
    int oldSecond = 0;
    
    public Model() {
        update();
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
    }
    
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
  
         /* Special case for head node */
         if (head == null || head.priority <= new_node.priority) 
         { 
            new_node.next = head; 
            head = new_node; 
            top = head;
         } 
         else { 
  
            /* Locate the node before point of insertion. */
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
            }*/
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
}