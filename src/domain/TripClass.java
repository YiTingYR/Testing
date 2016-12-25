
//V1.00 24MAR16 1152AM
package domain; //author:Teh Yi Ting
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
public class TripClass implements Serializable {
    private String tripno;
    private Date departdate;
    private String departtime;
    private int availableseat;
    private int totalseat;
    private double tripprice;
    private String status;
    private String routeid;
    private String busid;
public TripClass() {
    }

    public TripClass(String tripno) {
        this.tripno = tripno;
    }

    public TripClass(String tripno, Date departdate, String departtime, int availableseat, int totalseat, double tripprice, String status, String routeid, String busid) {
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

    public int getAvailableseat() {
        return availableseat;
    }

    public void setAvailableseat(int availableseat) {
        this.availableseat = availableseat;
    }

    public int getTotalseat() {
        return totalseat;
    }

    public void setTotalseat(int totalseat) {
        this.totalseat = totalseat;
    }

    public double getTripprice() {
        return tripprice;
    }

    public void setTripprice(double tripprice) {
        this.tripprice = tripprice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

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

   
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tripno != null ? tripno.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "domain.Trip[ tripno=" + tripno + " ]";
    }
}