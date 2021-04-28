package oop_lab3.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.InputEvent;
import javafx.stage.Stage;
import oop_lab3.adapter.DeveloperAdapter;
import oop_lab3.services.InfoAboutWorkService;
import oop_lab3.services.StateService;
import oop_lab3.entity.Worker;
import oop_lab3.entity.it_workers.Developer;
import oop_lab3.entity.it_workers.Tester;


public class ControllerWindowDeveloper implements EditController<Developer> {

    @FXML
    private ChoiceBox<Developer.ProgramLanguage> choiceBox;

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

    @FXML
    private ChoiceBox<Tester> testerChoiceBox;

    private final StateService state = StateService.getInstance();
    private final InfoAboutWorkService infoAboutWorkService = InfoAboutWorkService.getInstance();


    @FXML
    void initialize() {
        initChoiceBox();
        initTesterChoiceBox();

        inputAge.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.matches("\\d*")) return;
            inputAge.setText(newValue.replaceAll("[^\\d]", ""));
        });

        if (state.getCurrentAction().equals("edit")) {
            Developer developer = (Developer) state.getWorker();
            inputFirstname.setText(developer.getFirstName());
            inputLastname.setText(developer.getLastName());
            inputAge.setText(String.valueOf(developer.getAge()));
            inputNameOfCompany.setText(developer.getNameOfItCompany());
            inputResume.setText(developer.getResume());
            choiceBox.setValue(developer.getProgramLanguage());
            testerChoiceBox.setValue(developer.getTester());
            btnAdd.setVisible(false);
        } else {
            btnEdit.setVisible(true);
        }
    }

    void initChoiceBox(){
        choiceBox.getItems().add(Developer.ProgramLanguage.JAVA);
        choiceBox.getItems().add(Developer.ProgramLanguage.DART);
        choiceBox.getItems().add(Developer.ProgramLanguage.KOTLIN);
        choiceBox.getItems().add(Developer.ProgramLanguage.JS);
        choiceBox.getItems().add(Developer.ProgramLanguage.CPP);
        choiceBox.getItems().add(Developer.ProgramLanguage.PYTHON);
        choiceBox.getItems().add(Developer.ProgramLanguage.PHP);
        choiceBox.setValue(Developer.ProgramLanguage.JAVA);
    }

    void initTesterChoiceBox(){
        Tester defaultTester = new Tester("Tester", "Tester", 20, "resume", "company", Tester.TypeOfTester.AUTOMATION_TESTER);
        testerChoiceBox.getItems().add(defaultTester);
        testerChoiceBox.setValue(defaultTester);
        for (Worker worker: state.getObservableList()) {
            if(worker instanceof Tester) {
                testerChoiceBox.getItems().add((Tester) worker);
            }
        }
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
    public Developer getEntity() {
        Developer developer = new Developer();
        developer.setFirstName(inputFirstname.getText());
        developer.setLastName(inputLastname.getText());
        developer.setAge(Integer.parseInt(inputAge.getText()));
        developer.setNameOfItCompany(inputNameOfCompany.getText());
        developer.setProgramLanguage(choiceBox.getValue());
        developer.setResume(inputResume.getText() + "+" + infoAboutWorkService.printAboutWork(new DeveloperAdapter(developer)));
        developer.setTester(testerChoiceBox.getValue());
        return developer;
    }


}
