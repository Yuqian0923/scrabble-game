package edu.cmu.cs.cs214.hw4.core;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class testDictionary {
    private Dictionary dict;
    private Word word;
    private Word word2;

    @Before
    public void setUp() {
        dict = new Dictionary();
        dict.check("src/main/resources/words.txt");
    }

    @Test
    public void testInDictionary() {
        ArrayList<Location> list = new ArrayList<>();
        word = new Word("ec", list, list);
        assertTrue(!dict.isInDictionary(word));
        word2 = new Word("abate", list, list);
        assertTrue(dict.isInDictionary(word2));
    }

}
