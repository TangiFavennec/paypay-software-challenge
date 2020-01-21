package collections.immutable;

import collections.Queue;
import org.junit.jupiter.api.Test;

import static collections.immutable.ImmutableQueue.empty;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ImmutableQueueTest {

    @Test()
    public void deQueueBaseTest() {
        assertTrue(empty().enQueue(1).deQueue().isEmpty());
    }

    @Test()
    public void deQueueImmutableTest() {
        Queue<Integer> queue = empty().enQueue(3);

        assertFalse(queue.isEmpty());
        assertEquals(3, queue.head());

        queue.deQueue();

        assertFalse(queue.isEmpty());
        assertEquals(3, queue.head());
    }

    @Test()
    public void deQueueOnEmptyQueueIllegalTest() {
        assertThrows(IllegalStateException.class, () -> empty().deQueue());
    }

    @Test()
    public void deQueueQueueTest() {
        Queue<Integer> queue = empty().enQueue(1)
                .enQueue(2);
        assertFalse(queue.isEmpty());
        assertEquals(1, queue.head());

        queue = queue.deQueue();
        queue = queue.enQueue(3);

        assertEquals(2, queue.head());

        queue = queue.deQueue();
        queue = queue.deQueue();

        assertTrue(queue.isEmpty());
    }

    @Test
    public void emptyQueueTest() {
        assertTrue(empty().isEmpty());
    }

    @Test()
    public void enQueueBaseTest() {
        Queue<Integer> queue = empty().enQueue(1);
        assertFalse(queue.isEmpty());
        assertEquals(1, queue.head());
    }

    @Test()
    public void enQueueImmutableTest() {
        Queue<Integer> queue = empty().enQueue(1);

        assertEquals(queue.head(), 1);

        queue.enQueue(2);

        assertEquals(1, queue.head());
    }

    @Test()
    public void headOnEmptyQueueIllegalTest() {
        assertThrows(IllegalStateException.class, () -> empty().head());
    }

    @Test
    public void headTakesFirstPutInQueueTest() {
        assertEquals(0, empty().enQueue(0).enQueue(2).head());
    }

    @Test()
    public void notEmptyQueueTest() {
        assertFalse(empty().enQueue(1).isEmpty());
    }
}
