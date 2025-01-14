package com.paymentstrackervisual.payments.model;

import java.time.LocalDate;


public class PaymentRecord {
  private LocalDate transactionDate;
  private String transactionReason;
  private double transactionAmount;
  private String transactionReferenceNumber;
  private double closingBalance;
  private String paymentMode;
  private String senderOrReceiver;

  public double getTransactionAmount() {
    return transactionAmount;
  }

  public void setTransactionAmount(double transactionAmount) {
    this.transactionAmount = transactionAmount;
  }

  public String getPaymentMode() {
    return paymentMode;
  }

  public void setPaymentMode(String paymentMode) {
    this.paymentMode = paymentMode;
  }

  public String getSenderOrReceiver() {
    return senderOrReceiver;
  }

  public void setSenderOrReceiver(String senderOrReceiver) {
    this.senderOrReceiver = senderOrReceiver;
  }

  //Getters and Setters
  public LocalDate getTransactionDate() {
    return transactionDate;
  }

  public void setTransactionDate(LocalDate transactionDate) {
    this.transactionDate = transactionDate;
  }

  public String getTransactionReason() {
    return transactionReason;
  }

  public void setTransactionReason(String transactionReason) {
    this.transactionReason = transactionReason;
  }

  public String getTransactionReferenceNumber() {
    return transactionReferenceNumber;
  }

  public void setTransactionReferenceNumber(String transactionReferenceNumber) {
    this.transactionReferenceNumber = transactionReferenceNumber;
  }

  public double getClosingBalance() {
    return closingBalance;
  }

  public void setClosingBalance(double closingBalance) {
    this.closingBalance = closingBalance;
  }


}
