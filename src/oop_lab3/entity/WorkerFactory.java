package oop_lab3.entity;

import oop_lab3.entity.bank_workers.Accountant;
import oop_lab3.entity.bank_workers.FinancialAnalyst;
import oop_lab3.entity.it_workers.DevOpsEmployee;
import oop_lab3.entity.it_workers.Developer;
import oop_lab3.entity.it_workers.Tester;

public class WorkerFactory {

    public static Worker workerFactory(String typeOfWorker) {
        return switch (typeOfWorker) {
            case "Accountant" -> new Accountant();
            case "FinancialAnalyst" -> new FinancialAnalyst();
            case "Developer" -> new Developer();
            case "DevOpsEmployee" -> new DevOpsEmployee();
            case "Tester" -> new Tester();
            default -> null;
        };
    }

}
