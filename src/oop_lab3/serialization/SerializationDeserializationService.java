package oop_lab3.serialization;

import javafx.collections.ObservableList;
import oop_lab3.entity.Worker;

import java.io.File;
import java.io.FileNotFoundException;

public interface SerializationDeserializationService {

    void serialize(ObservableList<Worker> workers, File file);

    ObservableList<Worker> deserialize(File file) throws FileNotFoundException;
}
