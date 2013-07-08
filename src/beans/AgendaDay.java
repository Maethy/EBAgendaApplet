/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;

/**
 *
 * @author jsmaya
 */
public class AgendaDay extends javax.swing.JPanel {
    private Calendar dateOfDay;
    private AgendaWeek parentWeek;
    private List<AgendaEvent> eventList;
    private javax.swing.JLabel dayNumber;
    private boolean isToday;
    private JButton btAddEvent, btMore;
    private JScrollPane jScrollPane;
    public static int cpt = 0;
    
    public AgendaDay(Calendar dateOfDay){
        setMinimumSize(new Dimension(64, 96));
        eventList = new ArrayList<AgendaEvent>();
        this.dateOfDay = dateOfDay;
        if(this.dateOfDay.get(Calendar.DAY_OF_MONTH)==Calendar.getInstance().get(Calendar.DAY_OF_MONTH)){
            setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 51), 2));
        }else{
            setBorder(javax.swing.BorderFactory.createEtchedBorder());
        }
        btMore = new JButton("...");
        btMore.setMargin(new java.awt.Insets(1, 13, 1, 13));
        btMore.setContentAreaFilled(false);
        btAddEvent = new JButton("new");
        btAddEvent.setMargin(new java.awt.Insets(1, 13, 1, 13));
        btAddEvent.setContentAreaFilled(false);
        btAddEvent.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae) {
                addEvent();
            }
            
        });
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.PAGE_AXIS));
        dayNumber = new javax.swing.JLabel
                (""+dateOfDay.get(Calendar.DAY_OF_MONTH)+" "+dateOfDay.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.FRENCH));
        add(dayNumber);
        add(new JSeparator());
                
        add(btAddEvent);
        displayEvents();
    }
    
    private void addEvent(){
        eventList.add(new AgendaEvent("event "+cpt));
        cpt++;
        displayEvents();
    }
    
    private void displayEvents(){  
        removeAll();
        add(dayNumber);
        add(new JSeparator());
        if(eventList.size()>2){
            for(int i =0; i<2; i++){
                add(eventList.get(i));
            }
            add(btMore);
        }else{
            for(AgendaEvent ae : eventList){
                System.out.println(ae);
                add(ae);
                add(new JSeparator());
            }
        }
        add(btAddEvent);
        revalidate();
    }
}
