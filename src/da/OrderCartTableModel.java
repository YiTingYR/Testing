//Sum
//V1.00 24MAR16 1152AM

package da;

import domain.*;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class OrderCartTableModel extends AbstractTableModel{
    
    
    ArrayList<OrderLine> orderLineList = new ArrayList<OrderLine>();
    ArrayList<Trip> tripList = new ArrayList<>();
    //String[] columnHeaders = {"Food ID","Name","Price","Quantity"};
    String[] columnHeaders = {"Trip No","Origin","Destination", "Price", "Seat No"};
    Class[] columnTypes = {String.class, String.class, String.class, Double.class, String.class};

     public OrderCartTableModel(){
         
         
    }
     
     public void addRow(OrderLine orderLine, Trip trip){
         
         
         orderLineList.add(orderLine);
         tripList.add(trip);
        
        fireTableDataChanged();
    }
    
    public void removeRow(int rowIndex){
        orderLineList.remove(rowIndex);
        fireTableDataChanged();
    }
    
    public ArrayList<OrderLine> getOrderLineDetails(){
        return orderLineList;
    }
    
    public void clearTable(){
        orderLineList.clear();
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
        return orderLineList.size();
        
        //temporary
        //return 1;
    }   
    
    @Override
    public Object getValueAt(int rowIndex, int ColumnIndex){
        Object obj = null;
        OrderLine orderLine =  orderLineList.get(rowIndex);
        Trip trip = tripList.get(rowIndex);
        
        switch(ColumnIndex){
            case 0: //TRIPNO
                obj = orderLine.getTripNo();break;
    
            case 1: //ORIGIN
                obj = trip.getRoute().getOrigin(); break;
            case 2: //DESTINATION
                obj = trip.getRoute().getDestination(); break;
            case 3: //PRICE
                obj = trip.getTripprice(); break;         
            case 4: //SEATNO
                obj = orderLine.getSeatNo();break;
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
    public void setValueAt(Object value, int rowIndex, int 
columnIndex){
            
//        if(columnIndex == 3){
//           orderList.get(rowIndex).setQuantity(Integer.parseInt
//(value.toString()));      
//            fireTableCellUpdated(rowIndex, columnIndex);
//        }
        
    }


}