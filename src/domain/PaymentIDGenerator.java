
//V1.00 24MAR16 1152AM
package domain; 

import java.util.Random;
import java.util.Date;
import java.text.SimpleDateFormat;

public class PaymentIDGenerator{
     private String prefix;
    private int digitLength;
    private Random randomGenerator;
    
    public PaymentIDGenerator(){
       
    }
    
    public PaymentIDGenerator(String prefix,int digitLength){
        this.prefix = prefix;
        this.digitLength = digitLength;
        randomGenerator = new Random();
    }
    
     public String getPrefix() {
        return prefix;
    }

    public int getDigitLength() {
        return digitLength;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public void setDigitLength(int digitLength) {
        this.digitLength = digitLength;
    }
    
    /*public String generateIDByDate(){ 
        return prefix + getDateToday() + getRandomNumber();
    }*/
    
    public String generateID(){
        return prefix + getRandomNumber();
    }
    
    /*private String getDateToday(){
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("ddMMyyyy");
        return DATE_FORMAT.format(new Date());
    }*/
    
    private String getRandomNumber(){
        int upperBound = getRandomNumUpperBound();              
        int randomNum = randomGenerator.nextInt(upperBound);        
        return String.format("%0" + digitLength + "d",randomNum);
    }
        
    private int getRandomNumUpperBound(){        
        String temp = "1";
                     
        for(int i=0; i< digitLength; i++){
            temp += "0";
        }
        return Integer.parseInt(temp);
    }
    
  
   public static void main(String[] args){
        PaymentIDGenerator idGenerator = new PaymentIDGenerator("PY",8);
     
      for(int i=0; i<100; i++){
           System.out.println(idGenerator.generateID());
       }
    }
}