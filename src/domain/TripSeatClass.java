
//V1.00 24MAR16 1152AM
//Yi Ting
package domain; //author:Teh Yi Ting
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
public class TripSeatClass implements Serializable {
    private String tripno;
    private String seatno;
    private char seatAva;
    
    public TripSeatClass() {
    
    }

    public TripSeatClass(String tripno, String seatno, char seatAva) {
        this.tripno = tripno;
        this.seatno = seatno;
        this.seatAva = seatAva;
    }

    public String getTripno() {
        return tripno;
    }

    public void setTripno(String tripno) {
        this.tripno = tripno;
    }

    public String getSeatno() {
        return seatno;
    }

    public void setSeatno(String seatno) {
        this.seatno = seatno;
    }
    
     public char getSeatava() {
        return seatAva;
    }

    public void setSeatava(char seatAva) {
        this.seatAva = seatAva;
    }
    

   
}