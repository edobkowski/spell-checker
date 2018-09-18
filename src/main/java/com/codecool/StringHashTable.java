package com.codecool;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Hash table implementation to store <String, String> key-value pairs
 */
public class StringHashTable {

    private int size;
    private int capacity;
    private List<String>[] entries;
    private StringHasher stringHasher;

    /**
     * Class constructor that define the capacity of hash table and its hashing method
     * @param capacity initial maximal capacity of the hash table
     * @param stringHasher instance of a class responsible for hashing
     */
    public StringHashTable(int capacity, StringHasher stringHasher) {
        this.size = 0;
        this.capacity = capacity;
        this.entries = new LinkedList[capacity];
        this.stringHasher = stringHasher;
    }

    /**
     * Constructor initializing hash table with default capacity
     * @param stringHasher instance of a class responsible for hashing
     */
    public StringHashTable(StringHasher stringHasher) {
        this.stringHasher = stringHasher;
    }

    /**
     * Default constructor setting default parameters for capacity and stringHasher
     */
    public StringHashTable() {
        this(10, new DefaultHasher());
    }
}
