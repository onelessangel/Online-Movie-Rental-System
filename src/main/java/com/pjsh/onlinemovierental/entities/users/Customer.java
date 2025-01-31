package com.pjsh.onlinemovierental.entities.users;

import jakarta.persistence.Entity;

@Entity
public class Customer extends AbstractUser {
    private String membershipType;

    public Customer() {
        super();
    }

    public Customer(Long id, String name, String email, String password, String membershipType) {
        super(id, name, email, password);
        this.membershipType = membershipType;
    }

    public String getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(String membershipType) {
        this.membershipType = membershipType;
    }
}
