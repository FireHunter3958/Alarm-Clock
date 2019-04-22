/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clock;

/**
 *
 * @author FireHunter3958
 */
public class ListNode<T> {
    private T item;
    protected ListNode<T> next;
    protected ListNode<T> previous;
    protected int priority;
    
    public ListNode(T item, ListNode<T> next, int priority) {
        this.item = item;
        this.next = next;
        this.priority = priority;
    }
    
    public T getItem() {
        return item;
    }
    
    public T getListNode() {
        return item;
    }
    
    public ListNode<T> getNext() {
        return next;
    }
    
    public ListNode<T> getPrevious() {
        return previous;
    }
    
    public int getPriority() {
        return priority;
    }
    
    public void setNext(ListNode nextNode) {
    this.next = nextNode;
}
}
