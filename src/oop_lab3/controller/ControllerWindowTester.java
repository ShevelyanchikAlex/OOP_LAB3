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
import oop_lab3.entity.it_workers.Tester;

public class ControllerWindowTester implements EditController<Tester> {

    @FXML
    private ChoiceBox<Tester.TypeOfTester> typeOfTesterChoiceBox;

    @FXML
    private Button btnAdd;
    @FXML
    private Button btnEdit;

    @FXML
    private TextField inputLastname;

    @FXML
    private TextField inputFirstname;

    @FXML
    private TextArea inputResume;

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
            Tester tester = (Tester) state.getWorker();
            inputFirstname.setText(tester.getFirstName());
            inputLastname.setText(tester.getLastName());
            inputAge.setText(String.valueOf(tester.getAge()));
            inputNameOfCompany.setText(tester.getNameOfItCompany());
            inputResume.setText(tester.getResume());
            typeOfTesterChoiceBox.setValue(tester.getTypeOfTester());
            btnAdd.setVisible(false);
        } else {
            btnEdit.setVisible(true);
        }
    }


    void initChoiceBox(){
        typeOfTesterChoiceBox.getItems().add(Tester.TypeOfTester.AUTOMATION_TESTER);
        typeOfTesterChoiceBox.getItems().add(Tester.TypeOfTester.USABILITY_TESTER);
        typeOfTesterChoiceBox.getItems().add(Tester.TypeOfTester.SECURITY_TESTER);
        typeOfTesterChoiceBox.getItems().add(Tester.TypeOfTester.MANUAL_QA_TESTER);
        typeOfTesterChoiceBox.getItems().add(Tester.TypeOfTester.BUSINESS_INTELLIGENCE_TESTER);
        typeOfTesterChoiceBox.setValue(Tester.TypeOfTester.AUTOMATION_TESTER);
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
    public Tester getEntity() {
        Tester tester = new Tester();
        tester.setFirstName(inputFirstname.getText());
        tester.setLastName(inputLastname.getText());
        tester.setAge(Integer.parseInt(inputAge.getText()));
        tester.setNameOfItCompany(inputNameOfCompany.getText());
        tester.setTypeOfTester(typeOfTesterChoiceBox.getValue());
        tester.setResume(inputResume.getText());
        return tester;
    }


}
