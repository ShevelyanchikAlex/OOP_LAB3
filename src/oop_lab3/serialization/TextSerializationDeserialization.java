package oop_lab3.serialization;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import oop_lab3.entity.Worker;
import oop_lab3.entity.WorkerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class TextSerializationDeserialization implements SerializationDeserializationService {

    @Override
    public void serialize(ObservableList<Worker> workers, File file) {
        try (FileWriter fileWriter = new FileWriter(file)) {
            for (Worker worker : workers) {
                fileWriter.write(worker.toString());
            }
        } catch (Exception ignored) {
        }
    }

    @Override
    public ObservableList<Worker> deserialize(File file) {
        ObservableList<Worker> workers = FXCollections.observableArrayList();
        try (BufferedReader bufferreader = new BufferedReader(new FileReader(file))) {
            String worker;
            while ((worker = bufferreader.readLine()) != null) {
                workers.add(WorkerFactory.workerFactory(worker.substring(0, worker.indexOf('-'))).fromString(worker));
            }
        } catch (Exception ignored) {
        }
        return workers;
    }
}
