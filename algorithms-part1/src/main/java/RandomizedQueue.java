import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private class Iter implements Iterator<Item> {
        private int indx;
        private final Item[] items;

        public Iter() {
            indx = 0;

            // copy arr to items
            items = (Item[]) new Object[arr.length];
            for (int i = 0; i < size; i++) {
                items[i] = arr[i];
            }
            // shuffle items
            StdRandom.shuffle(items);
        }

        @Override
        public boolean hasNext() {
            return indx < size;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();

            return items[indx++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private Item[] arr;
    private int size;

    // construct an empty randomized queue
    public RandomizedQueue() {
        arr = (Item[]) new Object[1];
        size = 0;
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> q = new RandomizedQueue<>();
        StdOut.printf("isEmpty() expected %s, actual %s", Boolean.TRUE, q.isEmpty());
        StdOut.println();

        q.enqueue("A");
        q.enqueue("B");

        StdOut.print("Iterator1 ");
        Iterator<String> iter = q.iterator();
        while (iter.hasNext()) {
            StdOut.print(iter.next() + " ");
        }
        StdOut.println();

        StdOut.print("Iterator2 ");
        Iterator<String> iter2 = q.iterator();
        while (iter2.hasNext()) {
            StdOut.print(iter2.next() + " ");
        }
        StdOut.println();

        StdOut.printf("sample()  actual %s", q.sample());
        StdOut.println();

        StdOut.printf("dequeue() actual %s", q.dequeue());
        StdOut.println();

        StdOut.printf("size() expected %s, actual %s", 1, q.size());
        StdOut.println();
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException("Item must not be null");
        tryToResizeCapacity();

        arr[size++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        throwExIfEmpty();
        int toRemove = StdRandom.uniform(0, size);
        Item res = arr[toRemove];
        arr[toRemove] = arr[size - 1];
        size--;
        arr[size] = null;
        tryToResizeCapacity();
        return res;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        throwExIfEmpty();
        return arr[StdRandom.uniform(0, size)];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new Iter();
    }

    private void throwExIfEmpty() {
        if (isEmpty()) throw new NoSuchElementException("There are no element");
    }

    private void tryToResizeCapacity() {
        // arr is between 25% and 100% full
        if (arr.length == size) {
            resize(2 * arr.length);
        } else if (size > 0 && size == arr.length / 4) {
            resize(arr.length / 2);
        }
    }

    private void resize(int newCapacity) {
        Item[] newArr = (Item[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newArr[i] = arr[i];
        }
        arr = newArr;
    }
}