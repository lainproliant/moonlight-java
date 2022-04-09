package com.lainproliant.moonlight.automata;

public interface LambdaControl<K, C> {
    void push(K key);
    void pop();
    void transition(K key);
    void reset(K key);
    void terminate();
    State<LambdaContext<K, C>> parent();
    boolean isCurrent();
    C context();
}
