package org.example;

public class InvalidBookOperationException extends Exception {
    public InvalidBookOperationException() {
        super("Invalid operation performed on the book!");
    }

    public InvalidBookOperationException(String message) {
        super(message);
    }
}
