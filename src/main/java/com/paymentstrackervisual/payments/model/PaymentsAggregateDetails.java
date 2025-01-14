package com.paymentstrackervisual.payments.model;

public class PaymentsAggregateDetails {
  String senderOrReceiver;
  Double amount;

  //Getters and Setters
  public String getSenderOrReceiver() {
    return senderOrReceiver;
  }

  public void setSenderOrReceiver(String senderOrReceiver) {
    this.senderOrReceiver = senderOrReceiver;
  }

  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

}
