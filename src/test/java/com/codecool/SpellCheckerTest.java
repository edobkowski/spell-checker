package com.codecool;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class SpellCheckerTest {

    private static StringHashTable correctWords = new StringHashTable(new DefaultHasher());

    @BeforeAll
    static void initialize() throws IOException {
        Path wordsListPath = new File("assets/wordlist.txt").toPath();
        List<String> words = Files.readAllLines(wordsListPath);

        for (String word : words) {
            correctWords.add(word, word);
        }
    }

    @Test
    void checkTest_OneCorrectWord() {
        SpellChecker spellChecker = new SpellChecker(correctWords);
        Set<String> expectedResult = new HashSet<>();
        Set<String> actualResult = spellChecker.check("abalone");

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void checkTest_OneIncorrectWord_MissingLetter() {
        SpellChecker spellChecker = new SpellChecker(correctWords);
        Set<String> expectedResult = new HashSet<>();
        expectedResult.add("alone");
        expectedResult.add("abalone");
        expectedResult.add("a alone");
        Set<String> actualResult = spellChecker.check("aalone");

        assertTrue(actualResult.containsAll(expectedResult));
    }

    @Test
    void checkTest_OneIncorrectWord_MultipliedLetter() {
        SpellChecker spellChecker = new SpellChecker(correctWords);
        Set<String> expectedResult = new HashSet<>();
        expectedResult.add("abalone");
        Set<String> actualResult = spellChecker.check("abbalone");

        assertTrue(actualResult.containsAll(expectedResult));
    }

    @Test
    void checkTest_OneIncorrectWord_Misspelled() {
        SpellChecker spellChecker = new SpellChecker(correctWords);
        Set<String> expectedResult = new HashSet<>();
        expectedResult.add("abalone");
        Set<String> actualResult = spellChecker.check("analone");

        assertTrue(actualResult.containsAll(expectedResult));
    }

    @Test
    void checkTest_OneIncorrectWord_SwappedLetters() {
        SpellChecker spellChecker = new SpellChecker(correctWords);
        Set<String> expectedResult = new HashSet<>();
        expectedResult.add("abalone");
        Set<String> actualResult = spellChecker.check("abaolne");

        assertTrue(actualResult.containsAll(expectedResult));
    }

    @Test
    void checkTest_OneIncorrectWord_MissedSpace() {
        SpellChecker spellChecker = new SpellChecker(correctWords);
        Set<String> expectedResult = new HashSet<>();
        expectedResult.add("abacus abalone");
        Set<String> actualResult = spellChecker.check("abacusabalone");

        assertTrue(actualResult.containsAll(expectedResult));
    }
}