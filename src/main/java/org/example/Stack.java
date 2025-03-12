package org.example;

public class Stack <T>{
    private T[] stack;
    private int top;
    private int maxSize;
    private Class<T> type;

    public Stack(int size, Class<T> classType) {
        this.maxSize = size;
        try {
            //noinspection unchecked
            this.stack = (T[]) java.lang.reflect.Array.newInstance(classType, size);
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("Cannot create array of generic type T", e);
        }
        this.top = -1;
        this.type = classType;
    }

    public void push(T value) {
        if (top == maxSize - 1) {
            throw new StackOverflowError("Stack is full");
        }
        // Check if value type matches expected type, else throw exception
        if (!type.isInstance(value)) {
            throw new IllegalArgumentException("Invalid type: " + value.getClass().getName());
        }
        stack[++top] = value;
    }

    public T pop() {
        if (top == -1) {
            throw new IllegalStateException("Stack is empty");
        }
        return stack[top--];
    }

    public T peek() {
        if (top == -1) {
            throw new IllegalStateException("Stack is empty");
        }
        return stack[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == maxSize - 1;
    }

    public int size() {
        return top + 1;
    }
}
