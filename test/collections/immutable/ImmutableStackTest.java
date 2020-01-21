package collections.immutable;

import javafx.util.Pair;
import org.junit.jupiter.api.Test;

import static collections.immutable.ImmutableStack.empty;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ImmutableStackTest {

    @Test
    public void emptyStackTest() {
        assertTrue(empty().isEmpty());
    }

    @Test()
    public void notEmptyStackTest() {
        assertFalse(empty().push(1).isEmpty());
    }

    @Test
    public void peekTakesLastPushInStackTest() {
        assertEquals(2, empty().push(0).push(2).peek().getKey());
    }

    @Test()
    public void peekOnEmptyStackIllegalTest() {
        assertThrows(IllegalStateException.class, () -> empty().peek());
    }

    @Test()
    public void popOnEmptyStackIllegalTest() {
        assertThrows(IllegalStateException.class, () -> ImmutableStack.empty().pop());
    }

    @Test()
    public void popBaseTest() {
        Pair<Integer, ImmutableStack<Integer>> popped = empty().push(1).pop();
        assertTrue(popped.getValue().isEmpty());
    }

    @Test()
    public void pushBaseTest() {
        ImmutableStack<Integer> stack = empty().push(1);
        assertFalse(stack.isEmpty());
        assertEquals(1, stack.peek().getKey());
    }


    @Test()
    public void popImmutableTest() {
        ImmutableStack<Integer> stack = empty().push(3);

        assertFalse(stack.isEmpty());
        assertEquals(3, stack.peek().getKey());

        stack.pop();

        assertFalse(stack.isEmpty());
        assertEquals(3, stack.peek().getKey());
    }

    @Test()
    public void pushImmutableTest() {
        ImmutableStack<Integer> stack = empty().push(1);

        assertEquals(1, stack.peek().getKey());

        stack.push(2);

        assertEquals(1, stack.peek().getKey());
    }

    @Test()
    public void popStackTest() {
        ImmutableStack<Integer> stack = empty().push(1).push(2);

        assertFalse(stack.isEmpty());
        assertEquals(2, stack.peek().getKey());

        stack = stack.pop().getValue();
        stack = stack.push(4);

        assertEquals(4, stack.peek().getKey());

        stack = stack.pop().getValue();
        stack = stack.pop().getValue();

        assertTrue(stack.isEmpty());
    }
}
