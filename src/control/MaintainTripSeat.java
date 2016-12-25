//Sum
//V1.00 24MAR16 1152AM
package control;

import da.*;
import ui.*;
import domain.*;
import control.*;
import java.util.ArrayList;

public class MaintainTripSeat{
    
    private TripSeatDA tripSeatDA;
    
    public MaintainTripSeat(){
        tripSeatDA = new TripSeatDA();
    }
    
    public ArrayList<TripSeat2> getRecord(TripSeat2 ts){
        return tripSeatDA.getRecord(ts);
    }
    
//    public int addRecord(Staff staff){
//        return tripSeatDA.addRecord(staff);
//    }
//    
//    public int updateRecord(Staff staff){
//        return tripSeatDA.updateRecord(staff);
//    }
//    
//    public int deleteRecord(Staff staff){
//        return tripSeatDA.deleteRecord(staff);
//    }
//    
//    public String getLatestID(){
//        return tripSeatDA.getLatestID();
//    }
    
//    public int getNextNumber(){
//        return staffDA.getNextNumber();
//    }
    
    public void closeDB(){
        tripSeatDA.shutDown();
    }
}