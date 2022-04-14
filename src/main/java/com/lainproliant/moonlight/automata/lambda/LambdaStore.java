package com.lainproliant.moonlight.automata.lambda;

import com.lainproliant.moonlight.automata.State;

public interface LambdaStore<K, C> {
    State<C> get(K key);
}
