package dev.ivmikhail.coursera.algorithms_part1.week1;


public class Quiz2_Find {
    private int[] id;//store elements, where i = element, and value = root element of i
    private int[] treeSize;//number of objects in the tree rooted at i
    private int[] max;//max element of tree rooted at i

    public Quiz2_Find(int n) {
        id = new int[n];
        treeSize = new int[n];
        max = new int[n];

        for (int i = 0; i < id.length; i++) {
            id[i] = i;//set id of each user to itself
            treeSize[i] = 1;
            max[i] = i;
        }
    }

    /**
     * Returns the largest element in the connected component containing
     */
    public int find(int i) {
        return max[root(i)];
    }

    public void union(int first, int second) {
        int firstRoot = root(first);
        int secondRoot = root(second);

        if (firstRoot == secondRoot) return;

        int largest = Math.max(first, second);

        if (treeSize[firstRoot] < treeSize[secondRoot]) {
            id[firstRoot] = secondRoot;
            treeSize[secondRoot] += treeSize[firstRoot];

            max[secondRoot] = largest;
        } else {
            id[secondRoot] = firstRoot;
            treeSize[firstRoot] += treeSize[secondRoot];

            max[firstRoot] = largest;
        }
    }

    public boolean connected(int first, int second) {
        return root(first) == root(second);
    }

    private int root(int i) {
        while (i != id[i]) {
            id[i] = id[id[i]]; //compress path
            i = id[i];
        }
        return i;
    }
}
