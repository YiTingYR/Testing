
//V1.00 24MAR16 1152AM 
package domain; //author:Teh Yi Ting
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
public class DriverScheduleClass implements Serializable {
    private String scheduleid;
    private Date scheduledate;
    private String availability;
    private String driverid;
    
    public DriverScheduleClass() {
    }

    public DriverScheduleClass(String scheduleid, Date scheduledate, String availability, String driverid) {
        this.scheduleid = scheduleid;
        this.scheduledate = scheduledate;
        this.availability = availability;
        this.driverid = driverid;
    }

    public DriverScheduleClass(String scheduleid) {
        this.scheduleid = scheduleid;
    }

    public String getScheduleid() {
        return scheduleid;
    }

    public void setScheduleid(String scheduleid) {
        this.scheduleid = scheduleid;
    }

    public Date getScheduledate() {
        return scheduledate;
    }

    public void setScheduledate(Date scheduledate) {
        this.scheduledate = scheduledate;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getDriverid() {
        return driverid;
    }

    public void setDriverid(String driverid) {
        this.driverid = driverid;
    }
}