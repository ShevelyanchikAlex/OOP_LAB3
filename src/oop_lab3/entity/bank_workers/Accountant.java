package oop_lab3.entity.bank_workers;

import oop_lab3.entity.BankWorker;
import oop_lab3.entity.Worker;

import java.util.Objects;

public class Accountant extends BankWorker {
    private boolean isKnowledgeOfExcel;

    public Accountant(String firstName, String lastName, int age, String resume, String nameOfBank, boolean isKnowledgeOfExcel) {
        super(firstName, lastName, age, resume, nameOfBank);
        this.isKnowledgeOfExcel = isKnowledgeOfExcel;
        this.typeOfWorker = "Accountant";
    }

    public Accountant() {
        this.typeOfWorker = "Accountant";
    }

    @Override
    public void work() {
        System.out.println("Calculate workers salaries.");
    }

    @Override
    public String getTypeOfWorker() {
        return "Accountant";
    }

    public boolean getKnowledgeOfExcel() {
        return isKnowledgeOfExcel;
    }

    public void setKnowledgeOfExcel(boolean knowledgeOfExcel) {
        isKnowledgeOfExcel = knowledgeOfExcel;
    }

    @Override
    public Worker fromString(String str) {
        String[] arr = str.split("-");
        return new Accountant(arr[2], arr[3], Integer.parseInt(arr[4]), arr[5], arr[1], Boolean.parseBoolean(arr[6]));
    }

    @Override
    public String toString() {
        return "Accountant" +
                "-" + nameOfBank +
                "-" + firstName +
                "-" + lastName +
                "-" + age +
                "-" + resume +
                "-" + isKnowledgeOfExcel +
                '\r'+
                '\n';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Accountant that = (Accountant) o;
        return isKnowledgeOfExcel == that.isKnowledgeOfExcel;
    }

    @Override
    public int hashCode() {
        return Objects.hash(isKnowledgeOfExcel);
    }
}
