
//V1.00 24MAR16 1152AM
package da;
import domain.Bus;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
public class BusDA
{
private String host = "jdbc:derby://localhost:1527/ezwaydb";
    private String user = "nbuser";
    private String password = "nbuser";
    private String tableName = "Bus";
    private Connection conn;
    private PreparedStatement stmt;
    
    public BusDA() {
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
     public Bus getRecord(String busID) {
        String queryStr = "SELECT * FROM " + tableName + " WHERE busID = ?";
        Bus bus= null;
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, busID);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                 bus = new Bus(busID, rs.getString("plateno"), rs.getString("bustype"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return bus;
     }
     
     public Bus getPN(String plateNo) {
        String queryStr = "SELECT * FROM " + tableName + " WHERE plateno = ?";
        Bus bus= null;
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, plateNo);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                 bus = new Bus(plateNo, rs.getString("plateno"), rs.getString("bustype"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return bus;
     }
     
       public String displayAutomatedCode()
     {
         String lastRowCode="";
         String subString="";
         String newAutomatedCode="";
         
         try {
            ResultSet rs=selectRecord();
            rs.afterLast();
            while(rs.previous())
            {
                lastRowCode=rs.getString("BusID");
                break;
            }
            subString=lastRowCode.substring(2,lastRowCode.length());
            int convertedSubString=Integer.parseInt(subString)+1;
            newAutomatedCode="EZ"+String.format("%02d",convertedSubString);
          
             
            } catch (SQLException ex) {
                ex.getMessage();
                
            }
         return newAutomatedCode;
     }
       public ArrayList<Bus> getBusList(){
            String query = "SELECT * FROM BUS ORDER BY BUSID";
            ArrayList<Bus> busList = new ArrayList<Bus>();
            ResultSet rs = null;
            try{
                stmt = conn.prepareStatement(query);
               
                rs = stmt.executeQuery();
                while(rs.next()){
                    busList.add(new Bus(rs.getString("BUSID"), rs.getString("PLATENO"), rs.getString("BUSTYPE")));
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
            return busList;
        }
       
    public void createRecord(String busID,String plateno, String type)
    {
        try
            {
                PreparedStatement pstmtInsert=conn.prepareStatement("INSERT INTO BUS VALUES(?,?,?)");
                pstmtInsert.setString(1, busID);
                pstmtInsert.setString(2, plateno);
                pstmtInsert.setString(3, type);
                int result = pstmtInsert.executeUpdate();
                
                if (result>0)
                {
                    JOptionPane.showMessageDialog(null, busID + " is successfully created.");
                
                }
                    
                
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
       
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