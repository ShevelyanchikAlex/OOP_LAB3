package oop_lab3.entity;

import java.io.Serializable;

public abstract class Worker implements Serializable {
    protected String firstName;
    protected String lastName;
    protected int age;
    protected String resume;
    protected String typeOfWorker;

    public Worker(){};

    public Worker(String firstName, String lastName, int age, String resume) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.resume = resume;
    }

    public void setTypeOfWorker(String typeOfWorker) {
        this.typeOfWorker = typeOfWorker;
    }

    public abstract Worker fromString(String str);

    public abstract String getTypeOfWorker();

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }
}
