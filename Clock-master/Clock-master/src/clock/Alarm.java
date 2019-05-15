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
     *This method is responsible for initialising the alarmView priority queue.
     * @return alarmView this returns the value of the alarmView queue.
     */
    public PriorityQueue<String> alarmViewMaker() {
        return alarmView;
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
}
