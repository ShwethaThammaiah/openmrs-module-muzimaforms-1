package org.javarosa.xform.parse;

import java.util.List;

public class ValidationMessage {
    private final String message;
    private final Type type;

    public ValidationMessage(String message, Type type) {
        this.message = message;
        this.type = type;
    }

    public enum Type {
        WARNING, ERROR
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ValidationMessage that = (ValidationMessage) o;

        if (message != null ? !message.equals(that.message) : that.message != null) return false;
        if (type != that.type) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = message != null ? message.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ValidationMessage{" +
                "message='" + message + '\'' +
                ", type=" + type +
                '}';
    }
}
