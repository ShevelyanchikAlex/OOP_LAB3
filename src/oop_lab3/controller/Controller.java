package oop_lab3.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import oop_lab3.entity.Worker;
import oop_lab3.local_plugins.Plugins;
import oop_lab3.proxy.BinarySerializationMethodProxy;
import oop_lab3.proxy.TextSerializationMethodProxy;
import oop_lab3.proxy.XMLSerializationMethodProxy;
import oop_lab3.serialization.BinarySerializationDeserialization;
import oop_lab3.serialization.SerializationDeserializationService;
import oop_lab3.serialization.TextSerializationDeserialization;
import oop_lab3.serialization.XMLSerializationDeserialization;
import oop_lab3.services.AppService;
import oop_lab3.services.StateService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private MenuItem openListMenuItem;

    @FXML
    private MenuItem menuItemSaveList;

    @FXML
    private TableView<Worker> tableView;

    @FXML
    private TableColumn<Object, Object> firstnameColumn;

    @FXML
    private TableColumn<Object, Object> lastnameColumn;

    @FXML
    private TableColumn<Object, Object> typeOfWorkColumn;

    @FXML
    private TableColumn<Object, Object> ageColumn;

    @FXML
    private TableColumn<Object, Object> resumeColumn;

    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnDelete;

    @FXML
    private RadioButton binaryMethod;

    @FXML
    private ToggleGroup method;

    @FXML
    private RadioButton xmlMethod;

    @FXML
    private RadioButton textMethod;

    @FXML
    private CheckBox checkBoxZip2;

    @FXML
    private CheckBox checkBoxToZip;

    private final StateService state = StateService.getInstance();


    @FXML
    void initialize() {
        initChoiceBox();
        initColumns();
        initEvents();

        tableView.setItems(state.getObservableList());
        state.setTableView(tableView);
        state.getTableView().setItems(state.getObservableList());

        checkBoxToZip.setOnAction(ActionEvent -> {
            Plugins.ZIP_PLUGIN.setInclude(checkBoxToZip.isSelected());
        });

        checkBoxZip2.setOnAction(ActionEvent -> {
            Plugins.ZIP2_PLUGIN.setInclude(checkBoxZip2.isSelected());
        });
    }

    private void initEvents() {
        TableView.TableViewSelectionModel<Worker> selectionModel = tableView.getSelectionModel();
        selectionModel.selectedItemProperty().addListener((observableValue, worker, t1) -> {
            try {
                if (tableView.getSelectionModel().getSelectedItem() != null) {
                    state.setWorker(tableView.getSelectionModel().getSelectedItem());
                }
            } catch (NullPointerException e) {
                System.out.println("error: " + e);
            }
        });
    }


    private void initChoiceBox() {
        choiceBox.getItems().add("Accountant");
        choiceBox.getItems().add("FinancialAnalyst");
        choiceBox.getItems().add("Developer");
        choiceBox.getItems().add("DevOpsEmployee");
        choiceBox.getItems().add("Tester");
        choiceBox.setValue("Accountant");
    }


    private void initColumns() {
        tableView.setStyle("-fx-font-size: 16px");
        firstnameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastnameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        typeOfWorkColumn.setCellValueFactory(new PropertyValueFactory<>("typeOfWorker"));
        resumeColumn.setCellValueFactory(new PropertyValueFactory<>("resume"));
    }


    @FXML
    private void addWorker() {
        try {
            state.setCurrentAction("add");
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("Window" + choiceBox.getValue() + ".fxml"));
            AppService.getInstance().createWindow(fxmlLoader);
        } catch (IOException e) {
            System.out.println("error:" + e);
        }
    }


    @FXML
    private void editWorker() {
        if (tableView.getSelectionModel().getSelectedIndex() != -1) {
            try {
                state.setWorker(tableView.getSelectionModel().getSelectedItem());
                state.setCurrentAction("edit");
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("Window" + state.getWorker().getTypeOfWorker() + ".fxml"));
                AppService.getInstance().createWindow(fxmlLoader);
            } catch (IOException e) {
                System.out.println("error:" + e);
            }
        } else {
            AppService.getInstance().showErrorMessage("Select any worker!");
        }
    }


    @FXML
    private void deleteWorker() {
        if (AppService.getInstance().isSelectDelete()) {
            try {
                ObservableList<Worker> workerSelected, allWorkers;
                allWorkers = tableView.getItems();
                workerSelected = tableView.getSelectionModel().getSelectedItems();
                workerSelected.forEach(allWorkers::remove);
            } catch (NoSuchElementException e) {
                System.out.println("error:" + e);
            }
        }
    }


    public void openFile(ActionEvent actionEvent) throws FileNotFoundException {
        File file = AppService.getInstance().openFile();
        SerializationDeserializationService deserialization = new BinarySerializationDeserialization();

        if (xmlMethod.isSelected()) {
            deserialization = new XMLSerializationDeserialization();
        } else {
            if (textMethod.isSelected()) {
                deserialization = new TextSerializationDeserialization();
            } else {
                if (binaryMethod.isSelected()) {
                    deserialization = new BinarySerializationDeserialization();
                }
            }
        }

        state.setObservableList(deserialization.deserialize(file));
        state.getTableView().getItems().clear();

        state.getTableView().getItems().addAll(state.getObservableList());
    }

    public void saveFile(ActionEvent actionEvent) {
        File file = AppService.getInstance().saveFile();

        SerializationDeserializationService serialization = new BinarySerializationDeserialization();
        if (xmlMethod.isSelected()) {
            serialization = new XMLSerializationMethodProxy();
        } else {
            if (textMethod.isSelected()) {
                serialization = new TextSerializationMethodProxy();
            } else {
                if (binaryMethod.isSelected()) {
                    serialization = new BinarySerializationMethodProxy();
                }
            }
        }

        serialization.serialize(state.getObservableList(), file);


//        serialization.serialize(state.getObservableList(), file);
//        ZipService.getInstance().convertToZip(file);
//        ZIP2Service.getInstance().convertZip(file);
    }
}
