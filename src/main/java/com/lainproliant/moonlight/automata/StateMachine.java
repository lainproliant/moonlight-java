package com.lainproliant.moonlight.automata;

import java.util.Objects;
import java.util.Stack;

public class StateMachine<C> implements Control<C> {
    private final C context;
    private final Stack<State<C>> stateStack;

    public StateMachine(final C context, final State<C> initState) {
        this.context = context;
        this.stateStack = new Stack<>();
        push(initState);
    }

    public final boolean update() {
        Objects.requireNonNull(current()).run();
        return current() != null;
    }

    public final State<C> current() {
        if (stateStack.isEmpty()) {
            return null;
        }
        return stateStack.peek();
    }

    public final void run() {
        while (current() != null) {
            Objects.requireNonNull(current()).run();
        }
    }

    @Override
    public final void push(State<C> state) {
        final State<C> parent = current();
        stateStack.push(state);
        state.inject(this, parent);
        if (parent != null) {
            parent.exit();
        }
        state.setup();
        state.enter();
    }

    @Override
    public final void pop() {
        final var state = stateStack.pop();
        state.exit();
        state.cleanup();
    }

    @Override
    public final void transition(State<C> state) {
        pop();
        push(state);
    }

    @Override
    public void reset(State<C> state) {
        terminate();
        push(state);
    }

    @Override
    public final void terminate() {
        while (! stateStack.isEmpty()) {
            pop();
        }
    }

    @Override
    public final C context() {
       return this.context;
    }
}
