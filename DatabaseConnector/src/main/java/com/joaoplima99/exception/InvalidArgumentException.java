package com.joaoplima99.exception;

public class InvalidArgumentException extends Exception {

    private static String message = "\n The following argument is invalid: \n %s = '%s'";

    private static String formatMessageWithInvalidArgument(String argument, String name) {
        return String.format(message, argument, name);
    }

    public InvalidArgumentException(String argument, String name) {
        super(formatMessageWithInvalidArgument(argument, name));
    }
}
