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
@Table(name = "eb_events", catalog = "eb", schema = "")
@NamedQueries({
    @NamedQuery(name = "EbEvents.findAll", query = "SELECT e FROM EbEvents e"),
    @NamedQuery(name = "EbEvents.findByIdebEvent", query = "SELECT e FROM EbEvents e WHERE e.idebEvent = :idebEvent"),
    @NamedQuery(name = "EbEvents.findByEbeventsName", query = "SELECT e FROM EbEvents e WHERE e.ebeventsName = :ebeventsName"),
    @NamedQuery(name = "EbEvents.findByEbeventsStartDate", query = "SELECT e FROM EbEvents e WHERE e.ebeventsStartDate = :ebeventsStartDate"),
    @NamedQuery(name = "EbEvents.findByEbeventsEndDate", query = "SELECT e FROM EbEvents e WHERE e.ebeventsEndDate = :ebeventsEndDate"),
    @NamedQuery(name = "EbEvents.findByEbeventsIdCli", query = "SELECT e FROM EbEvents e WHERE e.ebeventsIdCli = :ebeventsIdCli")})
public class EbEvents implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ideb_event")
    private Integer idebEvent;
    @Column(name = "eb_eventsName")
    private String ebeventsName;
    @Lob
    @Column(name = "eb_eventsDesc")
    private String ebeventsDesc;
    @Basic(optional = false)
    @Column(name = "eb_eventsStartDate")
    @Temporal(TemporalType.DATE)
    private Date ebeventsStartDate;
    @Column(name = "eb_eventsEndDate")
    @Temporal(TemporalType.DATE)
    private Date ebeventsEndDate;
    @Basic(optional = false)
    @Column(name = "eb_eventsIdCli")
    private int ebeventsIdCli;

    public EbEvents() {
    }

    public EbEvents(Integer idebEvent) {
        this.idebEvent = idebEvent;
    }

    public EbEvents(Integer idebEvent, Date ebeventsStartDate, int ebeventsIdCli) {
        this.idebEvent = idebEvent;
        this.ebeventsStartDate = ebeventsStartDate;
        this.ebeventsIdCli = ebeventsIdCli;
    }

    public Integer getIdebEvent() {
        return idebEvent;
    }

    public void setIdebEvent(Integer idebEvent) {
        Integer oldIdebEvent = this.idebEvent;
        this.idebEvent = idebEvent;
        changeSupport.firePropertyChange("idebEvent", oldIdebEvent, idebEvent);
    }

    public String getEbeventsName() {
        return ebeventsName;
    }

    public void setEbeventsName(String ebeventsName) {
        String oldEbeventsName = this.ebeventsName;
        this.ebeventsName = ebeventsName;
        changeSupport.firePropertyChange("ebeventsName", oldEbeventsName, ebeventsName);
    }

    public String getEbeventsDesc() {
        return ebeventsDesc;
    }

    public void setEbeventsDesc(String ebeventsDesc) {
        String oldEbeventsDesc = this.ebeventsDesc;
        this.ebeventsDesc = ebeventsDesc;
        changeSupport.firePropertyChange("ebeventsDesc", oldEbeventsDesc, ebeventsDesc);
    }

    public Date getEbeventsStartDate() {
        return ebeventsStartDate;
    }

    public void setEbeventsStartDate(Date ebeventsStartDate) {
        Date oldEbeventsStartDate = this.ebeventsStartDate;
        this.ebeventsStartDate = ebeventsStartDate;
        changeSupport.firePropertyChange("ebeventsStartDate", oldEbeventsStartDate, ebeventsStartDate);
    }

    public Date getEbeventsEndDate() {
        return ebeventsEndDate;
    }

    public void setEbeventsEndDate(Date ebeventsEndDate) {
        Date oldEbeventsEndDate = this.ebeventsEndDate;
        this.ebeventsEndDate = ebeventsEndDate;
        changeSupport.firePropertyChange("ebeventsEndDate", oldEbeventsEndDate, ebeventsEndDate);
    }

    public int getEbeventsIdCli() {
        return ebeventsIdCli;
    }

    public void setEbeventsIdCli(int ebeventsIdCli) {
        int oldEbeventsIdCli = this.ebeventsIdCli;
        this.ebeventsIdCli = ebeventsIdCli;
        changeSupport.firePropertyChange("ebeventsIdCli", oldEbeventsIdCli, ebeventsIdCli);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idebEvent != null ? idebEvent.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EbEvents)) {
            return false;
        }
        EbEvents other = (EbEvents) object;
        if ((this.idebEvent == null && other.idebEvent != null) || (this.idebEvent != null && !this.idebEvent.equals(other.idebEvent))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ui.EbEvents[ idebEvent=" + idebEvent + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
