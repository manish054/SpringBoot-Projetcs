package com.bloodbankmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DonorsTemplate {
    private String ddid;
    private String ddfirstname;
    private String ddlastname;
    private String ddcity;
    private String ddcontactnum;
    private String ddbloodgroup;
}
