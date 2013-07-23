/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author jsmaya
 */
@Entity
@Table(name = "eb_releventempdate", catalog = "eb", schema = "")
@NamedQueries({
    @NamedQuery(name = "EbReleventempdate.findAll", query = "SELECT e FROM EbReleventempdate e"),
    @NamedQuery(name = "EbReleventempdate.findByIdebrelEventEmpDate", query = "SELECT e FROM EbReleventempdate e WHERE e.idebrelEventEmpDate = :idebrelEventEmpDate"),
    @NamedQuery(name = "EbReleventempdate.findByEbrelEventEmpDateEventId", query = "SELECT e FROM EbReleventempdate e WHERE e.ebrelEventEmpDateEventId = :ebrelEventEmpDateEventId"),
    @NamedQuery(name = "EbReleventempdate.findByEbrelEventEmpDateEmployee", query = "SELECT e FROM EbReleventempdate e WHERE e.ebrelEventEmpDateEmployee = :ebrelEventEmpDateEmployee"),
    @NamedQuery(name = "EbReleventempdate.findByEbrelEventEmpDateStart", query = "SELECT e FROM EbReleventempdate e WHERE e.ebrelEventEmpDateStart = :ebrelEventEmpDateStart"),
    @NamedQuery(name = "EbReleventempdate.findByEbrelEventEmpDateEnd", query = "SELECT e FROM EbReleventempdate e WHERE e.ebrelEventEmpDateEnd = :ebrelEventEmpDateEnd"),
    @NamedQuery(name = "EbReleventempdate.findByEbrelEventEmpDateTrajet", query = "SELECT e FROM EbReleventempdate e WHERE e.ebrelEventEmpDateTrajet = :ebrelEventEmpDateTrajet"),
    @NamedQuery(name = "EbReleventempdate.findByEbrelEventEmpDateLunch", query = "SELECT e FROM EbReleventempdate e WHERE e.ebrelEventEmpDateLunch = :ebrelEventEmpDateLunch")})
public class EbReleventempdate implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ideb_relEventEmpDate")
    private Integer idebrelEventEmpDate;
    @Basic(optional = false)
    @Column(name = "eb_relEventEmpDateEventId")
    private int ebrelEventEmpDateEventId;
    @Basic(optional = false)
    @Column(name = "eb_relEventEmpDateEmployee")
    private int ebrelEventEmpDateEmployee;
    @Basic(optional = false)
    @Column(name = "eb_relEventEmpDateStart")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ebrelEventEmpDateStart;
    @Basic(optional = false)
    @Column(name = "eb_relEventEmpDateEnd")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ebrelEventEmpDateEnd;
    @Column(name = "eb_relEventEmpDateTrajet")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ebrelEventEmpDateTrajet;
    @Column(name = "eb_relEventEmpDateLunch")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ebrelEventEmpDateLunch;
    @Lob
    @Column(name = "eb_relEventEmpDateJobDesc")
    private String ebrelEventEmpDateJobDesc;

    public EbReleventempdate() {
    }

    public EbReleventempdate(Integer idebrelEventEmpDate) {
        this.idebrelEventEmpDate = idebrelEventEmpDate;
    }

    public EbReleventempdate(Integer idebrelEventEmpDate, int ebrelEventEmpDateEventId, int ebrelEventEmpDateEmployee, Date ebrelEventEmpDateStart, Date ebrelEventEmpDateEnd) {
        this.idebrelEventEmpDate = idebrelEventEmpDate;
        this.ebrelEventEmpDateEventId = ebrelEventEmpDateEventId;
        this.ebrelEventEmpDateEmployee = ebrelEventEmpDateEmployee;
        this.ebrelEventEmpDateStart = ebrelEventEmpDateStart;
        this.ebrelEventEmpDateEnd = ebrelEventEmpDateEnd;
    }

    public Integer getIdebrelEventEmpDate() {
        return idebrelEventEmpDate;
    }

    public void setIdebrelEventEmpDate(Integer idebrelEventEmpDate) {
        Integer oldIdebrelEventEmpDate = this.idebrelEventEmpDate;
        this.idebrelEventEmpDate = idebrelEventEmpDate;
        changeSupport.firePropertyChange("idebrelEventEmpDate", oldIdebrelEventEmpDate, idebrelEventEmpDate);
    }

    public int getEbrelEventEmpDateEventId() {
        return ebrelEventEmpDateEventId;
    }

    public void setEbrelEventEmpDateEventId(int ebrelEventEmpDateEventId) {
        int oldEbrelEventEmpDateEventId = this.ebrelEventEmpDateEventId;
        this.ebrelEventEmpDateEventId = ebrelEventEmpDateEventId;
        changeSupport.firePropertyChange("ebrelEventEmpDateEventId", oldEbrelEventEmpDateEventId, ebrelEventEmpDateEventId);
    }

    public int getEbrelEventEmpDateEmployee() {
        return ebrelEventEmpDateEmployee;
    }

    public void setEbrelEventEmpDateEmployee(int ebrelEventEmpDateEmployee) {
        int oldEbrelEventEmpDateEmployee = this.ebrelEventEmpDateEmployee;
        this.ebrelEventEmpDateEmployee = ebrelEventEmpDateEmployee;
        changeSupport.firePropertyChange("ebrelEventEmpDateEmployee", oldEbrelEventEmpDateEmployee, ebrelEventEmpDateEmployee);
    }

    public Date getEbrelEventEmpDateStart() {
        return ebrelEventEmpDateStart;
    }

    public void setEbrelEventEmpDateStart(Date ebrelEventEmpDateStart) {
        Date oldEbrelEventEmpDateStart = this.ebrelEventEmpDateStart;
        this.ebrelEventEmpDateStart = ebrelEventEmpDateStart;
        changeSupport.firePropertyChange("ebrelEventEmpDateStart", oldEbrelEventEmpDateStart, ebrelEventEmpDateStart);
    }

    public Date getEbrelEventEmpDateEnd() {
        return ebrelEventEmpDateEnd;
    }

    public void setEbrelEventEmpDateEnd(Date ebrelEventEmpDateEnd) {
        Date oldEbrelEventEmpDateEnd = this.ebrelEventEmpDateEnd;
        this.ebrelEventEmpDateEnd = ebrelEventEmpDateEnd;
        changeSupport.firePropertyChange("ebrelEventEmpDateEnd", oldEbrelEventEmpDateEnd, ebrelEventEmpDateEnd);
    }

    public Date getEbrelEventEmpDateTrajet() {
        return ebrelEventEmpDateTrajet;
    }

    public void setEbrelEventEmpDateTrajet(Date ebrelEventEmpDateTrajet) {
        Date oldEbrelEventEmpDateTrajet = this.ebrelEventEmpDateTrajet;
        this.ebrelEventEmpDateTrajet = ebrelEventEmpDateTrajet;
        changeSupport.firePropertyChange("ebrelEventEmpDateTrajet", oldEbrelEventEmpDateTrajet, ebrelEventEmpDateTrajet);
    }

    public Date getEbrelEventEmpDateLunch() {
        return ebrelEventEmpDateLunch;
    }

    public void setEbrelEventEmpDateLunch(Date ebrelEventEmpDateLunch) {
        Date oldEbrelEventEmpDateLunch = this.ebrelEventEmpDateLunch;
        this.ebrelEventEmpDateLunch = ebrelEventEmpDateLunch;
        changeSupport.firePropertyChange("ebrelEventEmpDateLunch", oldEbrelEventEmpDateLunch, ebrelEventEmpDateLunch);
    }

    public String getEbrelEventEmpDateJobDesc() {
        return ebrelEventEmpDateJobDesc;
    }

    public void setEbrelEventEmpDateJobDesc(String ebrelEventEmpDateJobDesc) {
        String oldEbrelEventEmpDateJobDesc = this.ebrelEventEmpDateJobDesc;
        this.ebrelEventEmpDateJobDesc = ebrelEventEmpDateJobDesc;
        changeSupport.firePropertyChange("ebrelEventEmpDateJobDesc", oldEbrelEventEmpDateJobDesc, ebrelEventEmpDateJobDesc);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idebrelEventEmpDate != null ? idebrelEventEmpDate.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EbReleventempdate)) {
            return false;
        }
        EbReleventempdate other = (EbReleventempdate) object;
        if ((this.idebrelEventEmpDate == null && other.idebrelEventEmpDate != null) || (this.idebrelEventEmpDate != null && !this.idebrelEventEmpDate.equals(other.idebrelEventEmpDate))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ui.EbReleventempdate[ idebrelEventEmpDate=" + idebrelEventEmpDate + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
