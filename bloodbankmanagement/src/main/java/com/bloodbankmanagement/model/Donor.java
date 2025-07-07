package com.bloodbankmanagement.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Donors")
public class Donor {
    @Id
	@Column(name = "donorid")
    private String donorid;
    private String dfirstname;
    private String dlastname;
    private String dcity;
    private String dcontactnum;
    private String dbloodgroup;
    private String userUserId;

    public Donor(String donorid, String dfirstname, String dlastname, String dcity, 
    String dcontactnum, String dbloodgroup, String userUserId) {
		super();
        this.donorid = donorid;
		this.dfirstname = dfirstname;
		this.dlastname = dlastname;
		this.dcity = dcity;
		this.dcontactnum = dcontactnum;
		this.dbloodgroup = dbloodgroup;
        this.userUserId = userUserId;
	}

	public Donor(String dcity, String dbloodgroup){
		this.dcity = dcity;
		this.dbloodgroup = dbloodgroup;
	}
    public Donor(){
        super();
    }

    public String getDonorid(){
        return donorid;
    }
    public void setDonorid(String donorid){
        this.donorid = donorid;
    }
	public String getDfirstname() {
		return dfirstname;
	}
	public void setDfirstname(String dfirstname) {
		this.dfirstname = dfirstname;
	}
	public String getDlastname() {
		return dlastname;
	}
	public void setDlastname(String dlastname) {
		this.dlastname = dlastname;
	}
	public String getDcity() {
		return dcity;
	}
	public void setDcity(String dcity) {
		this.dcity = dcity;
	}
	public String getDcontactnum() {
		return dcontactnum;
	}
	public void setDcontactnum(String dcontactnum) {
		this.dcontactnum = dcontactnum;
	}
	public String getDbloodgroup() {
		return dbloodgroup;
	}
	public void setDbloodgroup(String dbloodgroup) {
		this.dbloodgroup = dbloodgroup;
	}

    public String getUserUserid(){
        return userUserId;
    }
    public void setUserUserId(String userUserId){
        this.userUserId = userUserId;;
    }
}
