package control;
import domain.*;
import da.*;
import java.util.ArrayList;
import java.util.Date;


public class TripSeatControlForSchedule{

    TripSeatScheduleDA tripSeatDA;
    
    public TripSeatControlForSchedule(){
        tripSeatDA = new TripSeatScheduleDA();
    }
    
     public int createRecord(String tripno,String seatno, char seatAva)
    {
        return tripSeatDA.createRecord(tripno,seatno,seatAva);
    }
     public int updateRecord(String tripno,String seatno,String seatava)
    {
        return tripSeatDA.updateRecord(tripno, seatno, seatava);
    }
     public int getTotalSeat(String tripno,String seatava)
     {
         return tripSeatDA.getTotalSeat(tripno, seatava);
     }
     public Seat selectDeck(String seatno) {
     
     return tripSeatDA.selectDeck(seatno);
     }
     
    public void closeDB(){
        tripSeatDA.shutDown();
    }
}