package com.lainproliant.moonlight.automata;

public class LambdaState<K, C> extends State<LambdaContext<K, C>> {
    private final Lambda<K, C> lambda;
    private final LambdaControl<K, C> control;

    public LambdaState(Lambda<K, C> lambda) {
        final State<LambdaContext<K, C>> self = this;
        this.lambda = lambda;
        control = new LambdaControl<K, C>() {
            @Override
            public void push(K key) {
                self.push(makeState(key));
            }

            @Override
            public void pop() {
                self.pop();
            }

            @Override
            public void transition(K key) {
                self.transition(makeState(key));
            }

            @Override
            public void reset(K key) {
                self.reset(makeState(key));
            }

            @Override
            public void terminate() {
                self.terminate();
            }

            @Override
            public State<LambdaContext<K, C>> parent() {
                return self.parent();
            }

            @Override
            public boolean isCurrent() {
                return self.isCurrent();
            }

            @Override
            public C context() {
                return self.context().context();
            }
        };
    }

    @Override
    public void run() {
        lambda.run(control);
    }

    private LambdaState<K, C> makeState(final K key) {
        return new LambdaState<>(context().get(key));
    }
}
