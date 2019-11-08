package dev.ivmikhail.coursera.algorithms_part1.week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;

public class Quiz1_SocialNetwork {
    private static final int TIMESTAMP = 0;
    private static final int USER1 = 1;
    private static final int USER2 = 2;

    private int[] id;//user ids
    private int[] treeSize;//number of objects in tree rooted at i

    public Quiz1_SocialNetwork(int n) {
        id = new int[n];
        treeSize = new int[n];

        for (int i = 0; i < id.length; i++) {
            id[i] = i;//set id of each user to itself
            treeSize[i] = 1;
        }
    }

    /**
     * Process friendships according the {@code logFile} until all members are connected
     *
     * @param logFile path to log file
     * @return the earliest time at which all members are connected or null in case of all members are not connected
     * @throws IOException
     */
    public LocalDate processFriendships(Path logFile) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(logFile)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(" ");// line looks like "1991-01-01 1 2"

                union(Integer.parseInt(fields[USER1]), Integer.parseInt(fields[USER2]));

                if (isAllMembersConnected()) {
                    return LocalDate.parse(fields[TIMESTAMP]);
                }
            }
        }
        return null;//members are not connected
    }

    private void union(int user1, int user2) {
        int first = root(user1);
        int second = root(user2);

        if (first == second) return;

        if (treeSize[first] < treeSize[second]) {
            id[first] = second;
            treeSize[second] += treeSize[first];
        } else {
            id[second] = first;
            treeSize[first] += treeSize[second];
        }
    }

    private int root(int i) {
        while (id[i] != i) {
            id[i] = id[id[i]];//path compression
            i = id[i];
        }
        return i;
    }

    private boolean isAllMembersConnected() {
        int root = id[0];
        for(int i = 1; i < id.length; i++) {
            if(root != id[i]) return false;
        }
        return true;
    }
}
