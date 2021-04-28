package oop_lab3.services;

import oop_lab3.local_plugins.Plugins;

import java.io.File;
import java.net.URI;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;


public class ZIP2Service {
    private static ZIP2Service instance;

    public static ZIP2Service getInstance() {
        if (instance == null) {
            instance = new ZIP2Service();
        }
        return instance;
    }

    public void convertZip(File file) {
        if (Plugins.isExistsPlugin(Plugins.ZIP2_PLUGIN) && Plugins.ZIP2_PLUGIN.getInclude()) {
            String zipFileName = file.getName().substring(0, file.getName().indexOf(".")) + "1" + ".zip";
            try {
                Map<String, String> env = new HashMap<>();

                env.put("create", "true");

                File outputFile = new File(System.getProperty("user.home"), "4sem/OOP/OOP_LAB3/zip/"+zipFileName);
                URI uri = URI.create("jar:file:" + outputFile.getAbsolutePath());

                try (FileSystem zipfs = FileSystems.newFileSystem(uri, env)) {
                    Path pathInZipfile = zipfs.getPath(file.getName());

                    Files.copy(file.toPath(), pathInZipfile, StandardCopyOption.REPLACE_EXISTING);
                }
            } catch (Exception e) {
                System.out.println("error: " + e);
            }
        } else if (Plugins.ZIP_PLUGIN.getInclude()) {
            AppService.getInstance().showErrorMessage("File will be saved without ZIP2 plugin!");
        }
    }

}
