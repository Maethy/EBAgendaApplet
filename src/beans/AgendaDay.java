/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import javax.swing.JButton;
import javax.swing.JPanel;
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
    private JPanel contentPane;
    
    public AgendaDay(){
        dateOfDay = Calendar.getInstance();
        this.setPreferredSize(new Dimension(64, 96));
        setLayout(new GridLayout(0,1));
//        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        eventList = new ArrayList<AgendaEvent>();
        this.dateOfDay = dateOfDay;
        //Set larger borders for today
        if((this.dateOfDay.get(Calendar.DAY_OF_YEAR)== Calendar.getInstance().
                get(Calendar.DAY_OF_YEAR))&&(this.dateOfDay.
                get(Calendar.WEEK_OF_YEAR)== Calendar.getInstance().
                get(Calendar.WEEK_OF_YEAR))&&(this.dateOfDay.
                get(Calendar.YEAR)== Calendar.getInstance().
                get(Calendar.YEAR))){
            setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 51), 2));
        }else{
            setBorder(javax.swing.BorderFactory.createEtchedBorder());
        }
        //Set color background for current week
        if((this.dateOfDay.get(Calendar.WEEK_OF_YEAR)== Calendar.getInstance().
                get(Calendar.WEEK_OF_YEAR))&&(this.dateOfDay.
                get(Calendar.YEAR)== Calendar.getInstance().
                get(Calendar.YEAR))){
            this.setBackground(new java.awt.Color(50, 150, 230));
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
                (""+dateOfDay.get(Calendar.DAY_OF_MONTH)+" "+dateOfDay.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.FRENCH)+" "+dateOfDay.get(Calendar.YEAR));
                   
        displayEvents();
    }
    
    public AgendaDay(Calendar dateOfDay){

        this.setPreferredSize(new Dimension(64, 128));
        setLayout(new BorderLayout());
        
//        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        eventList = new ArrayList<AgendaEvent>();
        contentPane = new JPanel();
        contentPane.setLayout(new GridLayout(0,1));
        this.dateOfDay = dateOfDay;
//        //Set larger borders for today
        if((this.dateOfDay.get(Calendar.DAY_OF_YEAR)== Calendar.getInstance().
                get(Calendar.DAY_OF_YEAR))&&(this.dateOfDay.
                get(Calendar.WEEK_OF_YEAR)== Calendar.getInstance().
                get(Calendar.WEEK_OF_YEAR))&&(this.dateOfDay.
                get(Calendar.YEAR)== Calendar.getInstance().
                get(Calendar.YEAR))){
            setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 51), 2));
        }else{
            setBorder(javax.swing.BorderFactory.createEtchedBorder());
        }
//        //Set color background for current week
        if((this.dateOfDay.get(Calendar.WEEK_OF_YEAR)== Calendar.getInstance().
                get(Calendar.WEEK_OF_YEAR))&&(this.dateOfDay.
                get(Calendar.YEAR)== Calendar.getInstance().
                get(Calendar.YEAR))){
            this.setBackground(new java.awt.Color(50, 150, 230));
        }
        btMore = new JButton("...");
        btMore.setMargin(new java.awt.Insets(1, 13, 1, 13));
        btMore.setContentAreaFilled(false);
        btMore.setBackground(getBackground().brighter());
        btAddEvent = new JButton("new");
        btAddEvent.setMargin(new java.awt.Insets(1, 13, 1, 13));
        btAddEvent.setContentAreaFilled(false);
        btAddEvent.setBackground(getBackground());
        add(btAddEvent);
        btAddEvent.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae) {
                addEvent();
            }
            
        });
        contentPane.setBackground(getBackground());
//        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.PAGE_AXIS));
        dayNumber = new javax.swing.JLabel
                (""+dateOfDay.get(Calendar.DAY_OF_MONTH)+" "+dateOfDay.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.FRENCH)+" "+dateOfDay.get(Calendar.YEAR));
        add(dayNumber, BorderLayout.PAGE_START);
        add(contentPane, BorderLayout.CENTER);
        add(btAddEvent, BorderLayout.PAGE_END);
        displayEvents();
    }
    
    private void addEvent(){
        eventList.add(new AgendaEvent(this, "event "+cpt));
        cpt++;
        displayEvents();
    }
    
    private void displayEvents(){  
        contentPane.removeAll();
//        add(dayNumber);
//        add(new JSeparator());
        if(eventList.size()>2){
            for(int i =0; i<2; i++){
                contentPane.add(eventList.get(i));
            }
            contentPane.add(btMore);
        }else{
            for(AgendaEvent ae : eventList){
                System.out.println(ae);
                contentPane.add(ae);
                contentPane.add(new JSeparator());
            }
        }
//        add(btAddEvent);
        revalidate();
    }
}
