//Sum
//V1.00 24MAR16 1152AM
package da;

import java.sql.*;
import javax.swing.table.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import da.*;
//import ui.*;
import domain.*;
//import control.*;
import java.text.SimpleDateFormat;

public class TripTableModel extends AbstractTableModel{
    
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;
    private ResultSetMetaData metaData;
    private int numberOfRows;
    private String host = "jdbc:derby://localhost:1527/ezwaydb";
    private String user = "nbuser";
    private String password = "nbuser";
    private SimpleDateFormat dateFormat;
    
    ArrayList<Trip> tripList = new ArrayList<Trip>();
    String[] columnHeaders = {"Trip No","Origin","Destination","Dep Date", "Dep Time", "Bus Type", "Plate No", "Price", "Available Seats"};
    Class[] columnTypes = {String.class, String.class, String.class, String.class, String.class, Character.class, String.class, Double.class, Integer.class};
    
    public TripTableModel(){
        
        dateFormat = new SimpleDateFormat("dd-MM-yyyy");
//        try{
//            String query = "SELECT TRIPNO as \"Trip No\", ORIGIN as \"Origin\", DESTINATION as \"Destination\", DEPARTDATE as \"Dep Date\", DEPARTTIME as \"Dep Time\", BUSTYPE as \"Bus Type\", PLATENO as \"Plate No\", TRIPPRICE as \"Price\", AVAILABLESEAT as \"Available Seats\" FROM TRIP t, ROUTE r, BUS b where t.BUSID = b.BUSID AND t.ROUTEID = r.ROUTEID AND STATUS = 'Y'";
//            conn = DriverManager.getConnection(host, user, password);
//            pstmt = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
//        
//            rs = pstmt.executeQuery();
//            metaData = rs.getMetaData();
//            rs.last();
//            numberOfRows = rs.getRow();
//            fireTableStructureChanged();
//            
//        }catch(SQLException ex){
//            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);   
//        }
    }
    
    
    
    
    public void addRow(Trip t){
//        boolean isNewFood = true; 
//        String newFoodId = d.getFood().getFoodId();
//        OrderDetail newOrderDetail = null;
//        for(int i=0; i<orderList.size(); i++){
//            if(newFoodId.equals(orderList.get(i).getFood().getFoodId())){
//                newOrderDetail = new OrderDetail(d.getFood(), d.getQuantity() + orderList.get(i).getQuantity());
//                orderList.set(i, newOrderDetail);
//                isNewFood = false;
//                break;
//            }
//        }
//        
//        if(isNewFood)
            tripList.add(t);    
        
        fireTableDataChanged();
    }
    
    public void removeRow(int rowIndex){
        tripList.remove(rowIndex);
        fireTableDataChanged();
    }
    
    public ArrayList<Trip> getTripDetails(){
        return tripList;
    }
    
    public void clearTable(){
        tripList.clear();
        fireTableDataChanged();
    }
        
    @Override
    public int getColumnCount(){
        return columnHeaders.length;
    }
    
    
    @Override 
    public String getColumnName(int columnIndex){
        return columnHeaders[columnIndex];
    }
    
    
    @Override
    public Class getColumnClass(int columnIndex){
        return columnTypes[columnIndex];
    }
    
    public int getRowCount(){
        return tripList.size();
    }   
    
    @Override
    public Object getValueAt(int rowIndex, int ColumnIndex){
        Object obj = null;
        Trip trip =  tripList.get(rowIndex);
        
        switch(ColumnIndex){
            case 0:
                //System.out.println(trip.getTripno());
                obj = trip.getTripno(); break;                
            case 1: 
                //System.out.println(trip.getRoute().getOrigin());
                obj = trip.getRoute().getOrigin(); break;
            case 2: 
                obj = trip.getRoute().getDestination(); break;
            case 3: 
                obj = dateFormat.format(trip.getDepartdate()); break;         
            case 4:
                obj = trip.getDeparttime(); break;
            case 5:
                obj = trip.getBus().getBustype(); break;
            case 6:
                obj = trip.getBus().getPlateno(); break;
            case 7:
                obj = trip.getTripprice(); break;
            case 8:
                obj = trip.getAvailableseat(); break;
        }
        return obj;        
    }
    
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex){
        if(columnIndex == 3)
            return true;
        else
            return false;
    }
    
    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex){
            
//        if(columnIndex == 3){
//           orderList.get(rowIndex).setQuantity(Integer.parseInt(value.toString()));      
//            fireTableCellUpdated(rowIndex, columnIndex);
//        }
        
    }

       
    
    
//        @Override
//        public String getColumnName(int column){
//            try{
//                return metaData.getColumnName(column+1);
//                
//            }catch(SQLException ex){
//                JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
//            }        
//            return "This is a table model.";
//        }
//    
//        @Override
//        public int getRowCount(){
//            return numberOfRows;
//        
//        }
//   
//        @Override
//        public int getColumnCount(){
//            try{
//                return metaData.getColumnCount();
//            }catch(SQLException ex){
//                JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
//            }
//            return 0;
//        }
//        
//        @Override
//        public Object getValueAt(int rowIndex, int columnIndex){
//            try{
//                rs.absolute(rowIndex+1);
//                return rs.getObject(columnIndex+1);
//            }catch(SQLException ex){
//                JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
//            }
//            return "This is a table model.";
//        }
//        
//        public void shutDown(){
//        if(pstmt != null){
//            try{
//                pstmt.close();
//            }catch(SQLException ex){
//                JOptionPane.showMessageDialog(null,ex.getMessage(),"DB ERROR",JOptionPane.ERROR_MESSAGE);
//            }
//        }
//        if(conn != null){
//            try{
//                conn.close();
//            }catch(SQLException ex){
//                JOptionPane.showMessageDialog(null,ex.getMessage(),"DB ERROR",JOptionPane.ERROR_MESSAGE);
//            }
//        }
//    }
        
}
