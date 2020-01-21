package collections.immutable;

import javafx.util.Pair;
import lombok.Value;

/*
* Immutable Stack :
* Chained items allows to save memory and preserving immutability
* */
public class ImmutableStack<T> {

    @Value
    private static final class StackItem<T> {
        T val;
        StackItem<T> next;
    }

    private final StackItem<T> currentItem;

    private ImmutableStack(final StackItem<T> newItem){
        this.currentItem = newItem;
    }

    public static ImmutableStack empty() {
        return new ImmutableStack<>(null);
    }

    public boolean isEmpty() {
        return currentItem == null;
    }

    public Pair<T, ImmutableStack<T>> peek() {
        if(!isEmpty()) {
            return new Pair(currentItem.getVal(), this);
        }
        throw new IllegalStateException("Peek illegal for empty stack");
    }

    public Pair<T, ImmutableStack<T>> pop() {
        if(!isEmpty()) {
            return new Pair(currentItem.getVal(), new ImmutableStack<>(currentItem.getNext()));
        }
        throw new IllegalStateException("Pop illegal for empty stack");
    }

    public ImmutableStack<T> push(final T input) {
        return new ImmutableStack<T>(new StackItem(input, currentItem));
    }

    public ImmutableStack<T> reverse(){
        ImmutableStack<T> newStack = empty();
        ImmutableStack<T> currentCopied = this;
        while(!currentCopied.isEmpty()) {
            Pair<T, ImmutableStack<T>> popped = currentCopied.pop();
            newStack = newStack.push(popped.getKey());
            currentCopied = popped.getValue();
        }
        return newStack;
    }
}
