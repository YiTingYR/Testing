//Sum
//V1.00 24MAR16 1152AM
package domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class Ordertable implements Serializable {
    
    private String orderid;
   

    private Date orderdate;
    
    private List<Tripseat> tripseatList;

    public Ordertable() {
    }

    public Ordertable(String orderid) {
        this.orderid = orderid;
    }

    public Ordertable(String orderid, Date orderdate) {
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

    public List<Tripseat> getTripseatList() {
        return tripseatList;
    }

    public void setTripseatList(List<Tripseat> tripseatList) {
        this.tripseatList = tripseatList;
    }


    @Override
    public String toString() {
        return "domain.Ordertable[ orderid=" + orderid + " ]";
    }
    
}
