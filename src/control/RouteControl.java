package control;

import domain.Route;
import da.*;
import java.util.ArrayList;

public class RouteControl{

    RouteDA routeDA;
    
    public RouteControl(){
        routeDA = new RouteDA();
    }
    public Route selectRecord(String routeID) {
        return routeDA.getRecord(routeID);
    }
    public void createRecord(String jtfRouteID,String jtfOrigin,String jtfDestination,String jtfRouteID2
    ,String jtfOrigin2, String jtfDestination2) {
        routeDA.createRecord(jtfRouteID,jtfOrigin,jtfDestination,jtfRouteID2,jtfOrigin2,jtfDestination2);
    }
     public ArrayList<Route> getRouteList(){
            return routeDA.getRouteList();
    }
    /*public void shutDown(){
        orderDA.shutDown();
    }*/
}