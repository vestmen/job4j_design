package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int sizeIn = 0;
    private int sizeOut = 0;

    /* добавьте переменные, если они требуются */

    public T poll() {
        T rsl;
        if (sizeIn == 0) {
            throw new NoSuchElementException();
        }
        while (sizeIn != 0) {
            out.push(in.pop());
            sizeIn--;
            sizeOut++;
        }
        rsl = out.pop();
        sizeOut--;
        while (sizeOut != 0) {
            in.push(out.pop());
            sizeOut--;
            sizeIn++;
        }
        return rsl;
    }

    public void push(T value) {
        in.push(value);
        sizeIn++;
    }
}
