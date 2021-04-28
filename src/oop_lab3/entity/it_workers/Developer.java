package oop_lab3.entity.it_workers;

import oop_lab3.entity.ITWorker;
import oop_lab3.entity.Worker;

import java.util.Objects;

public class Developer extends ITWorker implements DeveloperWork{
    private ProgramLanguage programLanguage;
    private Tester tester;

    public Developer() {
        this.typeOfWorker = "Developer";
    }

    public Developer(String firstName, String lastName, int age, String resume, String nameOfItCompany, ProgramLanguage programLanguage, Tester tester) {
        super(firstName, lastName, age, resume, nameOfItCompany);
        this.programLanguage = programLanguage;
        this.tester = tester;
        this.typeOfWorker = "Developer";
    }

    @Override
    public String writeCode() {
        return "Write a code.";
    }

    public enum ProgramLanguage {
        JAVA,
        DART,
        KOTLIN,
        JS,
        CPP,
        PYTHON,
        PHP;
    }

    public ProgramLanguage getProgramLanguage() {
        return programLanguage;
    }

    public void setProgramLanguage(ProgramLanguage programLanguage) {
        this.programLanguage = programLanguage;
    }

    public Tester getTester() {
        return tester;
    }

    public void setTester(Tester tester) {
        this.tester = tester;
    }

    @Override
    public String getTypeOfWorker() {
        return "Developer";
    }


    @Override
    public String toString() {
        return "Developer" +
                "-" + nameOfItCompany +
                "-" + firstName +
                "-" + lastName +
                "-" + age +
                "-" + resume +
                "-" + programLanguage +
                "-" + tester.toString();
    }

    @Override
    public Worker fromString(String str) {
        String[] arr = str.split("-");
        str = str.substring(str.indexOf("Tester"));
        Tester tester = new Tester();
        return new Developer(arr[2], arr[3], Integer.parseInt(arr[4]), arr[5], arr[1], ProgramLanguage.valueOf(arr[6]), (Tester) tester.fromString(str));
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Developer developer = (Developer) o;
        return programLanguage == developer.programLanguage;
    }

    @Override
    public int hashCode() {
        return Objects.hash(programLanguage);
    }
}
