//Sum
//V1.00 24MAR16 1152AM

package da;

import java.io.Serializable;
import java.util.Objects;
import java.sql.*;

import java.sql.*;
import javax.swing.*;

import da.*;
import ui.*;
import domain.*;
import control.*;

public class AuthenticationDA implements Serializable{
    //database variables
    private String host = "jdbc:derby://localhost:1527/ezwaydb";
    private String user = "nbuser";
    private String password = "nbuser";
    private String tableName = "Authentication";
    private Connection conn;
    private PreparedStatement ppst;
    
    public AuthenticationDA(){
        createConnection();
    }
    
    public boolean verifyLogin(Authentication login){
        
        boolean validity = false;
        
        try{
            String queryOne = "SELECT * FROM AUTHENTICATION WHERE USERNAME = ?";
            ppst = conn.prepareStatement(queryOne);
            ppst.setString(1, login.getUsername());
            ResultSet rs = ppst.executeQuery();
            if(rs.next()){
                String password = rs.getString("PASSWORD");
                
                //test
                //JOptionPane.showMessageDialog(null, "Entered: "+login.getPassword()+"\nIn DB: "+password,"Login Failed",JOptionPane.ERROR_MESSAGE);
                
                if(login.getPassword().equals(password)){
                    validity = true;
                }
                else{
                    validity = false;
                }
            }
            else{
                validity = false;
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        
        return validity;
    }
    
    public char verifyExistence(Authentication auth){
        
        try{
            String queryOne = "SELECT * FROM AUTHENTICATION WHERE USERNAME = ? OR STAFFID = ?";
            ResultSet rs = null;
            ppst = conn.prepareStatement(queryOne);
            ppst.setString(1, auth.getUsername());
            ppst.setString(2, auth.getStaffid());
            rs = ppst.executeQuery();
            if(rs.next()){
                return 'Y'; //There is existing record, cannot create username.
            }
            else{
                return 'N';
            }
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return 'X';
        }
        
    }
    
    public boolean verifyDefaultPassword(Authentication auth){
        boolean defaultPW = false;
        
        try{
            String queryOne = "SELECT * FROM AUTHENTICATION WHERE USERNAME = ?";
            ppst = conn.prepareStatement(queryOne);
            ppst.setString(1, auth.getUsername());
            ResultSet rs = ppst.executeQuery();
            if(rs.next()){
                String password = rs.getString("PASSWORD");
                
                //test
                //JOptionPane.showMessageDialog(null, "Entered: "+login.getPassword()+"\nIn DB: "+password,"Login Failed",JOptionPane.ERROR_MESSAGE);
                
                if(rs.getString("PASSWORD").equals(rs.getString("STAFFID"))){
                    defaultPW = true;
                }
                else{
                    defaultPW = false;
                }
            }
            else{
                defaultPW = false;
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        
        return defaultPW;
    }
            
    public int addUser(Authentication auth){
                
        int result = -1;
        try{
            String queryOne = "INSERT INTO AUTHENTICATION VALUES(?,?,?,?,?,?)";
            ppst = conn.prepareStatement(queryOne);
            ppst.setString(1, auth.getUsername());
            ppst.setString(2, auth.getPassword());
            ppst.setString(3, String.valueOf(auth.getAccounttype()));
            ppst.setInt(4, auth.getSecurityquestion());
            ppst.setString(5, auth.getSecurityanswer());
            ppst.setString(6, auth.getStaffid());
            result = ppst.executeUpdate();
            
            return result;
            
//            if(result > 0){
//                JOptionPane.showMessageDialog(null,"User: " + auth.getUsername() + " has been successfully created.","Success",JOptionPane.INFORMATION_MESSAGE);
//            }
//            else{
//                JOptionPane.showMessageDialog(null,"Result returned is not larger than 0.\nError in creating new user.","Error",JOptionPane.ERROR_MESSAGE);
//            }
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return result;
        }
    }
    
    public Authentication PasswordRecoveryVerification(Authentication auth){
        
        String username = auth.getUsername();
        String staffID = auth.getStaffid();
        
        Authentication authResult = null;
        
        try{
            String queryOne = "SELECT * FROM AUTHENTICATION WHERE USERNAME = ?";
            ResultSet rs = null;
            ppst = conn.prepareStatement(queryOne);
            ppst.setString(1, username);
            rs = ppst.executeQuery();
            
            if(rs.next()){
                if(username.equals(rs.getString("USERNAME"))){
                    if(staffID.equals(rs.getString("STAFFID"))){
                        //valid
                        authResult = new Authentication(rs.getString("USERNAME"),rs.getString("PASSWORD"),rs.getString("ACCOUNTTYPE").charAt(0),rs.getInt("SECURITYQUESTION"),rs.getString("SECURITYANSWER"),rs.getString("STAFFID"));
                        return authResult;
                    }
                    else{
                        return null; //null means invalid or non existing username or staffID, or username does not match with staffID
                    }
                }
                else{
                    return null;
                }
            }
            else{
                return null;
            }
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        
        //return null;
    }
    
    public int updatePassword(Authentication auth){
        
        int result = 99;
        
        try{
            
            String queryOne = "UPDATE AUTHENTICATION SET PASSWORD = ? WHERE USERNAME = ?";
            ppst = conn.prepareStatement(queryOne);
            ppst.setString(1, auth.getPassword());
            ppst.setString(2, auth.getUsername());
            
            result = ppst.executeUpdate();
            
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            
        }
        
        return result;
    }
    
    public int deleteAccount(Authentication auth){
       
        int result = 99;
        
        try{
            
            String queryOne = "DELETE FROM AUTHENTICATION WHERE USERNAME = ? OR STAFFID = ?";
            ppst = conn.prepareStatement(queryOne);
            ppst.setString(1, auth.getUsername());
            ppst.setString(2, auth.getStaffid());
            
            result = ppst.executeUpdate();
            
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            
        }
        
        return result;
    }
    
    public int getNextNumber(){
        
        int result = 0;
        
        try{
            
            ResultSet rs = null;
            String queryOne = "SELECT * FROM AUTHENTICATION";
            ppst = conn.prepareStatement(queryOne, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = ppst.executeQuery();
            
            if(rs.next()){

                rs.last();
                result = rs.getRow();
                        
                    }
            
            else{
                //no record if reach here
            }
            
        }catch(SQLException ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                    
                    
                }
        
        
        return result;
    }
    
    public Authentication selectAuth(Authentication auth){
        //boolean exist = false;
        Authentication authResult;
        
        //test1
                if(auth == null){
                    JOptionPane.showMessageDialog(null, "The authIn is null.", "Auth does not exist", JOptionPane.ERROR_MESSAGE);
                }
        
        try{
            
            ResultSet rs = null;
            String queryOne = "SELECT * FROM AUTHENTICATION WHERE USERNAME = ?";
            ppst = conn.prepareStatement(queryOne, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            if(auth != null){
            ppst.setString(1, auth.getUsername());
            rs = ppst.executeQuery();
            }
            
            if(rs.next()){

                authResult = new Authentication(rs.getString("USERNAME"),rs.getString("PASSWORD"),rs.getString("ACCOUNTTYPE").charAt(0),rs.getInt("SECURITYQUESTION"),rs.getString("SECURITYANSWER"),rs.getString("STAFFID"));
                        
                return authResult;
                    }
            
            else{
                //exist = false;
                return null;
            }
            
        }catch(SQLException ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                    //exist = false;
                    return null;
                }
        
        
        //return exist;
    }
    
    private void createConnection(){
        try{
            conn = DriverManager.getConnection(host, user, password);
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(),"DB ERROR",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void shutDown(){
        if(ppst != null){
            try{
                ppst.close();
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null,ex.getMessage(),"DB ERROR",JOptionPane.ERROR_MESSAGE);
            }
        }
        if(conn != null){
            try{
                conn.close();
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null,ex.getMessage(),"DB ERROR",JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}