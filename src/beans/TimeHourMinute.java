/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author MpUser
 */
public class TimeHourMinute {
    private int hour;
    private int minute;

    public TimeHourMinute() {
        hour = 0;
        minute = 0;
    }

    public TimeHourMinute(int hour) {
        this.hour = hour;
        this.minute = 0;
    }

    
    public TimeHourMinute(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    @Override
    public String toString() {
        return String.format("%02d:%02d", hour, minute);
    }
    
    
    
}
