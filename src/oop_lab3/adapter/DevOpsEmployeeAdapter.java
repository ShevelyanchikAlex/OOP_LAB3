package oop_lab3.adapter;

import oop_lab3.entity.it_workers.DevOpsEmployee;
import oop_lab3.services.PrintWork;

public class DevOpsEmployeeAdapter implements PrintWork {

    private final DevOpsEmployee devOpsEmployee;

    public DevOpsEmployeeAdapter(DevOpsEmployee devOpsEmployee) {
        this.devOpsEmployee = devOpsEmployee;
    }
    @Override
    public String printWork() {
        return devOpsEmployee.deliverCodeToProd();
    }
}
