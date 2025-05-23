package de.jonas.main;

import de.jonas.engine.utils.Console;
import de.jonas.engine.utils.FileUtils;
import de.jonas.engine.utils.JSONUtils;
import de.jonas.engine.data.UserData;
import de.jonas.engine.utils.ObjectUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class TestingGrounds {

    public static void main(String[] args) throws IOException {
        UserData.loadData();


    }



}
