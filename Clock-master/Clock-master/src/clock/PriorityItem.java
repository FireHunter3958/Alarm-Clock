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
public class PriorityItem<T> {

    private final T item;
    private final int priority;

    public PriorityItem(T item, int priority) {
        this.item = item;
        this.priority = priority;
    }

    public T getItem() {
        return item;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return "(" + getItem() + ", " + getPriority() + ")";
    }
}

