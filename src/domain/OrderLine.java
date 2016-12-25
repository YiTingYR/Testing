//Sum
//V1.00 24MAR16 1152AM
package domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class OrderLine{
    
    private String orderID;
    private String tripNo;
    private String seatNo;
    
    public OrderLine(){
        
    }
    
    public OrderLine(String orderID, String tripNo, String seatNo){
        this.orderID = orderID;
        this.tripNo = tripNo;
        this.seatNo = seatNo;
    }
    
    public String getOrderID(){
        return orderID;
    }
    
    public void setOrderID(String orderID){
        this.orderID = orderID;
    }
    
    public String getTripNo(){
        return tripNo;
    }
    
    public void setTripNo(String tripNo){
        this.tripNo = tripNo;
    }
    
    public String getSeatNo(){
        return seatNo;
    }
    
    public void setSeatNo(String seatNo){
        this.seatNo = seatNo;
    }
    
    public String toString(){
        return "This is an OrderLine object.";
    }
}