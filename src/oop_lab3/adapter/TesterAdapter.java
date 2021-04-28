package oop_lab3.adapter;

import oop_lab3.entity.it_workers.Tester;
import oop_lab3.services.PrintWork;

public class TesterAdapter implements PrintWork {

    private final Tester tester;

    public TesterAdapter(Tester tester) {
        this.tester = tester;
    }

    @Override
    public String printWork() {
        return tester.testSoftware();
    }
}
