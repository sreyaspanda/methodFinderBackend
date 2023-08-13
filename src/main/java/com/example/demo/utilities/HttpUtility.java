package com.example.demo.utilities;

import java.io.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpUtility {

    private static final String JSON_FILE_PATH = "methodHits.json";
    private static boolean isNewRunFlag = true;

    private static Logger logger = LoggerFactory.getLogger(HttpUtility.class);

    public static void checkNewRunFlag(boolean flag) {
        isNewRunFlag = flag;
    }

//    public static void addEntryToJson(String className, String methodName) throws IOException, ParseException {
//        File jsonFile = new File("methodResults/" + JSON_FILE_PATH);
//        // Create a new JSON file if it doesn't exist
//        if (!jsonFile.exists()) {
//            jsonFile.createNewFile();
//        }
//
//        // Read the existing JSON file into a JSONArray
//        FileReader fileReader = new FileReader(jsonFile);
//        BufferedReader bufferedReader = new BufferedReader(fileReader);
//        String jsonData = bufferedReader.readLine();
//        JSONArray jsonArray = new JSONArray();
//        JSONParser parser = new JSONParser();
//
//        if (jsonData != null) {
//            jsonArray = (JSONArray) parser.parse(jsonData);
//        }
//
//        logger.info(jsonArray.toJSONString());
//
//        if (isNewRunFlag) {
//            jsonArray.clear();
//            isNewRunFlag = false;
//        }
//
//        logger.info(jsonArray.toJSONString());
//
//        // Create a new object with the class name and method name
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put(className, methodName);
//        jsonArray.add(jsonObject);
//
//        // Write the JSONArray to the JSON file
//        FileWriter fileWriter = new FileWriter(jsonFile);
//        fileWriter.write(jsonArray.toJSONString());
//        fileWriter.flush();
//        fileWriter.close();
//    }

    public static void addEntryToJson(String className, String methodName) throws IOException, ParseException {
        File jsonFile = new File("methodResults/" + JSON_FILE_PATH);
        // Create a new JSON file if it doesn't exist
        if (!jsonFile.exists()) {
            jsonFile.createNewFile();
        }

        // Read the existing JSON file into a JSONObject
        JSONObject jsonObject = new JSONObject();
        if (jsonFile.length() > 0) {
            try (FileReader fileReader = new FileReader(jsonFile);
                 BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                JSONParser parser = new JSONParser();
                String jsonData = bufferedReader.readLine();
                if (jsonData != null) {
                    jsonObject = (JSONObject) parser.parse(jsonData);
                }
            }
        }

        if (isNewRunFlag) {
            jsonObject.clear();
            isNewRunFlag = false;
        }

        // Check if the class name already exists in the JSONObject
        if (jsonObject.containsKey(className)) {
            // Get the existing list of method names and add the new one
            JSONArray methodNames = (JSONArray) jsonObject.get(className);
            methodNames.add(methodName);
        } else {
            // Create a new JSONArray for the class name
            JSONArray methodNames = new JSONArray();
            methodNames.add(methodName);
            jsonObject.put(className, methodNames);
        }

        // Write the JSONObject to the JSON file
        try (FileWriter fileWriter = new FileWriter(jsonFile)) {
            fileWriter.write(jsonObject.toJSONString());
            fileWriter.flush();
        }
    }


    public static JSONObject readFile() throws ParseException {
        File jsonFile = new File("methodResults/" + JSON_FILE_PATH);
        JSONParser parser = new JSONParser();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(jsonFile))) {
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line);
            }
            return (JSONObject) parser.parse(content.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
