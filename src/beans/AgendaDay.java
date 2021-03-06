/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import business.EventUtil;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.WindowConstants;
import tables.EbEvents;
import tables.EbReleventempdate;

/**
 *
 * @author jsmaya
 */

public class AgendaDay extends javax.swing.JPanel {
    private Calendar endOfDay;
    private Calendar debutOfDay;
    private AgendaWeek parentWeek;
    private List <AgendaEvent> eventList;
    private javax.swing.JLabel dayNumber;
    private AgendaUser user;
    private boolean isToday;
    private JButton btAddEvent, btMore;
    private JPanel contentPane;
    
//    public AgendaDay(){
//        endOfDay = Calendar.getInstance();
//        this.setPreferredSize(new Dimension(64, 96));
//        setLayout(new GridLayout(0,1));
//        eventList = new ArrayList();
//        
//        //Set larger borders for today
//        if((this.endOfDay.get(Calendar.DAY_OF_YEAR)== Calendar.getInstance().
//                get(Calendar.DAY_OF_YEAR))&&(this.endOfDay.
//                get(Calendar.YEAR)== Calendar.getInstance().
//                get(Calendar.YEAR))){
//            setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 51), 2));
//        }else{
//            setBorder(javax.swing.BorderFactory.createEtchedBorder());
//        }
//        
//        //Set color background for current week
//        if((this.endOfDay.get(Calendar.WEEK_OF_YEAR)== Calendar.getInstance().
//                get(Calendar.WEEK_OF_YEAR))&&(this.endOfDay.
//                get(Calendar.YEAR)== Calendar.getInstance().
//                get(Calendar.YEAR))){
//            this.setBackground(new java.awt.Color(50, 150, 230));
//        }else{
//            setBorder(javax.swing.BorderFactory.createEtchedBorder());
//        }
//        btAddEvent = new JButton("new");
//        btAddEvent.setMargin(new java.awt.Insets(1, 13, 1, 13));
//        btAddEvent.setContentAreaFilled(false);
//        btAddEvent.addActionListener(new ActionListener(){
//
//            @Override
//            public void actionPerformed(ActionEvent ae) {
//                addEvent();
//            }
//            
//        });
////        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.PAGE_AXIS));
//        dayNumber = new javax.swing.JLabel
//                (""+endOfDay.get(Calendar.DAY_OF_MONTH)+" "+endOfDay.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.FRENCH)+" "+endOfDay.get(Calendar.YEAR));
//        displayEvents();    
//  
//        
//    }
    
    public AgendaDay(AgendaWeek parent, Calendar dateOfDay, AgendaUser user){
        
        parentWeek = parent;
        this.user = user;
        DropTarget dropTarget;
        dropTarget = new DropTarget(this, DnDConstants.ACTION_MOVE, 
                new MyDropTargetListImp(this), true, null);
        this.setDropTarget(dropTarget);
        
        this.setPreferredSize(new Dimension(64, 156));
        setLayout(new GridBagLayout());
        
        eventList = new ArrayList();
        contentPane = new JPanel();
        contentPane.setLayout(new GridBagLayout());
        this.debutOfDay = Calendar.getInstance();
        this.debutOfDay.setTimeInMillis(dateOfDay.getTimeInMillis());
        this.debutOfDay.set(Calendar.HOUR_OF_DAY, 0);
        this.debutOfDay.set(Calendar.MINUTE, 0);
        this.debutOfDay.add(Calendar.MINUTE, -1);
        this.endOfDay = dateOfDay;
        this.endOfDay.set(Calendar.HOUR_OF_DAY, 23);
        this.endOfDay.set(Calendar.MINUTE, 59);
        
        //Set larger borders for today
        if((this.endOfDay.get(Calendar.DAY_OF_YEAR)== Calendar.getInstance().
                get(Calendar.DAY_OF_YEAR))&&(this.endOfDay.
                get(Calendar.WEEK_OF_YEAR)== Calendar.getInstance().
                get(Calendar.WEEK_OF_YEAR))&&(this.endOfDay.
                get(Calendar.YEAR)== Calendar.getInstance().
                get(Calendar.YEAR))){
            setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 51), 2));
        }else{
            setBorder(javax.swing.BorderFactory.createEtchedBorder());
        }
        
        //Set color background for current week
        if((this.endOfDay.get(Calendar.WEEK_OF_YEAR)== Calendar.getInstance().
                get(Calendar.WEEK_OF_YEAR))&&(this.endOfDay.
                get(Calendar.YEAR)== Calendar.getInstance().
                get(Calendar.YEAR))){
            this.setBackground(new java.awt.Color(50, 150, 230));
        }
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

        contentPane.setBackground(getBackground());
        dayNumber = new javax.swing.JLabel
                (dateOfDay.get(Calendar.DAY_OF_MONTH)+" "+dateOfDay.
                getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.FRENCH));
        GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.gridx=0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(dayNumber, gridBagConstraints);//, BorderLayout.PAGE_START);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.gridx=0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(contentPane,gridBagConstraints);// BorderLayout.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.gridx=0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(btAddEvent, gridBagConstraints);//BorderLayout.PAGE_END);
        setBackground(this.parentWeek.getColor());
        setBackground(this.parentWeek.getColor());
//        displayEvents();
    }
    
     public AgendaDay(AgendaWeek parent, Calendar dateOfDay, List<EbEvents> list, AgendaUser user){
        
        this.user = user;
        parentWeek = parent;
        
        DropTarget dropTarget;
        dropTarget = new DropTarget(this, DnDConstants.ACTION_MOVE, 
                new MyDropTargetListImp(this), true, null);
        this.setDropTarget(dropTarget);
        
        setLayout(new GridBagLayout());//BorderLayout());
        
        eventList = new ArrayList();
        contentPane = new JPanel();
        contentPane.setLayout(new GridBagLayout());//GridLayout(0,1));
        this.debutOfDay = Calendar.getInstance();
        this.debutOfDay.setTimeInMillis(dateOfDay.getTimeInMillis());
        this.debutOfDay.set(Calendar.HOUR_OF_DAY, 0);
        this.debutOfDay.set(Calendar.MINUTE, 0);
        this.debutOfDay.add(Calendar.MINUTE, -1);
        this.endOfDay = dateOfDay;
        this.endOfDay.set(Calendar.HOUR_OF_DAY, 23);
        this.endOfDay.set(Calendar.MINUTE, 59);

        //Set larger borders for today
        if((this.endOfDay.get(Calendar.DAY_OF_YEAR)== Calendar.getInstance().
                get(Calendar.DAY_OF_YEAR))&&(this.endOfDay.
                get(Calendar.WEEK_OF_YEAR)== Calendar.getInstance().
                get(Calendar.WEEK_OF_YEAR))&&(this.endOfDay.
                get(Calendar.YEAR)== Calendar.getInstance().
                get(Calendar.YEAR))){
            setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 51), 2));
        }else{
            setBorder(javax.swing.BorderFactory.createEtchedBorder());
        }
        
        //Set color background for current week
        if((this.endOfDay.get(Calendar.WEEK_OF_YEAR)== Calendar.getInstance().
                get(Calendar.WEEK_OF_YEAR))&&(this.endOfDay.
                get(Calendar.YEAR)== Calendar.getInstance().
                get(Calendar.YEAR))){
            this.setBackground(new java.awt.Color(50, 150, 230));
        }
        
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

        contentPane.setBackground(getBackground());
        dayNumber = new javax.swing.JLabel
                (dateOfDay.get(Calendar.DAY_OF_MONTH)+" "+dateOfDay.
                getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.FRENCH));
        GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.gridx=0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(dayNumber, gridBagConstraints);//, BorderLayout.PAGE_START);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.gridx=0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(contentPane,gridBagConstraints);// BorderLayout.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.gridx=0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(btAddEvent, gridBagConstraints);//BorderLayout.PAGE_END);
        setBackground(this.parentWeek.getColor());
        addFromList(list);
        if(eventList.isEmpty())this.setPreferredSize(new Dimension(64, 156));
        displayEvents();        
        
    }
     
     public void reloadDay(List <EbEvents> list){
//        this.contentPane.removeAll();
        this.eventList.clear();
        this.contentPane.revalidate();
        addFromList(list);
        if(eventList.isEmpty())this.setPreferredSize(new Dimension(64, 156));
        displayEvents(); 
     }

    public AgendaUser getUser() {
        return user;
    }
     
     
    private void addFromList(List<EbEvents> list){
        for(EbEvents ee : list){
//            System.out.println("parsing "+ee.getEbEventsName());
            Calendar from = Calendar.getInstance();
            from.setTime(ee.getEbEventsStartDate());
            Calendar to = Calendar.getInstance();
            to.setTime(ee.getEbEventsEndDate());
            if((this.debutOfDay.before(to))
                    &&this.endOfDay.after(from)){
                List<EbReleventempdate> eventDate =EventUtil.getEventUser(ee.getIdebEvent(), this.user.getId());
                if(eventDate.size()>0){
                    for(EbReleventempdate eventEmpDate : eventDate){
                        Calendar eventCalendar = Calendar.getInstance();
                        eventCalendar.setTime(eventEmpDate.getEbRelEventEmpDateStart());
                        if(eventCalendar.get(Calendar.DAY_OF_YEAR)==this.endOfDay.get(Calendar.DAY_OF_YEAR)){
//                            System.out.println("adding "+ee.getEbEventsName());
                            Calendar cal1, cal2;
                            cal1 = Calendar.getInstance();
                            cal2 = Calendar.getInstance();
                            cal1.setTime(eventEmpDate.getEbRelEventEmpDateStart());
                            cal2.setTime(eventEmpDate.getEbRelEventEmpDateEnd());
                            eventList.add(new AgendaEvent(this, ee.getEbEventsName(), 
                                    eventEmpDate.getIdebRelEventEmpDate(),
                                    cal1, cal2, eventEmpDate.isEbRelEventEmpDateFullDay()));
                        }
                    }
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
        JPanel eventListPanel = new JPanel();
        eventListPanel.setLayout(new GridLayout(0,4));
        for(AgendaEvent e : eventList){
            eventListPanel.add(new AgendaEvent(e));
        }
        Object [] panelTab = new Object[1];
        panelTab[0] = eventListPanel;
        
        JOptionPane pane = new JOptionPane();
        pane.setOptions(panelTab);
        pane.setMessage("Evènements");
        pane.setOptionType(JOptionPane.CANCEL_OPTION);
        JDialog dialog = pane.createDialog(this, "");
        
        dialog.setModal(false);
        dialog.setVisible(true);
        
//        JOptionPane.showOptionDialog(this, "évènements", "",
//                JOptionPane.CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE,
//                null, panelTab, null);
    }
    
    
    private void addEvent(){
//        JOptionPane.showInputDialog(this.btAddEvent, new AgendaNewEvent(parentWeek.getUserList()));
        AgendaNewEvent ane;
        JFrame newEventFrame = new JFrame("Nouvel évènement");
        if(eventList.size()>0){
            ane = new AgendaNewEvent
                    ((eventList.get(eventList.size()-1)).getEnd(),
                    newEventFrame);
        }else{
            Calendar today = Calendar.getInstance();
            today.setTime(this.endOfDay.getTime());
            today.set(Calendar.HOUR_OF_DAY, 9);
            today.set(Calendar.MINUTE, 0);
            ane = new AgendaNewEvent(today, newEventFrame);
        }
        newEventFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        newEventFrame.add(ane);
        newEventFrame.pack();
        newEventFrame.setVisible(true);
//    
//            
//            List<EbEvents> listOneEvent = new ArrayList();                              //Création d'une liste pour accueillir le nouvel évènement créé
//            listOneEvent.add(EventUtil.addEvent(ane.getEventName(), ane.getEventDesc(), //On crée le nouvel évènement, et on le récupère dans la liste
//                ane.getStartTime(), ane.getEndTime(), 
//                ane.getClient().getIdebCli(), ane.getEmployees()));                     
//                                                                                        
//            
//            EventUtil.gettApp().reload();
            //addFromList(listOneEvent);              //On va maintenant récupérer les objets pour cet évènement pour les jours et utilisateurs concernés.
            //displayEvents();                        //On rafraichit l'affichage des évènements.
        
    }
    
    public void displayEvents(){  
        contentPane.removeAll();        
        GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx=0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        if(eventList.size()>2){
            for(int i =0; i<2; i++){
                AgendaEvent ae = eventList.get(i);
                contentPane.add(ae, gridBagConstraints);
                contentPane.add(new JSeparator(), gridBagConstraints);
            }
            btMore = new JButton("...");
            btMore.setMargin(new java.awt.Insets(1, 13, 1, 13));
            btMore.setContentAreaFilled(false);
            btMore.setOpaque(true);
            btMore.setBackground(this.parentWeek.getColor());
            btMore.addActionListener(new ActionListener(){

                @Override
                public void actionPerformed(ActionEvent ae) {
                    showMore();
                }


            });
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx=0;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.weighty = 1.0;
            gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
            contentPane.add(btMore, gridBagConstraints);
        }else{
            for(AgendaEvent ae : eventList){
//                System.out.println("displaying "+ae.getSummary());
                gridBagConstraints = new java.awt.GridBagConstraints();
                gridBagConstraints.gridx=0;
                gridBagConstraints.weightx = 1.0;
                gridBagConstraints.weighty = 1.0;
                gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
                contentPane.add(ae, gridBagConstraints);
                gridBagConstraints = new java.awt.GridBagConstraints();
                gridBagConstraints.gridx=0;
                gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
                contentPane.add(new JSeparator(), gridBagConstraints);
            }
        }
        revalidate();
    }

    
    public TimeHourMinute computeWorkTime(){
        long millis;
        long additionOfMillis=0;
        int hour = 0;
        int minute = 0;
        for(AgendaEvent ae : eventList){
            if(!ae.isIsWholeDayEvent()){
                millis = ae.getEnd().getTimeInMillis()-
                        ae.getStart().getTimeInMillis();
                additionOfMillis += millis;
            }
        }
        additionOfMillis = additionOfMillis/60000;
        hour =(int)additionOfMillis / 60;
        minute = (int)additionOfMillis % 60;
        TimeHourMinute thm = new TimeHourMinute(hour, minute);
        if(hour>0||minute>0){
//            System.out.println(this.endOfDay.getTime()+" THM: "+thm);
        }
        return thm;
    }
    
    public String getDayNumber() {
        return dayNumber.getText();
    }

    public Calendar getDateOfDay() {
        return endOfDay;
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
