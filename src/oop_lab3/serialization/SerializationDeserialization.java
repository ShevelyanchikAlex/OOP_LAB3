package oop_lab3.serialization;

import javafx.collections.ObservableList;
import oop_lab3.entity.Worker;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public abstract class SerializationDeserialization {

//    static final String SERIALIZATION_FILE_PATH = "serialization.";

    public abstract void serialize(ObservableList<Worker> workers, File file);

    public abstract ObservableList<Worker> deserialize(File file) throws FileNotFoundException;
}
