package com.warehouse.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ContainArticles {

    @JsonProperty("art_id")
    private String artId;
    @JsonProperty("amount_of")
    private String amountOf;
    public void setArtId(String artId) {
         this.artId = artId;
     }
     public String getArtId() {
         return artId;
     }

    public void setAmountOf(String amountOf) {
         this.amountOf = amountOf;
     }
     public String getAmountOf() {
         return amountOf;
     }

}
