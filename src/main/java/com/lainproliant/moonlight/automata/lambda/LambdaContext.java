package com.lainproliant.moonlight.automata.lambda;

public interface LambdaContext<K, C> {
    Lambda<K, C> get(K key);
    C context();
}
