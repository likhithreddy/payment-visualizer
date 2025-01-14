# Payments Visualizer

## Overview

**Payments Visualizer** is a Java-based tool that automates the extraction, aggregation, and visualization of financial transaction data. The application reads transaction records from a CSV file, analyzes the data to segregate credit and debit payments, and presents it in a visually appealing pie chart to provide clear financial insights.

This project is ideal for anyone looking to track and understand their financial transactions in an intuitive and efficient manner.

---

## Features

- **Transaction Parsing**: Reads transaction records from a CSV file with detailed fields like transaction date, reason, amount, and balance.
- **Data Aggregation**: Groups and aggregates transactions by sender or receiver to highlight payment trends.
- **Customizable Sorting**: Supports sorting by debit or credit transactions using a flexible comparator.
- **Interactive Visualization**: Displays payments in a pie chart with tooltips for detailed insights.
- **Clean and Modular Design**: Uses object-oriented principles for a well-structured codebase.

---

## Tech Stack

- **Java**: Core programming language.
- **XChart**: Library for generating pie charts and visualizations.
- **Swing**: Used for displaying charts in a standalone Java application.

---

## How It Works

1. **Data Fetching**: 
   - The application reads transactions from a CSV file (`201149121_1711531217858.csv`) using the `PaymentsFetch` class.
   - Transactions are parsed into objects of the `PaymentRecord` model.

2. **Data Analysis**:
   - Aggregates payment amounts by sender/receiver using `PaymentsAggregateDetails`.
   - Classifies transactions into debit, credit, or all categories.

3. **Visualization**:
   - The `PieChartView` class uses the XChart library to generate an interactive pie chart.
   - Displays payment amounts segregated by sender/receiver for better financial insights.

---

## Installation

Follow these steps to set up and run the project on your local machine:

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/likhithreddy/payment-visualizer.git
   cd payment-visualizer
2. Open the project in your favorite IDE (e.g., IntelliJ IDEA, Eclipse).
3. Add the necessary dependencies:
   - XChart library (Download or add via Maven/Gradle).
4. Run the PaymentsFetch class to execute the application.

---

## Author

Likhith Reddy Rechintala
For queries, please reach out to [rlikhith@gmail.com](mailto:rlikhith@gmail.com).
