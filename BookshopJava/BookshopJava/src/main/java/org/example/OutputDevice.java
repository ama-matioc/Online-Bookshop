package org.example;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import org.json.JSONObject;
public class OutputDevice {

    private OutputStream outputStream;

    public OutputDevice(OutputStream outputStream) {
        this.outputStream = outputStream;
    }
    public <T> void display(T message) {
        try {
            outputStream.write(message.toString().getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void writeJsonObjectsToFile(ArrayList<JSONObject> jsonObjects, String filePath) {
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            for (JSONObject jsonObject : jsonObjects) {
                fileWriter.write(jsonObject.toString(4));
                fileWriter.write(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
