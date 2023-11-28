package com.joaoplima99.exception;

public class PropertyNotFoundException extends Exception {

    private static String message = "Property '%s' does not exist.";

    private static String formatWithNotFoundPropertyName(String propertyName) {
        return String.format(message, propertyName);
    }

    public PropertyNotFoundException(String propertyName) {
        super(formatWithNotFoundPropertyName(propertyName));
    }
}
