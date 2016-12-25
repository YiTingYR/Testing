//Sum
//V1.00 24MAR16 1152AM
package control;

import da.*;
import ui.*;
import domain.*;
import control.*;
import java.util.ArrayList;

public class MaintainTicketChange{
    
    private TicketChangeDA ticketChangeDA;
    
    public MaintainTicketChange(){
        ticketChangeDA = new TicketChangeDA();
    }
    
    public TripSeat2 initiateTicketChange(TripSeat2 tripSeatIn){
        return ticketChangeDA.initiateTicketChange(tripSeatIn);
    }
    
    public ArrayList<TripSeat2> getSelectableTrips(TripSeat2 tripSeatIn){
        return ticketChangeDA.getSelectableTrips(tripSeatIn);
    }
    
    public ArrayList<TripSeat2> getSelectedTrip(TripSeat2 tripSeatIn){
        return ticketChangeDA.getSelectedTrip(tripSeatIn);
    }
    
    public int saveTicketChanges(TripSeat2 tripSeatOld, TripSeat2 tripSeatNew){
        return ticketChangeDA.saveTicketChanges(tripSeatOld, tripSeatNew);
    }
    
//    public ArrayList<Ordertable> getOrderList(){
//        return ticketChangeDA.getOrderList();
//    }
//    
//    public int saveOrder(Ordertable order, ArrayList<OrderLine> orderLineList){
//        return ticketChangeDA.saveOrder(order, orderLineList);
//    }
    
//    public ArrayList<Trip> getActiveTrips(){
//        
//        return orderDA.getActiveTrips();
//        
//        
//    }
//    
//    public ArrayList<Route> getAvailableRouteInfo(){
//        return orderDA.getAvailableRouteInfo();
//    }
//    
//    public ArrayList<Trip> searchTrip(Trip tripIn, Route routeIn){
//        return orderDA.searchTrip(tripIn, routeIn);
//    }
    
//    public Staff getRecord(String staffID){
//        return tripDA.getRecord(staffID);
//    }
//    
//    public int addRecord(Staff staff){
//        return tripDA.addRecord(staff);
//    }
//    
//    public int updateRecord(Staff staff){
//        return tripDA.updateRecord(staff);
//    }
//    
//    public int deleteRecord(Staff staff){
//        return tripDA.deleteRecord(staff);
//    }
//    
//    public String getLatestID(){
//        return tripDA.getLatestID();
//    }
    
//    public int getNextNumber(){
//        return staffDA.getNextNumber();
//    }
    
    public void closeDB(){
        ticketChangeDA.shutDown();
    }
}