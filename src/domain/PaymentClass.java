
//V1.00 24MAR16 1152AM
package domain; //author:Teh Yi Ting
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
public class PaymentClass implements Serializable {
    private String paymentid;
    private double totalamt;
    private String paymethod;
    private String creditcardno;
    private double paidamt;
    private double cashchange;
    private String orderid;
    
    public PaymentClass() {
    }

    public PaymentClass(String paymentid) {
        this.paymentid = paymentid;
    }

    public PaymentClass(String paymentid, double totalamt, String paymethod, String creditcardno, double paidamt, double cashchange, String orderid) {
        this.paymentid = paymentid;
        this.totalamt = totalamt;
        this.paymethod = paymethod;
        this.creditcardno = creditcardno;
        this.paidamt = paidamt;
        this.cashchange = cashchange;
        this.orderid = orderid;
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

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }
}