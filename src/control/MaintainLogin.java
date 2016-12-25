//Sum
//V1.00 24MAR16 1152AM
package control;

import da.*;
import ui.*;
import domain.*;
import control.*;

public class MaintainLogin{
    
    private AuthenticationDA loginDA;
    
    public MaintainLogin(){
        loginDA = new AuthenticationDA();
    }
    
    public boolean verifyLogin(Authentication login){
        
        boolean validity = false;
        
        validity = loginDA.verifyLogin(login);
        
        return validity;
    }
    
    public char verifyExistence(Authentication auth){
        return loginDA.verifyExistence(auth);
    }
    
    public int addUser(Authentication login){
        return loginDA.addUser(login);
    }
    
    public Authentication passwordRecoveryVerification(Authentication auth){
        return loginDA.PasswordRecoveryVerification(auth);
    }
    
    public int updatePassword(Authentication auth){
        return loginDA.updatePassword(auth);
    }
    
    public int deleteAccount(Authentication auth){
        return loginDA.deleteAccount(auth);
    }
    
    public int getNextNumber(){
        return loginDA.getNextNumber();
    }
    
    public Authentication selectAuth(Authentication auth){
        return loginDA.selectAuth(auth);
    }
    
    public boolean verifyDefaultPassword(Authentication auth){
        return loginDA.verifyDefaultPassword(auth);
    }
    
    public void closeDB(){
        loginDA.shutDown();
    }
}