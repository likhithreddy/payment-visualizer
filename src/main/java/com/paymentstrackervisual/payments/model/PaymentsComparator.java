package com.paymentstrackervisual.payments.model;

import java.util.Comparator;

public class PaymentsComparator implements Comparator<PaymentsAggregateDetails> {

  private SortOrder sortOrder;

  public PaymentsComparator(SortOrder sortOrder) {
    this.sortOrder = sortOrder;
  }

  @Override
  public int compare(PaymentsAggregateDetails p1, PaymentsAggregateDetails p2) {
    return (sortOrder == SortOrder.CREDIT_FIRST) ? (Double.compare(p2.getAmount(), p1.getAmount()))
            : (Double.compare(p1.getAmount(), p2.getAmount()));
  }

  public enum SortOrder {
    CREDIT_FIRST,
    DEBIT_FIRST
  }

}
