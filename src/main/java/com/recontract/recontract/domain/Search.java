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

    @OneToOne
    @JsonIgnore
    private User user;

    public Search() {
    }

    public Search(String functionTitle,
                  int amounts) {
        this.functionTitle = functionTitle;
        this.amount = amounts;
    }

    public long getSearchId() {
        return id;
    }

    public void setSearchId(long searchId) {
        this.id = searchId;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
