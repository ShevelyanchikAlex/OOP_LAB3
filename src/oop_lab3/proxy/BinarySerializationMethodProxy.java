package oop_lab3.proxy;

import javafx.collections.ObservableList;
import oop_lab3.entity.Worker;
import oop_lab3.serialization.BinarySerializationDeserialization;
import oop_lab3.serialization.SerializationDeserializationService;
import oop_lab3.services.ZIP2Service;
import oop_lab3.services.ZipService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class BinarySerializationMethodProxy implements SerializationDeserializationService {

    private final SerializationDeserializationService serializationService = new BinarySerializationDeserialization();

    @Override
    public void serialize(ObservableList<Worker> workers, File file) {
        serializationService.serialize(workers, file);
        ZipService.getInstance().convertToZip(file);
        ZIP2Service.getInstance().convertZip(file);
    }

    //will be implemented in the future
    @Override
    public ObservableList<Worker> deserialize(File file) throws FileNotFoundException {
        return null;
    }
}
