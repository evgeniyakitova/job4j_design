package ru.job4j.collection.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements List<E> {
    private Node<E> firstNode;
    private Node<E> lastNode;

    private int size;

    private int modCount;

    @Override
    public void add(E value) {
        Node<E> newNode = new Node<>(value);
        if (firstNode != null) {
            lastNode.next = newNode;
        } else {
            firstNode = newNode;
        }
        lastNode = newNode;
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> currentNode = firstNode;
        int step = 0;
        while (step < index) {
            currentNode = currentNode.next;
            step++;
        }
        return currentNode.value;
    }

    private static class Node<E> {
        E value;
        Node<E> next;
        Node(E value) {
            this.value = value;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> currentNode = firstNode;
            int expectedModCount = modCount;
            int count;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return currentNode != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E value = currentNode.value;
                currentNode = currentNode.next;
                return value;
            }
        };
    }
}
