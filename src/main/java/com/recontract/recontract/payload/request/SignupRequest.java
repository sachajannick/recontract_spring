package com.recontract.recontract.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

public class SignupRequest {

    @NotBlank
    @Size(
            min = 3,
            max = 20
    )
    private String username;

    private Set<String> role;

    @NotBlank
    @Size(
            min = 6,
            max = 40
    )
    private String password;

    private String hiringOrFreelancer;

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

    public Set<String> getRole() {
        return this.role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }

    public String getHiringOrFreelancer() {
        return hiringOrFreelancer;
    }

    public void setHiringOrFreelancer(String hiringOrFreelancer) {
        this.hiringOrFreelancer = hiringOrFreelancer;
    }
}
