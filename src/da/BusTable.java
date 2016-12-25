
//V1.00 24MAR16 1152AM
package da;
import java.sql.*;
import javax.swing.table.*;

public class BusTable extends AbstractTableModel{
    
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;
    private ResultSetMetaData metaData;
    private int numberOfRows;
    private String host = "jdbc:derby://localhost:1527/ezwaydb";
    private String user = "nbuser";
    private String password = "nbuser";
    
    public BusTable(){
        try{
            String query = "SELECT * FROM Bus order by BusID";
            conn = DriverManager.getConnection(host, user, password);
            pstmt = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        
            rs = pstmt.executeQuery();
            metaData = rs.getMetaData();
            rs.last();
            numberOfRows = rs.getRow();
            fireTableStructureChanged();
            
        }catch(SQLException ex){
            ex.printStackTrace();   
        }
    }
        
    public void retrieveRecordsByCode(String busID){
            try{
                String query = "SELECT * FROM Bus WHERE busid=? order by BusID";
                pstmt = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                pstmt.setString(1, busID);
                rs = pstmt.executeQuery();
                metaData = rs.getMetaData();
                rs.last();
                numberOfRows = rs.getRow();
                fireTableStructureChanged();
                
                
            }catch(SQLException ex){
                ex.printStackTrace();
            }        
        }
       
    public void retrieveRecordsByChar(String firstChar){
            try{
                String query = "SELECT * FROM Bus WHERE bustype=? order by BusID";
                pstmt = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                pstmt.setString(1, firstChar);
                rs = pstmt.executeQuery();
                metaData = rs.getMetaData();
                rs.last();
                numberOfRows = rs.getRow();
                fireTableStructureChanged();
                
                
            }catch(SQLException ex){
                ex.printStackTrace();
            }        
        }
    
    public void retrieveRecordsByNumber(String str){
            try{
                String query = "SELECT * FROM Bus WHERE plateno=? order by BusID";
                pstmt = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                pstmt.setString(1, str);
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
