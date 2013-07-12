/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.swing.JLabel;

/**
 *
 * @author MpUser
 */
public class JLabelEvent extends JLabel {
    private AgendaEvent event;

    public JLabelEvent(AgendaEvent event) {
        this.event = event;
        this.setText(event.getSummary());
    }

    public AgendaEvent getEvent() {
        return event;
    }

    public void setEvent(AgendaEvent event) {
        this.event = event;
        this.setText(event.getSummary());
    }
       
}
