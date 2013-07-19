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
    private String name, initiales, fonction, telephone, mail, skype;
    private int id;

    public AgendaUser(String name) {
        this.name = name;
        setText(name);
    }

    public AgendaUser(String name, String initiales, String fonction, String telephone, String mail, String skype, int id) {
        this.name = name;
        this.initiales = initiales;
        this.fonction = fonction;
        this.telephone = telephone;
        this.mail = mail;
        this.skype = skype;
        this.id = id;
        setText(initiales);
    }
    
    
    public String getUserName(){
        return this.name;
    }

    public String getInitiales() {
        return initiales;
    }

    public String getFonction() {
        return fonction;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getMail() {
        return mail;
    }

    public String getSkype() {
        return skype;
    }

    public int getId() {
        return id;
    }
    
}
