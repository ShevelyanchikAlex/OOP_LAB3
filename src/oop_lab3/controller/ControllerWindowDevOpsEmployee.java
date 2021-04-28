package oop_lab3.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.InputEvent;
import javafx.stage.Stage;
import oop_lab3.services.StateService;
import oop_lab3.entity.it_workers.DevOpsEmployee;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerWindowDevOpsEmployee implements EditController<DevOpsEmployee> {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField inputFirstname;

    @FXML
    private TextField inputLastname;

    @FXML
    private Button btnEdit;

    @FXML
    private ChoiceBox<Boolean> knowledgeOfLinuxChoiceBox;

    @FXML
    private TextArea inputResume;

    @FXML
    private Button btnAdd;

    @FXML
    private TextField inputAge;

    @FXML
    private TextField inputNameOfCompany;

    private final StateService state = StateService.getInstance();

    @FXML
    void initialize() {
        initChoiceBox();

        inputAge.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.matches("\\d*")) return;
            inputAge.setText(newValue.replaceAll("[^\\d]", ""));
        });

        if (state.getCurrentAction().equals("edit")) {
            DevOpsEmployee devOpsEmployee = (DevOpsEmployee) state.getWorker();
            inputFirstname.setText(devOpsEmployee.getFirstName());
            inputLastname.setText(devOpsEmployee.getLastName());
            inputAge.setText(String.valueOf(devOpsEmployee.getAge()));
            inputNameOfCompany.setText(devOpsEmployee.getNameOfItCompany());
            inputResume.setText(devOpsEmployee.getResume());
            knowledgeOfLinuxChoiceBox.setValue(devOpsEmployee.getKnowledgeOfLinux());
            btnAdd.setVisible(false);
        } else {
            btnEdit.setVisible(true);
        }
    }

    void initChoiceBox(){
        knowledgeOfLinuxChoiceBox.getItems().add(Boolean.TRUE);
        knowledgeOfLinuxChoiceBox.getItems().add(Boolean.FALSE);
        knowledgeOfLinuxChoiceBox.setValue(Boolean.TRUE);
    }

    @Override
    public void add(InputEvent event) {
        state.getObservableList().add(getEntity());
        state.getTableView().setItems(state.getObservableList());
        ((Stage) (((Node) event.getSource()).getScene().getWindow())).close();
    }

    @Override
    public void edit(InputEvent event) {
        state.getObservableList().set(state.getTableView().getSelectionModel().getSelectedIndex(), getEntity());
        ((Stage) (((Node) event.getSource()).getScene().getWindow())).close();
    }

    @Override
    public DevOpsEmployee getEntity() {
        DevOpsEmployee devOpsEmployee = new DevOpsEmployee();
        devOpsEmployee.setFirstName(inputFirstname.getText());
        devOpsEmployee.setLastName(inputLastname.getText());
        devOpsEmployee.setAge(Integer.parseInt(inputAge.getText()));
        devOpsEmployee.setNameOfItCompany(inputNameOfCompany.getText());
        devOpsEmployee.setKnowledgeOfLinux(knowledgeOfLinuxChoiceBox.getValue());
        devOpsEmployee.setResume(inputResume.getText());
        return devOpsEmployee;
    }

}
