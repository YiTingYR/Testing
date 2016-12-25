
//V1.00 24MAR16 1152AM
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
//@Table(name = "ROUTE")
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "Route.findAll", query = "SELECT r FROM Route r"),
//    @NamedQuery(name = "Route.findByRouteid", query = "SELECT r FROM Route r WHERE r.routeid = :routeid"),
//    @NamedQuery(name = "Route.findByOrigin", query = "SELECT r FROM Route r WHERE r.origin = :origin"),
//    @NamedQuery(name = "Route.findByDestination", query = "SELECT r FROM Route r WHERE r.destination = :destination")})
public class Route implements Serializable {
    private static final long serialVersionUID = 1L;
//    @Id
//    @Basic(optional = false)
//    @Column(name = "ROUTEID")
    private String routeid;
//    @Column(name = "ORIGIN")
    private String origin;
//    @Column(name = "DESTINATION")
    private String destination;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "routeid")
    private List<Trip> tripList;

    public Route() {
    }

    public Route(String routeid) {
        this.routeid = routeid;
    }
    
    
    //added
    public Route(String routeid, String origin, String destination){
        this.routeid = routeid;
        this.origin = origin;
        this.destination = destination;
    }

    public String getRouteid() {
        return routeid;
    }

    public void setRouteid(String routeid) {
        this.routeid = routeid;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
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
        hash += (routeid != null ? routeid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Route)) {
            return false;
        }
        Route other = (Route) object;
        if ((this.routeid == null && other.routeid != null) || (this.routeid != null && !this.routeid.equals(other.routeid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domain.Route[ routeid=" + routeid + " ]";
    }
    
}
