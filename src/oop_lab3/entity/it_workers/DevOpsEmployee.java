package oop_lab3.entity.it_workers;

import oop_lab3.entity.ITWorker;
import oop_lab3.entity.Worker;

import java.util.Objects;

public class DevOpsEmployee extends ITWorker {

    private boolean knowledgeOfLinux;

    public DevOpsEmployee(String firstName, String lastName, int age, String resume, String nameOfItCompany, boolean isKnowledgeOfLinux) {
        super(firstName, lastName, age, resume, nameOfItCompany);
        this.knowledgeOfLinux = isKnowledgeOfLinux;
        this.typeOfWorker = "DevOpsEmployee";
    }

    public DevOpsEmployee() {
        this.typeOfWorker = "DevOpsEmployee";
    }

    @Override
    public void work() {
        System.out.println("Deliver code to prod and automate systems.");
    }

    @Override
    public String getTypeOfWorker() {
        return "DevOpsEmployee";
    }

    public boolean getKnowledgeOfLinux() {
        return knowledgeOfLinux;
    }

    public void setKnowledgeOfLinux(boolean knowledgeOfLinux) {
        this.knowledgeOfLinux = knowledgeOfLinux;
    }

    @Override
    public String toString() {
        return "DevOpsEmployee" +
                "-" + nameOfItCompany +
                "-" + firstName +
                "-" + lastName +
                "-" + age +
                "-" + resume +
                "-" + knowledgeOfLinux +
                '\r'+
                '\n';
    }

    @Override
    public Worker fromString(String str) {
        String[] arr = str.split("-");
        return new DevOpsEmployee(arr[2], arr[3], Integer.parseInt(arr[4]), arr[5], arr[1], Boolean.parseBoolean(arr[6]));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DevOpsEmployee that = (DevOpsEmployee) o;
        return knowledgeOfLinux == that.knowledgeOfLinux;
    }

    @Override
    public int hashCode() {
        return Objects.hash(knowledgeOfLinux);
    }
}
