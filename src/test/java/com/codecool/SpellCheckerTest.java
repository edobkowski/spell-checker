package com.codecool;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class SpellCheckerTest {

    private static StringHashTable correctWords = new StringHashTable(new DefaultHasher());
    private static SpellChecker spellChecker;

    @BeforeAll
    static void initialize() throws IOException {
        Path wordsListPath = new File("assets/wordlist.txt").toPath();
        List<String> words = Files.readAllLines(wordsListPath);

        for(String word : words) {
            correctWords.add(word, word);
        }

        spellChecker = new SpellChecker(correctWords);
    }

    @Test
    void checkTest_OneCorrectWord() {
        Set<String> expectedResult = new HashSet<>();
        Set<String> actualResult = spellChecker.check("abalone");

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void checkTest_OneIncorrectWord_MissingLetter() {
        Set<String> expectedResult = new HashSet<>();
        expectedResult.add("alone");
        expectedResult.add("abalone");
        Set<String> actualResult = spellChecker.check("aalone");

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void checkTest_OneIncorrectWord_MultipliedLetter() {
        Set<String> expectedResult = new HashSet<>();
        expectedResult.add("abalone");
        Set<String> actualResult = spellChecker.check("abbalone");

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void checkTest_OneIncorrectWord_Misspelled() {
        Set<String> expectedResult = new HashSet<>();
        expectedResult.add("abalone");
        Set<String> actualResult = spellChecker.check("analone");

        assertEquals(expectedResult, actualResult);
    }
}