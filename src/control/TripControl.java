package control;
import domain.*;
import da.*;
import java.util.ArrayList;
import java.util.Date;


public class TripControl{

    TripDA2 tripDA;
    
    public TripControl(){
        tripDA = new TripDA2();
    }
    
    public TripClass selectRecord(String tripno) {
        return tripDA.getRecord(tripno);
    }
   /*public ArrayList<TripClass> selectDateRecord(Date sameDepDate) {
        return tripDA.selectDateRecord((java.sql.Date) sameDepDate);
    }*/
    public  ArrayList<String> selectBusIDbyDate(char bustype,Date sameDepDate)
    {
        return tripDA.selectBusIDbyDate(bustype,(java.sql.Date) sameDepDate);
    }
    public ArrayList<TripClass> getRecordbyDate()
    {
        return tripDA.getRecordbyDate();
    }
    public TripClass updateRecord(String tripno,double tPrice,char status,String busID)
    {
        return tripDA.updateRecord(tripno,tPrice,status,busID);
    }
     public TripClass inactiveRecord(String tripno,char status)
    {
        return tripDA.inactiveRecord(tripno,status);
    }
      public TripClass inactiveBackEndRecord(String tripno,char status)
    {
        return tripDA.inactiveBackEndRecord(tripno, status);
    }
     public void createRecord(String tripno,Date depDate,String depTime,int aSeat,int tSeat,
             double tPrice,char status,String routeID,String busID)
    {
        tripDA.createRecord(tripno,new java.sql.Date(depDate.getTime()),depTime,aSeat,tSeat,
             tPrice,status,routeID,busID);
    }
     public void updateSeat(String tripno,int aSeat)
    {
         tripDA.updateSeat(tripno, aSeat);
    }
     public void closeDB(){
        tripDA.shutDown();
    }
    
}