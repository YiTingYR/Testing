
//V1.00 24MAR16 1152AM
package domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
//import javax.persistence.Basic;
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.JoinTable;
//import javax.persistence.ManyToMany;
//import javax.persistence.NamedQueries;
//import javax.persistence.NamedQuery;
//import javax.persistence.OneToMany;
//import javax.persistence.Table;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Hon Ching
 */
//@Entity
//@Table(name = "ORDERTABLE")
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "Ordertable.findAll", query = "SELECT o FROM Ordertable o"),
//    @NamedQuery(name = "Ordertable.findByOrderid", query = "SELECT o FROM Ordertable o WHERE o.orderid = :orderid"),
//    @NamedQuery(name = "Ordertable.findByOrderdate", query = "SELECT o FROM Ordertable o WHERE o.orderdate = :orderdate")})
public class Ordertable2 implements Serializable {
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orderid")
    private Collection<Payment> paymentCollection;
    private static final long serialVersionUID = 1L;
//    @Id
//    @Basic(optional = false)
//    @Column(name = "ORDERID")
    private String orderid;
//    @Basic(optional = false)
//    @Column(name = "ORDERDATE")
//    @Temporal(TemporalType.DATE)
    private Date orderdate;
//    @JoinTable(name = "ORDERLINE", joinColumns = {
//        @JoinColumn(name = "ORDERID", referencedColumnName = "ORDERID")}, inverseJoinColumns = {
//        @JoinColumn(name = "TRIPNO", referencedColumnName = "TRIPNO"),
//        @JoinColumn(name = "SEATNO", referencedColumnName = "SEATNO")})
//    @ManyToMany
    private Collection<Tripseat> tripseatCollection;

    public Ordertable2() {
    }

    public Ordertable2(String orderid) {
        this.orderid = orderid;
    }

    public Ordertable2(String orderid, Date orderdate) {
        this.orderid = orderid;
        this.orderdate = orderdate;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public Date getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
    }

    @XmlTransient
    public Collection<Tripseat> getTripseatCollection() {
        return tripseatCollection;
    }

    public void setTripseatCollection(Collection<Tripseat> tripseatCollection) {
        this.tripseatCollection = tripseatCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderid != null ? orderid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ordertable2)) {
            return false;
        }
        Ordertable2 other = (Ordertable2) object;
        if ((this.orderid == null && other.orderid != null) || (this.orderid != null && !this.orderid.equals(other.orderid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domain.Ordertable[ orderid=" + orderid + " ]";
    }

    @XmlTransient
    public Collection<Payment> getPaymentCollection() {
        return paymentCollection;
    }

    public void setPaymentCollection(Collection<Payment> paymentCollection) {
        this.paymentCollection = paymentCollection;
    }
    
}
