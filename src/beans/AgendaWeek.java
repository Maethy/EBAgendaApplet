/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import business.EventUtil;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.Calendar;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import tables.EbEvents;

/**
 *
 * @author jsmaya
 */
public class AgendaWeek extends javax.swing.JPanel {
    private Calendar startOfWeek, dateOfDay, endOfWeek;
    private List <AgendaDay> dayList;
    private List <AgendaUser> userList;
    private List <EbEvents> eventsList;
    private GridBagConstraints gridBagConstraints;
    private AgendaPanel parentAgenda;
    private JPanel header;
    private JLabel lbUserName;
    
    public AgendaWeek(AgendaPanel parent, Calendar startOfWeek, 
            List<EbEvents> ebEventsList, List<AgendaUser> userList){
        this.parentAgenda = parent;
        this.startOfWeek = startOfWeek;
        lbUserName =  new JLabel();
        endOfWeek = Calendar.getInstance();
        endOfWeek.setTimeInMillis(startOfWeek.getTimeInMillis());
        endOfWeek.add(Calendar.DAY_OF_YEAR, 7);
        this.userList = userList;
        setLayout(new GridLayout(userList.size()+1, 8));
        setLayout(new GridBagLayout());
        List<EbEvents> preciseList = EventUtil.preciseEventList(startOfWeek, endOfWeek, ebEventsList);
        dateOfDay = Calendar.getInstance();
        dateOfDay.setTimeInMillis(startOfWeek.getTimeInMillis());
        JPanel userHeaderPanel = new JPanel();
        JLabel userHeader = new JLabel("User");
        userHeader.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        userHeaderPanel.add(userHeader);
        add(userHeaderPanel);
        for(int i=0; i<7; i++){
            String date = "<html><body><center>"
                    +commons.Constants.DAY_OF_WEEK[i]+"<br>"
                    +dateOfDay.get(Calendar.DAY_OF_MONTH)+"-"+dateOfDay.
                    get(Calendar.MONTH+1)+"-"+dateOfDay.get(Calendar.YEAR)
                    +" numWeek </center></body></html>";
            JPanel dayHeaderPanel = new JPanel();
            JLabel dayHeader = new javax.swing.JLabel(date);
            dayHeaderPanel.setLayout
                    (new BoxLayout(dayHeaderPanel, BoxLayout.PAGE_AXIS));
            dayHeaderPanel.add(dayHeader);
            dayHeader.setBorder(javax.swing.BorderFactory.createEtchedBorder());
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = i+1;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
            add(dayHeaderPanel, gridBagConstraints);
            dateOfDay.add(Calendar.DAY_OF_YEAR, 1);
        }
        dateOfDay.setTimeInMillis(startOfWeek.getTimeInMillis());
        for(int j=0; j<userList.size(); j++){
                gridBagConstraints = new java.awt.GridBagConstraints();
                gridBagConstraints.gridx = 0;
//                gridBagConstraints.gridy = 0;
                gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
                gridBagConstraints.weightx = 1.0;
                gridBagConstraints.weighty = 1.0;
                gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
            lbUserName =  new JLabel(userList.get(j).getInitiales());
            add(lbUserName, gridBagConstraints);
            for(int i=0; i<7; i++){
                AgendaDay aDay;
                Calendar newCal = Calendar.getInstance();
                newCal.setTimeInMillis(dateOfDay.getTimeInMillis());
                System.out.println(dateOfDay.getTime()+" "+preciseList.size());
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
                gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
                add(aDay, gridBagConstraints);
                dateOfDay.add(Calendar.DAY_OF_YEAR, 1);
            }
            TimeHourMinute timeOfWeek = new TimeHourMinute();
            for(Component comp : this.getComponents()){
                if(comp instanceof AgendaDay){
                    TimeHourMinute timeOfDay = ((AgendaDay)comp).computeWorkTime();
                    timeOfWeek = timeOfWeek.addTHM(timeOfDay);
                    if(timeOfDay.getHour()>0){
                        System.out.println("TOD: "+timeOfDay+"TOW: "+timeOfWeek);
                    }
                }
            }

            if(timeOfWeek.getHour()>0||timeOfWeek.getMinute()>0){
                System.out.println(this.dateOfDay.getTime()+" THMWeek: "+timeOfWeek);
            }
            lbUserName.setText("<html><body>"+lbUserName.getText()+"<br>"
                    + "Time this week: "+timeOfWeek.toString()+
                    "</body></html>");
            dateOfDay.setTimeInMillis(startOfWeek.getTimeInMillis());
        }
    }

    public AgendaPanel getParentAgenda() {
        return parentAgenda;
    }

    public List<AgendaUser> getUserList() {
        return userList;
    }
    
}
