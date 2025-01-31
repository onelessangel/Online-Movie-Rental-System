package com.pjsh.onlinemovierental.enums;

public enum MembershipType {
    BASIC(2),
    PREMIUM(4),
    VIP(6);

    private final int maxRentals;

    MembershipType(int maxRentals) {
        this.maxRentals = maxRentals;
    }

    public int getMaxRentals() {
        return maxRentals;
    }
}
