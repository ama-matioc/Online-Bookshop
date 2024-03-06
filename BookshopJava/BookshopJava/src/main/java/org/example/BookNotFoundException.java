package org.example;

public class BookNotFoundException extends Exception {
    public BookNotFoundException() {
        super("Book not found in the library!");
    }

    public BookNotFoundException(String message) {
        super(message);
    }
}
