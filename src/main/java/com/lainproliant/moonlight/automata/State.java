package com.lainproliant.moonlight.automata;

public abstract class State<C> {
    private Control<C> control;
    private State<C> parent;

    public abstract void run();

    public void cleanup() { }
    public void init() { }

    final void inject(Control<C> control, State<C> parent) {
        this.control = control;
        this.parent = parent;
    }

    protected final State<C> parent() {
        return parent;
    }

    protected final void push(final State<C> state) {
        control.push(state);
    }

    protected final void pop() {
        control.pop();
    }

    protected final void transition(final State<C> state) {
        control.transition(state);
    }

    protected final void reset(final State<C> state) {
        control.reset(state);
    }

    protected final void terminate() {
        control.terminate();
    }

    protected final C context() {
        return control.context();
    }

    protected final boolean isCurrent() {
        return control.current() == this;
    }
}
