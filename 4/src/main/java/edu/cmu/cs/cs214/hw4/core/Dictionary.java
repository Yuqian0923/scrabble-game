package edu.cmu.cs.cs214.hw4.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

public class Dictionary {
    /**
     * dictionary.
     */
    private HashSet<String> dict = new HashSet<>();

    /**
     * Read input file.
     *
     * @param path input text file.
     */
    public void check(String path) {
        try {
            Scanner sc = new Scanner(new File(path));
            while (sc.hasNext()) {
                dict.add(sc.next());
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param word unchecked word.
     * @return boolean.
     */
    public boolean isInDictionary(Word word) {
        return dict.contains(word.toString().toLowerCase());
    }
}
