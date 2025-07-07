package com.bloodbankmanagement.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.ToString;

@Entity
@Table(name = "Users")
@ToString
public class User {
    @Id
    private String userid;
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String password;
    private String contactnum;

    public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username = username;
    }
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
    
    public String getContactnum(){
        return contactnum;
    }

    public void setContactnum(String contactnum){
        this.contactnum = contactnum;
    }

	public User(String userid, String firstname, String lastname, String username, String email, String password, String city,
			String bloodgroup, String contactnum) {
		super();
		this.userid = userid;
		this.firstname = firstname;
		this.lastname = lastname;
        this.username = username;
		this.email = email;
		this.password = password;
        this.contactnum = contactnum;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
