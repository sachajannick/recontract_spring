package com.recontract.recontract.domain;

import javax.persistence.*;

@Entity
@Table(name = "searches")
public class Search {

    @Id
    @SequenceGenerator(
            name = "search_sequence",
            sequenceName = "search_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "search_sequence"
    )
    private long searchId;
    private String functionTitle;
    private int amount;

    @OneToOne
    private User user;

    public Search() {
    }

    public Search(String functionTitle,
                  int amounts) {
        this.functionTitle = functionTitle;
        this.amount = amounts;
    }

    public long getSearchId() {
        return searchId;
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
