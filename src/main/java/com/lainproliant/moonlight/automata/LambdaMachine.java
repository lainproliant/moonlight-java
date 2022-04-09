package com.lainproliant.moonlight.automata;

public class LambdaMachine<K, C> extends StateMachine<LambdaContext<K, C>> {
    public LambdaMachine(final K initState) {
        this(null, initState);
    }

    public LambdaMachine(final C context, final K initState) {
        super(new LambdaStoreContext<>(context),
                new LambdaState<>(control -> control.transition(initState)));
    }

    public LambdaMachine<K, C> def(final K key, final Lambda<K, C> lambda) {
        ((LambdaStoreContext<K, C>) context()).put(key, lambda);
        return this;
    }
}
