/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.Calendar;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author jsmaya
 */
public class AgendaEvent extends JPanel{
    private AgendaDay parentDay;
    private Calendar dateOfEvent;
    private String summary;
    private String description;
    private JLabel title;
    
    private boolean isWholeDayEvent;

    public AgendaEvent(String summary) {
        this.summary = summary;
        title = new JLabel(summary);
        add(title);
    }

    
    
    @Override
    public String toString() {
        return summary;
    }
    
}
