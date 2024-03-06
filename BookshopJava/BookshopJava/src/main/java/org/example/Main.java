package org.example;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class Main {
    public static void main(String[] args) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("file.txt", false);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            FileInputStream fileInputStream = new FileInputStream("input.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        InputDevice inputDevice = new InputDevice(System.in);
        OutputDevice outputDevice = new OutputDevice(System.out);

        Application application = new Application(inputDevice, outputDevice);

        //outputDevice.display(args[0]);
        switch(args[0]) {
            case "user":
                application.run(false);
                break;
            case "admin":
                application.run(true);
                break;
        }
    }
}