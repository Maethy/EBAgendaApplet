package tables;
// Generated 23 juil. 2013 18:33:26 by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * EbTravelfees generated by hbm2java
 */
public class EbTravelfees  implements java.io.Serializable {


     private int idebTravelFees;
     private EbCountry ebCountry;
     private Date ebTravelFeesDate;
     private int ebTravelFeesFee;

    public EbTravelfees() {
    }

    public EbTravelfees(int idebTravelFees, EbCountry ebCountry, Date ebTravelFeesDate, int ebTravelFeesFee) {
       this.idebTravelFees = idebTravelFees;
       this.ebCountry = ebCountry;
       this.ebTravelFeesDate = ebTravelFeesDate;
       this.ebTravelFeesFee = ebTravelFeesFee;
    }
   
    public int getIdebTravelFees() {
        return this.idebTravelFees;
    }
    
    public void setIdebTravelFees(int idebTravelFees) {
        this.idebTravelFees = idebTravelFees;
    }
    public EbCountry getEbCountry() {
        return this.ebCountry;
    }
    
    public void setEbCountry(EbCountry ebCountry) {
        this.ebCountry = ebCountry;
    }
    public Date getEbTravelFeesDate() {
        return this.ebTravelFeesDate;
    }
    
    public void setEbTravelFeesDate(Date ebTravelFeesDate) {
        this.ebTravelFeesDate = ebTravelFeesDate;
    }
    public int getEbTravelFeesFee() {
        return this.ebTravelFeesFee;
    }
    
    public void setEbTravelFeesFee(int ebTravelFeesFee) {
        this.ebTravelFeesFee = ebTravelFeesFee;
    }




}


