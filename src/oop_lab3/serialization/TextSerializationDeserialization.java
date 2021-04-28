package oop_lab3.serialization;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import oop_lab3.entity.Worker;
import oop_lab3.entity.WorkerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class TextSerializationDeserialization extends SerializationDeserialization {

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
            String typeOfWorker;
            while ((typeOfWorker = bufferreader.readLine()) != null) {
                workers.add(WorkerFactory.workerFactory(typeOfWorker.substring(0, typeOfWorker.indexOf('-'))).fromString(typeOfWorker));
            }
        } catch (Exception ignored) {
            System.out.println(ignored);
        }
        return workers;
    }
}
