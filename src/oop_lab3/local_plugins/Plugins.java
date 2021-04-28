package oop_lab3.local_plugins;

import java.io.File;

public enum Plugins {
    ZIP_PLUGIN("zip_plugin.txt", false, false),
    ZIP2_PLUGIN("zip2_plugin.txt",false,false);

    private static final String PLUGINS_DIR = "plugins/";

    private final String nameFile;
    private Boolean isLoad;
    private Boolean isInclude;

    Plugins(String nameFile, Boolean isLoad, Boolean isInclude) {
        this.nameFile = nameFile;
        this.isLoad = isLoad;
        this.isInclude = isInclude;
    }

    public String getNameFile() {
        return nameFile;
    }

    public Boolean getLoad() {
        return isLoad;
    }

    public void setLoad(Boolean load) {
        isLoad = load;
    }

    public Boolean getInclude() {
        return isInclude;
    }

    public void setInclude(Boolean include) {
        isInclude = include;
    }

    public static Boolean isExistsPlugin(Plugins plugin) {
        if (new File(PLUGINS_DIR + plugin.getNameFile()).exists()) {
            plugin.setLoad(true);
            return true;
        } else {
            plugin.setLoad(false);
        }
        return false;
    }

}
