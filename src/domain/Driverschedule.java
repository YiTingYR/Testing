//Sum
//V1.00 24MAR16 1152AM
package domain;

import java.io.Serializable;
import java.util.Date;
//import javax.persistence.Basic;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.NamedQueries;
//import javax.persistence.NamedQuery;
//import javax.persistence.Table;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Sum(Local)
 */
//@Entity
//@Table(name = "DRIVERSCHEDULE")
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "Driverschedule.findAll", query = "SELECT d FROM Driverschedule d"),
//    @NamedQuery(name = "Driverschedule.findByScheduleid", query = "SELECT d FROM Driverschedule d WHERE d.scheduleid = :scheduleid"),
//    @NamedQuery(name = "Driverschedule.findByScheduledate", query = "SELECT d FROM Driverschedule d WHERE d.scheduledate = :scheduledate"),
//    @NamedQuery(name = "Driverschedule.findByAvailability", query = "SELECT d FROM Driverschedule d WHERE d.availability = :availability")})
public class Driverschedule implements Serializable {
    private static final long serialVersionUID = 1L;
//    @Id
//    @Basic(optional = false)
//    @Column(name = "SCHEDULEID")
    private String scheduleid;
//    @Column(name = "SCHEDULEDATE")
//    @Temporal(TemporalType.DATE)
    private Date scheduledate;
//    @Column(name = "AVAILABILITY")
    private String availability;
//    @JoinColumn(name = "DRIVERID", referencedColumnName = "DRIVERID")
//    @ManyToOne(optional = false)
    private Driver driverid;

    public Driverschedule() {
    }

    public Driverschedule(String scheduleid, Date scheduledate, String availability, Driver driverid) {
        this.scheduleid = scheduleid;
        this.scheduledate = scheduledate;
        this.availability = availability;
        this.driverid = driverid;
    }

    public Driverschedule(String scheduleid) {
        this.scheduleid = scheduleid;
    }

    public String getScheduleid() {
        return scheduleid;
    }

    public void setScheduleid(String scheduleid) {
        this.scheduleid = scheduleid;
    }

    public Date getScheduledate() {
        return scheduledate;
    }

    public void setScheduledate(Date scheduledate) {
        this.scheduledate = scheduledate;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public Driver getDriverid() {
        return driverid;
    }

    public void setDriverid(Driver driverid) {
        this.driverid = driverid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (scheduleid != null ? scheduleid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Driverschedule)) {
            return false;
        }
        Driverschedule other = (Driverschedule) object;
        if ((this.scheduleid == null && other.scheduleid != null) || (this.scheduleid != null && !this.scheduleid.equals(other.scheduleid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domain.Driverschedule[ scheduleid=" + scheduleid + " ]";
    }
    
}
