package oop_lab3.services;

import oop_lab3.local_plugins.Plugins;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipService {

    private static ZipService instance;
    private static final String ZIP_DIR = "zip/";

    public static ZipService getInstance() {
        if (instance == null) {
            instance = new ZipService();
        }
        return instance;
    }

    public void convertToZip(File file) {
        if (Plugins.isExistsPlugin(Plugins.ZIP_PLUGIN) && Plugins.ZIP_PLUGIN.getInclude()) {
            String zipFileName = file.getName().substring(0, file.getName().indexOf(".")) + ".zip";
            try (
                    ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(ZIP_DIR + zipFileName));
                    FileInputStream fileInputStream = new FileInputStream(file);
            ) {
                ZipEntry entry = new ZipEntry(file.getName());
                zipOutputStream.putNextEntry(entry);
                byte[] buffer = new byte[fileInputStream.available()];

                fileInputStream.read(buffer);
                zipOutputStream.write(buffer);
                zipOutputStream.closeEntry();
            } catch (Exception e) {
                System.out.println("error: " + e);
            }
        } else if (Plugins.ZIP_PLUGIN.getInclude()) {
            AppService.getInstance().showErrorMessage("File will be saved without ZIP plugin!");
        }
    }
}
