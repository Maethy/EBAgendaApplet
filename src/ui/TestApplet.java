/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import beans.AgendaPanel;
import beans.AgendaUser;
import business.EventUtil;
import com.toedter.calendar.JCalendar;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.WindowConstants;
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
    public static List<Employees> empList;
    public static HashMap <String, Employees> empMap;
    private ImageIcon image;
    /**
     * Initializes the applet TestApplet
     */
    @Override
    public void init() {
        this.setSize(java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().
                getMaximumWindowBounds().width-16, java.awt.GraphicsEnvironment.
                        getLocalGraphicsEnvironment().getMaximumWindowBounds().
                        height-64);
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
                    EventUtil.settApp(TestApplet.this);
                    EventUtil.openSession();
                    PopulateUsers();
                    reload();
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

//    @Override
//    protected void finalize() throws Throwable {
//        EventUtil.closeSession();
//        super.finalize(); 
//    }

    
    
    public void reload(){
        
        final Calendar startTimeElapsed = Calendar.getInstance();        
        SwingWorker worker = new SwingWorker<Object, Void>() {
            
            JFrame loadFrame;
            
                @Override
                public Object doInBackground() {
                  
                   image = new ImageIcon("./resources/468.gif");
                   loadFrame = new JFrame("Chargement...");
                   loadFrame.setLayout(new BorderLayout());
                   JLabel lbLoad = new JLabel("Chargement...");
                   JLabel lbLoadImg = new JLabel();
                   lbLoadImg.setIcon(image);
                   loadFrame.add(lbLoad, BorderLayout.PAGE_END);
                   loadFrame.add(lbLoadImg, BorderLayout.CENTER);
                   loadFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                   loadFrame.setBounds(TestApplet.this.getBounds().x+(TestApplet.this.getBounds().width/2)-35, 
                           TestApplet.this.getBounds().y+(TestApplet.this.getBounds().height/2)-35,10,10);
                   loadFrame.pack();
                   loadFrame.setAlwaysOnTop(true);
                   loadFrame.setVisible(true);
                   loadFrame.revalidate();
                   TestApplet.this.setEnabled(false);
//                   loadFrame.pack();
                   return new Object();
                }

                @Override
                public void done() {
                    try {
                        Object object = get();
                        initAgenda();
                        loadFrame.dispose();
                        TestApplet.this.setEnabled(true);
                        Calendar timeElapsed = Calendar.getInstance();
                        long millisec =  timeElapsed.getTimeInMillis()- startTimeElapsed.getTimeInMillis();
                        lbDates.setText("Dates - généré en "+millisec+" ms");
                    }catch (InterruptedException ignore) {
                    }catch (java.util.concurrent.ExecutionException e) {
                        String why = null;
                        Throwable cause = e.getCause();
                        if (cause != null) {
                            why = cause.getMessage();
                        } else {
                            why = e.getMessage();
                        }
                        System.err.println("initagenda swingworker: " + 
                                e.getCause());
                        TestApplet.this.setEnabled(true);
                    }catch(Exception e){
                        System.err.println("initagenda swingworker exception "
                                +e.getClass()+" "+ e.getMessage()+" ");
                        for (StackTraceElement ste : e.getStackTrace()) {
                            System.out.println(ste);
                        }
                        TestApplet.this.setEnabled(true);
                    }
                }
            };
        worker.execute();
    }
   
    private void initAgenda(){
        EventUtil.openSession();
        startCalendar = startDateChooser.getCalendar();
        endCalendar = endDateChooser.getCalendar();
        if(startCalendar == null){
            startCalendar = Calendar.getInstance();

            startCalendar.add(Calendar.WEEK_OF_YEAR,-3);
        }
        startDateChooser.setCalendar(startCalendar);
        
        if(endCalendar == null){
            endCalendar = Calendar.getInstance();
            endCalendar.add(Calendar.WEEK_OF_YEAR, 4);
        }
        endDateChooser.setCalendar(endCalendar);
        
        if(userList == null){
            userList = new ArrayList();
//                            EventUtil.openSession();
        }
        checkUsers();
        int scrollProRata = endCalendar.get(Calendar.WEEK_OF_YEAR)-
                startCalendar.get(Calendar.WEEK_OF_YEAR);
        int scrollPos = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR)
                -startCalendar.get(Calendar.WEEK_OF_YEAR);
        int scrollValue;
        
        if(agenda==null){
            agenda = new AgendaPanel(startCalendar, endCalendar, userList);
        }else{
            agenda.ReloadAgendaPanel(startCalendar, endCalendar);
        }
        contentScrollPane.setViewportView(agenda);
        //Trying to set view on current week
        scrollValue = 
                ((contentScrollPane.getVerticalScrollBar().getMaximum())/scrollProRata)*scrollPos-
                ((userList.size()*56)+8);
        contentScrollPane.getVerticalScrollBar().setValue(scrollValue);
        TestApplet.this.revalidate();
        
         
//        if(agenda!=null)this.contentPanel.remove(agenda);
        
    }
    
    private void PopulateUsers(){
         if(empMap == null){
            empMap = new  HashMap();
        }
        empList = EventUtil.getEmployeeList();
        
        for(Employees emp : empList){
            JCheckBox ckb = new JCheckBox();
            ckb.setText(emp.getInitiales());
            userPanel.add(ckb);
            empMap.put(emp.getInitiales(), emp);
        }
        this.revalidate();
    }
    
    private void checkUsers(){
        if(userList==null){
            userList = new ArrayList();
        }else{
            userList.clear();
        }
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
        btResetWeek = new javax.swing.JButton();
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
        contentPanel = new javax.swing.JPanel();
        contentScrollPane = new javax.swing.JScrollPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

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
        lbDates.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        topPanel.add(lbDates, gridBagConstraints);

        btBuild.setText("Charger");
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

        datePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Dates"));
        datePanel.setLayout(new javax.swing.BoxLayout(datePanel, javax.swing.BoxLayout.LINE_AXIS));

        btCurWeek.setText("Cette semaine");
        btCurWeek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurWeekActionPerformed(evt);
            }
        });
        datePanel.add(btCurWeek);

        btResetWeek.setText("Reset");
        btResetWeek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btResetWeekActionPerformed(evt);
            }
        });
        datePanel.add(btResetWeek);

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
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 1.0;
        topPanel.add(datePanel, gridBagConstraints);

        userPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("Users"), "Utilisateurs"));
        userPanel.setLayout(new javax.swing.BoxLayout(userPanel, javax.swing.BoxLayout.LINE_AXIS));

        btAllUser.setText("Tous");
        btAllUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAllUserActionPerformed(evt);
            }
        });
        userBtPanel.add(btAllUser);

        btNoUser.setText("Aucun");
        btNoUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNoUserActionPerformed(evt);
            }
        });
        userBtPanel.add(btNoUser);

        userPanel.add(userBtPanel);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LAST_LINE_END;
        gridBagConstraints.weightx = 1.0;
        topPanel.add(userPanel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(topPanel, gridBagConstraints);

        contentPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Agenda"));
        contentPanel.setLayout(new java.awt.GridLayout(1, 7));
        contentPanel.add(contentScrollPane);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(contentPanel, gridBagConstraints);

        jMenu1.setText("File");

        jMenuItem1.setText("jMenuItem1");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);
    }// </editor-fold>//GEN-END:initComponents

    
    
    private void btBuildActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBuildActionPerformed
        reload();
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

    private void btResetWeekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btResetWeekActionPerformed
        this.startDateChooser.setCalendar(null);
        this.endDateChooser.setCalendar(null);
    }//GEN-LAST:event_btResetWeekActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
//        TablesUI ui = new TablesUI();
//        ui.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
//        ui.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAllUser;
    private javax.swing.JButton btBuild;
    private javax.swing.JButton btCurWeek;
    private javax.swing.JButton btJCalEnd;
    private javax.swing.JButton btJCalStart;
    private javax.swing.JButton btNoUser;
    private javax.swing.JButton btResetWeek;
    private javax.swing.JPanel contentPanel;
    private javax.swing.JScrollPane contentScrollPane;
    private javax.swing.JPanel datePanel;
    private com.toedter.calendar.JDateChooser endDateChooser;
    private javax.swing.JPanel endDatePanel;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JTextField labelEndDate;
    private javax.swing.JTextField labelStartDate;
    private javax.swing.JLabel lbDates;
    private javax.swing.JLabel lbEndDate;
    private javax.swing.JLabel lbStartDate;
    private com.toedter.calendar.JDateChooser startDateChooser;
    private javax.swing.JPanel startDatePanel;
    private javax.swing.JPanel topPanel;
    private javax.swing.JPanel userBtPanel;
    private javax.swing.JPanel userPanel;
    // End of variables declaration//GEN-END:variables
}
