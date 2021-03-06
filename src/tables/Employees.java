package tables;
// Generated 31-juil.-2013 17:30:28 by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 * Employees generated by hbm2java
 */
public class Employees  implements java.io.Serializable {


     private Integer id;
     private int idEmpl;
     private String fullName;
     private String initiales;
     private String fonction;
     private String telephone;
     private String mail;
     private String mail2;
     private String skype;
     private String login;
     private String pass;
     private boolean disableEmpl;
     private Set ebReleventempdates = new HashSet(0);
     private Set ebEventses = new HashSet(0);
     private Set ebWorktimetables = new HashSet(0);

    public Employees() {
    }

	
    public Employees(int idEmpl, String fullName, String initiales, String login, String pass, boolean disableEmpl) {
        this.idEmpl = idEmpl;
        this.fullName = fullName;
        this.initiales = initiales;
        this.login = login;
        this.pass = pass;
        this.disableEmpl = disableEmpl;
    }
    public Employees(int idEmpl, String fullName, String initiales, String fonction, String telephone, String mail, String mail2, String skype, String login, String pass, boolean disableEmpl, Set ebReleventempdates, Set ebEventses, Set ebWorktimetables) {
       this.idEmpl = idEmpl;
       this.fullName = fullName;
       this.initiales = initiales;
       this.fonction = fonction;
       this.telephone = telephone;
       this.mail = mail;
       this.mail2 = mail2;
       this.skype = skype;
       this.login = login;
       this.pass = pass;
       this.disableEmpl = disableEmpl;
       this.ebReleventempdates = ebReleventempdates;
       this.ebEventses = ebEventses;
       this.ebWorktimetables = ebWorktimetables;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public int getIdEmpl() {
        return this.idEmpl;
    }
    
    public void setIdEmpl(int idEmpl) {
        this.idEmpl = idEmpl;
    }
    public String getFullName() {
        return this.fullName;
    }
    
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public String getInitiales() {
        return this.initiales;
    }
    
    public void setInitiales(String initiales) {
        this.initiales = initiales;
    }
    public String getFonction() {
        return this.fonction;
    }
    
    public void setFonction(String fonction) {
        this.fonction = fonction;
    }
    public String getTelephone() {
        return this.telephone;
    }
    
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public String getMail() {
        return this.mail;
    }
    
    public void setMail(String mail) {
        this.mail = mail;
    }
    public String getMail2() {
        return this.mail2;
    }
    
    public void setMail2(String mail2) {
        this.mail2 = mail2;
    }
    public String getSkype() {
        return this.skype;
    }
    
    public void setSkype(String skype) {
        this.skype = skype;
    }
    public String getLogin() {
        return this.login;
    }
    
    public void setLogin(String login) {
        this.login = login;
    }
    public String getPass() {
        return this.pass;
    }
    
    public void setPass(String pass) {
        this.pass = pass;
    }
    public boolean isDisableEmpl() {
        return this.disableEmpl;
    }
    
    public void setDisableEmpl(boolean disableEmpl) {
        this.disableEmpl = disableEmpl;
    }
    public Set getEbReleventempdates() {
        return this.ebReleventempdates;
    }
    
    public void setEbReleventempdates(Set ebReleventempdates) {
        this.ebReleventempdates = ebReleventempdates;
    }
    public Set getEbEventses() {
        return this.ebEventses;
    }
    
    public void setEbEventses(Set ebEventses) {
        this.ebEventses = ebEventses;
    }
    public Set getEbWorktimetables() {
        return this.ebWorktimetables;
    }
    
    public void setEbWorktimetables(Set ebWorktimetables) {
        this.ebWorktimetables = ebWorktimetables;
    }




}


