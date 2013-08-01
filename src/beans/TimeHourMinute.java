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
    
    public TimeHourMinute addTHM(TimeHourMinute thm){
//        int newHour = 0;
//        int newMinute = 0;
//        newHour = this.hour + thm.hour + (this.minute + thm.minute)/60;
//        newMinute = (this.minute + thm.minute)%60;
        int oldMinute = hour*60 + minute;
        int newMinute = thm.hour*60 + thm.minute;
        int resultMinute = oldMinute + newMinute;
        int newHour = resultMinute/60;
        newMinute = resultMinute %60;
        return new TimeHourMinute(newHour, newMinute);
    }
    
    public TimeHourMinute subTHM(TimeHourMinute thm){
        int oldMinute = hour*60 + minute;
        int newMinute = thm.hour*60 + thm.minute;
        int resultMinute = oldMinute - newMinute;
        int newHour = resultMinute/60;
        newMinute = resultMinute %60;
        return new TimeHourMinute(newHour, newMinute);
    }

    @Override
    public String toString() {
        return String.format("%02d:%02d", hour, minute);
    }
    
    
    
}
