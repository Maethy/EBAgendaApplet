/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author jsmaya
 */
public class AgendaWeek extends javax.swing.JPanel {
    private Calendar startOfWeek, dateOfDay;
    private List <AgendaDay> dayList;
    private List <AgendaUser> userList;
    private GridBagConstraints gridBagConstraints;
    private AgendaPanel parentAgenda;
    private JPanel header;
    
    
    public AgendaWeek(){
        this.startOfWeek = Calendar.getInstance();
        userList = new ArrayList<AgendaUser>();
        userList.add(new AgendaUser("Alain"));userList.add(new AgendaUser("Nico"));userList.add(new AgendaUser("Jeff"));userList.add(new AgendaUser("Myriam"));userList.add(new AgendaUser("Manu"));userList.add(new AgendaUser("Luis"));userList.add(new AgendaUser("Gino"));userList.add(new AgendaUser("JS"));userList.add(new AgendaUser("J-C"));
        setLayout(new GridLayout(userList.size(), 8));
//        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        dateOfDay = Calendar.getInstance();
        dateOfDay.setTimeInMillis(startOfWeek.getTimeInMillis());
//        
        for(int j=0; j<userList.size(); j++){
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
//            gridBagConstraints.gridy = 0;
//            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
//            gridBagConstraints.weightx = 1.0;
//            gridBagConstraints.weighty = 1.0;
            add(userList.get(j));
            for(int i=0; i<7; i++){
                gridBagConstraints = new java.awt.GridBagConstraints();
                gridBagConstraints.gridx = 1+i;
//                gridBagConstraints.gridy = 1;
//                gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
                gridBagConstraints.weightx = 0.0;
                gridBagConstraints.weighty = 0.0;
                add(new AgendaDay(this, dateOfDay));
                dateOfDay.add(Calendar.DAY_OF_YEAR, 1);
            }
//        for(int j=0; j<userList.size(); j++){
//            JPanel userPanel = new JPanel();
//            userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.LINE_AXIS));
//            userPanel.add(userList.get(j));
//            for(int i=0; i<7; i++){
//                userPanel.add(new AgendaDay(dateOfDay));
//                dateOfDay.add(Calendar.DAY_OF_YEAR, 1);
//            }
//            add(userPanel);
            dateOfDay.setTimeInMillis(startOfWeek.getTimeInMillis());
        }
    }
    public AgendaWeek(AgendaPanel parent, Calendar startOfWeek){
        this.parentAgenda = parent;
        this.startOfWeek = startOfWeek;
        userList = new ArrayList<AgendaUser>();
        userList.add(new AgendaUser("Alain"));userList.add(new AgendaUser("Nico"));
        setLayout(new GridLayout(userList.size()+1, 8));
//        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        dateOfDay = Calendar.getInstance();
        dateOfDay.setTimeInMillis(startOfWeek.getTimeInMillis());
//      
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
                    +" numWeek "+dateOfDay.getMinimalDaysInFirstWeek()+"</center></body></html>";
            JPanel dayHeaderPanel = new JPanel();
            JLabel dayHeader = new javax.swing.JLabel(date);
            dayHeaderPanel.setLayout
                    (new BoxLayout(dayHeaderPanel, BoxLayout.PAGE_AXIS));
            dayHeaderPanel.add(dayHeader);
            dayHeader.setBorder(javax.swing.BorderFactory.createEtchedBorder());
            add(dayHeaderPanel);
            dateOfDay.add(Calendar.DAY_OF_YEAR, 1);
        }
        dateOfDay.setTimeInMillis(startOfWeek.getTimeInMillis());
        for(int j=0; j<userList.size(); j++){
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            add(userList.get(j));
            for(int i=0; i<7; i++){
                gridBagConstraints = new java.awt.GridBagConstraints();
                gridBagConstraints.gridx = 1+i;
                gridBagConstraints.weightx = 0.0;
                gridBagConstraints.weighty = 0.0;
                
                AgendaDay aDay = new AgendaDay(this, dateOfDay);
                add(aDay);
//                new MyDropTargetListImp(aDay);

                dateOfDay.add(Calendar.DAY_OF_YEAR, 1);
            }
//        for(int j=0; j<userList.size(); j++){
//            JPanel userPanel = new JPanel();
//            userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.LINE_AXIS));
//            userPanel.add(userList.get(j));
//            for(int i=0; i<7; i++){
//                userPanel.add(new AgendaDay(dateOfDay));
//                dateOfDay.add(Calendar.DAY_OF_YEAR, 1);
//            }
//            add(userPanel);
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