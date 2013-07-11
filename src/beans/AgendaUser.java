/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.swing.JLabel;

/**
 *
 * @author jsmaya
 */
public class AgendaUser extends JLabel {
    private String name;

    public AgendaUser(String name) {
        this.name = name;
        setText(name);
    }
    
}
