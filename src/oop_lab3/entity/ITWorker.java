package oop_lab3.entity;

public abstract class ITWorker extends Worker {
    protected String nameOfItCompany;

    public ITWorker(){};

    public ITWorker(String firstName, String lastName, int age, String resume, String nameOfItCompany) {
        super(firstName, lastName, age, resume);
        this.nameOfItCompany = nameOfItCompany;
    }

    public String getNameOfItCompany() {
        return nameOfItCompany;
    }

    public void setNameOfItCompany(String nameOfItCompany) {
        this.nameOfItCompany = nameOfItCompany;
    }
}