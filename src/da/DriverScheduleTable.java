
//V1.00 24MAR16 1152AM
package da;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.table.*;

public class DriverScheduleTable extends AbstractTableModel{
    
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;
    private ResultSetMetaData metaData;
    private int numberOfRows;
    private String host = "jdbc:derby://localhost:1527/ezwaydb";
    private String user = "nbuser";
    private String password = "nbuser";
    
    public DriverScheduleTable(){
        
        try{
            String query = "SELECT d.*, s.firstname, s.lastname,s.mobilephoneno"
                    + " FROM DriverSchedule d, STAFF s,Driver r WHERE s.staffid=r.staffid AND "
                    + "r.driverid=d.driverid AND d.SCHEDULEDATE=? order by d.ScheduleID";
            conn = DriverManager.getConnection(host, user, password);
            pstmt = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            
             java.util.Date utilDate = new java.util.Date();
             java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            
            pstmt.setDate(1, sqlDate);
           
            rs = pstmt.executeQuery();
            metaData = rs.getMetaData();
            rs.last();
            numberOfRows = rs.getRow();
            fireTableStructureChanged();
            
        }catch(SQLException ex){
            ex.printStackTrace();   
        }
    }
        
    public void retrieveRecordsByCode(String driverID){
            try{
                String query = "SELECT d.*, s.firstname, s.lastname,s.mobilephoneno"
                    + " FROM DriverSchedule d, STAFF s,Driver r WHERE s.staffid=r.staffid AND "
                    + "r.driverid=d.driverid AND d.driverid=? order by d.ScheduleID";
                pstmt = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                pstmt.setString(1, driverID);
                rs = pstmt.executeQuery();
                metaData = rs.getMetaData();
                rs.last();
                numberOfRows = rs.getRow();
                fireTableStructureChanged();
                
                
            }catch(SQLException ex){
                ex.printStackTrace();
            }        
        }
       
    
        @Override
        public String getColumnName(int column){
            try{
                return metaData.getColumnName(column+1);
                
            }catch(SQLException ex){
                ex.printStackTrace();
            }        
            return "";
        }
    
        @Override
        public int getRowCount(){
            return numberOfRows;
        
        }
   
        @Override
        public int getColumnCount(){
            try{
                return metaData.getColumnCount();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
            return 0;
        }
        
        @Override
        public Object getValueAt(int rowIndex, int columnIndex){
            try{
                rs.absolute(rowIndex+1);
                return rs.getObject(columnIndex+1);
            }catch(SQLException ex){
            
            }
            return "";
        }
    
}
