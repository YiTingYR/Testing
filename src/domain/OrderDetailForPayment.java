
//V1.00 24MAR16 1152AM
package domain; //author:Teh Yi Ting
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
public class OrderDetailForPayment implements Serializable {
    private String orderid;
    private String tripno;
    private String seatno;
    
    public OrderDetailForPayment() {
    }

    public OrderDetailForPayment(String orderid, String tripno, String seatno) {
        this.orderid = orderid;
        this.tripno = tripno;
        this.seatno = seatno;
    }

   
    public String getOrderId() {
        return orderid;
    }

    public void setOrderId(String orderid) {
        this.orderid = orderid;
    }

   
    public String getTripNo() {
        return tripno;
    }

    public void setTripNo(String tripno) {
        this.tripno = tripno;
    }

    public String getSeatNo() {
        return seatno;
    }

    public void setSeatNo(String seatno) {
        this.seatno = seatno;
    }
}