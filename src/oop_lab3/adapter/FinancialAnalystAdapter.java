package oop_lab3.adapter;

import oop_lab3.entity.bank_workers.FinancialAnalyst;
import oop_lab3.services.PrintWork;

public class FinancialAnalystAdapter implements PrintWork {

    private final FinancialAnalyst financialAnalyst;

    public FinancialAnalystAdapter(FinancialAnalyst financialAnalyst) {
        this.financialAnalyst = financialAnalyst;
    }

    @Override
    public String printWork() {
        return financialAnalyst.calculateFinanceAnalytics();
    }
}
