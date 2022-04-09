package com.lainproliant.moonlight.automata;

public interface Control<C> {
    void push(State<C> state);
    void pop();
    void transition(State<C> state);
    void reset(State<C> state);
    void terminate();
    State<C> current();
    C context();
}
