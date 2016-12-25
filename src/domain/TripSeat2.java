//Sum
//V1.00 24MAR16 1152AM
package domain;

public class TripSeat2{
    
    private String tripNo;
    private String seatNo;
    private char seatAvailability;
    
    
    //added 1123PM 21MAR16
    private Seat seat;
    private Trip trip;
    private OrderLine orderLine;
    
    public TripSeat2(){
        
    }
    
    public TripSeat2(String tripNo, String seatNo, char seatAvailability){
        this.tripNo = tripNo;
        this.seatNo = seatNo;
        this.seatAvailability = seatAvailability;
        
    }
    //added 1123PM 21MAR16
    public TripSeat2(String tripNo, String seatNo, char seatAvailability, Trip trip){
        this.tripNo = tripNo;
        this.seatNo = seatNo;
        this.seatAvailability = seatAvailability;
        this.trip = trip;
        
    }
    
    public TripSeat2(String tripNo, String seatNo, char seatAvailability, Trip trip, OrderLine orderLine){
        this.tripNo = tripNo;
        this.seatNo = seatNo;
        this.seatAvailability = seatAvailability;
        this.trip = trip;
        this.orderLine = orderLine;
        
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
    
    public char getSeatAvailability(){
        return seatAvailability;
    }
    
    public void setSeatAvailability(char seatAvailability){
        this.seatAvailability = seatAvailability;
    }
    
    //added 1123PM 21MAR16///////////
    public Trip getTrip(){
        return this.trip;
    }
    
    public void setTrip(Trip trip){
        this.trip = trip;
    }
    
    public OrderLine getOrderLine(){
        return this.orderLine;
    }
    
    public void setOrderLine(OrderLine orderLine){
        this.orderLine = orderLine;
    }
    
    public Seat getSeat(){
        return this.seat;
    }
    
    public void setSeat(Seat seat){
        this.seat = seat;
    }
    
    ////////////////////////////
    public String toString(){
        return "This is a TripSeat object.";
    }
}