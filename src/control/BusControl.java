package control;
import domain.*;
import da.*;
import java.util.ArrayList;

public class BusControl{

    BusDA busDA;
    
    public BusControl(){
        busDA = new BusDA();
    }
    
    public ArrayList<Bus> getBusList(){
            return busDA.getBusList();
    }
    public Bus selectRecord(String busID) {
        return busDA.getRecord(busID);
    }
    public Bus selectPN(String plateNo) {
        return busDA.getPN(plateNo);
    }
    public void createRecord(String busID,String plateno, String type){
        busDA.createRecord(busID, plateno, type);
    }
    /*public void shutDown(){
        orderDA.shutDown();
    }*/
}