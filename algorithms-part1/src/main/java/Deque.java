import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private class Iter implements Iterator {

        private Node current;

        @Override
        public boolean hasNext() {
            if (current == null) {
                return head != null;
            }

            return current.next != null;
        }

        @Override
        public Item next() {
            if (current == null) {
                current = head;
            } else {
                current = current.next;
            }
            if (current == null) throw new NoSuchElementException();

            return current.val;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private class Node {
        Item val;
        Node next;
        Node prev;

        public Node(Item val) {
            this.val = val;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    // construct an empty deque
    public Deque() {
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        checkForNull(item);

        Node newNode = new Node(item);
        if (isEmpty()) {
            head = newNode;
            tail = head;
        } else {
            Node oldHead = head;
            newNode.next = oldHead;

            oldHead.prev = newNode;
            head = newNode;
        }

        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        checkForNull(item);

        Node newNode = new Node(item);
        if (isEmpty()) {
            tail = newNode;
            head = tail;
        } else {
            Node oldTail = tail;
            oldTail.next = newNode;

            newNode.prev = oldTail;
            tail = newNode;
        }

        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        throwExIfEmpty();

        Node oldHead = head;
        head = oldHead.next;
        if (head == null) {
            tail = null;
        } else {
            head.prev = null;
        }

        size--;
        return oldHead.val;
    }

    // remove and return the item from the back
    public Item removeLast() {
        throwExIfEmpty();

        Node oldTail = tail;
        tail = tail.prev;
        if (tail == null) {
            head = null;
        } else {
            tail.next = null;
        }

        size--;
        return oldTail.val;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new Iter();
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<String> d = new Deque<>();
        StdOut.printf("isEmpty()     expected %s, actual %s", true, d.isEmpty());
        StdOut.println();

        d.addLast("C");
        d.addLast("D");
        d.addFirst("B");
        d.addFirst("A");

        //A B C D
        StdOut.print("Iterator      ");
        Iterator<String> iter = d.iterator();
        while (iter.hasNext()) {
            StdOut.print(iter.next() + " ");
        }
        StdOut.println();

        StdOut.printf("size()        expected %s, actual %s ", 4, d.size());
        StdOut.println();

        StdOut.printf("removeFirst() expected %s, actual %s", "A", d.removeFirst());
        StdOut.println();

        StdOut.printf("removeLast()  expected %s, actual %s", "D", d.removeLast());
        StdOut.println();

        StdOut.printf("size()        expected %s, actual %s ", 2, d.size());
        StdOut.println();

        StdOut.printf("removeLast()  expected %s, actual %s", "C", d.removeLast());
        StdOut.println();

        StdOut.printf("removeLast()  expected %s, actual %s", "B", d.removeLast());
        StdOut.println();

        StdOut.printf("size()        expected %s, actual %s ", 0, d.size());
        StdOut.println();

        d.addLast("1");
        d.addLast("2");

        StdOut.printf("removeLast()  expected %s, actual %s", "1", d.removeFirst());
        StdOut.println();

        StdOut.printf("removeLast()  expected %s, actual %s", "2", d.removeFirst());
        StdOut.println();

        StdOut.printf("size()        expected %s, actual %s ", 0, d.size());
        StdOut.println();
    }

    private void checkForNull(Item i) {
        if (i == null) throw new IllegalArgumentException("Item cannot be null");
    }

    private void throwExIfEmpty() {
        if (isEmpty()) throw new NoSuchElementException("There are no element");
    }
}