/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clock;

import java.util.PriorityQueue;

/**
 *This class is responsible for handling all alarm data.
 * @author 07014975
 */
public class Alarm {
    //PriorityQueue<Integer> alarmQueue = new PriorityQueue<Integer>();
    PriorityQueue<String> alarmView = new PriorityQueue<String>();
    
    /**
     *
     * @return
     */ /*
    public PriorityQueue<Integer> alarmMaker() {
        return alarmQueue;
    }
    
    /**
     *This method is responsible for initialising the alarmView priority queue.
     * @return alarmView this returns the value of the alarmView queue.
     */
    public PriorityQueue<String> alarmViewMaker() {
        return alarmView;
    }
    
    /**
     *
     * @param alarmTime
     * @return
     */ /*
    public PriorityQueue<Integer> alarmAdder(int alarmTime) {
        //int alarmEntry = alarmTime;
        alarmQueue.add(alarmTime);
        System.out.println(alarmQueue);
        //Model a = new Model();
        return alarmQueue;
    }
    
    /**
     *This method is responsible for adding alarms to the queue.
     * @param alarmTime is the value of the alarm being added to the queue
     * @return alarmView this returns the value of the alarmView priority queue.
     */
    public PriorityQueue<String> alarmViewAdder(String alarmTime) {
        //int alarmEntry = alarmTime;
        alarmView.add(alarmTime);
        System.out.println(alarmView);
        //Model a = new Model();
        return alarmView;
    }
    
    /**
     *
     * @param removeObject
     * @return
     */ /*
    public PriorityQueue<Integer> alarmRemover(int removeObject) {
        if (alarmQueue.contains(removeObject)) {
            alarmQueue.remove(removeObject);
        }
        return alarmQueue;
    }
    
    /**
     *This method is responsible for removing an alarm from the queue.
     * @param removeView is the value of the alarm to be removed from the queue.
     * @return alarmView this returns the value of the alarmView queue.
     */
    public PriorityQueue<String> alarmViewRemover(String removeView) {
        if (alarmView.contains(removeView)) {
            alarmView.remove(removeView);
        }
        return alarmView;
    }
    
    /**
     *
     * @return
     */ /*
    public Integer head() {
        int head = alarmQueue.peek();
        System.out.println(head);
        return head;
    }
*/
}
