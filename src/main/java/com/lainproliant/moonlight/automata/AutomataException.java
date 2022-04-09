package com.lainproliant.moonlight.automata;

public class AutomataException extends RuntimeException {
    public AutomataException(String message) {
        super(message);
    }

    public AutomataException(String message, Throwable cause) {
        super(message, cause);
    }

    public AutomataException(Throwable cause) {
        super(cause);
    }
}
