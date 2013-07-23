/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author jsmaya
 */
@Entity
@Table(name = "employees", catalog = "eb", schema = "")
@NamedQueries({
    @NamedQuery(name = "Employees.findAll", query = "SELECT e FROM Employees e"),
    @NamedQuery(name = "Employees.findById", query = "SELECT e FROM Employees e WHERE e.id = :id"),
    @NamedQuery(name = "Employees.findByIdEmpl", query = "SELECT e FROM Employees e WHERE e.idEmpl = :idEmpl"),
    @NamedQuery(name = "Employees.findByFullName", query = "SELECT e FROM Employees e WHERE e.fullName = :fullName"),
    @NamedQuery(name = "Employees.findByInitiales", query = "SELECT e FROM Employees e WHERE e.initiales = :initiales"),
    @NamedQuery(name = "Employees.findByFonction", query = "SELECT e FROM Employees e WHERE e.fonction = :fonction"),
    @NamedQuery(name = "Employees.findByTelephone", query = "SELECT e FROM Employees e WHERE e.telephone = :telephone"),
    @NamedQuery(name = "Employees.findByMail", query = "SELECT e FROM Employees e WHERE e.mail = :mail"),
    @NamedQuery(name = "Employees.findByMail2", query = "SELECT e FROM Employees e WHERE e.mail2 = :mail2"),
    @NamedQuery(name = "Employees.findBySkype", query = "SELECT e FROM Employees e WHERE e.skype = :skype"),
    @NamedQuery(name = "Employees.findByLogin", query = "SELECT e FROM Employees e WHERE e.login = :login"),
    @NamedQuery(name = "Employees.findByPass", query = "SELECT e FROM Employees e WHERE e.pass = :pass"),
    @NamedQuery(name = "Employees.findByDisableEmpl", query = "SELECT e FROM Employees e WHERE e.disableEmpl = :disableEmpl")})
public class Employees implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "IdEmpl")
    private int idEmpl;
    @Basic(optional = false)
    @Column(name = "FullName")
    private String fullName;
    @Basic(optional = false)
    @Column(name = "Initiales")
    private String initiales;
    @Column(name = "Fonction")
    private String fonction;
    @Column(name = "Telephone")
    private String telephone;
    @Column(name = "Mail")
    private String mail;
    @Column(name = "Mail2")
    private String mail2;
    @Column(name = "Skype")
    private String skype;
    @Basic(optional = false)
    @Column(name = "Login")
    private String login;
    @Basic(optional = false)
    @Column(name = "Pass")
    private String pass;
    @Basic(optional = false)
    @Column(name = "DisableEmpl")
    private short disableEmpl;

    public Employees() {
    }

    public Employees(Integer id) {
        this.id = id;
    }

    public Employees(Integer id, int idEmpl, String fullName, String initiales, String login, String pass, short disableEmpl) {
        this.id = id;
        this.idEmpl = idEmpl;
        this.fullName = fullName;
        this.initiales = initiales;
        this.login = login;
        this.pass = pass;
        this.disableEmpl = disableEmpl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        Integer oldId = this.id;
        this.id = id;
        changeSupport.firePropertyChange("id", oldId, id);
    }

    public int getIdEmpl() {
        return idEmpl;
    }

    public void setIdEmpl(int idEmpl) {
        int oldIdEmpl = this.idEmpl;
        this.idEmpl = idEmpl;
        changeSupport.firePropertyChange("idEmpl", oldIdEmpl, idEmpl);
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        String oldFullName = this.fullName;
        this.fullName = fullName;
        changeSupport.firePropertyChange("fullName", oldFullName, fullName);
    }

    public String getInitiales() {
        return initiales;
    }

    public void setInitiales(String initiales) {
        String oldInitiales = this.initiales;
        this.initiales = initiales;
        changeSupport.firePropertyChange("initiales", oldInitiales, initiales);
    }

    public String getFonction() {
        return fonction;
    }

    public void setFonction(String fonction) {
        String oldFonction = this.fonction;
        this.fonction = fonction;
        changeSupport.firePropertyChange("fonction", oldFonction, fonction);
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        String oldTelephone = this.telephone;
        this.telephone = telephone;
        changeSupport.firePropertyChange("telephone", oldTelephone, telephone);
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        String oldMail = this.mail;
        this.mail = mail;
        changeSupport.firePropertyChange("mail", oldMail, mail);
    }

    public String getMail2() {
        return mail2;
    }

    public void setMail2(String mail2) {
        String oldMail2 = this.mail2;
        this.mail2 = mail2;
        changeSupport.firePropertyChange("mail2", oldMail2, mail2);
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        String oldSkype = this.skype;
        this.skype = skype;
        changeSupport.firePropertyChange("skype", oldSkype, skype);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        String oldLogin = this.login;
        this.login = login;
        changeSupport.firePropertyChange("login", oldLogin, login);
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        String oldPass = this.pass;
        this.pass = pass;
        changeSupport.firePropertyChange("pass", oldPass, pass);
    }

    public short getDisableEmpl() {
        return disableEmpl;
    }

    public void setDisableEmpl(short disableEmpl) {
        short oldDisableEmpl = this.disableEmpl;
        this.disableEmpl = disableEmpl;
        changeSupport.firePropertyChange("disableEmpl", oldDisableEmpl, disableEmpl);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Employees)) {
            return false;
        }
        Employees other = (Employees) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ui.Employees[ id=" + id + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
