package com.codecool;

public class DefaultHasher implements StringHasher {
    @Override
    public int hash(String key) {
        return Math.abs(key.hashCode());
    }
}
