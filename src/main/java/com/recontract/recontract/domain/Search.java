package com.recontract.recontract.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;

@Entity
@Table(name = "searches")
public class Search {

    @Id
    @GeneratedValue
    private long id;
    private String functionTitle;
    private int amount;
    private String location;
    private String headline;
    private String email;
    private String fullName;
    @Lob
    private byte[] profilePicture;

    @OneToOne
    @JsonIgnore
    private User user;

    public Search() {
    }

    public Search(String functionTitle,
                  int amounts,
                  String location,
                  String headline,
                  String email,
                  String fullName,
                  byte[] profilePicture) {
        this.functionTitle = functionTitle;
        this.amount = amounts;
        this.location = location;
        this.headline = headline;
        this.email = email;
        this.fullName = fullName;
        this.profilePicture = profilePicture;
    }

    public long getSearchId() {
        return id;
    }

    public void setSearchId(long id) {
        this.id = id;
    }

    public String getFunctionTitle() {
        return functionTitle;
    }

    public void setFunctionTitle(String functionTitle) {
        this.functionTitle = functionTitle;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public byte[] getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(byte[] profilePicture) {
        this.profilePicture = profilePicture;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
