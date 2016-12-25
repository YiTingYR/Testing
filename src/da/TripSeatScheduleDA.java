
//V1.00 24MAR16 1152AM
package da; //Author:Teh Yi Ting

import domain.*;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.*;

public class TripSeatScheduleDA {
    private String host = "jdbc:derby://localhost:1527/ezwaydb";
    private String user = "nbuser";
    private String password = "nbuser";
    private String tableName = "TripSeat";
    private Connection conn;
    private PreparedStatement stmt;
    ResultSet rs = null;
    public TripSeatScheduleDA() {
      
        createConnection(); 
    }
    public int createRecord(String tripno,String seatno, char seatAva)
    { 
        //int countSuccess=0;
        int result=0;
        try
            {
                PreparedStatement pstmtInsert=conn.prepareStatement("INSERT INTO TRIPSEAT VALUES(?,?,?)");
                pstmtInsert.setString(1,tripno);
                pstmtInsert.setString(2, seatno);
                pstmtInsert.setString(3, seatAva+"");
                result = pstmtInsert.executeUpdate();
               
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
    //return countSuccess;
        return result;
    
    }
    public int updateRecord(String tripno,String seatno,String seatava)
    {
        int counter=0;
        try{
               PreparedStatement pstmt= conn.prepareStatement("UPDATE TripSeat SET seatavailability=? WHERE tripno = ? AND seatno=? ");
               pstmt.setString(1,seatava);
               pstmt.setString(2,tripno);  
               pstmt.setString(3,seatno);
               
                int result = pstmt.executeUpdate();
                
                if(result > 0)
                { 
                    counter++;
                }
                
                pstmt.close();
            }catch(SQLException ex){
               JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }finally{
                if(rs !=null){
                    try{
                        rs.close();
                        //pstmt.close();
                    }catch(SQLException ex){
                        showErrorDialog(ex.toString());
                    }
                }
            } 
     return counter;
    }
     public int getTotalSeat(String tripno,String seatava)
     {
     int numberOfRows=0;
        try{
               stmt= conn.prepareStatement("SELECT COUNT(*) FROM TRIPSEAT WHERE tripno = ? AND seatavailability=? ");
               
               stmt.setString(1,tripno);  
               stmt.setString(2,seatava);
                ResultSet rs = null;
                rs = stmt.executeQuery();
                
                if (rs.next()) {
                    
                numberOfRows = rs.getInt(1);
                System.out.println("numberOfRows= " + numberOfRows);
                } else {
                System.out.println("error: could not get the record counts");
                }
                rs.close();
                stmt.close();
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
     return numberOfRows;
     
     
     }
      public Seat selectDeck(String seatno) {
        String queryStr = "SELECT * from SEAT WHERE seatno=?";
        Seat seat = null;
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, seatno);
            //stmt.setString(1, tripno);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                 seat = new Seat(seatno, rs.getString("Deck"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return seat;
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
    private void showErrorDialog(String message){
                JOptionPane.showMessageDialog(null, message,"ERROR", JOptionPane.ERROR_MESSAGE);
        }
    public static void main(String[] args) {
        TripSeatScheduleDA da=new TripSeatScheduleDA();
        da.getTotalSeat("KTKLD1203161100", "Y");
        //PastaDA da = new PastaDA();
        //PastaClass pasta = da.getRecord("PA001");
       // System.out.println(pasta);
    }
}