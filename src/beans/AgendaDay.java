/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import business.EventUtil;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import tables.EbEvents;
import tables.EbReleventempdate;

/**
 *
 * @author jsmaya
 */

class ListSurvey extends Thread {

    private AgendaDay day;
    public ListSurvey(AgendaDay day) {
        this.day = day;
    }

    
    @Override
    public void run() {
        int size;
        int oldsize;
        size = day.getEventList().size();
        day.getBtMore().setText(size+"");
        oldsize = size;
        while(true){
            try{
                if(oldsize != size){
                    System.out.println(oldsize+"  "+size);
                }
                oldsize = size;
                if(oldsize == 0 && size == 1){
                    JOptionPane.showMessageDialog(day, "ERROR");
                }
                if(Integer.parseInt(day.getBtMore().getText())>size){
                    System.out.println("ERROR");
                }else{
                    day.getBtMore().setText(size+"");
                }
                size = day.getEventList().size();
//                sleep(5);
//            }catch(InterruptedException ie){
//                System.out.println("interrupted");
            }catch(Exception e){
                
            }
        }
    }
    
}
public class AgendaDay extends javax.swing.JPanel {
    private Calendar dateOfDay;
    private AgendaWeek parentWeek;
    private List <AgendaEvent> eventList;
    private javax.swing.JLabel dayNumber;
    private AgendaUser user;
    private boolean isToday;
    private JButton btAddEvent, btMore, btnb;
    private JScrollPane jScrollPane;
    public static int cpt = 0;
    private JPanel contentPane;
    
    public AgendaDay(){
        dateOfDay = Calendar.getInstance();
        this.setPreferredSize(new Dimension(64, 96));
        setLayout(new GridLayout(0,1));
//        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        eventList = new ArrayList();
        
        //Set larger borders for today
        if((this.dateOfDay.get(Calendar.DAY_OF_YEAR)== Calendar.getInstance().
                get(Calendar.DAY_OF_YEAR))&&(this.dateOfDay.
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
    
    public AgendaDay(AgendaWeek parent, Calendar dateOfDay, AgendaUser user){
        
        parentWeek = parent;
        this.user = user;
        DropTarget dropTarget;
        dropTarget = new DropTarget(this, DnDConstants.ACTION_MOVE, 
                new MyDropTargetListImp(this), true, null);
        this.setDropTarget(dropTarget);
        
        this.setPreferredSize(new Dimension(64, 156));
        setLayout(new BorderLayout());
        
        eventList = new ArrayList();
        contentPane = new JPanel();
        contentPane.setLayout(new GridLayout(0,1));
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
        }
        btMore = new JButton("...");
        btMore.setMargin(new java.awt.Insets(1, 13, 1, 13));
        btMore.setContentAreaFilled(false);
        btMore.setBackground(getBackground().brighter());
        btMore.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae) {
                displayEvents();
            }
            
            
        });
        btAddEvent = new JButton("new");
        btAddEvent.setMargin(new java.awt.Insets(1, 13, 1, 13));
        btAddEvent.setContentAreaFilled(false);
        btAddEvent.setBackground(getBackground());
        btAddEvent.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae) {
                addEvent();
            }
            
        });

        btnb = new JButton("nb");
        btnb.setMargin(new java.awt.Insets(1, 13, 1, 13));
        btnb.setContentAreaFilled(false);
        btnb.setBackground(getBackground());
        btnb.addActionListener(new ActionListener(){
            ListSurvey ls = new ListSurvey(AgendaDay.this);
            @Override
            public void actionPerformed(ActionEvent ae) {
                ls.start();
            }
            
        });
        contentPane.setBackground(getBackground());
        dayNumber = new javax.swing.JLabel
                (""+dateOfDay.get(Calendar.DAY_OF_MONTH)+" "+dateOfDay.
                getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.FRENCH)
                +" "+dateOfDay.get(Calendar.YEAR)+" "+this.hashCode());
        add(btAddEvent, BorderLayout.PAGE_END);
//        displayEvents();
    }
    
     public AgendaDay(AgendaWeek parent, Calendar dateOfDay, List<EbEvents> list, AgendaUser user){
        
        this.user = user;
        parentWeek = parent;
        
        DropTarget dropTarget;
        dropTarget = new DropTarget(this, DnDConstants.ACTION_MOVE, 
                new MyDropTargetListImp(this), true, null);
        this.setDropTarget(dropTarget);
        
        this.setPreferredSize(new Dimension(64, 156));
        setLayout(new BorderLayout());
        
        eventList = new ArrayList();
        contentPane = new JPanel();
        contentPane.setLayout(new GridLayout(0,1));
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
        }
        btMore = new JButton("...");
        btMore.setMargin(new java.awt.Insets(1, 13, 1, 13));
        btMore.setContentAreaFilled(false);
        btMore.setBackground(getBackground().brighter());
        btMore.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae) {
                showMore();
            }
            
            
        });
        btAddEvent = new JButton("new");
        btAddEvent.setMargin(new java.awt.Insets(1, 13, 1, 13));
        btAddEvent.setContentAreaFilled(false);
        btAddEvent.setBackground(getBackground());
        btAddEvent.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae) {
                addEvent();
            }
            
        });
//
//        btnb = new JButton("nb");
//        btnb.setMargin(new java.awt.Insets(1, 13, 1, 13));
//        btnb.setContentAreaFilled(false);
//        btnb.setBackground(getBackground());
//        btnb.addActionListener(new ActionListener(){
//            ListSurvey ls = new ListSurvey(AgendaDay.this);
//            @Override
//            public void actionPerformed(ActionEvent ae) {
//                ls.start();
//            }
//            
//        });
        contentPane.setBackground(getBackground());
        dayNumber = new javax.swing.JLabel
                (""+dateOfDay.get(Calendar.DAY_OF_MONTH)+" "+dateOfDay.
                getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.FRENCH));
//                +" "+dateOfDay.get(Calendar.YEAR)+" "+this.hashCode());
        add(dayNumber, BorderLayout.PAGE_START);
        add(contentPane, BorderLayout.CENTER);
        add(btAddEvent, BorderLayout.PAGE_END);
        addFromList(list);
        displayEvents();
        
        
    }

    private void addFromList(List<EbEvents> list){
        System.out.println("adding from list");
        for(EbEvents ee : list){
            Date from = ee.getEbEventsStartDate();
            Date to = ee.getEbEventsEndDate();
            from.setTime(from.getTime()-86400);
            to.setTime(to.getTime()+86400);
            if(this.dateOfDay.getTime().before(to)
                    &&this.dateOfDay.getTime().after(from)){
                EbReleventempdate eventDate =EventUtil.getEventUser(ee.getIdebEvent(), this.user.getId());
                if(eventDate!=null){
                    eventList.add(new AgendaEvent(this, ee.getEbEventsName(), 
                            ee.getIdebEvent(), 
                            eventDate.getEbRelEventEmpDateStart(),
                            eventDate.getEbRelEventEmpDateEnd()));
                }
            }
        }
    }
    
    public JPanel getContentPane() {
        return contentPane;
    }

    public List<AgendaEvent> getEventList() {
        return eventList;
    }

    public JButton getBtMore() {
        return btMore;
    }

    private void showMore(){
        JList jListEvent = new JList();
        for(AgendaEvent e : eventList){
            jListEvent.add(e);
        }
        JOptionPane.showOptionDialog(this, jListEvent, "",
                JOptionPane.CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null, eventList.toArray(), null);
    }
    
    
    private void addEvent(){
//        JOptionPane.showInputDialog(this.btAddEvent, new AgendaNewEvent(parentWeek.getUserList()));
        JOptionPane.showOptionDialog(this.btAddEvent, new AgendaNewEvent(parentWeek.getUserList()) ,
                    "Nouvel évènement",JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
        eventList.add(new AgendaEvent(this, "event "+cpt));
        System.out.println("hash from start day eventlist "+ eventList.hashCode());
        System.out.println("hash from start day "+this.hashCode());
        cpt++;
        displayEvents();
    }
    
    public void displayEvents(){  
        System.out.println("nb events before remove all: "+contentPane.getComponentCount()+" list: "+eventList.size());
        contentPane.removeAll();
        System.out.println("nb events after remove all: "+contentPane.getComponentCount()+" list: "+eventList.size());
        if(eventList.size()>2){
//            System.out.println(eventList.hashCode());
            for(int i =0; i<2; i++){
                AgendaEvent ae = eventList.get(i);
                contentPane.add(ae);
                contentPane.add(new JSeparator());
                System.out.println(i);
            }
            contentPane.add(btMore);
        }else{
//            System.out.println(eventList.hashCode());
            for(AgendaEvent ae : eventList){
                System.out.println(ae.getSummary());
                contentPane.add(ae);
                contentPane.add(new JSeparator());
            }
        }
        System.out.println("nb events : "+contentPane.getComponentCount());
        System.out.println(getDayNumber()+" refreshed");
        validate();
        System.out.println("nb events after validate: "+contentPane.getComponentCount()+" list: "+eventList.size());
    }

    public String getDayNumber() {
        return dayNumber.getText();
    }

    public Calendar getDateOfDay() {
        return dateOfDay;
    }
    
    
    
    public void dropAgendaEvent(AgendaEvent ae){        
        System.out.println("hash from new day "+this.hashCode());
        System.out.println("hash from new "+eventList.hashCode());
//        ae.setParentDay(this);
        this.eventList.add(new AgendaEvent(ae));
        System.out.println("item dropped onto "+getDayNumber());
        System.out.println("============================================");
        displayEvents();
    }
    
    public void dragAgendaEvent(AgendaEvent ae){
        System.out.println("hash from old day "+this.hashCode());
        System.out.println("hash from old "+eventList.hashCode());

        System.out.println(this.eventList.indexOf(ae));
        this.eventList.remove(ae);
        System.out.println("item dragged from "+getDayNumber());        
        System.out.println("============================================");

        displayEvents();
    }
}
