package oop_lab3.entity.it_workers;

import oop_lab3.entity.ITWorker;
import oop_lab3.entity.Worker;

import java.util.Objects;

public class Tester extends ITWorker {

    private TypeOfTester typeOfTester;

    public Tester() {
        this.typeOfWorker = "Tester";
    }

    public Tester(String firstName, String lastName, int age, String resume, String nameOfItCompany, TypeOfTester typeOfTester) {
        super(firstName, lastName, age, resume, nameOfItCompany);
        this.typeOfTester = typeOfTester;
        this.typeOfWorker = "Tester";
    }

    @Override
    public void work() {
        System.out.println("Test software.");
    }

    @Override
    public String getTypeOfWorker() {
        return "Tester";
    }

    public enum TypeOfTester {
        MANUAL_QA_TESTER,
        AUTOMATION_TESTER,
        SECURITY_TESTER,
        BUSINESS_INTELLIGENCE_TESTER,
        USABILITY_TESTER
    }

    public TypeOfTester getTypeOfTester() {
        return typeOfTester;
    }

    public void setTypeOfTester(TypeOfTester typeOfTester) {
        this.typeOfTester = typeOfTester;
    }


    @Override
    public String toString() {
        return "Tester" +
                "-" + nameOfItCompany +
                "-" + firstName +
                "-" + lastName +
                "-" + age +
                "-" + resume +
                "-" + typeOfTester +
                '\r'+
                '\n';
    }

    @Override
    public Worker fromString(String str) {
        String[] arr = str.split("-");
        return new Tester(arr[2], arr[3], Integer.parseInt(arr[4]), arr[5], arr[1], TypeOfTester.valueOf(arr[6]));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tester tester = (Tester) o;
        return typeOfTester == tester.typeOfTester;
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeOfTester);
    }
}
