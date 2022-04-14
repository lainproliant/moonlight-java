package com.lainproliant.moonlight.automata.lambda;

import com.lainproliant.moonlight.automata.State;

public class LambdaState<K, C> extends State<LambdaContext<K, C>> {
    private final Lambda<K, C> lambda;
    private final LambdaControl<K, C> control;

    public LambdaState(Lambda<K, C> lambda) {
        final State<LambdaContext<K, C>> self = this;
        this.lambda = lambda;
        this.control = new Controller();
    }

    @Override
    public void run() {
        lambda.run(control);
    }

    private LambdaState<K, C> makeState(final K key) {
        return new LambdaState<>(context().get(key));
    }

    private class Controller implements LambdaControl<K, C> {
        @Override
        public void push(K key) {
            LambdaState.this.push(makeState(key));
        }

        @Override
        public void pop() {
            LambdaState.this.pop();
        }

        @Override
        public void transition(K key) {
            LambdaState.this.transition(makeState(key));
        }

        @Override
        public void reset(K key) {
            LambdaState.this.reset(makeState(key));
        }

        @Override
        public void terminate() {
            LambdaState.this.terminate();
        }

        @Override
        public State<LambdaContext<K, C>> parent() {
            return LambdaState.this.parent();
        }

        @Override
        public boolean isCurrent() {
            return LambdaState.this.isCurrent();
        }

        @Override
        public C context() {
            return LambdaState.this.context().context();
        }
    }

}
