package oop_lab3.entity;

public abstract class BankWorker extends Worker {
    protected String nameOfBank;

    public BankWorker(){};
    public BankWorker(String firstName, String lastName, int age, String resume,String nameOfBank) {
        super(firstName, lastName, age, resume);
        this.nameOfBank = nameOfBank;
    }

    public String getNameOfBank() {
        return nameOfBank;
    }

    public void setNameOfBank(String nameOfBank) {
        this.nameOfBank = nameOfBank;
    }
}
