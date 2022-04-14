package com.lainproliant.moonlight.automata.lambda;

public interface Lambda<K, C> {
    void run(LambdaControl<K, C> control);
}
