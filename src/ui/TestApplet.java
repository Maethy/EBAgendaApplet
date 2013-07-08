/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import beans.AgendaPanel;
import com.toedter.calendar.JCalendar;
import java.util.Calendar;
import javax.swing.JOptionPane;
import javax.swing.JViewport;

/**
 *
 * @author jsmaya
 */
public class TestApplet extends javax.swing.JApplet {

    private AgendaPanel agenda;
    private JCalendar jcalendar;
    private Calendar startCalendar, endCalendar;
    /**
     * Initializes the applet TestApplet
     */
    @Override
    public void init() {
        this.setSize(640, 480);
        jcalendar = new JCalendar();
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
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
        if(startCalendar == null){
            startCalendar = Calendar.getInstance();
        }
        agenda = new AgendaPanel(startCalendar, endCalendar);
        contentScrollPane.setViewportView(agenda);
        System.out.println(startCalendar.get(Calendar.WEEK_OF_YEAR));
        this.revalidate();
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

        topPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        labelStartDate = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        labelEndDate = new javax.swing.JTextField();
        btJCalStart = new javax.swing.JButton();
        btJCalEnd = new javax.swing.JButton();
        contentPanel = new javax.swing.JPanel();
        contentScrollPane = new javax.swing.JScrollPane();

        getContentPane().setLayout(new java.awt.GridBagLayout());

        topPanel.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("Eurobrevets");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        topPanel.add(jLabel1, gridBagConstraints);

        jButton2.setText("Build");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.weightx = 1.0;
        topPanel.add(jButton2, gridBagConstraints);

        jLabel2.setText("startDate");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        topPanel.add(jLabel2, gridBagConstraints);

        labelStartDate.setColumns(8);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        topPanel.add(labelStartDate, gridBagConstraints);

        jLabel3.setText("endDate");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        topPanel.add(jLabel3, gridBagConstraints);

        labelEndDate.setColumns(8);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        topPanel.add(labelEndDate, gridBagConstraints);

        btJCalStart.setText("jCal");
        btJCalStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btJCalStartActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        topPanel.add(btJCalStart, gridBagConstraints);

        btJCalEnd.setText("jCal");
        btJCalEnd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btJCalEndActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 2;
        topPanel.add(btJCalEnd, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
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

    
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        initAgenda();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btJCalStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btJCalStartActionPerformed
        if(JOptionPane.showOptionDialog(this, jcalendar, "Select a date", 
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, null, null) == 0){    
            labelStartDate.setText(jcalendar.getCalendar()
                    .get(Calendar.DAY_OF_MONTH)+"-"+(jcalendar.getCalendar()
                    .get(Calendar.MONTH)+1)+"-"+jcalendar.getCalendar()
                    .get(Calendar.YEAR));
            startCalendar = jcalendar.getCalendar();
        }
    }//GEN-LAST:event_btJCalStartActionPerformed

    private void btJCalEndActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btJCalEndActionPerformed
        if(JOptionPane.showOptionDialog(this, jcalendar, "Select a date", 
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, null, null) == 0){    
            labelEndDate.setText(jcalendar.getCalendar()
                    .get(Calendar.DAY_OF_MONTH)+"-"+(jcalendar.getCalendar()
                    .get(Calendar.MONTH)+1)+"-"+jcalendar.getCalendar()
                    .get(Calendar.YEAR));
            endCalendar = jcalendar.getCalendar();
        }
    }//GEN-LAST:event_btJCalEndActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btJCalEnd;
    private javax.swing.JButton btJCalStart;
    private javax.swing.JPanel contentPanel;
    private javax.swing.JScrollPane contentScrollPane;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField labelEndDate;
    private javax.swing.JTextField labelStartDate;
    private javax.swing.JPanel topPanel;
    // End of variables declaration//GEN-END:variables
}