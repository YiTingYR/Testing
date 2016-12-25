package control;
import domain.*;
import da.*;
import java.util.ArrayList;

public class PaymentControl{

    PaymentDA payDA;
    OrderForPaymentDA paymentDA;
    OrderDetailForPaymentDA detailPaymentDA;
    
    public PaymentControl(){
        payDA = new PaymentDA();
    }
     public void createRecord(String paymentID,double totalAmount,String payMethod,
                String creditCardNo,double paidAmt,double cashChange,String orderid){
        payDA.createRecord(paymentID,totalAmount,payMethod,creditCardNo,paidAmt,cashChange,orderid);
    }
     public ArrayList<OrderDetailForPayment> getOrderListById(String orderid){
            return payDA.getOrderListById(orderid);
    }
     public  PaymentClass getPaymentList(String paymentid){
            return payDA.getPaymentList(paymentid);
    }
    public void deleteRecord(String orderid){
    paymentDA.deleteRecord(orderid);
    
    }
    public void deleteOrderDetailRecord(String orderid){
    detailPaymentDA.deleteOrderDetailRecord(orderid);
    
    }
    public String getPaymentId(String orderid)
    {
        return payDA.getPaymentId(orderid);
    }
     public void closeDB(){
        payDA.shutDown();
    }
}