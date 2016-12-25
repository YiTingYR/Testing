//Sum
//V1.00 24MAR16 1152AM
package control;

import da.*;
import ui.*;
import domain.*;
import control.*;
import java.util.ArrayList;

public class MaintainTrip{
    
    private TripDA tripDA;
    
    public MaintainTrip(){
        tripDA = new TripDA();
    }
    
    public ArrayList<Trip> getActiveTrips(){
        
        return tripDA.getActiveTrips();
        
        
    }
    
    public ArrayList<Route> getAvailableRouteInfo(){
        return tripDA.getAvailableRouteInfo();
    }
    
    public ArrayList<Trip> searchTrip(Trip tripIn, Route routeIn){
        return tripDA.searchTrip(tripIn, routeIn);
    }
    
    public Trip getTripByTripNo(Trip trip){
        return tripDA.getTripByTripNo(trip);
    }
    
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
        tripDA.shutDown();
    }
}