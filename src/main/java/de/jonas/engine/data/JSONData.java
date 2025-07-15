package de.jonas.engine.data;

import com.google.gson.Gson;
import de.jonas.engine.utils.Console;
import de.jonas.engine.data.dataClasses.SettingsData;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class JSONData {
    public SettingsData settings;

    public JSONData() {
        loadData();
    }

    private void loadData() {
        Gson gson = new Gson();

        FileReader reader = null;
        try {
            reader = new FileReader(StaticData.SETTINGS_FILE_PATH);
        } catch (FileNotFoundException e) {
            Console.printFatal("Could not find the settings file!", StaticData.SETTINGS_FILE_PATH);
        }

        if (reader != null) {
            settings = gson.fromJson(reader, SettingsData.class);
        }
    }
}
