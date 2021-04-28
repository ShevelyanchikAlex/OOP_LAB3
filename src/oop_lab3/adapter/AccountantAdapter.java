package oop_lab3.adapter;

import oop_lab3.entity.bank_workers.Accountant;
import oop_lab3.services.PrintWork;

public class AccountantAdapter implements PrintWork {

    private final Accountant accountant;

    public AccountantAdapter(Accountant accountant) {
        this.accountant = accountant;
    }

    @Override
    public String printWork() {
        return accountant.calculationOfSalaries();
    }
}
