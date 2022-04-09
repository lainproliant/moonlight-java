package com.lainproliant.moonlight.automata;

import java.util.HashMap;
import java.util.Map;

public class LambdaStoreContext<K, C> implements LambdaContext<K, C> {
    private final Map<K, Lambda<K, C>> store;
    private final C context;

    public LambdaStoreContext(C context) {
        this.store = new HashMap<>();
        this.context = context;
    }

    public final Lambda<K, C> get(K key) {
        if (! store.containsKey(key)) {
            throw new AutomataException(
                    String.format("State is not defined for key: \"%s\".", key));
        }
        return store.get(key);
    }

    public final void put(final K key, final Lambda<K, C> lambda) {
        if (store.containsKey(key)) {
            throw new IllegalArgumentException(
                    String.format("State is already defined for key: \"%s\"", key));
        }
        store.put(key, lambda);
    }

    public final C context() {
        return this.context;
    }
}
