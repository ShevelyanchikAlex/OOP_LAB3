package oop_lab3.serialization;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import oop_lab3.services.StateService;
import oop_lab3.entity.Worker;

import java.io.*;

public class BinarySerializationDeserialization implements SerializationDeserializationService {

    @Override
    public void serialize(ObservableList<Worker> workers, File file) {
        try (ObjectOutputStream encoder = new ObjectOutputStream(new BufferedOutputStream(
                new FileOutputStream(file)));
        ) {
            for (Worker worker :
                    StateService.getInstance().getObservableList()) {
                encoder.writeObject(worker);
            }
        } catch (Exception ignored) {
        }
    }


    @Override
    public ObservableList<Worker> deserialize(File file) throws FileNotFoundException {
        ObservableList<Worker> workers = FXCollections.observableArrayList();
        try (ObjectInputStream decoder = new ObjectInputStream(new FileInputStream(file))
        ) {
            Worker worker;
            while ((worker = (Worker) decoder.readObject()) != null) {
                workers.add(worker);
            }
        } catch (Exception ignored) {
        }
        return workers;
    }
}
