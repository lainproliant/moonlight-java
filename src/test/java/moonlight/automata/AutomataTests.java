package moonlight.automata;

import com.lainproliant.moonlight.automata.State;
import com.lainproliant.moonlight.automata.StateMachine;
import com.lainproliant.moonlight.automata.lambda.LambdaMachine;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AutomataTests {
    @Test
    void basicStateTransitionTest() {
        class CountingState extends State<List<Integer>> {
            private final int n;
            private int x;

            public CountingState(int n, int x) {
                this.n = n;
                this.x = x;
            }

            @Override
            public void run() {
                context().add(x += n);
                if (x >= 100000000) {
                    terminate();
                } else if (x >= (2 * n)) {
                    transition(new CountingState(n * 2, x));
                }
            }
        }

        final List<Integer> list = new ArrayList<>();
        new StateMachine<>(list, new CountingState(1, 0)).run();
        assertEquals(28, list.size());
    }

    @Test
    void stateMachineParentStateTest() {
        class Context {
            public int x = 0;
            public int y = 0;
        }

        class YState extends State<Context> {
            @Override
            public void run() {
                parent().run();
                context().y ++;

                if (context().y >= 10) {
                    terminate();
                }
            }
        }

        class XState extends State<Context> {
            @Override
            public void run() {
                context().x ++;

                if (isCurrent() && context().x >= 10) {
                    push(new YState());
                }
            }
        }

        var context = new Context();
        new StateMachine<>(context, new XState()).run();
        assertEquals(20, context.x);
        assertEquals(10, context.y);
    }

    @Test
    void lambdaStateMachineTest() {
        class Context {
            public int x = 0;
            public int y = 0;
        }

        var context = new Context();
        new LambdaMachine<>(context, "init")
                .def("init", c -> {
                    c.context().x ++;
                    if (c.isCurrent()) {
                        c.push("alpha");
                    }
                })
                .def("alpha", c -> {
                    c.parent().run();
                    c.context().y ++;
                    if (c.isCurrent()) {
                        c.transition("beta");
                    }
                })
                .def("beta", c -> {
                    c.parent().run();
                    c.context().x ++;
                    c.terminate();
                }).run();

        assertEquals(4, context.x);
        assertEquals(1, context.y);
    }

    @Test
    void stateInitAndCleanup() {
        class Context {
            public int initCalled = 0;
            public int runCalled = 0;
            public int cleanupCalled = 0;
        }

        class SampleState extends State<Context> {
            @Override
            public void setup() {
                context().initCalled ++;
            }

            @Override
            public void cleanup() {
                context().cleanupCalled ++;
            }

            @Override
            public void run() {
                context().runCalled ++;
                if (context().runCalled == 1) {
                    push(new SampleState());
                } else {
                    pop();
                }
            }
        }

        var context = new Context();
        new StateMachine<>(context, new SampleState()).run();
        assertEquals(2, context.initCalled);
        assertEquals(3, context.runCalled);
        assertEquals(2, context.cleanupCalled);
    }
}
