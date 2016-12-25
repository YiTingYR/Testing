
//V1.00 24MAR16 1152AM
package domain;

import java.io.Serializable;
//import javax.persistence.Basic;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.NamedQueries;
//import javax.persistence.NamedQuery;
//import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author HP
 */
//@Entity
//@Table(name = "PAYMENT")
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "Payment.findAll", query = "SELECT p FROM Payment p"),
//    @NamedQuery(name = "Payment.findByPaymentid", query = "SELECT p FROM Payment p WHERE p.paymentid = :paymentid"),
//    @NamedQuery(name = "Payment.findByTotalamt", query = "SELECT p FROM Payment p WHERE p.totalamt = :totalamt"),
//    @NamedQuery(name = "Payment.findByPaymethod", query = "SELECT p FROM Payment p WHERE p.paymethod = :paymethod"),
//    @NamedQuery(name = "Payment.findByCreditcardno", query = "SELECT p FROM Payment p WHERE p.creditcardno = :creditcardno"),
//    @NamedQuery(name = "Payment.findByPaidamt", query = "SELECT p FROM Payment p WHERE p.paidamt = :paidamt"),
//    @NamedQuery(name = "Payment.findByCashchange", query = "SELECT p FROM Payment p WHERE p.cashchange = :cashchange")})
public class Payment implements Serializable {
    private static final long serialVersionUID = 1L;
//    @Id
//    @Basic(optional = false)
//    @Column(name = "PAYMENTID")
    private String paymentid;
//    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
//    @Column(name = "TOTALAMT")
    private Double totalamt;
//    @Column(name = "PAYMETHOD")
    private String paymethod;
//    @Column(name = "CREDITCARDNO")
    private String creditcardno;
//    @Column(name = "PAIDAMT")
    private Double paidamt;
//    @Column(name = "CASHCHANGE")
    private Double cashchange;
//    @JoinColumn(name = "ORDERID", referencedColumnName = "ORDERID")
//    @ManyToOne(optional = false)
    private Ordertable2 orderid;

    public Payment() {
    }

    public Payment(String paymentid) {
        this.paymentid = paymentid;
    }

    public String getPaymentid() {
        return paymentid;
    }

    public void setPaymentid(String paymentid) {
        this.paymentid = paymentid;
    }

    public Double getTotalamt() {
        return totalamt;
    }

    public void setTotalamt(Double totalamt) {
        this.totalamt = totalamt;
    }

    public String getPaymethod() {
        return paymethod;
    }

    public void setPaymethod(String paymethod) {
        this.paymethod = paymethod;
    }

    public String getCreditcardno() {
        return creditcardno;
    }

    public void setCreditcardno(String creditcardno) {
        this.creditcardno = creditcardno;
    }

    public Double getPaidamt() {
        return paidamt;
    }

    public void setPaidamt(Double paidamt) {
        this.paidamt = paidamt;
    }

    public Double getCashchange() {
        return cashchange;
    }

    public void setCashchange(Double cashchange) {
        this.cashchange = cashchange;
    }

    public Ordertable2 getOrderid() {
        return orderid;
    }

    public void setOrderid(Ordertable2 orderid) {
        this.orderid = orderid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (paymentid != null ? paymentid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Payment)) {
            return false;
        }
        Payment other = (Payment) object;
        if ((this.paymentid == null && other.paymentid != null) || (this.paymentid != null && !this.paymentid.equals(other.paymentid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domain.Payment[ paymentid=" + paymentid + " ]";
    }
    
}
