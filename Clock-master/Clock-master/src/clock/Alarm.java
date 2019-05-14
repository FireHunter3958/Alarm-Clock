/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clock;

import java.util.PriorityQueue;

/**
 *
 * @author FireHunter3958
 */
public class Alarm {
    PriorityQueue<Integer> alarmQueue = new PriorityQueue<Integer>();
    PriorityQueue<String> alarmView = new PriorityQueue<String>();
    
    public PriorityQueue<Integer> alarmMaker() {
        return alarmQueue;
    }
    
    public PriorityQueue<String> alarmViewMaker() {
        return alarmView;
    }
    
    public PriorityQueue<Integer> alarmAdder(int alarmTime) {
        //int alarmEntry = alarmTime;
        alarmQueue.add(alarmTime);
        System.out.println(alarmQueue);
        //Model a = new Model();
        return alarmQueue;
    }
    
    public PriorityQueue<String> alarmViewAdder(String alarmTime) {
        //int alarmEntry = alarmTime;
        alarmView.add(alarmTime);
        System.out.println(alarmView);
        //Model a = new Model();
        return alarmView;
    }
    
    public PriorityQueue<Integer> alarmRemover(int removeObject) {
        if (alarmQueue.contains(removeObject)) {
            alarmQueue.remove(removeObject);
        }
        return alarmQueue;
    }
    
    public PriorityQueue<String> alarmViewRemover(String removeView) {
        if (alarmView.contains(removeView)) {
            alarmView.remove(removeView);
        }
        return alarmView;
    }
    
    
    public Integer head() {
        int head = alarmQueue.peek();
        System.out.println(head);
        return head;
    }
}
