
//V1.00 24MAR16 1152AM
package da; //Author:Teh Yi Ting

import domain.TripClass;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.*;

public class TripDA2 {
    private String host = "jdbc:derby://localhost:1527/ezwaydb";
    private String user = "nbuser";
    private String password = "nbuser";
    private String tableName = "Trip";
    private Connection conn;
    private PreparedStatement stmt;
    ResultSet rs = null;
    public TripDA2() {
      
        createConnection(); 
    }
   
    public  ArrayList<String> selectBusIDbyDate(char bustype,Date sameDepDate) {
        String queryStr = "SELECT t.busid FROM trip t,bus b WHERE departdate=? AND t.busid=b.busid AND b.bustype=?";
        ArrayList<String> busIDOnSameDate = new ArrayList<String>();
        //ArrayList<TripClass> tripList = new ArrayList<TripClass>();
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setDate(1, sameDepDate);
            stmt.setString(2, bustype+"");
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                 busIDOnSameDate.add(rs.getString("busid"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return  busIDOnSameDate;
    }
    
    public TripClass getRecord(String tripno) {
        String queryStr = "SELECT * FROM " + tableName + " WHERE tripno =?";
        TripClass trip = null;
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, tripno);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                 trip= new TripClass(tripno, rs.getDate("departdate"),rs.getString("departtime"),rs.getInt("availableseat")
                 ,rs.getInt("totalseat"),rs.getDouble("tripprice"),rs.getString("status"),rs.getString("routeid"),rs.getString("busid"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return trip;
    }
    public ArrayList<TripClass> getRecordbyDate() {
        String queryStr = "SELECT * FROM " + tableName ;
        //TripClass trip = null;
        ArrayList<TripClass> tripList=new ArrayList<TripClass>();
        try {
            stmt = conn.prepareStatement(queryStr);
           
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()) {
                 tripList.add( new TripClass(rs.getString("tripno"), rs.getDate("departdate"),rs.getString("departtime"),rs.getInt("availableseat")
                 ,rs.getInt("totalseat"),rs.getDouble("tripprice"),rs.getString("status"),rs.getString("routeid"),rs.getString("busid")));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return tripList;
    }
    
    public void createRecord(String tripno,Date depDate,String depTime,int aSeat,int tSeat,
             double tPrice,char status,String routeID,String busID)
    {
            try
            {
                PreparedStatement pstmtInsert=conn.prepareStatement("INSERT INTO TRIP VALUES(?,?,?,?,?,?,?,?,?)");
                pstmtInsert.setString(1,tripno);
                pstmtInsert.setDate(2, depDate);
                pstmtInsert.setString(3,depTime);
                pstmtInsert.setInt(4, aSeat);
                pstmtInsert.setInt(5, tSeat);
                pstmtInsert.setDouble(6, tPrice);
                pstmtInsert.setString(7, status+"");
                pstmtInsert.setString(8, routeID);
                pstmtInsert.setString(9, busID);
                int result = pstmtInsert.executeUpdate();
                
                if (result>0)
                {
                    JOptionPane.showMessageDialog(null, "Trip number "+tripno+" 's record is successfully created.");
                }
                
                    
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
       
    }
    
    public TripClass updateRecord(String tripno,double tPrice,char status,String busID)
    {
        TripClass trip = null;
        try{
               stmt= conn.prepareStatement("UPDATE Trip SET tripprice=?, status=?, busID=? WHERE tripno = ?");
               stmt.setDouble(1,tPrice);
               stmt.setString(2,status+"");  
               stmt.setString(3,busID);
               stmt.setString(4,tripno);
                int result = stmt.executeUpdate();
                
                if(result > 0)
                    stmt.executeUpdate();
                
                JOptionPane.showMessageDialog(null, "Record with trip number "+tripno+" has been successfully updated.");
               
            }catch(SQLException ex){
               JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        return trip;
    }
     public TripClass inactiveBackEndRecord(String tripno,char status)
    {
        TripClass trip = null;
        try{
               stmt= conn.prepareStatement("UPDATE Trip SET status=? WHERE tripno = ?");
               
               stmt.setString(1,status+"");  
               
               stmt.setString(2,tripno);
                int result = stmt.executeUpdate();
                
                if(result > 0)
                    stmt.executeUpdate();
                
               // JOptionPane.showMessageDialog(null, "Record with trip number "+tripno+" is currently inactive.");
               
            }catch(SQLException ex){
               JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        return trip;
    }
   public TripClass inactiveRecord(String tripno,char status)
    {
        TripClass trip = null;
        try{
               stmt= conn.prepareStatement("UPDATE Trip SET status=? WHERE tripno = ?");
               
               stmt.setString(1,status+"");  
               
               stmt.setString(2,tripno);
                int result = stmt.executeUpdate();
                
                if(result > 0)
                    stmt.executeUpdate();
                
                JOptionPane.showMessageDialog(null, "Record with trip number "+tripno+" is currently inactive.");
               
            }catch(SQLException ex){
               JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        return trip;
    }
   
   public TripClass updateSeat(String tripno,int aSeat)
    {
        TripClass trip = null;
        try{
               stmt= conn.prepareStatement("UPDATE Trip SET availableseat=? WHERE tripno = ?");
               stmt.setInt(1, aSeat);
               stmt.setString(2,tripno);
                int result = stmt.executeUpdate();
                
                if(result > 0)
                    stmt.executeUpdate();
                
                stmt.close();
                //JOptionPane.showMessageDialog(null, "Record with trip number "+tripno+" has been successfully updated.");
               
            }catch(SQLException ex){
               JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }finally{
                if(rs !=null){
                    try{
                        rs.close();
                        stmt.close();
                    }catch(SQLException ex){
                        showErrorDialog(ex.toString());
                    }
                }
            } 
        return trip;
    }
   
    private void createConnection() {
        try {
            conn = DriverManager.getConnection(host, user, password);
            System.out.println("***TRACE: Connection established.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void shutDown() {
        if (conn != null)
            try {
            conn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void main(String[] args) {
        //PastaDA da = new PastaDA();
        //PastaClass pasta = da.getRecord("PA001");
       // System.out.println(pasta);
    }
    private void showErrorDialog(String message){
                JOptionPane.showMessageDialog(null, message,"ERROR", JOptionPane.ERROR_MESSAGE);
        }
}
