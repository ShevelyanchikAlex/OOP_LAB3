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
import oop_lab3.entity.bank_workers.Accountant;


public class ControllerWindowAccountant implements EditController<Accountant>{

    public TextField nameOfBankTextField;
    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private ChoiceBox<Boolean> knowledgeOfExcelChoiceBox;

    @FXML
    private TextArea resumeTextField;

    @FXML
    private TextField ageTextField;

    @FXML
    private Button btnAdd;
    @FXML
    private Button btnEdit;

    private final StateService state = StateService.getInstance();

    @FXML
    void initialize() {
        initChoiceBox();

        ageTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.matches("\\d*")) return;
            ageTextField.setText(newValue.replaceAll("[^\\d]", ""));
        });

        if (state.getCurrentAction().equals("edit")) {
            Accountant accountant = (Accountant) state.getWorker();
            firstNameTextField.setText(accountant.getFirstName());
            lastNameTextField.setText(accountant.getLastName());
            ageTextField.setText(String.valueOf(accountant.getAge()));
            resumeTextField.setText(accountant.getResume());
            knowledgeOfExcelChoiceBox.setValue(accountant.getKnowledgeOfExcel());
            nameOfBankTextField.setText(accountant.getNameOfBank());
            btnAdd.setVisible(false);
        } else {
            btnEdit.setVisible(false);
        }
    }
    
    void initChoiceBox(){
        knowledgeOfExcelChoiceBox.getItems().add(Boolean.TRUE);
        knowledgeOfExcelChoiceBox.getItems().add(Boolean.FALSE);
        knowledgeOfExcelChoiceBox.setValue(Boolean.TRUE);
    }


    @Override
    public void add(InputEvent event) {
        state.getObservableList().add(getEntity());
        state.getTableView().setItems(state.getObservableList());
        ((Stage) (((Node) event.getSource()).getScene().getWindow())).close();
    }

    @Override
    public void edit(InputEvent event) {
        System.out.println(state.getObservableList());
        //System.out.println(buffer.getObservableList().set(buffer.getTableView().getSelectionModel().getSelectedIndex(), getEntity())+"2222222");
        state.getObservableList().set(state.getTableView().getSelectionModel().getSelectedIndex(), getEntity());
        System.out.println(state.getObservableList());
        ((Stage) (((Node) event.getSource()).getScene().getWindow())).close();
    }
    

    @Override
    public Accountant getEntity() {
        Accountant accountant = new Accountant();
        accountant.setFirstName(firstNameTextField.getText());
        accountant.setLastName(lastNameTextField.getText());
        accountant.setAge(Integer.parseInt(ageTextField.getText()));
        accountant.setResume(resumeTextField.getText());
        accountant.setKnowledgeOfExcel(knowledgeOfExcelChoiceBox.getValue());
        accountant.setNameOfBank(nameOfBankTextField.getText());
        return accountant;
    }

}
