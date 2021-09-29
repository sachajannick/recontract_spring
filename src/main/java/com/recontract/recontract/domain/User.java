package com.recontract.recontract.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "app_user")
public class User {

    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    @Column(columnDefinition = "serial")
    private long userId;
    private String username;
    private String email;
    private String password;
    private String hiringOrFreelancer;
    private String fullName;
    private String location;
    private String headline;
    @Lob
    private byte[] profilePicture;

    @OneToOne(mappedBy = "user")
    @JsonIgnore
    private Search search;

    @ManyToMany
    @JoinTable (name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public User() {
    }

    public User(String username,
                String email,
                String password,
                String hiringOrFreelancer,
                String fullName,
                String location,
                String headline,
                byte[] profilePicture) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.hiringOrFreelancer = hiringOrFreelancer;
        this.fullName = fullName;
        this.location = location;
        this.headline = headline;
        this.profilePicture = profilePicture;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long id) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
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

    public String getHiringOrFreelancer() {
        return hiringOrFreelancer;
    }

    public void setHiringOrFreelancer(String hiringOrFreelancer) {
        this.hiringOrFreelancer = hiringOrFreelancer;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public byte[] getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(byte[] profilePicture) {
        this.profilePicture = profilePicture;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Search getSearch() {
        return search;
    }

    public void setSearch(Search search) {
        this.search = search;
    }
}
