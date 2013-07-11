/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.awt.Container;
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

    public AgendaEvent(){
        this.summary = "dummy";
        title = new JLabel(summary);
        add(title);
    }
    
    public AgendaEvent(AgendaDay parent, String summary) {
        this.summary = summary;
        this.parentDay = parent;
        title = new JLabel(summary);
        setBackground(parentDay.getBackground());
        add(title);
    }

    
    
    @Override
    public String toString() {
        return summary;
    }
    
}
