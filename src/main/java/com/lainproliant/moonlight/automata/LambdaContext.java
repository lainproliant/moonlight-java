package com.lainproliant.moonlight.automata;

public interface LambdaContext<K, C> {
    Lambda<K, C> get(K key);
    C context();
}
