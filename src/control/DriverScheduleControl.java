package control;

import domain.*;
import da.*;
import java.util.ArrayList;
import java.util.Date;

public class DriverScheduleControl{

    DriverScheduleDA dsDA;
    
    public DriverScheduleControl(){
        dsDA = new DriverScheduleDA();
    }
    
    public ArrayList<Driver> getDriverList(){
            return dsDA.getDriverList();
    }
    public ArrayList<DriverScheduleClass> getScheduleList(){
            return dsDA.getScheduleList();
    }
     public  ArrayList<String> selectDriverIDbyDate(Date sameDepDate)
    {
        return dsDA.selectDriverIDbyDate((java.sql.Date) sameDepDate);
    }
     
     public void createRecord(String scheduleid, Date scheduledate, String availability, String driverid){
        dsDA.createRecord(scheduleid,new java.sql.Date(scheduledate.getTime()),availability,driverid);
    }
     public DriverScheduleClass getRecord(String id) {
        return dsDA.getRecord(id);
    }
     public DriverScheduleClass updateRecord(String scheduleid, String availability)
    {
        return dsDA.updateRecord(scheduleid,availability);
    }
      public DriverScheduleClass renewRecord(String scheduleid, String availability)
    {
        return dsDA.renewRecord(scheduleid,availability);
    }
   
     
    /*public void shutDown(){
        orderDA.shutDown();
    }*/
}