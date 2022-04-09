package com.lainproliant.moonlight.automata;

public interface LambdaStore<K, C> {
    State<C> get(K key);
}
