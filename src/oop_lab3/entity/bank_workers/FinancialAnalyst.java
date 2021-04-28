package oop_lab3.entity.bank_workers;

import oop_lab3.entity.BankWorker;
import oop_lab3.entity.Worker;

import java.util.Objects;

public class FinancialAnalyst extends BankWorker {
    private boolean isAvailabilityOfInternationalCertificate;

    public FinancialAnalyst() {
        this.typeOfWorker = "FinancialAnalyst";
    }

    public FinancialAnalyst(String firstName, String lastName, int age, String resume, String nameOfBank, boolean isAvailabilityOfInternationalCertificate) {
        super(firstName, lastName, age, resume, nameOfBank);
        this.isAvailabilityOfInternationalCertificate = isAvailabilityOfInternationalCertificate;
        this.typeOfWorker = "FinancialAnalyst";
    }

    @Override
    public void work() {
        System.out.println("Calculate finance analytics.");
    }

    @Override
    public String getTypeOfWorker() {
        return "FinancialAnalyst";
    }

    public boolean getIsAvailabilityOfInternationalCertificate() {
        return isAvailabilityOfInternationalCertificate;
    }

    public void setAvailabilityOfInternationalCertificate(boolean availabilityOfInternationalCertificate) {
        isAvailabilityOfInternationalCertificate = availabilityOfInternationalCertificate;
    }

    @Override
    public String toString() {
        return "FinancialAnalyst" +
                "-" + nameOfBank +
                "-" + firstName +
                "-" + lastName +
                "-" + age +
                "-" + resume +
                "-" + isAvailabilityOfInternationalCertificate +
                '\r'+
                '\n';
    }

    @Override
    public Worker fromString(String str) {
        String[] arr = str.split("-");
        return new FinancialAnalyst(arr[2], arr[3], Integer.parseInt(arr[4]), arr[5], arr[1], Boolean.parseBoolean(arr[6]));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinancialAnalyst that = (FinancialAnalyst) o;
        return isAvailabilityOfInternationalCertificate == that.isAvailabilityOfInternationalCertificate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(isAvailabilityOfInternationalCertificate);
    }
}
