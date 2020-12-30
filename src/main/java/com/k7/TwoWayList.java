package com.k7;

import java.util.Iterator;

public class TwoWayList<T> implements Iterable<T> {
    private Node head;
    private Node tail;
    int size;

    @Override
    public Iterator iterator() {
        return new OneWayListIterator();
    }

    public Iterator iteratorReverse() {
        return new BackWayListIterator();
    }

    private class Node {
        private T value;
        private Node next;
        private Node prev;

        public Node(T value) {
            this.value = value;
        }
    }

    private class OneWayListIterator implements Iterator {
        Node cur = head;

        @Override
        public boolean hasNext() {
            return cur != null;
        }

        @Override
        public Object next() {
            T value = (T) cur.value;
            cur = cur.next;
            return value;
        }


    }

    private class BackWayListIterator implements Iterator<T> {
        Node cur = tail;

        @Override
        public boolean hasNext() {
            return cur != null;
        }

        @Override
        public T next() {
            T value = (T) cur.value;
            cur = cur.prev;
            return value;
        }


    }

    public void add(T value) {
        Node n = new Node(value);
        if (head == null) {
            head = tail = n;
        } else {
            tail.next = n;
            n.prev = tail;
            tail = n;
        }
        ++size;
    }

    public T get(int index) {
        checkIndex(index);
        Node cur = getNodeByIndexFromHead(index);
        return cur.value;
    }

    public T getFromTail(int index) {
        checkIndex(index);
        Node cur = getNodeByIndexFromTail(index);
        return cur.value;
    }

    public void set(int index, T value) {
        checkIndex(index);
        Node cur = getNodeByIndexFromHead(index);
        cur.value = value;
    }

    public void setFromTail(int index, T value) {
        checkIndex(index);
        Node cur = getNodeByIndexFromTail(index);
        cur.value = value;
    }

    public void remove(int index) {
        checkIndex(index);
        Node cur = getNodeByIndexFromHead(index);
        if (index == 0 || index == size - 1) relinkHadOrTailNode(index, cur);
        else relinkRemoveNode(cur);
        --size;
    }

    public void removeFromTail(int index) {
        checkIndex(index);
        Node cur = getNodeByIndexFromTail(index);
        if (index == 0 || index == size - 1)
            relinkHadOrTailNode(index, cur);
        else relinkRemoveNode(cur);
        --size;
    }

    private void relinkHadOrTailNode(int index, Node cur) {
        if (index > 0) {
            tail = cur.prev;
            cur = cur.prev;
            cur.next = null;
        } else {
            head = cur.next;
            cur = cur.next;
            cur.prev = null;
        }
    }

    private void relinkRemoveNode(Node cur) {
        Node tmpNext = cur.next;
        Node tmpPrev = cur.prev;
        cur = cur.prev;
        cur.next = tmpNext;
        cur = cur.next;
        cur.prev = tmpPrev;
    }

    private Node getNodeByIndexFromHead(int index) {
        Node cur = head;
        for (int i = 0; i < index; ++i) cur = cur.next;
        return cur;
    }

    private Node getNodeByIndexFromTail(int index) {
        Node cur = tail;
        for (int i = size; i > size - index; --i) cur = cur.prev;
        return cur;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
    }

}
