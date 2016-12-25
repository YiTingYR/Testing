
//V1.00 24MAR16 1152AM
package da; //Author:Teh Yi Ting

import domain.Route;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.*;

public class RouteDA {
    private String host = "jdbc:derby://localhost:1527/ezwaydb";
    private String user = "nbuser";
    private String password = "nbuser";
    private String tableName = "Route";
    private Connection conn;
    private PreparedStatement stmt;
    //private PastaTable tableModel;
    
    public RouteDA() {
       // tableModel = new PastaTable();
        createConnection(); 
    }
    public ArrayList<Route> getRouteList(){
            String query = "SELECT * FROM Route order by routeid";
            ArrayList<Route> routeList = new ArrayList<Route>();
            ResultSet rs = null;
            try{
                stmt = conn.prepareStatement(query);
               
                rs = stmt.executeQuery();
                while(rs.next()){
                    routeList.add(new Route(rs.getString("routeid"), rs.getString("origin"), rs.getString("destination")));
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
            System.out.println(routeList);
            return routeList;
        }
   
    
    public Route getRecord(String routeID) {
        String queryStr = "SELECT * FROM " + tableName + " WHERE UPPER(routeID) = ?";
        Route route = null;
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, routeID);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                 route = new Route(routeID, rs.getString("Origin"), rs.getString("Destination"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return route;
    }
    
    
    public void createRecord(String jtfRouteID,String jtfOrigin,String jtfDestination,String jtfRouteID2
    ,String jtfOrigin2, String jtfDestination2)
    {
            try
            {
                PreparedStatement pstmtInsert=conn.prepareStatement("INSERT INTO ROUTE VALUES(?,?,?)");
                pstmtInsert.setString(1, jtfRouteID);
                pstmtInsert.setString(2, jtfOrigin);
                pstmtInsert.setString(3, jtfDestination);
                int result = pstmtInsert.executeUpdate();
                
                if(result > 0) //first record success
                {
                pstmtInsert.setString(1, jtfRouteID2);
                pstmtInsert.setString(2, jtfOrigin2);
                pstmtInsert.setString(3, jtfDestination2);
                
                result = pstmtInsert.executeUpdate();
                if (result>0) //second record success
                {
                    JOptionPane.showMessageDialog(null, "Two routes are successfully added.");
                }
                }
                    
                
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
       
    }
    /*
    public PastaClass updateRecord(String pastaCode,String pastaName,double pastaPrice)
    {
        PastaClass pasta = null;
        try{
               stmt= conn.prepareStatement("UPDATE Pasta SET pastaName=? , pastaPrice=? WHERE pastaCode = ?");
               stmt.setString(1,pastaName);
                stmt.setDouble(2, pastaPrice);  
                stmt.setString(3, pastaCode);            
                int result = stmt.executeUpdate();
                
                if(result > 0)
                    stmt.executeUpdate();
                
                JOptionPane.showMessageDialog(null, "Record updated.");
                //new Pasta().refreshTable();

            }catch(SQLException ex){
               JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        return pasta;
    }
    
    */
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
        RouteDA da=new RouteDA();
        da.getRouteList();
        //PastaDA da = new PastaDA();
        //PastaClass pasta = da.getRecord("PA001");
       // System.out.println(pasta);
    }
    private void showErrorDialog(String message){
                JOptionPane.showMessageDialog(null, message,"ERROR", JOptionPane.ERROR_MESSAGE);
        }
}
