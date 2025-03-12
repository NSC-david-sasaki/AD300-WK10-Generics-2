package org.example;

import com.sun.jdi.InvalidTypeException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StackTest {
    private Stack stack;
    @BeforeEach
    void setUp() {
        stack = new Stack<Pair>(1000, Pair.class);
    }

    @AfterEach
    void tearDown() {
        stack = null;
        System.gc();
    }

    @Test
    void push() {
        stack.push(new Pair<>(0,"foo"));
        assertEquals("Pair{k=0, v=foo}", stack.peek().toString()); // also checks peek
    }

    @Test
    void pushIllegalArgTest(){
        stack.push(new Pair<>(0,"foo"));
        assertThrows(IllegalArgumentException.class, () -> stack.push("bar"));
    }

    @Test
    void pushOverflowTest() {
        int STACK_SIZE = 1000;
        int i = 0;
        while (i < STACK_SIZE) {
            stack.push(new Pair<>(i++,"foo"));
        }
        // noinspection unchecked
        assertThrows(StackOverflowError.class, () -> stack.push(new Pair<>(1, "bar")));
    }

    @Test
    void pop() {
        stack.push(new Pair<>(0,"foo"));
        Pair p2 = (Pair) stack.pop();
        assertEquals("Pair{k=0, v=foo}", p2.toString());
    }

    @Test
    void popEmptyTest() {
        assertThrows(IllegalStateException.class, () -> stack.pop()); // can't pop empty stack
    }

    @Test
    void peek() {
        //Refer to @Test push
    }

    @Test
    void isEmpty() {
        assertTrue(stack.isEmpty());
    }

    @Test
    void isFull() {
        int STACK_SIZE = 1000;
        int i = 0;
        Stack s2 = new Stack(STACK_SIZE, Pair.class);

        while (i < STACK_SIZE) {
            s2.push(new Pair<>(i++,"foo"));
        }
        // noinspection unchecked
        assertThrows(StackOverflowError.class, () -> s2.push(new Pair<>(1, "bar")));
    }

    @Test
    void size() {
        int STACK_SIZE = 1000;
        int i = 0;
        Stack s2 = new Stack(STACK_SIZE, Pair.class);
        assertEquals(0, s2.size()); // no items pushed to stack
        while (i < STACK_SIZE) {
            s2.push(new Pair<>(i++,"foo"));
        }
        assertEquals(STACK_SIZE, s2.size()); // stack is now full
    }
}