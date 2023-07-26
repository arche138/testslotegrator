package org.example.helpers;

import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Slf4j
public class ReadJson {
    private final static JSONObject jsonTesting;
    private static JSONObject getJsonTestingObject() {
        return jsonTesting;
    }

    static {
        String path = "./src/main/resources/testConfig.json";
        try (BufferedReader fileReaderTesting =
                     new BufferedReader(new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8))){
            JSONParser parser = new JSONParser();
            jsonTesting = (JSONObject) parser.parse(fileReaderTesting);
        }
        catch (Exception ex) {
            log.info("incorrect filepath: "+ path);
            throw new RuntimeException(ex);
        }
    }

    public static String getTestingDataFromObject(String keyObject, String keyValue){
        JSONObject structure = (JSONObject) getJsonTestingObject().get(keyObject);
        return structure.get(keyValue).toString();
    }
}
