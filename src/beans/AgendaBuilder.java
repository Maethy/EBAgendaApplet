/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import com.toedter.calendar.JCalendar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import javax.swing.JButton;

/**
 *
 * @author jsmaya
 */
public class AgendaBuilder {
    private Calendar startCalendar;
    private Calendar endCalendar;
    private JCalendar jcalendar;
    
    public AgendaBuilder () {
        JButton btGetDate = new JButton("get");
        JButton btResetCalendar = new JButton("set");
        
         startCalendar = Calendar.getInstance();
         btGetDate.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae) {
                startCalendar.setTime(AgendaBuilder.this.jcalendar.getDate());
                System.out.println(startCalendar.toString());
            }
            
        });
        btResetCalendar.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae) {
//                new AgendaPanel(startCalendar, null);
            }
        });
//        
//        add(jcalendar);
//        add(btGetDate);
//        add(btResetCalendar);
        
    }
    
//    public static AgendaPanel createAgenda(Calendar startCalendar, Calendar endCalendar){
//        return new AgendaPanel(startCalendar, endCalendar);
//    }
}
