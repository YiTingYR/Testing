//Sum
//V1.00 24MAR16 1152AM
package domain;

import java.io.Serializable;
//import javax.persistence.Basic;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.NamedQueries;
//import javax.persistence.NamedQuery;
//import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Sum(Local)
 */
//@Entity
//@Table(name = "AUTHENTICATION")
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "Authentication.findAll", query = "SELECT a FROM Authentication a"),
//    @NamedQuery(name = "Authentication.findByUsername", query = "SELECT a FROM Authentication a WHERE a.username = :username"),
//    @NamedQuery(name = "Authentication.findByPassword", query = "SELECT a FROM Authentication a WHERE a.password = :password"),
//    @NamedQuery(name = "Authentication.findByAccounttype", query = "SELECT a FROM Authentication a WHERE a.accounttype = :accounttype"),
//    @NamedQuery(name = "Authentication.findBySecurityquestion", query = "SELECT a FROM Authentication a WHERE a.securityquestion = :securityquestion"),
//    @NamedQuery(name = "Authentication.findBySecurityanswer", query = "SELECT a FROM Authentication a WHERE a.securityanswer = :securityanswer")})
public class Authentication implements Serializable {
    private static final long serialVersionUID = 1L;
//    @Id
//    @Basic(optional = false)
//    @Column(name = "USERNAME")
    private String username;
//    @Basic(optional = false)
//    @Column(name = "PASSWORD")
    private String password;
//    @Basic(optional = false)
//    @Column(name = "ACCOUNTTYPE")
    private Character accounttype;
//    @Basic(optional = false)
//    @Column(name = "SECURITYQUESTION")
    private int securityquestion;
//    @Basic(optional = false)
//    @Column(name = "SECURITYANSWER")
    private String securityanswer;
//    @JoinColumn(name = "STAFFID", referencedColumnName = "STAFFID")
//    @ManyToOne(optional = false)
//    private Staff staffid;
    private String staffid;

    public Authentication() {
    }

    public Authentication(String username) {
        this.username = username;
    }
    
    //for login use
    public Authentication(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    //for pwd recovery initial search use
//    public Authentication(String username, String staffID){
//        this.username = username;
//        this.staffid = staffID;
//    }

    public Authentication(String username, String password, Character accounttype, int securityquestion, String securityanswer, String staffID) {
        this.username = username;
        this.password = password;
        this.accounttype = accounttype;
        this.securityquestion = securityquestion;
        this.securityanswer = securityanswer;
        this.staffid = staffID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Character getAccounttype() {
        return accounttype;
    }

    public void setAccounttype(Character accounttype) {
        this.accounttype = accounttype;
    }

    public int getSecurityquestion() {
        return securityquestion;
    }

    public void setSecurityquestion(int securityquestion) {
        this.securityquestion = securityquestion;
    }

    public String getSecurityanswer() {
        return securityanswer;
    }

    public void setSecurityanswer(String securityanswer) {
        this.securityanswer = securityanswer;
    }

//    public Staff getStaffid() {
//        return staffid;
//    }
//
//    public void setStaffid(Staff staffid) {
//        this.staffid = staffid;
//    }
    
        public String getStaffid() {
        return staffid;
    }

    public void setStaffid(String staffid) {
        this.staffid = staffid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (username != null ? username.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Authentication)) {
            return false;
        }
        Authentication other = (Authentication) object;
        if ((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domain.Authentication[ username=" + username + " ]";
    }
    
}
