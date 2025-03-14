package org.example;

public class Stack <T>{
    private T[] stack;
    private int top;
    private int maxSize;
    private Class<T> type;

    @SuppressWarnings("unchecked")
    public Stack(int size) {
        this.maxSize = size;
        this.stack = (T[]) new Object[size];
        this.top = -1;
    }

    public void push(T value) {
        if (top == maxSize - 1) {
            throw new StackOverflowError("Stack is full");
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
