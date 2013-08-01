/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.Objects;
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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + Objects.hashCode(this.name);
        hash = 43 * hash + Objects.hashCode(this.initiales);
        hash = 43 * hash + Objects.hashCode(this.fonction);
        hash = 43 * hash + Objects.hashCode(this.telephone);
        hash = 43 * hash + Objects.hashCode(this.mail);
        hash = 43 * hash + Objects.hashCode(this.skype);
        hash = 43 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AgendaUser other = (AgendaUser) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.initiales, other.initiales)) {
            return false;
        }
        if (!Objects.equals(this.fonction, other.fonction)) {
            return false;
        }
        if (!Objects.equals(this.telephone, other.telephone)) {
            return false;
        }
        if (!Objects.equals(this.mail, other.mail)) {
            return false;
        }
        if (!Objects.equals(this.skype, other.skype)) {
            return false;
        }
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
}
