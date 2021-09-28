package com.recontract.recontract.dto;

import javax.persistence.Lob;

public class dtoUser {
    public String username;
    public String email;
    public String password;
    public String fullName;
    public String location;
    public String headline;
    @Lob
    public byte[] profilePicture;
}
