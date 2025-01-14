package com.paymentstrackervisual.payments.views;

import com.paymentstrackervisual.payments.model.PaymentsAggregateDetails;

import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.PieSeries.PieSeriesRenderStyle;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.style.Styler.LegendLayout;
import org.knowm.xchart.style.Styler.LegendPosition;

import java.awt.*;
import java.util.List;

public class PieChartView {

  List<PaymentsAggregateDetails> payments;

  public PieChartView(List<PaymentsAggregateDetails> pad) {
    super();
    this.payments = pad;
  }

  public PieChart getChart() {
    PieChart pieChart = new PieChartBuilder().width(1920).height(1080).title("Payments").build();

    for (PaymentsAggregateDetails payment : payments) {
      pieChart.addSeries(payment.getSenderOrReceiver(), payment.getAmount());
    }

    // Customize Chart
//		pieChart.getStyler().setMarkerSize(16);
//		pieChart.getStyler().setAnnotationTextPanelBackgroundColor(Color.BLACK);
    pieChart.getStyler().setDefaultSeriesRenderStyle(PieSeriesRenderStyle.Pie);
//		pieChart.getStyler().setAnnotationLineColor(Color.BLUE);
//		pieChart.getStyler().setAnnotationTextFontColor(Color.WHITE);
    pieChart.getStyler().setChartBackgroundColor(Color.WHITE);
    pieChart.getStyler().setPlotBorderVisible(false);
    pieChart.getStyler().setLegendBackgroundColor(Color.WHITE);
    pieChart.getStyler().setLegendPosition(LegendPosition.OutsideE);
    pieChart.getStyler().setLegendLayout(LegendLayout.Vertical);
    pieChart.getStyler().setLegendSeriesLineLength(75);
//		pieChart.getStyler().setAnnotationLineStroke(new BasicStroke(1, BasicStroke.CAP_BUTT,
//		BasicStroke.CAP_ROUND));
//		pieChart.getStyler().setForceAllLabelsVisible(true);
//		pieChart.getStyler().setLabelType(LabelType.Percentage);
    pieChart.getStyler().setToolTipsEnabled(true);
    pieChart.getStyler().setToolTipBackgroundColor(Color.WHITE);

    // Display Chart
    new SwingWrapper(pieChart).displayChart();
    return pieChart;
  }
}