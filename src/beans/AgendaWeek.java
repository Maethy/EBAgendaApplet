/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import business.EventUtil;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import tables.EbEvents;
import tables.EbWorktimetable;

/**
 *
 * @author jsmaya
 */
public class AgendaWeek extends javax.swing.JPanel {
    private Calendar dateOfDay, endOfWeek;
    private List <AgendaDay> dayList;
    private List <AgendaUser> userList;
    private List<EbEvents> preciseList;   
    private List<JLabel> labelList;
    private GridBagConstraints gridBagConstraints;
    private AgendaPanel parentAgenda;
//    private JPanel header;
    private JLabel lbUserName;
    private JLabel lbUserTime;
    private Color color;
    private static boolean colorFlag = false;
    
    public AgendaWeek(AgendaPanel parent, Calendar startOfWeek, 
            List<EbEvents> ebEventsList, List<AgendaUser> userList){
        this.parentAgenda = parent;
        lbUserName =  new JLabel();
        endOfWeek = Calendar.getInstance();
        endOfWeek.setTimeInMillis(startOfWeek.getTimeInMillis());
        endOfWeek.add(Calendar.DAY_OF_YEAR, 7);
        this.color = this.getBackground();
        this.userList = userList;
        this.dayList = new ArrayList();
        this.labelList = new ArrayList();
        setLayout(new GridLayout(userList.size()+1, 8));
        setLayout(new GridBagLayout());
        preciseList = EventUtil.preciseEventList(startOfWeek, endOfWeek, ebEventsList);
        dateOfDay = Calendar.getInstance();
        dateOfDay.setTimeInMillis(startOfWeek.getTimeInMillis());
        JPanel userHeaderPanel = new JPanel();
        JLabel userHeader = new JLabel("semaine "+dateOfDay.get(Calendar.WEEK_OF_YEAR));
        userHeader.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        userHeaderPanel.add(userHeader);
        add(userHeaderPanel);
       
        for(int i=0; i<7; i++){
            String date = "<html><body><center>"
                    +commons.Constants.DAY_OF_WEEK[i]+"<br>"
                    +dateOfDay.get(Calendar.DAY_OF_MONTH)+"-"+(dateOfDay.
                    get(Calendar.MONTH)+1)+"-"+dateOfDay.get(Calendar.YEAR)
                    +"</center></body></html>";
            JPanel dayHeaderPanel = new JPanel();
            JLabel dayHeader = new javax.swing.JLabel(date);
            dayHeaderPanel.setLayout
                    (new BoxLayout(dayHeaderPanel, BoxLayout.PAGE_AXIS));
            dayHeaderPanel.add(dayHeader);
            dayHeader.setBorder(javax.swing.BorderFactory.createEtchedBorder());
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = i+1;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.weightx =1;
            gridBagConstraints.fill = GridBagConstraints.BOTH;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
            add(dayHeaderPanel, gridBagConstraints);
            dateOfDay.add(Calendar.DAY_OF_YEAR, 1);
        }
        dateOfDay.setTimeInMillis(startOfWeek.getTimeInMillis());
        for(int j=0; j<userList.size(); j++){
                if(!colorFlag){
                    color = new Color(210,210,210);
                    colorFlag=!colorFlag;
                }else{
                    color = new Color(240,240,240);
                    colorFlag=!colorFlag;
                }
                gridBagConstraints = new java.awt.GridBagConstraints();
                gridBagConstraints.gridx = 0;
//                gridBagConstraints.gridy = 0;
                gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
                gridBagConstraints.weightx = 1.0;
                gridBagConstraints.weighty = 1.0;
                gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            lbUserName =  new JLabel(userList.get(j).getInitiales());
            lbUserName.setOpaque(true);
            lbUserName.setBackground(color);
            lbUserTime = new JLabel();
            labelList.add(lbUserTime);
            add(lbUserTime, gridBagConstraints);
            TimeHourMinute timeOfWeek = new TimeHourMinute();
            TimeHourMinute timeOfWeekRemaining = new TimeHourMinute(38);
            for(int i=0; i<7; i++){
                AgendaDay aDay;
                Calendar newCal = Calendar.getInstance();
                newCal.setTimeInMillis(dateOfDay.getTimeInMillis());
                if(preciseList.isEmpty()){
                    aDay = new AgendaDay(this, newCal,userList.get(j));
                }else{
                    aDay = new AgendaDay(this, newCal, preciseList,userList.get(j));
                }
                gridBagConstraints = new java.awt.GridBagConstraints();
                gridBagConstraints.gridx = i+1;
//                gridBagConstraints.gridy = 0;
                gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
                gridBagConstraints.weightx = 1.0;
                gridBagConstraints.weighty = 1.0;
                gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
                dayList.add(aDay);
                add(aDay, gridBagConstraints);
                dateOfDay.add(Calendar.DAY_OF_YEAR, 1);
                TimeHourMinute timeOfDay = aDay.computeWorkTime();
//                System.out.println(aDay.getDayNumber()+" "+timeOfDay);
                EbWorktimetable wtt = EventUtil.getWorkTime
                                (aDay.getUser().getId(), dateOfDay);
                        if(wtt == null){
                            wtt = EventUtil.addWorkTime(aDay.getUser().getId(), dateOfDay,
                                timeOfDay.getHour(), timeOfDay.getMinute());
                        }
                timeOfWeek = timeOfWeek.addTHM(timeOfDay);
            }
            timeOfWeekRemaining = timeOfWeekRemaining.subTHM(timeOfWeek);
            TimeHourMinute credit = new TimeHourMinute();
            if(timeOfWeek.getHour()>=38 && timeOfWeek.getMinute()>=0){
                credit.setHour(timeOfWeek.getHour()-38);
                credit.setMinute(timeOfWeek.getMinute());
                timeOfWeekRemaining.setHour(0);
                timeOfWeekRemaining.setMinute(0);
            }
            lbUserTime.setText("<html><body><b>"+userList.get(j).getInitiales()+"</b><br>"
                        + "Temps de travail<br>cette semaine: <b>"+timeOfWeek.toString()+
                        "</b><br>Temps de travail<br>restant: <b>"
                        + timeOfWeekRemaining.toString()+"</b>"
                    + "<br>Crédit <b>: "+credit.toString()+"</b></body></html>");

            dateOfDay.setTimeInMillis(startOfWeek.getTimeInMillis());
        }
    }

    public void reloadWeek(List<EbEvents> ebEventsList){
        
        List<EbEvents> rePreciseList = EventUtil.preciseEventList
                (dateOfDay, endOfWeek, ebEventsList);
        if(!rePreciseList.equals(this.preciseList)){
            for(EbEvents event : rePreciseList){
//                System.out.println(event.getEbEventsName());
            }
//            System.out.println(dateOfDay.getTime()+" to "+endOfWeek.getTime()+" not equals");
    //        System.out.println(ebEventsList.size()+"/"+preciseList.size());
            for(int j=0; j<userList.size(); j++){
                    if(!colorFlag){
                        color = new Color(210,210,210);
                        colorFlag=!colorFlag;
                    }else{
                        color = new Color(240,240,240);
                        colorFlag=!colorFlag;
                    }

            TimeHourMinute timeOfWeek = new TimeHourMinute();
            TimeHourMinute timeOfWeekRemaining = new TimeHourMinute(38);
            int cpt = 0;
            for(int k=j*7; k<(j*7+6);k++){
                Calendar newCal = Calendar.getInstance();
                newCal.setTimeInMillis(dateOfDay.getTimeInMillis());

                dayList.get(k).reloadDay(rePreciseList);


                TimeHourMinute timeOfDay = dayList.get(k).computeWorkTime();
                EbWorktimetable wtt = EventUtil.getWorkTime
                        (dayList.get(k).getUser().getId(), dateOfDay);
                if(wtt == null){
                    wtt = EventUtil.addWorkTime(dayList.get(k).getUser().getId(), dateOfDay,
                        timeOfDay.getHour(), timeOfDay.getMinute());
                }
                timeOfWeek = timeOfWeek.addTHM(timeOfDay);
            }
            timeOfWeekRemaining = timeOfWeekRemaining.subTHM(timeOfWeek);
            TimeHourMinute credit = new TimeHourMinute();
            if(timeOfWeek.getHour()>=38 && timeOfWeek.getMinute()>=0){
                credit.setHour(timeOfWeek.getHour()-38);
                credit.setMinute(timeOfWeek.getMinute());
                timeOfWeekRemaining.setHour(0);
                timeOfWeekRemaining.setMinute(0);
            }
            labelList.get(j).setText("<html><body><b>"+userList.get(j).getInitiales()+"</b><br>"
                + "Temps de travail<br>cette semaine: <b>"+timeOfWeek.toString()+
                "</b><br>Temps de travail<br>restant: <b>"
                 + timeOfWeekRemaining.toString()+"</b>"
                    + "<br>Crédit : <b>"+credit.toString()+"</b></body></html>");
//            for(Component comp : this.getComponents()){
//                JLabel lbUser= new JLabel();
//                if(comp instanceof JLabel){
//                    lbUser = (JLabel)comp;
//                }
//                if(comp instanceof AgendaDay){
//                    cpt++;
//                    AgendaDay aDay = (AgendaDay) comp;
//                    Calendar newCal = Calendar.getInstance();
//                    newCal.setTimeInMillis(dateOfDay.getTimeInMillis());
//
//                    aDay.reloadDay(rePreciseList);
//
//
//                    TimeHourMinute timeOfDay = aDay.computeWorkTime();
//                    EbWorktimetable wtt = EventUtil.getWorkTime
//                            (aDay.getUser().getId(), dateOfDay);
//                    if(wtt == null){
//                        wtt = EventUtil.addWorkTime(aDay.getUser().getId(), dateOfDay,
//                            timeOfDay.getHour(), timeOfDay.getMinute());
//                    }
//                    timeOfWeek = timeOfWeek.addTHM(timeOfDay);
//                    timeOfWeekRemaining = timeOfWeekRemaining.subTHM(timeOfWeek);
//
//                    
//                    }
//                    if(cpt==6){
//                        System.out.println("changing label");
//                        cpt=0;
//                        lbUser.setText("<html><body>"+lbUser.getText()+"<br>"
//                            + "Temps de travail<br>cette semaine: "+timeOfWeek.toString()+
//                            "<br>Temps de travail<br>restant: "
//                            + timeOfWeekRemaining.toString()+"</body></html>");
//                    }
//                }
            }
        }
    }
    public Color getColor() {
        return color;
    }

    
    
    public AgendaPanel getParentAgenda() {
        return parentAgenda;
    }

    public List<AgendaUser> getUserList() {
        return userList;
    }
    
}
