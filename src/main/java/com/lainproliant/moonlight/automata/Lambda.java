package com.lainproliant.moonlight.automata;

public interface Lambda<K, C> {
    void run(LambdaControl<K, C> control);
}
