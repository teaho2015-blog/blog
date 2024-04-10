package net.teaho.blog.server.util.exception;

public class IncompatibleObjectException extends RuntimeException{
    public IncompatibleObjectException(String message) {
        super(message);
        System.err.println("The parsed in object is not compatible with this system");
    }

    public IncompatibleObjectException(String message, Object object) {
        this(message);
        System.err.println("The class name of the parsed in Object is : " + object.getClass().getName());
    }
}
