package oop_lab3.adapter;

import oop_lab3.entity.it_workers.Developer;
import oop_lab3.services.PrintWork;

public class DeveloperAdapter implements PrintWork {
    private final Developer developer;

    public DeveloperAdapter(Developer developer) {
        this.developer = developer;
    }

    @Override
    public String printWork() {
        return developer.writeCode();
    }
}
