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
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Sum(Local)
 */
//@Entity
//@Table(name = "BUS")
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "Bus.findAll", query = "SELECT b FROM Bus b"),
//    @NamedQuery(name = "Bus.findByBusid", query = "SELECT b FROM Bus b WHERE b.busid = :busid"),
//    @NamedQuery(name = "Bus.findByPlateno", query = "SELECT b FROM Bus b WHERE b.plateno = :plateno"),
//    @NamedQuery(name = "Bus.findByBustype", query = "SELECT b FROM Bus b WHERE b.bustype = :bustype")})
public class Bus implements Serializable {
    private static final long serialVersionUID = 1L;
//    @Id
//    @Basic(optional = false)
//    @Column(name = "BUSID")
    private String busid;
//    @Column(name = "PLATENO")
    private String plateno;
//    @Column(name = "BUSTYPE")
    private Character bustype;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "busid")
    private List<Trip> tripList;

    public Bus() {
    }

    public Bus(String busid) {
        this.busid = busid;
    }
    public Bus(String busid, String plateno, String bustype) { //YiTing
        this.busid = busid;
        this.plateno = plateno;
        this.bustype =bustype.charAt(0);
    }
    
    public Bus(String busid, String plateno, char bustype){ //Sum
        this.busid = busid;
        this.bustype = bustype;
        this.plateno = plateno;
    }

    public String getBusid() {
        return busid;
    }

    public void setBusid(String busid) {
        this.busid = busid;
    }

    public String getPlateno() {
        return plateno;
    }

    public void setPlateno(String plateno) {
        this.plateno = plateno;
    }

    public Character getBustype() {
        return bustype;
    }

    public void setBustype(Character bustype) {
        this.bustype = bustype;
    }

    @XmlTransient
    public List<Trip> getTripList() {
        return tripList;
    }

    public void setTripList(List<Trip> tripList) {
        this.tripList = tripList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (busid != null ? busid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bus)) {
            return false;
        }
        Bus other = (Bus) object;
        if ((this.busid == null && other.busid != null) || (this.busid != null && !this.busid.equals(other.busid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domain.Bus[ busid=" + busid + " ]";
    }
    
}
