//Sum
//V1.00 24MAR16 1152AM
// Name: Sum Weng Fai       Tutorial Group: 2DIA2
package da;

import da.*;
import ui.*;
import domain.*;
import control.*;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.*;

public class TicketChangeDA{
    //database variables
    private String host = "jdbc:derby://localhost:1527/ezwaydb";
    private String user = "nbuser";
    private String password = "nbuser";
    //private String tableName = "Trip";
    private Connection conn;
    private PreparedStatement ppst;
    private Statement stmt;
    
    public TicketChangeDA(){
        createConnection();
    }
    
    public TripSeat2 initiateTicketChange(TripSeat2 tripSeatIn){
        ResultSet rs = null;
        
        String queryOne = "SELECT t.TRIPNO, t.ROUTEID, ORIGIN, DESTINATION, DEPARTDATE, DEPARTTIME, ts.SEATNO FROM ORDERLINE ol, TRIP t, ROUTE r, TRIPSEAT ts  WHERE t.ROUTEID = r.ROUTEID AND t.TRIPNO = ts.TRIPNO AND ol.TRIPNO = ts.TRIPNO AND ol.SEATNO = ts.SEATNO AND ol.ORDERID = ? AND ol.TRIPNO = ? AND ol.SEATNO = ? ";
        //String queryTwo = "SELECT TRIPNO, t.ROUTEID, ORIGIN, DESTINATION, DEPARTDATE, DEPARTTIME FROM ORDERLINE ol, TRIP t, ROUTE r, TRIPSEAT ts  WHERE t.ROUTEID = r.ROUTEID AND t.TRIPNO = ts.TRIPNO AND ol.TRIPNO = ts.TRIPNO AND ol.SEATNO = ts.SEATNO AND ol.ORDERID = ? AND ol.TRIPNO = ? AND ol.SEATNO = ? ";
        ArrayList<TripSeat2> tripSeatList = new ArrayList<>();
        
        TripSeat2 tripSeatOut = new TripSeat2();
        
        try{
            
            
            ppst = conn.prepareStatement(queryOne, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ppst.setString(1, tripSeatIn.getOrderLine().getOrderID());
            ppst.setString(2, tripSeatIn.getTrip().getTripno());
            ppst.setString(3, tripSeatIn.getSeat().getSeatno());
            
            rs = ppst.executeQuery();
            
            if(rs.next()){
                //routeList.add(new Route(rs.getString("ROUTEID"),rs.getString("ORIGIN"),rs.getString("DESTINATION")));
                Route routeOut = new Route();
                routeOut.setRouteid(rs.getString("ROUTEID"));
                routeOut.setOrigin(rs.getString("ORIGIN"));
                routeOut.setDestination(rs.getString("DESTINATION"));
                
//                Bus busOut = new Bus();
//                busOut.setBusid(rs.getString("BUSID"));
//                busOut.setBustype(rs.getString("BUSTYPE").charAt(0));
//                busOut.setPlateno(rs.getString("PLATENO"));
                
                Seat seatOut = new Seat();
                seatOut.setSeatno(rs.getString("SEATNO"));
                
                Trip tripOut = new Trip();
                tripSeatOut.setTrip(tripOut);
                tripOut.setTripno(rs.getString("TRIPNO"));
//                tripOut.setBus(busOut);
                tripSeatOut.getTrip().setRoute(routeOut);
//                tripOut.setAvailableseat(rs.getInt("AVAILABLESEAT"));
                //tripSeatOut.getTrip().setTripprice(rs.getDouble("TRIPPRICE"));
                tripSeatOut.getTrip().setDeparttime(rs.getString("DEPARTTIME"));
                tripSeatOut.getTrip().setDepartdate(rs.getDate("DEPARTDATE"));
                tripSeatOut.setSeat(seatOut);
                
                //test
                System.out.println("INITIATE TICKET CHANGE result: "+tripSeatOut.getTrip().getTripno()+"\n"+tripSeatOut.getSeat().getSeatno());
                
                return tripSeatOut;
                
            }
            else{
                return null;
            }
            
        }catch(SQLException ex){
                    JOptionPane.showMessageDialog(null, "INITIATE TICKET CHANGE - "+ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                    return null;
                }
        
        
        
        
    }
    
    public ArrayList<TripSeat2> getSelectableTrips(TripSeat2 tripSeatIn){  //separated from initialeTicketChange because a method can only return one data type, can be hardcoded to have the result in 0, then other combo box options at 1 and above, but looks troublesome
        ResultSet rs = null;
        
        //String queryOne = "SELECT TRIPNO, t.ROUTEID, ORIGIN, DESTINATION, DEPARTDATE, DEPARTTIME FROM ORDERLINE ol, TRIP t, ROUTE r, TRIPSEAT ts  WHERE t.ROUTEID = r.ROUTEID AND t.TRIPNO = ts.TRIPNO AND ol.TRIPNO = ts.TRIPNO AND ol.SEATNO = ts.SEATNO AND ol.ORDERID = ?, ol.TRIPNO = ? AND ol.SEATNO = ? ";
        //String queryOne = "SELECT t.TRIPNO, t.ROUTEID, ORIGIN, DESTINATION, DEPARTDATE, DEPARTTIME, ts.SEATNO FROM TRIP t, ROUTE r, TRIPSEAT ts  WHERE t.ROUTEID = r.ROUTEID AND t.TRIPNO = ts.TRIPNO AND ORIGIN = ? AND DESTINATION = ? AND STATUS = 'Y' AND AVAILABLESEAT > 0 ORDER BY TRIPNO";
        String queryOne = "SELECT TRIPNO, t.ROUTEID, ORIGIN, DESTINATION, DEPARTDATE, DEPARTTIME FROM TRIP t, ROUTE r  WHERE t.ROUTEID = r.ROUTEID AND ORIGIN = ? AND DESTINATION = ? AND STATUS = 'Y' AND AVAILABLESEAT > 0 AND TRIPNO <> ? ORDER BY TRIPNO";
        ArrayList<TripSeat2> tripSeatList = new ArrayList<>();
        
        TripSeat2 tripSeatOut;
        
        try{
            
            
            ppst = conn.prepareStatement(queryOne, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
//            ppst.setString(1, tripSeatIn.getOrderLine().getOrderID());
//            ppst.setString(2, tripSeatIn.getTrip().getTripno());
//            ppst.setString(3, tripSeatIn.getSeat().getSeatno());
            ppst.setString(1, tripSeatIn.getTrip().getRoute().getOrigin());
            System.out.println("Para 1:"+tripSeatIn.getTrip().getRoute().getOrigin());
            ppst.setString(2, tripSeatIn.getTrip().getRoute().getDestination());
            System.out.println("Para 2:"+tripSeatIn.getTrip().getRoute().getDestination());
            // put in old trip no, to exclude it from option
            ppst.setString(3, tripSeatIn.getTrip().getTripno());
            System.out.println("Para 3:"+tripSeatIn.getTrip().getTripno());
            
            
            
            rs = ppst.executeQuery();
            
            while(rs.next()){
                //routeList.add(new Route(rs.getString("ROUTEID"),rs.getString("ORIGIN"),rs.getString("DESTINATION")));
                Route routeOut = new Route();
                routeOut.setRouteid(rs.getString("ROUTEID"));
                routeOut.setOrigin(rs.getString("ORIGIN"));
                routeOut.setDestination(rs.getString("DESTINATION"));
                
//                Bus busOut = new Bus();
//                busOut.setBusid(rs.getString("BUSID"));
//                busOut.setBustype(rs.getString("BUSTYPE").charAt(0));
//                busOut.setPlateno(rs.getString("PLATENO"));
                
//                Seat seatOut = new Seat();
//                seatOut.setSeatno(rs.getString("SEATNO"));
//                tripSeatOut.setSeat(seatOut);
                tripSeatOut = new TripSeat2();
                
                Trip tripOut = new Trip();
                //tripSeatOut.setTrip(tripOut);
                tripOut.setTripno(rs.getString("TRIPNO"));
                tripSeatOut.setTrip(tripOut);
//                tripOut.setBus(busOut);
                tripSeatOut.getTrip().setRoute(routeOut);
//                tripOut.setAvailableseat(rs.getInt("AVAILABLESEAT"));
                //tripSeatOut.getTrip().setTripprice(rs.getDouble("TRIPPRICE"));
                tripSeatOut.getTrip().setDeparttime(rs.getString("DEPARTTIME"));
                tripSeatOut.getTrip().setDepartdate(rs.getDate("DEPARTDATE"));
                //tripSeatOut.setSeat(seatOut);
                
                tripSeatList.add(tripSeatOut);
                
                //return tripSeatOut;
                
            }
            //test
            //System.out.println("Get Selectable Trips(number 0 in arraylist) result: "+"TripNo: "+tripSeatList.get(0).getTrip().getTripno()+"\n"+"\n"+"Size: "+tripSeatList.size());
            System.out.println("The size is: "+tripSeatList.size());
            for(int i = 0; i<tripSeatList.size(); i++){
                System.out.println(i+1+" . Trip No: "+tripSeatList.get(i).getTrip().getTripno());
            }
            return tripSeatList;
            
        }catch(SQLException ex){
                    JOptionPane.showMessageDialog(null, "GETSELECTABLETRIPS - "+ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                    return tripSeatList;
                }
        
        //return tripSeatList;
    }
    
    public ArrayList<TripSeat2> getSelectedTrip(TripSeat2 tripSeatIn){
        ResultSet rs = null;
        
        String queryOne = "SELECT ts.TRIPNO, ts.SEATNO, DEPARTDATE, DEPARTTIME FROM TRIP t, TRIPSEAT ts  WHERE t.TRIPNO = ts.TRIPNO AND ts.TRIPNO = ? AND SEATAVAILABILITY = 'Y' ORDER BY ts.SEATNO DESC"; //DESC so that the earlier seatno can be selected first in the UI end
        //String queryTwo = "SELECT TRIPNO, t.ROUTEID, ORIGIN, DESTINATION, DEPARTDATE, DEPARTTIME FROM ORDERLINE ol, TRIP t, ROUTE r, TRIPSEAT ts  WHERE t.ROUTEID = r.ROUTEID AND t.TRIPNO = ts.TRIPNO AND ol.TRIPNO = ts.TRIPNO AND ol.SEATNO = ts.SEATNO AND ol.ORDERID = ?, ol.TRIPNO = ? AND ol.SEATNO = ? ";
        ArrayList<TripSeat2> tripSeatList = new ArrayList<>();
        
        TripSeat2 tripSeatOut = new TripSeat2();
        
        try{
            
            
            ppst = conn.prepareStatement(queryOne, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            //ppst.setString(1, tripSeatIn.getOrderLine().getOrderID());
            ppst.setString(1, tripSeatIn.getTrip().getTripno());
            //ppst.setString(3, tripSeatIn.getSeat().getSeatno());
            
            rs = ppst.executeQuery();
            
            while(rs.next()){
                //routeList.add(new Route(rs.getString("ROUTEID"),rs.getString("ORIGIN"),rs.getString("DESTINATION")));
//                Route routeOut = new Route();
//                routeOut.setRouteid(rs.getString("ROUTEID"));
//                routeOut.setOrigin(rs.getString("ORIGIN"));
//                routeOut.setDestination(rs.getString("DESTINATION"));
                
//                Bus busOut = new Bus();
//                busOut.setBusid(rs.getString("BUSID"));
//                busOut.setBustype(rs.getString("BUSTYPE").charAt(0));
//                busOut.setPlateno(rs.getString("PLATENO"));
                
                Seat seatOut = new Seat();
                seatOut.setSeatno(rs.getString("SEATNO"));
                
                
                Trip tripIn = new Trip();
                
                tripSeatOut.setTrip(tripIn);
                tripSeatOut.getTrip().setTripno(rs.getString("TRIPNO"));
//                tripOut.setBus(busOut);
//                tripSeatOut.getTrip().setRoute(routeOut);
//                tripOut.setAvailableseat(rs.getInt("AVAILABLESEAT"));
//                tripSeatOut.getTrip().setTripprice(rs.getDouble("TRIPPRICE"));
                
                
                tripSeatOut.getTrip().setDeparttime(rs.getString("DEPARTTIME"));
                tripSeatOut.getTrip().setDepartdate(rs.getDate("DEPARTDATE"));
                tripSeatOut.setSeat(seatOut);
                
                //return tripSeatOut;
                
                tripSeatList.add(tripSeatOut);
                
            }
            return tripSeatList;
            
        }catch(SQLException ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                    return tripSeatList;
                }
        
        //return tripSeatOut;
    }
    
    public int saveTicketChanges(TripSeat2 tripSeatOld, TripSeat2 tripSeatNew){
        
        //int result = -1;
        
        int[] rowsAffectedArr;
        int totalRowsAffected = 0;
        
        try {
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            
            //update orderline NEW,NEW,OLD,OLD,OLD
            //String SQL1 = "UPDATE ORDERLINE SET TRIPNO = ?, SEATNO = ? WHERE ORDERID = ? AND TRIPNO = ? AND SEATNO = ?";
            String SQL1 = "UPDATE ORDERLINE SET TRIPNO = '" + tripSeatNew.getTrip().getTripno() +"'"+", SEATNO ='"+tripSeatNew.getSeat().getSeatno()+"' WHERE ORDERID = '"+tripSeatOld.getOrderLine().getOrderID()+"' AND TRIPNO = '"+tripSeatOld.getTrip().getTripno()+"' AND SEATNO = '"+tripSeatOld.getSeat().getSeatno()+"'";
            stmt.addBatch(SQL1);
            //occupy new seat
            //String SQL2 = "UPDATE TRIPSEAT SET SEATAVAILABILITY = 'N' WHERE TRIPNO = ? AND SEATNO = ?";
            String SQL2 = "UPDATE TRIPSEAT SET SEATAVAILABILITY = 'N' WHERE TRIPNO = '"+tripSeatNew.getTrip().getTripno()+"' AND SEATNO = '"+tripSeatNew.getSeat().getSeatno()+"'";
            stmt.addBatch(SQL2);
            String SQL3  = "UPDATE TRIP SET AVAILABLESEAT = AVAILABLESEAT-1 WHERE TRIPNO = '"+tripSeatNew.getTrip().getTripno()+"'";
            stmt.addBatch(SQL3);
            //release ori seat
            //String SQL3 = "UPDATE TRIPSEAT SET SEATAVAILABILITY = 'Y' WHERE TRIPNO = ? AND SEATNO = ?";
            String SQL4 = "UPDATE TRIPSEAT SET SEATAVAILABILITY = 'Y' WHERE TRIPNO = '"+tripSeatOld.getTrip().getTripno()+"' AND SEATNO = '"+tripSeatOld.getSeat().getSeatno()+"'";
            stmt.addBatch(SQL4);
            String SQL5 = "UPDATE TRIP SET AVAILABLESEAT = AVAILABLESEAT+1 WHERE TRIPNO = '"+tripSeatOld.getTrip().getTripno()+"'";
            stmt.addBatch(SQL5);
//            
//            
//            ppst = conn.prepareStatement(SQL1);
//            ppst.setString(1, tripSeatNew.getTrip().getTripno());
//            ppst.setString(2, tripSeatNew.getSeat().getSeatno());
//            ppst.setString(3, tripSeatOld.getOrderLine().getOrderID());
//            ppst.setString(4, tripSeatOld.getTrip().getTripno());
//            ppst.setString(5, tripSeatOld.getSeat().getSeatno());
//            result = ppst.executeUpdate();
            
            rowsAffectedArr = stmt.executeBatch();
                conn.commit();
                
                for(int i=0; i<rowsAffectedArr.length;i++){
                    totalRowsAffected += rowsAffectedArr[i];
                }
            
            return totalRowsAffected;
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return totalRowsAffected;
        }
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