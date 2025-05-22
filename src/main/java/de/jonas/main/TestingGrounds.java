package de.jonas.main;

import de.jonas.engine.utils.Console;
import de.jonas.engine.utils.JSONUtils;
import de.jonas.engine.data.UserData;
import org.json.simple.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestingGrounds {

    public static void main(String[] args) throws IOException {
        UserData.loadData();
        JSONObject jsonObject = JSONUtils.getJSONFile("data/settings.json");


    }
    public static void priato(String x) {
        System.out.println("YOU JUST TESTED THIS METHODSASD" + x);
    }

}
