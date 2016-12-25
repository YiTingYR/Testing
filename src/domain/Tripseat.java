
//V1.00 24MAR16 1152AM
//Yi Ting
package domain;

import java.io.Serializable;
import java.util.Collection;
//import javax.persistence.Basic;
//import javax.persistence.Column;
//import javax.persistence.EmbeddedId;
//import javax.persistence.Entity;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToMany;
//import javax.persistence.ManyToOne;
//import javax.persistence.NamedQueries;
//import javax.persistence.NamedQuery;
//import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Hon Ching
 */
//@Entity
//@Table(name = "TRIPSEAT")
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "Tripseat.findAll", query = "SELECT t FROM Tripseat t"),
//    @NamedQuery(name = "Tripseat.findByTripno", query = "SELECT t FROM Tripseat t WHERE t.tripseatPK.tripno = :tripno"),
//    @NamedQuery(name = "Tripseat.findBySeatno", query = "SELECT t FROM Tripseat t WHERE t.tripseatPK.seatno = :seatno"),
//    @NamedQuery(name = "Tripseat.findBySeatavailability", query = "SELECT t FROM Tripseat t WHERE t.seatavailability = :seatavailability")})
public class Tripseat implements Serializable {
    private static final long serialVersionUID = 1L;
//    @EmbeddedId
    protected TripseatPK tripseatPK;
//    @Basic(optional = false)
//    @Column(name = "SEATAVAILABILITY")
    private Character seatavailability;
//    @ManyToMany(mappedBy = "tripseatCollection")
    private Collection<Ordertable2> ordertableCollection;
//    @JoinColumn(name = "TRIPNO", referencedColumnName = "TRIPNO", insertable = false, updatable = false)
//    @ManyToOne(optional = false)
    private Trip trip;
//    @JoinColumn(name = "SEATNO", referencedColumnName = "SEATNO", insertable = false, updatable = false)
//    @ManyToOne(optional = false)
    private Seat seat;

    public Tripseat() {
    }

    public Tripseat(TripseatPK tripseatPK) {
        this.tripseatPK = tripseatPK;
    }

    public Tripseat(TripseatPK tripseatPK, Character seatavailability) {
        this.tripseatPK = tripseatPK;
        this.seatavailability = seatavailability;
    }

    public Tripseat(String tripno, String seatno) {
        this.tripseatPK = new TripseatPK(tripno, seatno);
    }

    public TripseatPK getTripseatPK() {
        return tripseatPK;
    }

    public void setTripseatPK(TripseatPK tripseatPK) {
        this.tripseatPK = tripseatPK;
    }

    public Character getSeatavailability() {
        return seatavailability;
    }

    public void setSeatavailability(Character seatavailability) {
        this.seatavailability = seatavailability;
    }

    @XmlTransient
    public Collection<Ordertable2> getOrdertableCollection() {
        return ordertableCollection;
    }

    public void setOrdertableCollection(Collection<Ordertable2> ordertableCollection) {
        this.ordertableCollection = ordertableCollection;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tripseatPK != null ? tripseatPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tripseat)) {
            return false;
        }
        Tripseat other = (Tripseat) object;
        if ((this.tripseatPK == null && other.tripseatPK != null) || (this.tripseatPK != null && !this.tripseatPK.equals(other.tripseatPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domain.Tripseat[ tripseatPK=" + tripseatPK + " ]";
    }
    
}
