/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import beans.AgendaPanel;
import beans.AgendaUser;
import business.EventUtil;
import com.toedter.calendar.JCalendar;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import tables.Employees;

/**
 *
 * @author jsmaya
 */
public class TestApplet extends javax.swing.JApplet {

    private AgendaPanel agenda;
    private JCalendar jcalendar;
    private Calendar startCalendar, endCalendar;
    private List <AgendaUser> userList;
    private List<Employees> empList;
    private HashMap <String, Employees> empMap;
    /**
     * Initializes the applet TestApplet
     */
    @Override
    public void init() {
        this.setSize(java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().
                getMaximumWindowBounds().width-16, java.awt.GraphicsEnvironment.
                        getLocalGraphicsEnvironment().getMaximumWindowBounds().
                        height-64);
//        Calendar test = Calendar.getInstance();
//        test.set(2012, 12, 25);
//        for(int i =0; i<1000; i++){
//            test.add(Calendar.DAY_OF_MONTH, 1);
//            System.out.println(test.get(Calendar.DAY_OF_YEAR));
//        }
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
        javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TestApplet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TestApplet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TestApplet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TestApplet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /* Create and display the applet */
        try {
            java.awt.EventQueue.invokeAndWait(new Runnable() {
                public void run() {
                    initComponents();
                    initAgenda();
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void initAgenda(){
//        if(agenda!=null)this.contentPanel.remove(agenda);
        Calendar startTimeElapsed = Calendar.getInstance();
        startCalendar = startDateChooser.getCalendar();
        endCalendar = endDateChooser.getCalendar();
        if(startCalendar == null){
            startCalendar = Calendar.getInstance();
        }
        if(endCalendar == null){
            endCalendar = Calendar.getInstance();
        }
        if(empMap == null){
            empMap = new  HashMap();
        }
        if(userList == null){
            userList = new ArrayList();
            EventUtil.openSession();
            PopulateUsers();
        }
        checkUsers();
        agenda = new AgendaPanel(startCalendar, endCalendar, this.userList);
        contentScrollPane.setViewportView(agenda);
//        System.out.println(startCalendar.get(Calendar.WEEK_OF_YEAR));
        this.revalidate();
        Calendar timeElapsed = Calendar.getInstance();
        long millisec =  timeElapsed.getTimeInMillis()-startTimeElapsed.getTimeInMillis();
        lbDates.setText("Dates - généré en "+millisec+" ms");
    }
    
    private void PopulateUsers(){
        empList = EventUtil.getEmployeeList();
        
        for(Employees emp : empList){
            JCheckBox ckb = new JCheckBox();
            ckb.setText(emp.getInitiales());
            userPanel.add(ckb);
            empMap.put(emp.getInitiales(), emp);
        }
    }
    
    private void checkUsers(){
        userList = new ArrayList();
        for(Object o : userPanel.getComponents()){
            if(o instanceof JCheckBox){
                JCheckBox ckb = (JCheckBox)o;
                String initiales = ckb.getText();
                if(ckb.isSelected()){
                    if(empMap.containsKey(initiales)){
                        Employees emp = empMap.get(initiales);
                        userList.add(new AgendaUser(emp.getFullName(),emp.getInitiales(),
                        emp.getFonction(), emp.getTelephone(), emp.getMail(), 
                        emp.getSkype(), emp.getId()));
                    }
                }
            }
        }
    }
    
    private void checkUserBoxes(boolean check){
        for(Object o : userPanel.getComponents()){
            if(o instanceof JCheckBox){
                JCheckBox ckb = (JCheckBox)o;
                ckb.setSelected(check);
            }
        }
    }
    /**
     * This method is called from within the init() method to initialize the
     * form. WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        labelStartDate = new javax.swing.JTextField();
        btJCalStart = new javax.swing.JButton();
        labelEndDate = new javax.swing.JTextField();
        btJCalEnd = new javax.swing.JButton();
        topPanel = new javax.swing.JPanel();
        lbDates = new javax.swing.JLabel();
        btBuild = new javax.swing.JButton();
        datePanel = new javax.swing.JPanel();
        btCurWeek = new javax.swing.JButton();
        btCurWeek1 = new javax.swing.JButton();
        startDatePanel = new javax.swing.JPanel();
        lbStartDate = new javax.swing.JLabel();
        startDateChooser = new com.toedter.calendar.JDateChooser();
        endDatePanel = new javax.swing.JPanel();
        lbEndDate = new javax.swing.JLabel();
        endDateChooser = new com.toedter.calendar.JDateChooser();
        userPanel = new javax.swing.JPanel();
        userBtPanel = new javax.swing.JPanel();
        btAllUser = new javax.swing.JButton();
        btNoUser = new javax.swing.JButton();
        lbUsers = new javax.swing.JLabel();
        contentPanel = new javax.swing.JPanel();
        contentScrollPane = new javax.swing.JScrollPane();

        labelStartDate.setColumns(8);

        btJCalStart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/toedter/calendar/images/JCalendarColor16.gif"))); // NOI18N
        btJCalStart.setMargin(new java.awt.Insets(2, 2, 2, 2));
        btJCalStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btJCalStartActionPerformed(evt);
            }
        });

        labelEndDate.setColumns(8);

        btJCalEnd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/toedter/calendar/images/JCalendarColor16.gif"))); // NOI18N
        btJCalEnd.setMargin(new java.awt.Insets(2, 2, 2, 2));
        btJCalEnd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btJCalEndActionPerformed(evt);
            }
        });

        getContentPane().setLayout(new java.awt.GridBagLayout());

        topPanel.setLayout(new java.awt.GridBagLayout());

        lbDates.setText("Dates");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        topPanel.add(lbDates, gridBagConstraints);

        btBuild.setText("Build");
        btBuild.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBuildActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.weightx = 1.0;
        topPanel.add(btBuild, gridBagConstraints);

        datePanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btCurWeek.setText("Current Week");
        btCurWeek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurWeekActionPerformed(evt);
            }
        });
        datePanel.add(btCurWeek);

        btCurWeek1.setText("Reset");
        btCurWeek1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurWeek1ActionPerformed(evt);
            }
        });
        datePanel.add(btCurWeek1);

        lbStartDate.setText("from");
        startDatePanel.add(lbStartDate);
        startDatePanel.add(startDateChooser);

        datePanel.add(startDatePanel);

        lbEndDate.setText("to");
        endDatePanel.add(lbEndDate);
        endDatePanel.add(endDateChooser);

        datePanel.add(endDatePanel);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        topPanel.add(datePanel, gridBagConstraints);

        userPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btAllUser.setText("All");
        btAllUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAllUserActionPerformed(evt);
            }
        });
        userBtPanel.add(btAllUser);

        btNoUser.setText("None");
        btNoUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNoUserActionPerformed(evt);
            }
        });
        userBtPanel.add(btNoUser);

        userPanel.add(userBtPanel);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        topPanel.add(userPanel, gridBagConstraints);

        lbUsers.setText("Users");
        topPanel.add(lbUsers, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(topPanel, gridBagConstraints);

        contentPanel.setLayout(new java.awt.GridLayout(1, 7));
        contentPanel.add(contentScrollPane);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(contentPanel, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    
    
    private void btBuildActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBuildActionPerformed
        initAgenda();
    }//GEN-LAST:event_btBuildActionPerformed

    private void btJCalEndActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btJCalEndActionPerformed
        jcalendar = new JCalendar();
        if(JOptionPane.showOptionDialog(btJCalEnd, jcalendar, "Select a date",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
            null, null, null) == 0){
        labelEndDate.setText(jcalendar.getCalendar()
            .get(Calendar.DAY_OF_MONTH)+"-"+(jcalendar.getCalendar()
                .get(Calendar.MONTH)+1)+"-"+jcalendar.getCalendar()
            .get(Calendar.YEAR));
        endCalendar = jcalendar.getCalendar();
        }
    }//GEN-LAST:event_btJCalEndActionPerformed

    private void btJCalStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btJCalStartActionPerformed
        jcalendar = new JCalendar();
        if(JOptionPane.showOptionDialog(btJCalStart, jcalendar, "Select a date",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
            null, null, null) == 0){
        labelStartDate.setText(jcalendar.getCalendar()
            .get(Calendar.DAY_OF_MONTH)+"-"+(jcalendar.getCalendar()
                .get(Calendar.MONTH)+1)+"-"+jcalendar.getCalendar()
            .get(Calendar.YEAR));
        startCalendar = jcalendar.getCalendar();
        }
    }//GEN-LAST:event_btJCalStartActionPerformed

    private void btAllUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAllUserActionPerformed
        checkUserBoxes(true);
    }//GEN-LAST:event_btAllUserActionPerformed

    private void btNoUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNoUserActionPerformed
        checkUserBoxes(false);
    }//GEN-LAST:event_btNoUserActionPerformed

    private void btCurWeekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurWeekActionPerformed
        this.startDateChooser.setCalendar(Calendar.getInstance());
    }//GEN-LAST:event_btCurWeekActionPerformed

    private void btCurWeek1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurWeek1ActionPerformed
        this.startDateChooser.setCalendar(null);
        this.endDateChooser.setCalendar(null);
    }//GEN-LAST:event_btCurWeek1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAllUser;
    private javax.swing.JButton btBuild;
    private javax.swing.JButton btCurWeek;
    private javax.swing.JButton btCurWeek1;
    private javax.swing.JButton btJCalEnd;
    private javax.swing.JButton btJCalStart;
    private javax.swing.JButton btNoUser;
    private javax.swing.JPanel contentPanel;
    private javax.swing.JScrollPane contentScrollPane;
    private javax.swing.JPanel datePanel;
    private com.toedter.calendar.JDateChooser endDateChooser;
    private javax.swing.JPanel endDatePanel;
    private javax.swing.JTextField labelEndDate;
    private javax.swing.JTextField labelStartDate;
    private javax.swing.JLabel lbDates;
    private javax.swing.JLabel lbEndDate;
    private javax.swing.JLabel lbStartDate;
    private javax.swing.JLabel lbUsers;
    private com.toedter.calendar.JDateChooser startDateChooser;
    private javax.swing.JPanel startDatePanel;
    private javax.swing.JPanel topPanel;
    private javax.swing.JPanel userBtPanel;
    private javax.swing.JPanel userPanel;
    // End of variables declaration//GEN-END:variables
}
