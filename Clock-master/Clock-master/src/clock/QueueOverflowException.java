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
public class QueueOverflowException extends Exception {

    public QueueOverflowException() {
        super("Queue is full");
    }
}
