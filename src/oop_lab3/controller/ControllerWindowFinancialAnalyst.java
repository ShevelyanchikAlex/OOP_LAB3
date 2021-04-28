package oop_lab3.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.InputEvent;
import javafx.stage.Stage;
import oop_lab3.adapter.FinancialAnalystAdapter;
import oop_lab3.services.InfoAboutWorkService;
import oop_lab3.services.StateService;
import oop_lab3.entity.bank_workers.FinancialAnalyst;

public class ControllerWindowFinancialAnalyst implements EditController<FinancialAnalyst> {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private ChoiceBox<Boolean> isAvailabilityOfInternationalCertificateis;

    @FXML
    private TextArea resumeTextField;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnEdit;

    @FXML
    private TextField ageTextField;

    @FXML
    private TextField nameOfBankTextField;

    private final StateService state = StateService.getInstance();
    private final InfoAboutWorkService infoAboutWorkService = InfoAboutWorkService.getInstance();

    @FXML
    void initialize() {
        initChoiceBox();

        ageTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.matches("\\d*")) return;
            ageTextField.setText(newValue.replaceAll("[^\\d]", ""));
        });

        if (state.getCurrentAction().equals("edit")) {
            FinancialAnalyst financialAnalyst = (FinancialAnalyst) state.getWorker();
            firstNameTextField.setText(financialAnalyst.getFirstName());
            lastNameTextField.setText(financialAnalyst.getLastName());
            ageTextField.setText(String.valueOf(financialAnalyst.getAge()));
            resumeTextField.setText(financialAnalyst.getResume());
            isAvailabilityOfInternationalCertificateis.setValue(financialAnalyst.getIsAvailabilityOfInternationalCertificate());
            nameOfBankTextField.setText(financialAnalyst.getNameOfBank());
            btnAdd.setVisible(false);
        } else {
            btnEdit.setVisible(false);
        }
    }

    void initChoiceBox(){
        isAvailabilityOfInternationalCertificateis.getItems().add(Boolean.TRUE);
        isAvailabilityOfInternationalCertificateis.getItems().add(Boolean.FALSE);
        isAvailabilityOfInternationalCertificateis.setValue(Boolean.TRUE);
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
    public FinancialAnalyst getEntity() {
        FinancialAnalyst financialAnalyst = new FinancialAnalyst();
        financialAnalyst.setFirstName(firstNameTextField.getText());
        financialAnalyst.setLastName(lastNameTextField.getText());
        financialAnalyst.setAge(Integer.parseInt(ageTextField.getText()));
        financialAnalyst.setResume(resumeTextField.getText() + "+" + infoAboutWorkService.printAboutWork(new FinancialAnalystAdapter(financialAnalyst)));
        financialAnalyst.setAvailabilityOfInternationalCertificate(isAvailabilityOfInternationalCertificateis.getValue());
        financialAnalyst.setNameOfBank(nameOfBankTextField.getText());
        return financialAnalyst;
    }
}
