package com.paymentstrackervisual.payments.main;

import com.paymentstrackervisual.payments.model.PaymentRecord;
import com.paymentstrackervisual.payments.model.PaymentsAggregateDetails;
import com.paymentstrackervisual.payments.model.PaymentsComparator;
import com.paymentstrackervisual.payments.model.PaymentsComparator.SortOrder;
import com.paymentstrackervisual.payments.views.PieChartView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class PaymentsFetch {

  private static final String COMMA_DELIMITER = ",";
  private static final String HYPHEN_DELIMITER = "-";

  private static List<PaymentRecord> fetchPaymentRecordsFromCSV()
          throws NumberFormatException, IOException {
    List<PaymentRecord> transactionRecords = new ArrayList<>();
    int recordCount = 0;
    try (BufferedReader br = new BufferedReader(
            new FileReader("src/main/resources/201149121_1711531217858.csv"))) {
      String line;
      while ((line = br.readLine()) != null) {
        recordCount += 1;
        if (recordCount > 2) {
          PaymentRecord pr = new PaymentRecord();
          String[] transactionRecordValues = line.split(COMMA_DELIMITER);
          DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yy", Locale.ENGLISH);
          LocalDate transactionDate = LocalDate.parse(transactionRecordValues[0].trim(), dtf);
          pr.setTransactionDate(transactionDate);
          pr.setTransactionReason(transactionRecordValues[1].trim());
          double debitAmount = Double.parseDouble(transactionRecordValues[3].trim());
          double creditAmount = Double.parseDouble(transactionRecordValues[4].trim());
          pr.setTransactionAmount((debitAmount == 0) ? creditAmount : (debitAmount) * (-1));
          pr.setTransactionReferenceNumber(transactionRecordValues[5].trim());
          pr.setClosingBalance(Double.parseDouble(transactionRecordValues[6].trim()));
          pr.setPaymentMode(pr.getTransactionReason().substring(0, 3));
          String[] transactionDetails = pr.getTransactionReason().split(HYPHEN_DELIMITER);
          pr.setSenderOrReceiver(
                  (transactionDetails.length > 1) ? transactionDetails[1] : transactionDetails[0]);
          transactionRecords.add(pr);
        }
      }
    } catch (FileNotFoundException e) {
      System.out.println("File Not Found!");
    }

    return transactionRecords;
  }

  ;

  private static List<PaymentsAggregateDetails> analyzePayments(List<PaymentRecord> payments) {
    List<PaymentsAggregateDetails> paymentAggregateDetails = new ArrayList<>();
    HashMap<String, Double> hm = new HashMap<>();
    for (PaymentRecord payment : payments) {
      String senderOrReceiver = payment.getSenderOrReceiver();
      double transactionAmount = payment.getTransactionAmount();
      try {
        hm.put(senderOrReceiver, hm.get(senderOrReceiver) + transactionAmount);
      } catch (Exception e) {
        hm.put(senderOrReceiver, transactionAmount);
      }
    }
    for (Map.Entry<String, Double> entry : hm.entrySet()) {
      PaymentsAggregateDetails pad = new PaymentsAggregateDetails();
      pad.setSenderOrReceiver(entry.getKey());
      pad.setAmount(entry.getValue());
      paymentAggregateDetails.add(pad);
    }
    return paymentAggregateDetails;
  }

  private static List<PaymentsAggregateDetails> filterPayments(
          List<PaymentsAggregateDetails> allPayments,
          PaymentType paymentType) {
    if (paymentType == PaymentType.CREDIT) {
      List<PaymentsAggregateDetails> creditPayments = new ArrayList<>();
      for (PaymentsAggregateDetails payment : allPayments) {
        if (payment.getAmount() > 0) {
          creditPayments.add(payment);
        }
      }
      return creditPayments;
    } else if (paymentType == PaymentType.DEBIT) {
      List<PaymentsAggregateDetails> debitPayments = new ArrayList<>();
      for (PaymentsAggregateDetails payment : allPayments) {
        if (payment.getAmount() < 0) {
          debitPayments.add(payment);
        }
      }
      return debitPayments;
    }
    return allPayments;
  }

  public static void main(String[] args) throws IOException {
    final List<PaymentRecord> records = fetchPaymentRecordsFromCSV();
    final List<PaymentsAggregateDetails> paymentsSegregate = analyzePayments(records);
    Collections.sort(paymentsSegregate, new PaymentsComparator(SortOrder.DEBIT_FIRST));

    PieChartView pieChart = new PieChartView(filterPayments(paymentsSegregate, PaymentType.DEBIT));
    pieChart.getChart();
  }

  private static enum PaymentType {
    DEBIT,
    CREDIT,
    ALL
  }

}
