package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements LinkedList<E> {

    private int size = 0;
    private int modCount = 0;
    private Node<E> head;
    private Node<E> first;

    @Override
    public void add(E value) {
        final Node<E> h = head;
        final Node<E> newNode = new Node<>(value, null);
        head = newNode;
        if (h == null) {
            first = newNode;
        } else {
            h.next = newNode;
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        E rsl;
        if (index == size - 1) {
            rsl = head.item;
        } else if (index == 0) {
            rsl = first.item;
        } else {
            Node<E> temp = first;
            for (int i = 0; i < index; i++) {
                temp = temp.next;
            }
            rsl = temp.item;
        }
        return rsl;
    }

    @Override
    public Iterator<E> iterator() {

        return new Iterator<>() {
            private Node<E> head = first;
            final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return head != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                SimpleLinkedList.Node<E> temp = head;
                head = head.next;
                return temp.item;
            }
        };
    }

    private static class Node<E> {
        private E item;
        private Node<E> next;

        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }
}
