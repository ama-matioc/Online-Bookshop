package org.example;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Locale;
import org.json.JSONObject;
public class InputDevice {
    private InputStream inputStream;
    private Scanner scanner;
    public InputDevice(InputStream inputStream)
    {
        scanner = new Scanner(inputStream);
        scanner.useLocale(Locale.US);
    }
    public int getIntInput ()
    {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            return 0;
        }
    }

    public String getStringInput() {
        try {
            return scanner.nextLine();
        } catch (InputMismatchException e)
        {
            return null;
        }
    }
    public float getFloatInput() {
        try {
            return scanner.nextFloat();
        } catch (InputMismatchException e) {
            scanner.nextLine();
            throw e;
        }
    }

    public static ArrayList<JSONObject> readJSONObjectFromFile(String filePath) {
        ArrayList<JSONObject> jsonObjectList = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            StringBuilder content = new StringBuilder();

            while ((line = bufferedReader.readLine()) != null) {
                content.append(line.trim());
                if (line.trim().endsWith("}")) {
                    jsonObjectList.add(new JSONObject(content.toString()));
                    content.setLength(0);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonObjectList;
    }
}
