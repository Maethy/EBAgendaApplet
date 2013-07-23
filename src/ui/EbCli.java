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
@Table(name = "eb_cli", catalog = "eb", schema = "")
@NamedQueries({
    @NamedQuery(name = "EbCli.findAll", query = "SELECT e FROM EbCli e"),
    @NamedQuery(name = "EbCli.findByIdebCli", query = "SELECT e FROM EbCli e WHERE e.idebCli = :idebCli"),
    @NamedQuery(name = "EbCli.findByEbCliname", query = "SELECT e FROM EbCli e WHERE e.ebCliname = :ebCliname"),
    @NamedQuery(name = "EbCli.findByEbcliFriendName", query = "SELECT e FROM EbCli e WHERE e.ebcliFriendName = :ebcliFriendName"),
    @NamedQuery(name = "EbCli.findByEbcliStreet", query = "SELECT e FROM EbCli e WHERE e.ebcliStreet = :ebcliStreet"),
    @NamedQuery(name = "EbCli.findByEbcliCity", query = "SELECT e FROM EbCli e WHERE e.ebcliCity = :ebcliCity"),
    @NamedQuery(name = "EbCli.findByEbcliPostCode", query = "SELECT e FROM EbCli e WHERE e.ebcliPostCode = :ebcliPostCode"),
    @NamedQuery(name = "EbCli.findByEbcliCountry", query = "SELECT e FROM EbCli e WHERE e.ebcliCountry = :ebcliCountry"),
    @NamedQuery(name = "EbCli.findByEbcliTelGeneral", query = "SELECT e FROM EbCli e WHERE e.ebcliTelGeneral = :ebcliTelGeneral"),
    @NamedQuery(name = "EbCli.findByEbcliTypeClient", query = "SELECT e FROM EbCli e WHERE e.ebcliTypeClient = :ebcliTypeClient")})
public class EbCli implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ideb_cli")
    private Integer idebCli;
    @Column(name = "eb_cliname")
    private String ebCliname;
    @Column(name = "eb_cliFriendName")
    private String ebcliFriendName;
    @Column(name = "eb_cliStreet")
    private String ebcliStreet;
    @Column(name = "eb_cliCity")
    private String ebcliCity;
    @Column(name = "eb_cliPostCode")
    private String ebcliPostCode;
    @Column(name = "eb_cliCountry")
    private Integer ebcliCountry;
    @Column(name = "eb_cliTelGeneral")
    private String ebcliTelGeneral;
    @Column(name = "eb_cliTypeClient")
    private Integer ebcliTypeClient;

    public EbCli() {
    }

    public EbCli(Integer idebCli) {
        this.idebCli = idebCli;
    }

    public Integer getIdebCli() {
        return idebCli;
    }

    public void setIdebCli(Integer idebCli) {
        Integer oldIdebCli = this.idebCli;
        this.idebCli = idebCli;
        changeSupport.firePropertyChange("idebCli", oldIdebCli, idebCli);
    }

    public String getEbCliname() {
        return ebCliname;
    }

    public void setEbCliname(String ebCliname) {
        String oldEbCliname = this.ebCliname;
        this.ebCliname = ebCliname;
        changeSupport.firePropertyChange("ebCliname", oldEbCliname, ebCliname);
    }

    public String getEbcliFriendName() {
        return ebcliFriendName;
    }

    public void setEbcliFriendName(String ebcliFriendName) {
        String oldEbcliFriendName = this.ebcliFriendName;
        this.ebcliFriendName = ebcliFriendName;
        changeSupport.firePropertyChange("ebcliFriendName", oldEbcliFriendName, ebcliFriendName);
    }

    public String getEbcliStreet() {
        return ebcliStreet;
    }

    public void setEbcliStreet(String ebcliStreet) {
        String oldEbcliStreet = this.ebcliStreet;
        this.ebcliStreet = ebcliStreet;
        changeSupport.firePropertyChange("ebcliStreet", oldEbcliStreet, ebcliStreet);
    }

    public String getEbcliCity() {
        return ebcliCity;
    }

    public void setEbcliCity(String ebcliCity) {
        String oldEbcliCity = this.ebcliCity;
        this.ebcliCity = ebcliCity;
        changeSupport.firePropertyChange("ebcliCity", oldEbcliCity, ebcliCity);
    }

    public String getEbcliPostCode() {
        return ebcliPostCode;
    }

    public void setEbcliPostCode(String ebcliPostCode) {
        String oldEbcliPostCode = this.ebcliPostCode;
        this.ebcliPostCode = ebcliPostCode;
        changeSupport.firePropertyChange("ebcliPostCode", oldEbcliPostCode, ebcliPostCode);
    }

    public Integer getEbcliCountry() {
        return ebcliCountry;
    }

    public void setEbcliCountry(Integer ebcliCountry) {
        Integer oldEbcliCountry = this.ebcliCountry;
        this.ebcliCountry = ebcliCountry;
        changeSupport.firePropertyChange("ebcliCountry", oldEbcliCountry, ebcliCountry);
    }

    public String getEbcliTelGeneral() {
        return ebcliTelGeneral;
    }

    public void setEbcliTelGeneral(String ebcliTelGeneral) {
        String oldEbcliTelGeneral = this.ebcliTelGeneral;
        this.ebcliTelGeneral = ebcliTelGeneral;
        changeSupport.firePropertyChange("ebcliTelGeneral", oldEbcliTelGeneral, ebcliTelGeneral);
    }

    public Integer getEbcliTypeClient() {
        return ebcliTypeClient;
    }

    public void setEbcliTypeClient(Integer ebcliTypeClient) {
        Integer oldEbcliTypeClient = this.ebcliTypeClient;
        this.ebcliTypeClient = ebcliTypeClient;
        changeSupport.firePropertyChange("ebcliTypeClient", oldEbcliTypeClient, ebcliTypeClient);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idebCli != null ? idebCli.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EbCli)) {
            return false;
        }
        EbCli other = (EbCli) object;
        if ((this.idebCli == null && other.idebCli != null) || (this.idebCli != null && !this.idebCli.equals(other.idebCli))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ui.EbCli[ idebCli=" + idebCli + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
