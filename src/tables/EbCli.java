package tables;
// Generated 16 juil. 2013 22:43:07 by Hibernate Tools 3.2.1.GA



/**
 * EbCli generated by hbm2java
 */
public class EbCli  implements java.io.Serializable {


     private Integer idebCli;
     private Typeclient typeclient;
     private EbCountry ebCountry;
     private String ebCliname;
     private String ebCliFriendName;
     private String ebCliStreet;
     private String ebCliCity;
     private String ebCliPostCode;
     private String ebCliTelGeneral;

    public EbCli() {
    }

    public EbCli(Typeclient typeclient, EbCountry ebCountry, String ebCliname, String ebCliFriendName, String ebCliStreet, String ebCliCity, String ebCliPostCode, String ebCliTelGeneral) {
       this.typeclient = typeclient;
       this.ebCountry = ebCountry;
       this.ebCliname = ebCliname;
       this.ebCliFriendName = ebCliFriendName;
       this.ebCliStreet = ebCliStreet;
       this.ebCliCity = ebCliCity;
       this.ebCliPostCode = ebCliPostCode;
       this.ebCliTelGeneral = ebCliTelGeneral;
    }
   
    public Integer getIdebCli() {
        return this.idebCli;
    }
    
    public void setIdebCli(Integer idebCli) {
        this.idebCli = idebCli;
    }
    public Typeclient getTypeclient() {
        return this.typeclient;
    }
    
    public void setTypeclient(Typeclient typeclient) {
        this.typeclient = typeclient;
    }
    public EbCountry getEbCountry() {
        return this.ebCountry;
    }
    
    public void setEbCountry(EbCountry ebCountry) {
        this.ebCountry = ebCountry;
    }
    public String getEbCliname() {
        return this.ebCliname;
    }
    
    public void setEbCliname(String ebCliname) {
        this.ebCliname = ebCliname;
    }
    public String getEbCliFriendName() {
        return this.ebCliFriendName;
    }
    
    public void setEbCliFriendName(String ebCliFriendName) {
        this.ebCliFriendName = ebCliFriendName;
    }
    public String getEbCliStreet() {
        return this.ebCliStreet;
    }
    
    public void setEbCliStreet(String ebCliStreet) {
        this.ebCliStreet = ebCliStreet;
    }
    public String getEbCliCity() {
        return this.ebCliCity;
    }
    
    public void setEbCliCity(String ebCliCity) {
        this.ebCliCity = ebCliCity;
    }
    public String getEbCliPostCode() {
        return this.ebCliPostCode;
    }
    
    public void setEbCliPostCode(String ebCliPostCode) {
        this.ebCliPostCode = ebCliPostCode;
    }
    public String getEbCliTelGeneral() {
        return this.ebCliTelGeneral;
    }
    
    public void setEbCliTelGeneral(String ebCliTelGeneral) {
        this.ebCliTelGeneral = ebCliTelGeneral;
    }




}


