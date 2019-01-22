package com.diyankomitov.digitalpasswallet.models.pass;

public enum PassType {

    BOARDING_PASS("boardingPass"),
    COUPON("coupon"),
    EVENT_TICKET("eventTicket"),
    GENERIC("generic"),
    STORE_CARD("storeCard"),;

    private final String pkType;

    PassType(String pkType) {
        this.pkType = pkType;
    }

    public String getPkType() {
        return pkType;
    }
}
