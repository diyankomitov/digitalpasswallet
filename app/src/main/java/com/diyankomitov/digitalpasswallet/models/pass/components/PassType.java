package com.diyankomitov.digitalpasswallet.models.pass.components;

/**
 * This enum represents the type of the pass.
 */
public enum PassType {
    
    /**
     * Boarding Pass pass type.
     */
    BOARDING_PASS("boardingPass"),
    /**
     * Coupon pass type.
     */
    COUPON("coupon"),
    /**
     * Event Ticket pass type.
     */
    EVENT_TICKET("eventTicket"),
    /**
     * Generic pass type.
     */
    GENERIC("generic"),
    /**
     * Store Card pass type.
     */
    STORE_CARD("storeCard"),
    ;
    
    private final String pkType;
    
    PassType(String pkType) {
        
        this.pkType = pkType;
    }
    
    /**
     * Gets pkpass string representation of this type.
     *
     * @return the pkpass string representation.
     */
    public String getPkType() {
        
        return pkType;
    }
}
