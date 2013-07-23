package tables;
// Generated 23 juil. 2013 18:33:26 by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * EbReleventempdate generated by hbm2java
 */
public class EbReleventempdate  implements java.io.Serializable {


     private Integer idebRelEventEmpDate;
     private Employees employees;
     private EbEvents ebEvents;
     private Date ebRelEventEmpDateStart;
     private Date ebRelEventEmpDateEnd;
     private Date ebRelEventEmpDateTrajet;
     private Date ebRelEventEmpDateLunch;
     private String ebRelEventEmpDateJobDesc;
     private Boolean ebRelEventEmpDateUserTimeValid;
     private Boolean ebRelEventEmpDateAdminTimeValid;

    public EbReleventempdate() {
    }

	
    public EbReleventempdate(Employees employees, EbEvents ebEvents, Date ebRelEventEmpDateStart, Date ebRelEventEmpDateEnd) {
        this.employees = employees;
        this.ebEvents = ebEvents;
        this.ebRelEventEmpDateStart = ebRelEventEmpDateStart;
        this.ebRelEventEmpDateEnd = ebRelEventEmpDateEnd;
    }
    public EbReleventempdate(Employees employees, EbEvents ebEvents, Date ebRelEventEmpDateStart, Date ebRelEventEmpDateEnd, Date ebRelEventEmpDateTrajet, Date ebRelEventEmpDateLunch, String ebRelEventEmpDateJobDesc, Boolean ebRelEventEmpDateUserTimeValid, Boolean ebRelEventEmpDateAdminTimeValid) {
       this.employees = employees;
       this.ebEvents = ebEvents;
       this.ebRelEventEmpDateStart = ebRelEventEmpDateStart;
       this.ebRelEventEmpDateEnd = ebRelEventEmpDateEnd;
       this.ebRelEventEmpDateTrajet = ebRelEventEmpDateTrajet;
       this.ebRelEventEmpDateLunch = ebRelEventEmpDateLunch;
       this.ebRelEventEmpDateJobDesc = ebRelEventEmpDateJobDesc;
       this.ebRelEventEmpDateUserTimeValid = ebRelEventEmpDateUserTimeValid;
       this.ebRelEventEmpDateAdminTimeValid = ebRelEventEmpDateAdminTimeValid;
    }
   
    public Integer getIdebRelEventEmpDate() {
        return this.idebRelEventEmpDate;
    }
    
    public void setIdebRelEventEmpDate(Integer idebRelEventEmpDate) {
        this.idebRelEventEmpDate = idebRelEventEmpDate;
    }
    public Employees getEmployees() {
        return this.employees;
    }
    
    public void setEmployees(Employees employees) {
        this.employees = employees;
    }
    public EbEvents getEbEvents() {
        return this.ebEvents;
    }
    
    public void setEbEvents(EbEvents ebEvents) {
        this.ebEvents = ebEvents;
    }
    public Date getEbRelEventEmpDateStart() {
        return this.ebRelEventEmpDateStart;
    }
    
    public void setEbRelEventEmpDateStart(Date ebRelEventEmpDateStart) {
        this.ebRelEventEmpDateStart = ebRelEventEmpDateStart;
    }
    public Date getEbRelEventEmpDateEnd() {
        return this.ebRelEventEmpDateEnd;
    }
    
    public void setEbRelEventEmpDateEnd(Date ebRelEventEmpDateEnd) {
        this.ebRelEventEmpDateEnd = ebRelEventEmpDateEnd;
    }
    public Date getEbRelEventEmpDateTrajet() {
        return this.ebRelEventEmpDateTrajet;
    }
    
    public void setEbRelEventEmpDateTrajet(Date ebRelEventEmpDateTrajet) {
        this.ebRelEventEmpDateTrajet = ebRelEventEmpDateTrajet;
    }
    public Date getEbRelEventEmpDateLunch() {
        return this.ebRelEventEmpDateLunch;
    }
    
    public void setEbRelEventEmpDateLunch(Date ebRelEventEmpDateLunch) {
        this.ebRelEventEmpDateLunch = ebRelEventEmpDateLunch;
    }
    public String getEbRelEventEmpDateJobDesc() {
        return this.ebRelEventEmpDateJobDesc;
    }
    
    public void setEbRelEventEmpDateJobDesc(String ebRelEventEmpDateJobDesc) {
        this.ebRelEventEmpDateJobDesc = ebRelEventEmpDateJobDesc;
    }
    public Boolean getEbRelEventEmpDateUserTimeValid() {
        return this.ebRelEventEmpDateUserTimeValid;
    }
    
    public void setEbRelEventEmpDateUserTimeValid(Boolean ebRelEventEmpDateUserTimeValid) {
        this.ebRelEventEmpDateUserTimeValid = ebRelEventEmpDateUserTimeValid;
    }
    public Boolean getEbRelEventEmpDateAdminTimeValid() {
        return this.ebRelEventEmpDateAdminTimeValid;
    }
    
    public void setEbRelEventEmpDateAdminTimeValid(Boolean ebRelEventEmpDateAdminTimeValid) {
        this.ebRelEventEmpDateAdminTimeValid = ebRelEventEmpDateAdminTimeValid;
    }




}


