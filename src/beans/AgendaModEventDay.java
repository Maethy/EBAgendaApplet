/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import business.EventUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import tables.EbCli;
import tables.EbReleventempdate;
import tables.Employees;
import ui.TestApplet;

/**
 *
 * @author jsmaya
 */
public class AgendaModEventDay extends javax.swing.JPanel {

    private List<EbCli> cliList;
    private List<JCheckBox> userCheckBoxes;
    private EbReleventempdate event;
    private JFrame parent;
    private Calendar start, end;
    /**
     * Creates new form AgendaModEventDay
     */
    public AgendaModEventDay() {
        initComponents();
    }
    
    public AgendaModEventDay(JFrame parent, EbReleventempdate event) {
        initComponents();
        start = Calendar.getInstance();
        end = Calendar.getInstance();
        start.setTime(event.getEbRelEventEmpDateStart());
        end.setTime(event.getEbRelEventEmpDateEnd());
        this.event = event;
        this.parent = parent;
        userCheckBoxes = new ArrayList();
        cliList = new ArrayList();
        populateUsers();
        this.txtTitle.setText(this.event.getEbEvents().getEbEventsName());
        txtGeneralDesc.setText(event.getEbEvents().getEbEventsDesc());
        this.txtTaskDesc.setText(event.getEbRelEventEmpDateJobDesc());
        this.startTimeBean.setDateTime(start);
        this.endTimeBean.setDateTime(end);
        this.startTimeBean.setDateEnabled(false);
        this.endTimeBean.setDateEnabled(false);
        this.parent.setAlwaysOnTop(true);
        if(event.isEbRelEventEmpDateFullDay()){
            this.ckChgDH.setText("Évènement journée entière, veuillez confirmer les heures");
        }
        this.ckChgDH.setSelected(!event.isEbRelEventEmpDateFullDay());
        this.ckChgDHActionPerformed(null);
    }
    
    private Object[] populateClients(){
        cliList = EventUtil.getClients();
        return cliList.toArray();
    }
    
    
    private void populateUsers(){

        for(Employees emp : TestApplet.empList){
            JCheckBox ckb = new JCheckBox();
            ckb.setText(emp.getInitiales());
            userCheckBoxes.add(ckb);
        }
        System.out.println("populating users");
        for(JCheckBox ckb : userCheckBoxes){
            this.userPanel.add(ckb);
        }
        setEmployees();
        revalidate();
    }
        
    public Employees[] getEmployees(){
        List <Employees> participants = new ArrayList();
        for(Object o : userPanel.getComponents()){
            if(o instanceof JCheckBox){
                JCheckBox ckb = (JCheckBox)o;
                String initiales = ckb.getText();
                if(ckb.isSelected()){
                    if(TestApplet.empMap.containsKey(initiales)){
                        Employees emp = TestApplet.empMap.get(initiales);
                        participants.add(emp);
                    }
                }
            }
        }
        System.out.println(participants.size()+" participants");
        return participants.toArray(new Employees[0]);
    }
    
    public void setEmployees(){
        List <Employees> participants = 
                EventUtil.getOtherParticipants
                (event.getEbEvents().getIdebEvent(), 
                event.getEbRelEventEmpDateDate());
        System.out.println(participants.size());
        for(Object o : userPanel.getComponents()){
            if(o instanceof JCheckBox){
                JCheckBox ckb = (JCheckBox)o;
                String initiales = ckb.getText();
                for(Employees emp : participants){
                    if(initiales.equals(emp.getInitiales())){
                        ckb.setSelected(true);
                    }
                }
            }
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        contentPanel = new javax.swing.JPanel();
        titlePanel = new javax.swing.JPanel();
        txtTitle = new javax.swing.JTextField();
        ckChgDH = new javax.swing.JCheckBox();
        datesPanel = new javax.swing.JPanel();
        debutPanel = new javax.swing.JPanel();
        startTimeBean = new beans.DateTimeBean();
        finPanel = new javax.swing.JPanel();
        endTimeBean = new beans.DateTimeBean();
        ckModDate = new javax.swing.JCheckBox();
        ckAllUsersDate = new javax.swing.JCheckBox();
        userPanel = new javax.swing.JPanel();
        cliPanel = new javax.swing.JPanel();
        jCBoxCli = new javax.swing.JComboBox();
        descPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtGeneralDesc = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtTaskDesc = new javax.swing.JTextArea();
        btPanel = new javax.swing.JPanel();
        modBtPanel = new javax.swing.JPanel();
        btMod = new javax.swing.JButton();
        cancelBtPanel = new javax.swing.JPanel();
        btCancel = new javax.swing.JButton();
        deleteBtPanel = new javax.swing.JPanel();
        ckAllUsers = new javax.swing.JCheckBox();
        ckAllDays = new javax.swing.JCheckBox();
        btDelete = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        contentPanel.setLayout(new java.awt.GridBagLayout());

        titlePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Nom de l'évènement"));
        titlePanel.setLayout(new javax.swing.BoxLayout(titlePanel, javax.swing.BoxLayout.LINE_AXIS));
        titlePanel.add(txtTitle);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        contentPanel.add(titlePanel, gridBagConstraints);

        ckChgDH.setText("Décocher pour transformer en évènement journée entière (heures non définies)");
        ckChgDH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckChgDHActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        contentPanel.add(ckChgDH, gridBagConstraints);

        datesPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Dates"));

        debutPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Début"));
        debutPanel.add(startTimeBean);

        datesPanel.add(debutPanel);

        finPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Fin"));
        finPanel.add(endTimeBean);

        datesPanel.add(finPanel);

        ckModDate.setText("Modifier dates globales");
        ckModDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckModDateActionPerformed(evt);
            }
        });
        datesPanel.add(ckModDate);

        ckAllUsersDate.setText("Pour tout le monde");
        datesPanel.add(ckAllUsersDate);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        contentPanel.add(datesPanel, gridBagConstraints);

        userPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Participants"));
        userPanel.setLayout(new java.awt.GridLayout(0, 6));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        contentPanel.add(userPanel, gridBagConstraints);

        cliPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Client"));
        cliPanel.setLayout(new javax.swing.BoxLayout(cliPanel, javax.swing.BoxLayout.LINE_AXIS));

        jCBoxCli.setModel(new javax.swing.DefaultComboBoxModel(populateClients()));
        cliPanel.add(jCBoxCli);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        contentPanel.add(cliPanel, gridBagConstraints);

        descPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Description"));
        descPanel.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("Description Générale");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        descPanel.add(jLabel1, gridBagConstraints);

        txtGeneralDesc.setBackground(new java.awt.Color(204, 204, 204));
        txtGeneralDesc.setColumns(20);
        txtGeneralDesc.setRows(5);
        jScrollPane1.setViewportView(txtGeneralDesc);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        descPanel.add(jScrollPane1, gridBagConstraints);

        jLabel2.setText("Description Tâche");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        descPanel.add(jLabel2, gridBagConstraints);

        txtTaskDesc.setColumns(20);
        txtTaskDesc.setRows(5);
        jScrollPane2.setViewportView(txtTaskDesc);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        descPanel.add(jScrollPane2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        contentPanel.add(descPanel, gridBagConstraints);

        btPanel.setLayout(new java.awt.GridBagLayout());

        modBtPanel.setLayout(new java.awt.GridBagLayout());

        btMod.setText("Modifier");
        btMod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btModActionPerformed(evt);
            }
        });
        modBtPanel.add(btMod, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        btPanel.add(modBtPanel, gridBagConstraints);

        cancelBtPanel.setLayout(new java.awt.GridBagLayout());

        btCancel.setText("Annuler");
        btCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelActionPerformed(evt);
            }
        });
        cancelBtPanel.add(btCancel, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        btPanel.add(cancelBtPanel, gridBagConstraints);

        deleteBtPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Supprimer"));
        deleteBtPanel.setLayout(new java.awt.GridBagLayout());

        ckAllUsers.setText("Pour tout le monde");
        deleteBtPanel.add(ckAllUsers, new java.awt.GridBagConstraints());

        ckAllDays.setText("Pour tous les jours");
        deleteBtPanel.add(ckAllDays, new java.awt.GridBagConstraints());

        btDelete.setText("Supprimer");
        btDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDeleteActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.weightx = 1.0;
        deleteBtPanel.add(btDelete, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        btPanel.add(deleteBtPanel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        contentPanel.add(btPanel, gridBagConstraints);

        add(contentPanel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDeleteActionPerformed
        if((JOptionPane.showConfirmDialog(this, 
                "Êtes-vous sur de vouloir supprimer cet évènement ?",
                 "Suppression", JOptionPane.OK_CANCEL_OPTION))
                == JOptionPane.OK_OPTION){
            Integer eventId, empId;
            eventId = null;
            empId = null;

            if(this.ckAllDays.isSelected()){
                eventId = this.event.getEbEvents().getIdebEvent();
                empId = this.event.getEmployees().getId();
            }
            if(this.ckAllUsers.isSelected()){
                empId = null;
            }
            EventUtil.deleteRelEventEmpDate(this.event.getIdebRelEventEmpDate(), 
                    eventId, empId);
            this.parent.dispose();
            EventUtil.gettApp().reload();
        }
        this.parent.setAlwaysOnTop(true);
    }//GEN-LAST:event_btDeleteActionPerformed

    private void ckModDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckModDateActionPerformed
        this.startTimeBean.setDateEnabled(this.ckModDate.isSelected());
        this.endTimeBean.setDateEnabled(this.ckModDate.isSelected());
    }//GEN-LAST:event_ckModDateActionPerformed

    private void btModActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btModActionPerformed
        EventUtil.modRelempeventdate(this.event, 
                null, 
                this.startTimeBean.getDateTime(), 
                this.endTimeBean.getDateTime(), 
                this.event.getEmployees().getId(),
                this.txtTitle.getText(),
                this.txtGeneralDesc.getText(), 
                this.txtTaskDesc.getText(),
                this.getEmployees(),
                ((EbCli)this.jCBoxCli.getSelectedItem()),
                !this.ckChgDH.isSelected());
        this.parent.dispose();
        EventUtil.gettApp().reload();
    }//GEN-LAST:event_btModActionPerformed

    private void btCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelActionPerformed
        this.parent.dispose();
    }//GEN-LAST:event_btCancelActionPerformed

    private void ckChgDHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckChgDHActionPerformed
        this.datesPanel.setEnabled(this.ckChgDH.isSelected());
        this.debutPanel.setEnabled(this.ckChgDH.isSelected());
        this.finPanel.setEnabled(this.ckChgDH.isSelected());
        this.ckModDate.setEnabled(this.ckChgDH.isSelected());
        this.ckAllUsersDate.setEnabled(this.ckChgDH.isSelected());
        this.startTimeBean.setEnabled(this.ckChgDH.isSelected());
        this.endTimeBean.setEnabled(this.ckChgDH.isSelected());
    }//GEN-LAST:event_ckChgDHActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCancel;
    private javax.swing.JButton btDelete;
    private javax.swing.JButton btMod;
    private javax.swing.JPanel btPanel;
    private javax.swing.JPanel cancelBtPanel;
    private javax.swing.JCheckBox ckAllDays;
    private javax.swing.JCheckBox ckAllUsers;
    private javax.swing.JCheckBox ckAllUsersDate;
    private javax.swing.JCheckBox ckChgDH;
    private javax.swing.JCheckBox ckModDate;
    private javax.swing.JPanel cliPanel;
    private javax.swing.JPanel contentPanel;
    private javax.swing.JPanel datesPanel;
    private javax.swing.JPanel debutPanel;
    private javax.swing.JPanel deleteBtPanel;
    private javax.swing.JPanel descPanel;
    private beans.DateTimeBean endTimeBean;
    private javax.swing.JPanel finPanel;
    private javax.swing.JComboBox jCBoxCli;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel modBtPanel;
    private beans.DateTimeBean startTimeBean;
    private javax.swing.JPanel titlePanel;
    private javax.swing.JTextArea txtGeneralDesc;
    private javax.swing.JTextArea txtTaskDesc;
    private javax.swing.JTextField txtTitle;
    private javax.swing.JPanel userPanel;
    // End of variables declaration//GEN-END:variables
}
