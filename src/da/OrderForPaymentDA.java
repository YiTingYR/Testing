
//V1.00 24MAR16 1152AM
package da;

import domain.Ordertable;
import java.sql.*;
import javax.swing.*;

public class OrderForPaymentDA {
    private String host = "jdbc:derby://localhost:1527/ezwaydb";
    private String user = "nbuser";
    private String password = "nbuser";
    private Connection conn;
    private PreparedStatement stmt;
    
    public OrderForPaymentDA() {
        createConnection();
    }
    
    public void deleteRecord(String orderid){
            
            try{
                    stmt = conn.prepareStatement("DELETE FROM ORDERTABLE WHERE orderid =?");
                    stmt.setString(1,orderid);
                    int result = stmt.executeUpdate();
                    
                    if(result > 0)
                        JOptionPane.showMessageDialog(null, orderid+" 's records has been deleted.");

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