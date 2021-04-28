package oop_lab3.serialization;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import oop_lab3.services.StateService;
import oop_lab3.entity.Worker;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;

public class XMLSerializationDeserialization implements SerializationDeserializationService {

    @Override
    public void serialize(ObservableList<Worker> workers, File file) {
        try (XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(
                new FileOutputStream(file)));
        ) {
            for (Worker worker:
                    StateService.getInstance().getObservableList()) {
                encoder.writeObject(worker);
            }
        } catch (Exception ignored) {
        }
    }

    @Override
    public ObservableList<Worker> deserialize(File file) throws FileNotFoundException {
        ObservableList<Worker> workers = FXCollections.observableArrayList();
        try (XMLDecoder decoder = new XMLDecoder(new FileInputStream(file))
        ) {
            Worker worker;
            while ((worker = (Worker) decoder.readObject()) != null){
                workers.add(worker);
            }
        } catch (Exception ignored) {
        }
        return workers;
    }
}
