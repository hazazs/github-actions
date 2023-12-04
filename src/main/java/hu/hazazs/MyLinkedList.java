package hu.hazazs;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

final class MyLinkedList<E extends Serializable> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private transient int size;
    private transient MyLink<E> head;

    private static class MyLink<E extends Serializable> implements Serializable {

        @Serial
        private static final long serialVersionUID = 1L;
        private final E element;
        private MyLink<E> next;

        private MyLink(E element) {
            this.element = element;
        }

        private MyLink(E element, MyLink<E> next) {
            this.element = element;
            this.next = next;
        }

    }

    @Serial
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeInt(size);
        for (MyLink<E> current = head; current != null; current = current.next) {
            out.writeObject(current.element);
        }
    }

    @Serial
    @SuppressWarnings("unchecked")
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        int size = in.readInt();
        for (int i = 0; i < size; i++) {
            add((E) in.readObject());
        }
    }

    int size() {
        return size;
    }

    void add(E newElement) {
        if (head == null) {
            head = new MyLink<>(newElement);
        } else {
            MyLink<E> current = head;
            MyLink<E> next = head.next;
            while (next != null) {
                current = next;
                next = next.next;
            }
            current.next = new MyLink<>(newElement);
        }
        size++;
    }

    E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index.");
        }
        MyLink<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.element;
    }

    @Override
    public String toString() {
        MyLink<E> current = head;
        List<String> elements = new ArrayList<>();
        while (current != null) {
            elements.add(current.element.toString());
            current = current.next;
        }
        return elements.stream()
                .collect(Collectors.joining(", ", "MyLinkedList [", "]"));
    }

}