// Name: Sum Weng Fai       Tutorial Group: 2DIA2
//this DA file will use the TripSeat2.java as the domain file.

//Sum
//V1.00 24MAR16 1152AM
package da;

import da.*;
import ui.*;
import domain.*;
import control.*;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.*;

public class OrderDA{
    //database variables
    private String host = "jdbc:derby://localhost:1527/ezwaydb";
    private String user = "nbuser";
    private String password = "nbuser";
    private String tableName = "ORDERTABLE";
    private Connection conn;
    private PreparedStatement ppst;
    private Statement stmt;
    
    public OrderDA(){
        createConnection();
    }
    
    
    
    public ArrayList<Ordertable> getOrderList(){
        
        ArrayList<Ordertable> orderList = new ArrayList<Ordertable>();
        
        try{
            
            ResultSet rs = null;
            String queryOne = "SELECT * FROM ORDERTABLE ORDER BY ORDERID";
            ppst = conn.prepareStatement(queryOne, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            //ppst.setString(1, tripSeat.getTripNo());
            rs = ppst.executeQuery();
            
            while(rs.next()){


                
                orderList.add(new Ordertable(rs.getString("ORDERID"),rs.getDate("ORDERDATE")));
                        
                    }
            
            
            
        }catch(SQLException ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                    
                    return orderList; //size is 0
                }
        
        return orderList;
        
    }
    
    public int saveOrder(Ordertable order, ArrayList<OrderLine> orderLineList){
        
        String queryStr = "";
        //ArrayList<OrderDetail> details = null;            
        int[] rowsAffectedArr;
        int totalRowsAffected = 0;

        try{          

            conn.setAutoCommit(false);
             stmt = conn.createStatement();

            queryStr = "INSERT INTO ORDERTABLE VALUES('"+
                    order.getOrderid()+ "','" +                      
                    new java.sql.Date(order.getOrderdate().getTime())+ "')"; 
            //test
            System.out.println(queryStr);
            
            stmt.addBatch(queryStr);

            //details = order.getOrderDetails();
            for(int i = 0; i < orderLineList.size(); i++){   
                queryStr = "INSERT INTO ORDERLINE VALUES('"+order.getOrderid() +"','"+ orderLineList.get(i).getTripNo()+"',"+"'"+ orderLineList.get(i).getSeatNo()+"'" +")";
            System.out.println(queryStr);
                stmt.addBatch(queryStr);
            }
            rowsAffectedArr = stmt.executeBatch();
            conn.commit();

            for(int i=0; i<rowsAffectedArr.length;i++){
                totalRowsAffected += rowsAffectedArr[i];
            }
         
        }
        catch (BatchUpdateException ex) {
			int[] updateCount = ex.getUpdateCounts();
			
			int count = 1;
			for (int i : updateCount) {
				if  (i == Statement.EXECUTE_FAILED) {
					System.out.println("Error on request " + count +": Execute failed");
				} else {
					System.out.println("Request " + count +": OK");
				}
				count++;
				
			}
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(),"DB ERROR",JOptionPane.ERROR_MESSAGE);
        }
        return totalRowsAffected;
        
    }
    
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