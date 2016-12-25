//Sum
//V1.00 24MAR16 1152AM
// Name: Sum Weng Fai       Tutorial Group: 2DIA2
//this DA file will use the TripSeat2.java as the domain file.
package da;

import da.*;
import ui.*;
import domain.*;
import control.*;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.*;

public class TripSeatDA{
    //database variables
    private String host = "jdbc:derby://localhost:1527/ezwaydb";
    private String user = "nbuser";
    private String password = "nbuser";
    private String tableName = "TripSeat";
    private Connection conn;
    private PreparedStatement ppst;
    
    public TripSeatDA(){
        createConnection();
    }
    
//    public String getLatestID(){
//        String staffID = "";
//        try{
//            
//            ResultSet rs = null;
//            String queryOne = "SELECT * FROM STAFF ORDER BY STAFFID";
//            ppst = conn.prepareStatement(queryOne, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
//            rs = ppst.executeQuery();
//            
//            if(rs.next()){
//
//                rs.last();
//                staffID = rs.getString("STAFFID");
//                        
//                    }
//            
//        }catch(SQLException ex){
//                    JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
//                }
//        
//        
//        return staffID;
//    }
//    
//    public int addRecord(Staff staff){
//        
//        int result = -1;
//        
//        try{
//
//            PreparedStatement ppstInsert = conn.prepareStatement("INSERT INTO STAFF VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");
//            ppstInsert.setString(1, staff.getStaffid());
//            ppstInsert.setString(2, staff.getFirstname());
//            ppstInsert.setString(3, staff.getLastname());
//            ppstInsert.setString(4, String.valueOf(staff.getGender()));
//            ppstInsert.setString(5, staff.getStreetaddress1());
//            ppstInsert.setString(6, staff.getStreetaddress2());
//            ppstInsert.setString(7, staff.getCity());
//            ppstInsert.setString(8, staff.getState());
//            ppstInsert.setString(9, staff.getPosition());
//            ppstInsert.setDouble(10, staff.getSalary());
//            ppstInsert.setString(11, staff.getHomephoneno());
//            ppstInsert.setString(12, staff.getMobilephoneno());
//            ppstInsert.setString(13, staff.getEmailaddress());
//            
//            
//            result = ppstInsert.executeUpdate();
//
//            return result;
//            
//        }catch(SQLException ex){
//                    JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
//                    
//                    return result;
//        }
//    }
//    
//
//    public int updateRecord(Staff staff){
//        
//        int result = -1;
//        
//        try {
//            String updateStr = "UPDATE STAFF SET STREETADDRESS1 = ?, STREETADDRESS2 = ?, CITY = ?, STATE = ?, HOMEPHONENO = ?, MOBILEPHONENO = ?, EMAILADDRESS = ?, POSITION = ?, SALARY = ? WHERE STAFFID = ?";
//            ppst = conn.prepareStatement(updateStr);
////            ppst.setDate(1, staff.getDOB());
////            ppst.setString(2, staff.getNationality());
////            ppst.setString(3, staff.getBankAccNo());
//            ppst.setString(1, staff.getStreetaddress1());
//            ppst.setString(2, staff.getStreetaddress2());
////            ppst.setString(6, staff.getPostcode());
//            ppst.setString(3, staff.getCity());
//            ppst.setString(4, staff.getState());
////            ppst.setString(9, staff.getCountry());
//            ppst.setString(5, staff.getHomephoneno());
//            ppst.setString(6, staff.getMobilephoneno());
//            ppst.setString(7, staff.getEmailaddress());
//            ppst.setString(8, staff.getPosition());
////            ppst.setString(14, staff.getEmpStat());
//            ppst.setDouble(9, staff.getSalary());
////            ppst.setDouble(16, staff.getWages());
////            ppst.setDate(17, staff.getHireDate());
//            
//            ppst.setString(10, staff.getStaffid());
//            
//            result = ppst.executeUpdate();
//            
//            return result;
//            
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
//            return result;
//        }
//    }
//    
//    public int deleteRecord(Staff staffDel){
//        
//        int result = -1;
//        
//        try{
////            ResultSet rs = null;
////            String queryOne = "SELECT * FROM STAFF WHERE STAFFID = ?";
////            ppst = conn.prepareStatement(queryOne);
////            ppst.setString(1,staffID);
////            rs = ppst.executeQuery();
//            
//            String queryTwo = "DELETE FROM STAFF WHERE STAFFID = ?";
//            PreparedStatement ppstDelete = conn.prepareStatement(queryTwo);
//            ppstDelete.setString(1,staffDel.getStaffid());
//            result = ppstDelete.executeUpdate();
//            
//            return result;
//            
////            if(rs.next()){
////                //DELETE THE RECORD
////                
////                
////                int confirm = JOptionPane.showConfirmDialog(null,"Are you sure you want to delete the staff record?","Confirm?",JOptionPane.YES_NO_OPTION);
////                
////                if(confirm == JOptionPane.YES_OPTION)
////                {
////                    
////
////                    if(result > 0){
////                        JOptionPane.showMessageDialog(null,"Record for staff ID: "+ staffID + " has been deleted successfully.","Record Deleted",JOptionPane.INFORMATION_MESSAGE);
////                    }
////                    else{
////                        JOptionPane.showMessageDialog(null,"Result returned is not a positive integer, deletion has failed.","Error",JOptionPane.ERROR_MESSAGE);
////
////                    }
////                }
////            }
////            else{
////               //No action
////            }
//            }catch(SQLException ex){
//                JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
//                return result;
//            }
//    }
    
    public ArrayList<TripSeat2> getRecord(TripSeat2 tripSeat){
        
        ArrayList<TripSeat2> tripSeatList = new ArrayList<TripSeat2>();
        
        try{
            
            ResultSet rs = null;
            String queryOne = "SELECT * FROM TRIPSEAT WHERE TRIPNO = ?";
            ppst = conn.prepareStatement(queryOne, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ppst.setString(1, tripSeat.getTripNo());
            rs = ppst.executeQuery();
            
            while(rs.next()){

//                staffResult.setCity(rs.getString("CITY"));
//                staffResult.setEmailaddress(rs.getString("EMAILADDRESS"));
//                staffResult.setFirstname(rs.getString("FIRSTNAME"));
//                staffResult.setGender(rs.getString("GENDER").charAt(0));
//                staffResult.setHomephoneno(rs.getString("HOMEPHONENO"));
//                staffResult.setLastname(rs.getString("LASTNAME"));
//                staffResult.setMobilephoneno(rs.getString("MOBILEPHONENO"));
//                staffResult.setPosition(rs.getString("POSITION"));
//                staffResult.setSalary(rs.getDouble("SALARY"));
//                staffResult.setStaffid(rs.getString("STAFFID"));
//                staffResult.setState(rs.getString("STATE"));
//                staffResult.setStreetaddress1(rs.getString("STREETADDRESS1"));
//                staffResult.setStreetaddress2(rs.getString("STREETADDRESS2"));
//                
//                return staffResult;
                
                tripSeatList.add(new TripSeat2(rs.getString("TRIPNO"),rs.getString("SEATNO"),rs.getString("SEATAVAILABILITY").charAt(0)));
                        
                    }
            
            
            
        }catch(SQLException ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                    
                    return tripSeatList; //size is 0
                }
        
        return tripSeatList;
        
    }
    
//    public int getNextNumber(){
//        
//        int result = 0;
//        
//        try{
//            
//            ResultSet rs = null;
//            String queryOne = "SELECT * FROM STAFF";
//            ppst = conn.prepareStatement(queryOne, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
//            rs = ppst.executeQuery();
//            
//            if(rs.next()){
//
//                rs.last();
//                result = rs.getRow();
//                        
//                    }
//            
//            else{
//                //no record if reach here
//            }
//            
//        }catch(SQLException ex){
//                    JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
//                    
//                    
//                }
//        
//        
//        return result;
//    }
    
    private void createConnection(){
        try{
            conn = DriverManager.getConnection(host, user, password);
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(),"DB ERROR",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void shutDown(){
        if(ppst != null){
            try{
                ppst.close();
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null,ex.getMessage(),"DB ERROR",JOptionPane.ERROR_MESSAGE);
            }
        }
        if(conn != null){
            try{
                conn.close();
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null,ex.getMessage(),"DB ERROR",JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
}