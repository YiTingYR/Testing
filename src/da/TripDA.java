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

public class TripDA{
    //database variables
    private String host = "jdbc:derby://localhost:1527/ezwaydb";
    private String user = "nbuser";
    private String password = "nbuser";
    private String tableName = "Trip";
    private Connection conn;
    private PreparedStatement ppst;
    
    public TripDA(){
        createConnection();
    }
    
    public Trip getTripByTripNo(Trip trip){ //for used with orderLine Side by Side reference of origin, destination and price.
        ResultSet rs = null;
        //Trip tripResult = new Trip();
        Trip tripOut = new Trip();
        String queryOne = "SELECT TRIPNO, t.ROUTEID, ORIGIN, DESTINATION, DEPARTDATE, DEPARTTIME, t.BUSID, BUSTYPE, PLATENO, TRIPPRICE, AVAILABLESEAT FROM TRIP t, ROUTE r, BUS b WHERE t.ROUTEID = r.ROUTEID AND t.BUSID = b.BUSID AND TRIPNO = ?"; //DO NOT PUT ORDER BY
        //ArrayList<Route> routeList = new ArrayList<>();
        
        try{
            
            
            ppst = conn.prepareStatement(queryOne, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ppst.setString(1, trip.getTripno());
            rs = ppst.executeQuery();
            
            if(rs.next()){
                //routeList.add(new Route(rs.getString("ROUTEID"),rs.getString("ORIGIN"),rs.getString("DESTINATION")));
                Route routeOut = new Route();
                routeOut.setRouteid(rs.getString("ROUTEID"));
                routeOut.setOrigin(rs.getString("ORIGIN"));
                routeOut.setDestination(rs.getString("DESTINATION"));
                
                Bus busOut = new Bus();
                busOut.setBusid(rs.getString("BUSID"));
                busOut.setBustype(rs.getString("BUSTYPE").charAt(0));
                busOut.setPlateno(rs.getString("PLATENO"));
                
                
                tripOut.setTripno(rs.getString("TRIPNO"));
                tripOut.setBus(busOut);
                tripOut.setRoute(routeOut);
                tripOut.setAvailableseat(rs.getInt("AVAILABLESEAT"));
                tripOut.setTripprice(rs.getDouble("TRIPPRICE"));
                tripOut.setDeparttime(rs.getString("DEPARTTIME"));
                tripOut.setDepartdate(rs.getDate("DEPARTDATE"));
                
                return tripOut;
                
            }
            
        }catch(SQLException ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                    return tripOut;
                }
        
        return tripOut;
    }
    
    public ArrayList<Route> getAvailableRouteInfo(){
        ResultSet rs = null;
        String queryOne = "SELECT r.RouteID, Origin, Destination FROM ROUTE r, Trip t WHERE r.ROUTEID = t.ROUTEID AND STATUS = 'Y'";
        ArrayList<Route> routeList = new ArrayList<>();
        
        try{
            
            
            ppst = conn.prepareStatement(queryOne, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            //ppst.setString(1, "Y");
            rs = ppst.executeQuery();
            
            while(rs.next()){
                routeList.add(new Route(rs.getString("ROUTEID"),rs.getString("ORIGIN"),rs.getString("DESTINATION")));
                
            }
            
        }catch(SQLException ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                }
        
        return routeList;
    }
    
    public ArrayList<Trip> getActiveTrips(){
        
        ResultSet rs = null;
        //String queryOne = "SELECT * FROM TRIP WHERE STATUS = ? ORDER BY TRIPNO";
        String queryOne = "SELECT TRIPNO, t.ROUTEID, ORIGIN, DESTINATION, DEPARTDATE, DEPARTTIME, t.BUSID, BUSTYPE, PLATENO, TRIPPRICE, AVAILABLESEAT FROM TRIP t, ROUTE r, BUS b WHERE t.ROUTEID = r.ROUTEID AND t.BUSID = b.BUSID AND STATUS = 'Y' ORDER BY TRIPNO";
        ArrayList<Trip> tripList = new ArrayList<>();
        
        try{
            
            
            ppst = conn.prepareStatement(queryOne, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            //ppst.setString(1, "Y");
            rs = ppst.executeQuery();
            
            while(rs.next()){
                //tripList.add(new Trip(rs.getString("TRIPNO"),rs.getDate("DEPARTDATE"),rs.getString("DEPARTTIME"),rs.getInt("AVAILABLESEAT"),rs.getInt("TOTALSEAT"),rs.getDouble("TRIPPRICE"),(rs.getString("STATUS")).charAt(0),rs.getString("ROUTEID"),rs.getString("BUSID")));
                Route routeOut = new Route();
                routeOut.setRouteid(rs.getString("ROUTEID"));
                routeOut.setOrigin(rs.getString("ORIGIN"));
                routeOut.setDestination(rs.getString("DESTINATION"));
                
                Bus busOut = new Bus();
                busOut.setBusid(rs.getString("BUSID"));
                busOut.setBustype(rs.getString("BUSTYPE").charAt(0));
                busOut.setPlateno(rs.getString("PLATENO"));
                
                Trip tripOut = new Trip();
                tripOut.setTripno(rs.getString("TRIPNO"));
                tripOut.setBus(busOut);
                tripOut.setRoute(routeOut);
                tripOut.setAvailableseat(rs.getInt("AVAILABLESEAT"));
                tripOut.setTripprice(rs.getDouble("TRIPPRICE"));
                tripOut.setDeparttime(rs.getString("DEPARTTIME"));
                tripOut.setDepartdate(rs.getDate("DEPARTDATE"));
                
                tripList.add(tripOut);
                
                ppst.clearBatch();
                
            }
            
        }catch(SQLException ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                }
        
//        System.out.println("In DA, size is: "+ tripList.size());
        
        return tripList;
    }
    
    public ArrayList<Trip> searchTrip(Trip tripIn, Route routeIn){
        
        ResultSet rs = null;
        String queryOne = "SELECT TRIPNO, t.ROUTEID, ORIGIN, DESTINATION, DEPARTDATE, DEPARTTIME, t.BUSID, BUSTYPE, PLATENO, TRIPPRICE, AVAILABLESEAT FROM TRIP t, ROUTE r, BUS b WHERE t.ROUTEID = r.ROUTEID AND t.BUSID = b.BUSID AND STATUS = 'Y'";
        String queryEnd = " ORDER BY TRIPNO";
        String queryOrigin = " AND ORIGIN = ?";
        String queryDestination = " AND DESTINATION = ?";
        String queryDepDate = " AND DEPARTDATE = ?";
        String queryDepTime = " AND DEPARTTIME = ?";
        ArrayList<Trip> tripList = new ArrayList<>();
        
        //lets play with SQL
        String selOrigin = routeIn.getOrigin();
        String selDestination = routeIn.getDestination();
        String selDepDate = tripIn.getDateString();   
        String selDepTime = tripIn.getDeparttime();
        
        //boolean
        boolean originCRT = false;
        boolean destinationCRT = false;
        boolean depDateCRT = false;
        boolean depTimeCRT = false;
        
        //counters for setString(x,y) the x
        int commonCounter = 0;
        int originPos = 0;
        int destinationPos = 0;
        int depDatePos = 0;
        int depTimePos = 0;
        
        
        if(!selOrigin.equals("-")){
            queryOne = queryOne + queryOrigin;
            originCRT = true;
            
            originPos = 1;
            commonCounter ++;
        }
        else{
            originCRT = false;
        }
        
        if(!selDestination.equals("-")){
            queryOne = queryOne + queryDestination;
            destinationCRT = true;
            destinationPos = commonCounter + 1;
            commonCounter ++;
            
        }
        else{
            destinationCRT = false;
        }
        
        if(!selDepDate.equals("-")){
            queryOne = queryOne + queryDepDate;
            depDateCRT = true;
            depDatePos = commonCounter + 1;
            commonCounter ++;
        }
        else{
            depDateCRT = false;
        }
        
        if(!selDepTime.equals("-")){
            queryOne = queryOne + queryDepTime;
            depTimeCRT = true;
            depTimePos = commonCounter + 1;
            commonCounter ++;
        }
        else{
            depTimeCRT = false;
        }
        
        queryOne = queryOne + queryEnd;
        
        //test
        System.out.println("SQL Query in SearchTrip in DA: "+queryOne);
        
        try{
            
            
            ppst = conn.prepareStatement(queryOne, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            //ppst.setString(1, "Y");
            
            //set parameters based on selected criteria
            if(originCRT){
                ppst.setString(originPos, selOrigin);
            }
            if(destinationCRT){
                ppst.setString(destinationPos, selDestination);
            }
            if(depDateCRT){
                String selDepDateCVT;
                String selDepDate_p1 = selDepDate.substring(0,2);
                String selDepDate_p2 = selDepDate.substring(3,5);
                String selDepDate_p3 = selDepDate.substring(6);
                selDepDateCVT = selDepDate_p3 + "-" + selDepDate_p2 + "-" + selDepDate_p1;
                //test
                System.out.println("Converted Date String for java.sql.Date is: "+selDepDateCVT);
                
                ppst.setDate(depDatePos, java.sql.Date.valueOf(selDepDateCVT));
                //ppst.setString(depDatePos, selDepDate);
            }
            if(depTimeCRT){
                ppst.setString(depTimePos, selDepTime);
            }
            
            //finally execute query
            rs = ppst.executeQuery();
            
            while(rs.next()){
                //tripList.add(new Trip(rs.getString("TRIPNO"),rs.getDate("DEPARTDATE"),rs.getString("DEPARTTIME"),rs.getInt("AVAILABLESEAT"),rs.getInt("TOTALSEAT"),rs.getDouble("TRIPPRICE"),(rs.getString("STATUS")).charAt(0),rs.getString("ROUTEID"),rs.getString("BUSID")));
                Route routeOut = new Route();
                routeOut.setRouteid(rs.getString("ROUTEID"));
                routeOut.setOrigin(rs.getString("ORIGIN"));
                routeOut.setDestination(rs.getString("DESTINATION"));
                
                Bus busOut = new Bus();
                busOut.setBusid(rs.getString("BUSID"));
                busOut.setBustype(rs.getString("BUSTYPE").charAt(0));
                busOut.setPlateno(rs.getString("PLATENO"));
                
                Trip tripOut = new Trip();
                tripOut.setTripno(rs.getString("TRIPNO"));
                tripOut.setBus(busOut);
                tripOut.setRoute(routeOut);
                tripOut.setAvailableseat(rs.getInt("AVAILABLESEAT"));
                tripOut.setTripprice(rs.getDouble("TRIPPRICE"));
                tripOut.setDeparttime(rs.getString("DEPARTTIME"));
                tripOut.setDepartdate(rs.getDate("DEPARTDATE"));
                
                //test
                System.out.println(rs.getString("TRIPNO"));
                
                tripList.add(tripOut);
                
                ppst.clearBatch();
            }
            
            
            
        }catch(SQLException ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                }
        
        //test
        System.out.println("In DA Search, size is: "+ tripList.size());
        //test
            //System.out.println("\n");
        
        return tripList;
        
    }
    
//    public String getLatestID(){
//        String staffID = "";
//        try{
//            
//            ResultSet rs = null;
//            String queryOne = "SELECT * FROM STAFF ORDER BY STAFFID";
//            ppst = conn.prepareStatement(queryOne, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
//            rs = ppst.executeQuery();
//            
//            if(rs.next()){
//
//                rs.last();
//                staffID = rs.getString("STAFFID");
//                        
//                    }
//            
//        }catch(SQLException ex){
//                    JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
//                }
//        
//        
//        return staffID;
//    }
//    
//    public int addRecord(Staff staff){
//        
//        int result = -1;
//        
//        try{
//
//            PreparedStatement ppstInsert = conn.prepareStatement("INSERT INTO STAFF VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");
//            ppstInsert.setString(1, staff.getStaffid());
//            ppstInsert.setString(2, staff.getFirstname());
//            ppstInsert.setString(3, staff.getLastname());
//            ppstInsert.setString(4, String.valueOf(staff.getGender()));
//            ppstInsert.setString(5, staff.getStreetaddress1());
//            ppstInsert.setString(6, staff.getStreetaddress2());
//            ppstInsert.setString(7, staff.getCity());
//            ppstInsert.setString(8, staff.getState());
//            ppstInsert.setString(9, staff.getPosition());
//            ppstInsert.setDouble(10, staff.getSalary());
//            ppstInsert.setString(11, staff.getHomephoneno());
//            ppstInsert.setString(12, staff.getMobilephoneno());
//            ppstInsert.setString(13, staff.getEmailaddress());
//            
//            
//            result = ppstInsert.executeUpdate();
//
//            return result;
//            
//        }catch(SQLException ex){
//                    JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
//                    
//                    return result;
//        }
//    }
//    
//
//    public int updateRecord(Staff staff){
//        
//        int result = -1;
//        
//        try {
//            String updateStr = "UPDATE STAFF SET STREETADDRESS1 = ?, STREETADDRESS2 = ?, CITY = ?, STATE = ?, HOMEPHONENO = ?, MOBILEPHONENO = ?, EMAILADDRESS = ?, POSITION = ?, SALARY = ? WHERE STAFFID = ?";
//            ppst = conn.prepareStatement(updateStr);
////            ppst.setDate(1, staff.getDOB());
////            ppst.setString(2, staff.getNationality());
////            ppst.setString(3, staff.getBankAccNo());
//            ppst.setString(1, staff.getStreetaddress1());
//            ppst.setString(2, staff.getStreetaddress2());
////            ppst.setString(6, staff.getPostcode());
//            ppst.setString(3, staff.getCity());
//            ppst.setString(4, staff.getState());
////            ppst.setString(9, staff.getCountry());
//            ppst.setString(5, staff.getHomephoneno());
//            ppst.setString(6, staff.getMobilephoneno());
//            ppst.setString(7, staff.getEmailaddress());
//            ppst.setString(8, staff.getPosition());
////            ppst.setString(14, staff.getEmpStat());
//            ppst.setDouble(9, staff.getSalary());
////            ppst.setDouble(16, staff.getWages());
////            ppst.setDate(17, staff.getHireDate());
//            
//            ppst.setString(10, staff.getStaffid());
//            
//            result = ppst.executeUpdate();
//            
//            return result;
//            
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
//            return result;
//        }
//    }
//    
//    public int deleteRecord(Staff staffDel){
//        
//        int result = -1;
//        
//        try{
////            ResultSet rs = null;
////            String queryOne = "SELECT * FROM STAFF WHERE STAFFID = ?";
////            ppst = conn.prepareStatement(queryOne);
////            ppst.setString(1,staffID);
////            rs = ppst.executeQuery();
//            
//            String queryTwo = "DELETE FROM STAFF WHERE STAFFID = ?";
//            PreparedStatement ppstDelete = conn.prepareStatement(queryTwo);
//            ppstDelete.setString(1,staffDel.getStaffid());
//            result = ppstDelete.executeUpdate();
//            
//            return result;
//            
////            if(rs.next()){
////                //DELETE THE RECORD
////                
////                
////                int confirm = JOptionPane.showConfirmDialog(null,"Are you sure you want to delete the staff record?","Confirm?",JOptionPane.YES_NO_OPTION);
////                
////                if(confirm == JOptionPane.YES_OPTION)
////                {
////                    
////
////                    if(result > 0){
////                        JOptionPane.showMessageDialog(null,"Record for staff ID: "+ staffID + " has been deleted successfully.","Record Deleted",JOptionPane.INFORMATION_MESSAGE);
////                    }
////                    else{
////                        JOptionPane.showMessageDialog(null,"Result returned is not a positive integer, deletion has failed.","Error",JOptionPane.ERROR_MESSAGE);
////
////                    }
////                }
////            }
////            else{
////               //No action
////            }
//            }catch(SQLException ex){
//                JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
//                return result;
//            }
//    }
//    
//    public Staff getRecord(String staffID){
//        
//        Staff staffResult = new Staff();
//        try{
//            
//            ResultSet rs = null;
//            String queryOne = "SELECT * FROM STAFF WHERE STAFFID = ?";
//            ppst = conn.prepareStatement(queryOne, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
//            ppst.setString(1, staffID);
//            rs = ppst.executeQuery();
//            
//            if(rs.next()){
//
//                staffResult.setCity(rs.getString("CITY"));
//                staffResult.setEmailaddress(rs.getString("EMAILADDRESS"));
//                staffResult.setFirstname(rs.getString("FIRSTNAME"));
//                staffResult.setGender(rs.getString("GENDER").charAt(0));
//                staffResult.setHomephoneno(rs.getString("HOMEPHONENO"));
//                staffResult.setLastname(rs.getString("LASTNAME"));
//                staffResult.setMobilephoneno(rs.getString("MOBILEPHONENO"));
//                staffResult.setPosition(rs.getString("POSITION"));
//                staffResult.setSalary(rs.getDouble("SALARY"));
//                staffResult.setStaffid(rs.getString("STAFFID"));
//                staffResult.setState(rs.getString("STATE"));
//                staffResult.setStreetaddress1(rs.getString("STREETADDRESS1"));
//                staffResult.setStreetaddress2(rs.getString("STREETADDRESS2"));
//                
//                return staffResult;
//                        
//                    }
//            
//            else{
//                return null;
//            }
//            
//        }catch(SQLException ex){
//                    JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
//                    
//                    return null;
//                }
//        
//    }
//    
////    public int getNextNumber(){
////        
////        int result = 0;
////        
////        try{
////            
////            ResultSet rs = null;
////            String queryOne = "SELECT * FROM STAFF";
////            ppst = conn.prepareStatement(queryOne, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
////            rs = ppst.executeQuery();
////            
////            if(rs.next()){
////
////                rs.last();
////                result = rs.getRow();
////                        
////                    }
////            
////            else{
////                //no record if reach here
////            }
////            
////        }catch(SQLException ex){
////                    JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
////                    
////                    
////                }
////        
////        
////        return result;
////    }
    
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