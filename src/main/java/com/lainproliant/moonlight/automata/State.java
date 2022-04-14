package com.lainproliant.moonlight.automata;

public abstract class State<C> {
    private Control<C> control;
    private State<C> parent;

    public abstract void run();

    /**
     * Called when the state is initially added to the state machine.
     * Should be used to setup initial resources for the state.
     */
    public void setup() { }

    /**
     * Called when the state is removed from the state machine.
     * Should be used to cleanup any resources allocated by the state.
     */
    public void cleanup() { }

    /**
     * Called when the state becomes the current state.
     *
     * This happens both when the state is first added to the state
     * machine after {@link #setup()}, and if the state is re-entered,
     * i.e. after a child state is {@link #pop()}ed from the state machine.
     */
    public void enter() { }

    /**
     * Called when the state is no longer the current state.
     *
     * This happens both when a child state is {@link #push(State)}ed
     * before this state in the state machine, and when this state
     * is removed from the state machine via {@link #pop()}, or
     * indirectly via {@link #transition(State)} or {@link #terminate()}.
     */
    public void exit() { }

    /**
     * Package-private.  Called by the state machine to inject
     * a controller and a reference to the parent, if any.
     */
    final void inject(Control<C> control, State<C> parent) {
        this.control = control;
        this.parent = parent;
    }

    /**
     * Used by a state to fetch its parent state.
     * @return The parent state, or null if there isn't one.
     */
    protected final State<C> parent() {
        return parent;
    }

    /**
     * Push a state above this one in the state machine.
     */
    protected final void push(final State<C> state) {
        control.push(state);
    }

    /**
     * Remove the current state from the state machine.
     */
    protected final void pop() {
        control.pop();
    }

    /**
     * Replace the current state in the state machine with a new one.
     */
    protected final void transition(final State<C> state) {
        control.transition(state);
    }

    /**
     * Removes all states from the state machine and adds the
     * given state as the only state.  Often used to reset
     * the state machine to an initial state.
     */
    protected final void reset(final State<C> state) {
        control.reset(state);
    }

    /**
     * Terminate the state machine by removing all states.
     */
    protected final void terminate() {
        control.terminate();
    }

    /**
     * Get the state machine's shared context object.
     */
    protected final C context() {
        return control.context();
    }

    /**
     * Determine if this state is the current state.
     * Useful when parent states are invoked by children.
     */
    protected final boolean isCurrent() {
        return control.current() == this;
    }
}
