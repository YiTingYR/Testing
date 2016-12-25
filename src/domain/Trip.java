//Sum
//V1.00 24MAR16 1152AM
package domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class Trip implements Serializable {
    private static final long serialVersionUID = 1L;
    private String tripno;
    private Date departdate;
    private String departtime;
    private Integer availableseat;
    private Integer totalseat;
    private Double tripprice;
    private Character status;
//    @JoinColumn(name = "ROUTEID", referencedColumnName = "ROUTEID")
//    @ManyToOne(optional = false)
//    private Route routeid;
//    @JoinColumn(name = "BUSID", referencedColumnName = "BUSID")
//    @ManyToOne(optional = false)
//    private Bus busid;
    
    //////SUM///////////////////
    private String busid;
    private String routeid;
    private String depDateStr;//needed for search purpose
    private Route route;
    private Bus bus;
    //////////SUM/////////////////
    
    private List<Tripseat> tripseatList;

    public Trip() {
    }

    public Trip(String tripno) {
        this.tripno = tripno;
    }
    
    //SUM///////////////
    public Trip(String tripno, Date departdate, String departtime, int availableseat, int totalseat, double tripprice, char status, String routeid, String busid){
        this.tripno = tripno;
        this.departdate = departdate;
        this.departtime = departtime;
        this.availableseat = availableseat;
        this.totalseat = totalseat;
        this.tripprice = tripprice;
        this.status = status;
        this.routeid = routeid;
        this.busid = busid;
    }
    ////SUM/////////////
    
    ////SUM/////////////////////////
    //for search result purpose
    public Trip(String tripNo, String depDate, String depTime, double price, int availableSeat, Route route, Bus bus){
        this.tripno = tripNo;
        this.depDateStr = depDate;
        this.departtime = depTime;
        this.tripprice = price;
        this.availableseat = availableSeat;
        this.route = route;
        this.bus = bus;

    }
    ///SUM///////////////////////

    public String getTripno() {
        return tripno;
    }

    public void setTripno(String tripno) {
        this.tripno = tripno;
    }

    public Date getDepartdate() {
        return departdate;
    }

    public void setDepartdate(Date departdate) {
        this.departdate = departdate;
    }

    public String getDeparttime() {
        return departtime;
    }

    public void setDeparttime(String departtime) {
        this.departtime = departtime;
    }

    public Integer getAvailableseat() {
        return availableseat;
    }

    public void setAvailableseat(Integer availableseat) {
        this.availableseat = availableseat;
    }

    public Integer getTotalseat() {
        return totalseat;
    }

    public void setTotalseat(Integer totalseat) {
        this.totalseat = totalseat;
    }

    public Double getTripprice() {
        return tripprice;
    }

    public void setTripprice(Double tripprice) {
        this.tripprice = tripprice;
    }

    public Character getStatus() {
        return status;
    }

    public void setStatus(Character status) {
        this.status = status;
    }

    
    ///////SUM//////////////////////
    public String getRouteid() {
        return routeid;
    }

    public void setRouteid(String routeid) {
        this.routeid = routeid;
    }

    public String getBusid() {
        return busid;
    }

    public void setBusid(String busid) {
        this.busid = busid;
    }
    /////SUM///////////////
    
    /////SUM//////////////
    //newly added
    public String getDateString(){
        return depDateStr;
    }
    
    public void setDateString(String dateString){
        this.depDateStr = dateString;
    }
    /////SUM///////////////
    
    //////////SUM///////////////
    public Route getRoute(){
        return route;
    }
    
    public void setRoute(Route route){
        this.route = route;
    }
    
    public Bus getBus(){
        return bus;
    }
    
    public void setBus(Bus bus){
        this.bus = bus;
    }
    //////////////SUM//////////////////
    
    
    public List<Tripseat> getTripseatList() {
        return tripseatList;
    }

    public void setTripseatList(List<Tripseat> tripseatList) {
        this.tripseatList = tripseatList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tripno != null ? tripno.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Trip)) {
            return false;
        }
        Trip other = (Trip) object;
        if ((this.tripno == null && other.tripno != null) || (this.tripno != null && !this.tripno.equals(other.tripno))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domain.Trip[ tripno=" + tripno + " ]";
    }
    
}
