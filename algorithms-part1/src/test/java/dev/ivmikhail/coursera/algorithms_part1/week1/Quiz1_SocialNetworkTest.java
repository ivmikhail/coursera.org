package dev.ivmikhail.coursera.algorithms_part1.week1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class Quiz1_SocialNetworkTest {
    private Quiz1_SocialNetwork socialNetwork;

    @BeforeEach
    void setUp() {
        socialNetwork = new Quiz1_SocialNetwork(5);
    }

    @Test
    void processFriendships() throws IOException, URISyntaxException {
        URL log = getClass().getResource("/Quiz1SocialNetwork.txt");
        LocalDate earliest = socialNetwork.processFriendships(Paths.get(log.toURI()));
        assertEquals(LocalDate.parse("1993-01-01"), earliest);
    }
}