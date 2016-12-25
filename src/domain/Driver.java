//Sum//V1.00 24MAR16 1152AM
package domain;

import java.io.Serializable;
import java.util.List;
//import javax.persistence.Basic;
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.NamedQueries;
//import javax.persistence.NamedQuery;
//import javax.persistence.OneToMany;
//import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Sum(Local)
 */
//@Entity
//@Table(name = "DRIVER")
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "Driver.findAll", query = "SELECT d FROM Driver d"),
//    @NamedQuery(name = "Driver.findByDriverid", query = "SELECT d FROM Driver d WHERE d.driverid = :driverid"),
//    @NamedQuery(name = "Driver.findByStaffid", query = "SELECT d FROM Driver d WHERE d.staffid = :staffid")})
public class Driver implements Serializable {
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "driverid")
    private List<Driverschedule> driverscheduleList;
    private static final long serialVersionUID = 1L;
//    @Id
//    @Basic(optional = false)
//    @Column(name = "DRIVERID")
    private String driverid;
//    @Basic(optional = false)
//    @Column(name = "STAFFID")
    private String staffid;

    public Driver() {
    }

    public Driver(String driverid) {
        this.driverid = driverid;
    }

    public Driver(String driverid, String staffid) {
        this.driverid = driverid;
        this.staffid = staffid;
    }

    public String getDriverid() {
        return driverid;
    }

    public void setDriverid(String driverid) {
        this.driverid = driverid;
    }

    public String getStaffid() {
        return staffid;
    }

    public void setStaffid(String staffid) {
        this.staffid = staffid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (driverid != null ? driverid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Driver)) {
            return false;
        }
        Driver other = (Driver) object;
        if ((this.driverid == null && other.driverid != null) || (this.driverid != null && !this.driverid.equals(other.driverid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domain.Driver[ driverid=" + driverid + " ]";
    }

    public List<Driverschedule> getDriverscheduleList() {
        return driverscheduleList;
    }

    public void setDriverscheduleList(List<Driverschedule> driverscheduleList) {
        this.driverscheduleList = driverscheduleList;
    }
    
}
