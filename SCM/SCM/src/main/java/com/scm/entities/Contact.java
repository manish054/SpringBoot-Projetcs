package com.scm.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.ArrayList;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phNum;

    @Column(nullable = false)
    private String emailId;

    private String pic;
    private String address;

    @Column(length = 5000)
    private String about;
    private boolean favourite = false;

    @Column(length = 5000)
    private String websiteLink;

    @Column(length = 5000)
    private String linkedInLink;

    private String cloudinaryImagePublicId;
    
    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "contact")
    private List<SocialLinks> socialLinks = new ArrayList<>();
}