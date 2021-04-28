package oop_lab3.services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import oop_lab3.entity.Worker;


public class StateService {
    private Worker worker;
    private ObservableList<Worker> observableList = FXCollections.observableArrayList();
    private TableView<Worker> tableView;
    private String currentAction;
    public static StateService instance;


    public static StateService getInstance() {
        if (instance == null) {
            instance = new StateService();
        }
        return instance;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public ObservableList<Worker> getObservableList() {
        return observableList;
    }

    public void setObservableList(ObservableList<Worker> observableList) {
        this.observableList = observableList;
    }

    public TableView<Worker> getTableView() {
        return tableView;
    }

    public void setTableView(TableView<Worker> tableView) {
        this.tableView = tableView;
    }

    public String getCurrentAction() {
        return currentAction;
    }

    public void setCurrentAction(String currentAction) {
        this.currentAction = currentAction;
    }

}
