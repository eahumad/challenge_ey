package com.ey.challenge.dto;

import com.ey.challenge.entity.Phone;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class UserResDTO {

    private UUID id;
    private String name;
    private String email;
    private List<Phone> phones;
    private Date created;
    private Date modified;
    private Date lastLogin;
    private String token;
    private Boolean isActive;


    public UserResDTO(UUID id, String name, String email, List<Phone> phones, Date created, Date modified, Date lastLogin, String token, Boolean isActive) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phones = phones;
        this.created = created;
        this.modified = modified;
        this.lastLogin = lastLogin;
        this.token = token;
        this.isActive = isActive;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
