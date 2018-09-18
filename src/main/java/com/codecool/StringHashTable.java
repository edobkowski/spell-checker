package com.codecool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Hash table implementation to store <String, String> key-value pairs
 */
public class StringHashTable {

    private int size;
    private int capacity;
    private List<StringKeyValue>[] entries;
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
        this(10, stringHasher);
    }

    /**
     * Default constructor setting default parameters for capacity and stringHasher
     */
    public StringHashTable() {
        this(10, new DefaultHasher());
    }

    public void add(String key, String value) {
        ensureCapacity(this.size+1);
        int hashedKey = hash(key);
        if(this.entries[hashedKey] == null) {
            this.entries[hashedKey] = new LinkedList<>();
        }

        for(StringKeyValue kv : this.entries[hashedKey]) {
            if(kv.getKey().equals(key)) {
                kv.setValue(value);
                return;
            }
        }

        this.entries[hashedKey].add(new StringKeyValue(key, value));
        size++;
    }

    public boolean remove(String key) {
        int hashedKey = hash(key);
        if(this.entries[hashedKey] == null) {
            return false;
        }
        for(StringKeyValue kv : this.entries[hashedKey]) {
            if(kv.getKey().equals(key)) {
                entries[hashedKey].remove(kv);
                size--;
                return true;
            }
        }

        return false;
    }

    public String get(String key) {
        int hashedKey = hash(key);
        if(this.entries[hashedKey] == null) {
            return null;
        }

        for(StringKeyValue kv : this.entries[hashedKey]) {
            if(kv.getKey().equals(key)) {
                return kv.getValue();
            }
        }

        return null;
    }

    public List<String> getAll() {
        List<String> result = new ArrayList<>();
        for(int i = 0; i < this.size; i++) {
            if(this.entries[i] == null) {
                continue;
            }
            for(StringKeyValue kv : this.entries[i]) {
                result.add(kv.getValue());
            }
        }

        return result;
    }

    public int size() {
        return this.size;
    }

    private void ensureCapacity(int requiredSize) {
        if(requiredSize < 0) {
            throw new IllegalArgumentException("Cannot set a negative table size");
        }
        if(requiredSize > this.capacity) {
            int newCapacity = this.capacity*3/2 + 1;
            this.entries = Arrays.copyOf(this.entries, newCapacity);
        }
    }

    private int hash(String key) {
        return stringHasher.hash(key)%this.capacity;
    }
}
