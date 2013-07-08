/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import commons.Constants;
import java.awt.GridLayout;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author jsmaya
 */
public class AgendaWeek extends javax.swing.JPanel {
    private Calendar startOfWeek;
    private List <AgendaDay> dayList;
    private List <AgendaUser> userList;
    
    public AgendaWeek(Calendar startOfWeek){
        //int rows = userList.size();
        this.startOfWeek = startOfWeek;
        setLayout(new GridLayout(1,7));


        Calendar dateOfDay = Calendar.getInstance();
        dateOfDay.setTimeInMillis(startOfWeek.getTimeInMillis());
        int currentDay = dateOfDay.get((Calendar.DAY_OF_WEEK)-1)%7;
//        System.out.println(currentDay);
        
        for(int i=-currentDay; i<(-currentDay+7); i++){
            dateOfDay.setTimeInMillis(startOfWeek.getTimeInMillis()+(i*86400000));
//            System.out.println(dateOfDay.getFirstDayOfWeek());
//            System.out.println(dateOfDay.get(Calendar.DAY_OF_MONTH));
//            System.out.println((Constants.DAY_OF_WEEK[Math.abs(dateOfDay.get(Calendar.DAY_OF_WEEK)-2)%7]));
//            System.out.println(dateOfDay.get(Calendar.DAY_OF_WEEK_IN_MONTH));
//            System.out.println("===============");
            add(new AgendaDay(dateOfDay));

        }
    }
}
