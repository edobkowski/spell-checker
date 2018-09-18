package com.codecool;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StringHashTableTest {

    private StringHashTable hashTable;

    @BeforeEach()
    void initializeHashTable() {
        hashTable = new StringHashTable(3, new DefaultHasher());
    }


    @Test
    void addTest_SingleCorrectInput() {
        hashTable.add("Dog", "dog");
        String expectedResult = "dog";
        String actualResult = hashTable.get("Dog");
        int expectedSize = 1;
        int actualSize = hashTable.size();

        assertEquals(expectedSize, actualSize);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void addTest_MultipleCorrectInput() {
        hashTable.add("Dog", "dog");
        hashTable.add("Cat", "cat");
        hashTable.add("Fish", "fish");
        List<String> expectedResult = Arrays.asList("dog", "cat", "fish");
        List<String> actualResult = hashTable.getAll();
        int expectedSize = 3;
        int actualSize = hashTable.size();

        assertEquals(expectedSize, actualSize);
        assertIterableEquals(expectedResult, actualResult);
    }

    @Test
    void addTest_MultipleOverCapacityInput() {
        hashTable.add("Dog", "dog");
        hashTable.add("Cat", "cat");
        hashTable.add("Fish", "fish");
        hashTable.add("Snake", "snake");
        List<String> expectedResult = Arrays.asList("dog", "cat", "fish", "snake");
        List<String> actualResult = hashTable.getAll();
        int expectedSize = 4;
        int actualSize = hashTable.size();

        assertEquals(expectedSize, actualSize);
        assertTrue(actualResult.containsAll(expectedResult));
    }

    @Test
    void getTest_CorrectInput() {
        hashTable.add("Dog", "dog");
        String expectedResult = "dog";
        String actualResult = hashTable.get("Dog");

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getTest_IncorrectInput() {
        hashTable.add("Dog", "dog");
        String result = hashTable.get("dog");

        assertNull(result);
    }
}