package collections.immutable;

import collections.Queue;
import javafx.util.Pair;

public class ImmutableQueue<T> implements Queue<T> {

    // Two stacks approach which are flipped anytime an item is enqueued or removed
    private final ImmutableStack<T> arriving;
    private final ImmutableStack<T> leaving;

    private ImmutableQueue(final ImmutableStack<T> arriving, final ImmutableStack<T> leaving) {
        if (leaving.isEmpty() && !arriving.isEmpty()) {
            this.arriving = ImmutableStack.empty();
            this.leaving = arriving.reverse();
        } else {
            this.arriving = arriving;
            this.leaving = leaving;
        }
    }

    public static ImmutableQueue empty() {
        return new ImmutableQueue(ImmutableStack.empty(), ImmutableStack.empty());
    }

    @Override
    public Queue deQueue() {
        if(!leaving.isEmpty()) {
            return new ImmutableQueue(arriving, leaving.pop().getValue());
        }
        throw new IllegalStateException("DeQueue illegal for empty queue");
    }

    @Override
    public boolean isEmpty() {
        return arriving.isEmpty() && leaving.isEmpty();
    }

    @Override
    public Queue enQueue(T item) {
        return new ImmutableQueue(arriving.push(item), leaving);
    }

    @Override
    public T head() {
        if(!leaving.isEmpty()) {
            Pair<T, ImmutableStack<T>> peeked = leaving.peek();
            return peeked.getKey();
        }
        throw new IllegalStateException("Head illegal for empty queue");
    }
}
