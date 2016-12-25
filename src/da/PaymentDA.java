
//V1.00 24MAR16 1152AM
package da;
import domain.OrderDetailForPayment;
import domain.PaymentClass;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;
public class PaymentDA
{
private String host = "jdbc:derby://localhost:1527/ezwaydb";
    private String user = "nbuser";
    private String password = "nbuser";
    private String tableName = "OrderTable";
    private Connection conn;
    private PreparedStatement stmt;
    
    public PaymentDA() {
        createConnection();
    }
     public ResultSet selectRecord() {
        String queryStr = "SELECT * FROM " + tableName +" order by orderid";
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement(queryStr, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            
            rs = stmt.executeQuery();
        } catch (SQLException ex) {
           
            ex.getMessage();
        }
        return rs;
    }
       public String displayLastOrderID()
     {
         String lastRowCode="";
         String lastRowOrderID="";
         //String newAutomatedCode="";
         
         try {
            ResultSet rs=selectRecord();
            rs.afterLast();
            while(rs.previous())
            {
                lastRowCode=rs.getString("OrderID");
                break;
            }
          
            lastRowOrderID=lastRowCode;
            } catch (SQLException ex) {
                ex.getMessage();
                
            }
         return lastRowOrderID;
        
     }
       public ArrayList<OrderDetailForPayment> getOrderListById(String orderid){
            String query = "SELECT * FROM OrderLine WHERE orderid=?";
            ArrayList<OrderDetailForPayment> orderList = new ArrayList<OrderDetailForPayment>();
            ResultSet rs = null;
            try{
               stmt = conn.prepareStatement(query);
               stmt.setString(1, orderid);
                rs = stmt.executeQuery();
                while(rs.next()){
                    orderList.add(new OrderDetailForPayment(rs.getString("orderid"), rs.getString("tripno"), rs.getString("seatno")));
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
            return orderList;
        }
       
       public PaymentClass getPaymentList(String paymentid){
            String query = "SELECT * FROM Payment WHERE paymentid=?";
            //ArrayList<PaymentClass> payList = new ArrayList<PaymentClass>();
            PaymentClass payment = null;
            try{
                stmt = conn.prepareStatement(query);
               stmt.setString(1, paymentid);
               ResultSet rs = stmt.executeQuery();
                if(rs.next()){
                    payment=new PaymentClass(rs.getString("paymentid"), rs.getDouble("totalamt"), 
                            rs.getString("paymethod"),rs.getString("creditcardno"),rs.getDouble("paidamt"),
                            rs.getDouble("cashchange"),rs.getString("orderid"));
                }
            }catch(SQLException ex){
                showErrorDialog(ex.toString());
            }
             
            return payment;
        }
     
        public void createRecord(String paymentID,double totalAmount,String payMethod,
                String creditCardNo,double paidAmt,double cashChange,String orderid){
        try
            {
                PreparedStatement pstmtInsert=conn.prepareStatement("INSERT INTO PAYMENT VALUES(?,?,?,?,?,?,?)");
                pstmtInsert.setString(1, paymentID);
                pstmtInsert.setDouble(2, totalAmount);
                pstmtInsert.setString(3, payMethod);
                pstmtInsert.setString(4, creditCardNo);
                pstmtInsert.setDouble(5, paidAmt);
                pstmtInsert.setDouble(6,cashChange);
                pstmtInsert.setString(7,orderid);
                int result = pstmtInsert.executeUpdate();
                
                if (result>0)
                {
                    JOptionPane.showMessageDialog(null, "Your payment is successfully processed");
                
                }
                    
                
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
       
    }
          public String getPaymentId(String orderid){
            String query = "SELECT * FROM Payment WHERE orderid=?";
            //ArrayList<PaymentClass> payList = new ArrayList<PaymentClass>();
            PaymentClass payment = null;
            String paymentid="";
            try{
                stmt = conn.prepareStatement(query);
               stmt.setString(1, orderid);
               ResultSet rs = stmt.executeQuery();
                if(rs.next()){
                    /*payment=new PaymentClass(rs.getString("paymentid"), rs.getDouble("totalamt"), 
                            rs.getString("paymethod"),rs.getString("creditcardno"),rs.getDouble("paidamt"),
                            rs.getDouble("cashchange"),rs.getString("orderid"));*/
                    paymentid=rs.getString("paymentid");
                }
            }catch(SQLException ex){
                showErrorDialog(ex.toString());
            }
             
            return paymentid;
        }
   
     private void createConnection() {
        try {
            conn = DriverManager.getConnection(host, user, password);
            System.out.println("***TRACE: Connection established.");
        } catch (SQLException ex) {
            ex.getMessage();
            
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
   
}