package oop_lab3.services;

public class InfoAboutWorkService {

    private static InfoAboutWorkService instance;

    public static InfoAboutWorkService getInstance() {
        if (instance == null) {
            instance = new InfoAboutWorkService();
        }
        return instance;
    }

    public String printAboutWork(PrintWork infoAboutWork) {
        return infoAboutWork.printWork();
    }
}
