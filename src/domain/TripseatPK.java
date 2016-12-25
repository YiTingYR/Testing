//Sum
//V1.00 24MAR16 1152AM
package domain;

import java.io.Serializable;
//import javax.persistence.Basic;
//import javax.persistence.Column;
//import javax.persistence.Embeddable;

/**
 *
 * @author Sum(Local)
 */
//@Embeddable
public class TripseatPK implements Serializable {
//    @Basic(optional = false)
//    @Column(name = "TRIPNO")
    private String tripno;
//    @Basic(optional = false)
//    @Column(name = "SEATNO")
    private String seatno;

    public TripseatPK() {
    }

    public TripseatPK(String tripno, String seatno) {
        this.tripno = tripno;
        this.seatno = seatno;
    }

    public String getTripno() {
        return tripno;
    }

    public void setTripno(String tripno) {
        this.tripno = tripno;
    }

    public String getSeatno() {
        return seatno;
    }

    public void setSeatno(String seatno) {
        this.seatno = seatno;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tripno != null ? tripno.hashCode() : 0);
        hash += (seatno != null ? seatno.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TripseatPK)) {
            return false;
        }
        TripseatPK other = (TripseatPK) object;
        if ((this.tripno == null && other.tripno != null) || (this.tripno != null && !this.tripno.equals(other.tripno))) {
            return false;
        }
        if ((this.seatno == null && other.seatno != null) || (this.seatno != null && !this.seatno.equals(other.seatno))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domain.TripseatPK[ tripno=" + tripno + ", seatno=" + seatno + " ]";
    }
    
}
