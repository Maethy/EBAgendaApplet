/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.swing.JComboBox;

/**
 *
 * @author MpUser
 */
public class TimeComboBox extends JComboBox {

    /**
     * Creates new form TimeComboBox
     */
    public TimeComboBox() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setEditable(true);
        setMaximumRowCount(7);
        setModel(new javax.swing.DefaultComboBoxModel(newModel()));
        setMinimumSize(new java.awt.Dimension(80, 22));
        setPreferredSize(new java.awt.Dimension(80, 22));
        addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                formActionPerformed(evt);
            }
        });
    }// </editor-fold>//GEN-END:initComponents
    private TimeHourMinute[] newModel(){
       TimeHourMinute[] thmArray;
       thmArray = new TimeHourMinute[48];
       for(int i = 0; i<48; i+=2){
           TimeHourMinute thm = new TimeHourMinute(i/2);
           TimeHourMinute thm2 = new TimeHourMinute(i/2, 30);
           thmArray[i] = thm;
           thmArray[i+1]=thm2;
       }
       return thmArray;
}
    private void formActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_formActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_formActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}