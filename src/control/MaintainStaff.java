//Sum
//V1.00 24MAR16 1152AM
package control;

import da.*;
import ui.*;
import domain.*;
import control.*;

public class MaintainStaff{
    
    private StaffDA staffDA;
    
    public MaintainStaff(){
        staffDA = new StaffDA();
    }
    
    public Staff getRecord(String staffID){
        return staffDA.getRecord(staffID);
    }
    
    public int addRecord(Staff staff){
        return staffDA.addRecord(staff);
    }
    
    public int updateRecord(Staff staff){
        return staffDA.updateRecord(staff);
    }
    
    public int deleteRecord(Staff staff){
        return staffDA.deleteRecord(staff);
    }
    
    public String getLatestID(){
        return staffDA.getLatestID();
    }
    
//    public int getNextNumber(){
//        return staffDA.getNextNumber();
//    }
    
    public void closeDB(){
        staffDA.shutDown();
    }
}