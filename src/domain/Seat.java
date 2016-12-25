//Sum
//V1.00 24MAR16 1152AM
package domain;

import java.io.Serializable;
import java.util.List;


public class Seat implements Serializable {
    private String seatno;
    private String deck;


    public Seat() {
    }

    public Seat(String seatno) {
        this.seatno = seatno;
    }

    public Seat(String seatno, String deck) {
        this.seatno = seatno;
        this.deck = deck;
    }

    public String getSeatno() {
        return seatno;
    }

    public void setSeatno(String seatno) {
        this.seatno = seatno;
    }

    public String getDeck() {
        return deck;
    }

    public void setDeck(String deck) {
        this.deck = deck;
    }

    @Override
    public String toString() {
        return "domain.Seat[ seatno=" + seatno + " ]";
    }
    
}
