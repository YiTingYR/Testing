
//V1.00 24MAR16 1152AM
package da;
import java.sql.*;
import javax.swing.table.*;

public class PaymentTable extends AbstractTableModel{
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;
    private ResultSetMetaData metaData;
    private int numberOfRows;
    private String host = "jdbc:derby://localhost:1527/ezwaydb";
    private String user = "nbuser";
    private String password = "nbuser";
    
    public PaymentTable(String orderid){
        try{
            String query = "SELECT o.tripno, r.origin, r.destination, t.tripprice, o.seatno FROM OrderLine o, trip t, route r "
                    + "WHERE o.tripno=t.tripno AND t.routeid=r.routeid AND o.orderid=?";
            conn = DriverManager.getConnection(host, user, password);
            pstmt = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            pstmt.setString(1, orderid);
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
