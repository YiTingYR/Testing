
//V1.00 24MAR16 1152AM
package da;
import domain.Bus;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import domain.Driver;
import domain.DriverScheduleClass;
public class DriverScheduleDA
{
private String host = "jdbc:derby://localhost:1527/ezwaydb";
    private String user = "nbuser";
    private String password = "nbuser";
    private String tableName = "DriverSchedule";
    private Connection conn;
    private PreparedStatement stmt;
    
    public DriverScheduleDA() {
        createConnection();
    }
     public ResultSet selectRecord() {
        String queryStr = "SELECT * FROM " + tableName ;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement(queryStr, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            
            rs = stmt.executeQuery();
        } catch (SQLException ex) {
           
            ex.getMessage();
        }
        return rs;
    }
     public DriverScheduleClass getRecord(String id) {
        String queryStr = "SELECT * FROM " + tableName + " WHERE scheduleid = ?";
        DriverScheduleClass ds = null;
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                ds = new DriverScheduleClass(id, rs.getDate("scheduledate"), rs.getString("availability"),rs.getString("driverid"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return ds;
    }
     
     public void createRecord(String scheduleid, Date scheduledate, String availability, String driverid)
    {
        try
            {
                PreparedStatement pstmtInsert=conn.prepareStatement("INSERT INTO DRIVERSCHEDULE VALUES(?,?,?,?)");
                pstmtInsert.setString(1, scheduleid);
                pstmtInsert.setDate(2, scheduledate);
                pstmtInsert.setString(3, availability);
                pstmtInsert.setString(4, driverid);
                int result = pstmtInsert.executeUpdate();
                
                if (result>0)
                {
                    JOptionPane.showMessageDialog(null, scheduleid + " is successfully created.");
                
                }
                    
                
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
       
    }
      public DriverScheduleClass updateRecord(String scheduleid, String availability)
    {
        DriverScheduleClass ds = null;
        try{
               stmt= conn.prepareStatement("UPDATE DRIVERSCHEDULE SET availability=? WHERE scheduleid = ?");
               stmt.setString(1,availability);
               stmt.setString(2,scheduleid);  
             
                int result = stmt.executeUpdate();
                
                if(result > 0)
                    stmt.executeUpdate();
                
                JOptionPane.showMessageDialog(null, "Record with schedule ID "+scheduleid+" has been successfully updated.");
               
            }catch(SQLException ex){
               JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        return ds;
    }
      public DriverScheduleClass renewRecord(String scheduleid, String availability)
    {
        DriverScheduleClass ds = null;
        try{
               stmt= conn.prepareStatement("UPDATE DRIVERSCHEDULE SET availability=? WHERE scheduleid = ?");
               stmt.setString(1,availability);
               stmt.setString(2,scheduleid);  
             
                int result = stmt.executeUpdate();
                
                if(result > 0)
                    stmt.executeUpdate();
                
                //JOptionPane.showMessageDialog(null, "Record with schedule ID "+scheduleid+" has been successfully updated.");
               
            }catch(SQLException ex){
               JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        return ds;
    }
       public String displayAutomatedCode()
     {
         String lastRowCode="";
         String subString="";
         String newAutomatedCode="";
         int convertedSubString=0;
         
         try {
            ResultSet rs=selectRecord();
            rs.afterLast();
            while(rs.previous())
            {
                lastRowCode=rs.getString("ScheduleID");
                break;
            }
            subString=lastRowCode.substring(3,lastRowCode.length()-1);
            convertedSubString=Integer.parseInt(subString)+1;
            newAutomatedCode="SCH"+String.format("%04d",convertedSubString);
          
             
            } catch (SQLException ex) {
                ex.getMessage();
                
            }
         return newAutomatedCode;
     }
       public ArrayList<Driver> getDriverList(){
            String query = "SELECT * FROM DRIVER ORDER BY DRIVERID";
            ArrayList<Driver> driverList = new ArrayList<Driver>();
            ResultSet rs = null;
            try{
                stmt = conn.prepareStatement(query);
               
                rs = stmt.executeQuery();
                while(rs.next()){
                    driverList.add(new Driver(rs.getString("DRIVERID"), rs.getString("STAFFID")));
                }
            }catch(SQLException ex){
                showErrorDialog(ex.toString());
            }finally{
                if(rs !=null){
                    try{
                        rs.close();                        
                    }catch(SQLException ex){
                        showErrorDialog(ex.toString());
                    }
                }
            } 
            return driverList;
        }
       
       public ArrayList<DriverScheduleClass> getScheduleList(){
            String query = "SELECT * FROM DRIVERSCHEDULE";
            ArrayList<DriverScheduleClass> scheduleList = new ArrayList<DriverScheduleClass>();
            ResultSet rs = null;
            try{
                stmt = conn.prepareStatement(query);
               //stmt.setDate(1,date);
                rs = stmt.executeQuery();
                while(rs.next()){
                    scheduleList.add(new DriverScheduleClass(rs.getString("ScheduleID"), 
                            rs.getDate("ScheduleDate"),rs.getString("availability"),
                            rs.getString("driverid")));
                }
            }catch(SQLException ex){
                showErrorDialog(ex.toString());
            }finally{
                if(rs !=null){
                    try{
                        rs.close();                        
                    }catch(SQLException ex){
                        showErrorDialog(ex.toString());
                    }
                }
            } 
            return scheduleList;
        }
       
       public  ArrayList<String> selectDriverIDbyDate(Date sameDepDate) {
        String queryStr = "SELECT driverid FROM DriverSchedule WHERE scheduledate=?";
        ArrayList<String> driverOnSameDate = new ArrayList<String>();
       
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setDate(1, sameDepDate);
           
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                 driverOnSameDate.add(rs.getString("driverid"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return  driverOnSameDate;
    }
       
     private void createConnection() {
        try {
            conn = DriverManager.getConnection(host, user, password);
            System.out.println("***TRACE: Connection established.");
        } catch (SQLException ex) {
            ex.getMessage();
            
        }
        
    }

    private void showErrorDialog(String message){
                JOptionPane.showMessageDialog(null, message,"ERROR", JOptionPane.ERROR_MESSAGE);
        }
   
}