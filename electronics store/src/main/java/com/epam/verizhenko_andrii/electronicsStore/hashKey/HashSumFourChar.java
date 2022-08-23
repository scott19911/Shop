package com.epam.verizhenko_andrii.electronicsStore.hashKey;

import java.util.Objects;

public class HashSumFourChar {
    private final String key;

    public HashSumFourChar(String key) {
        this.key = key;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HashSumFourChar)) return false;

        HashSumFourChar that = (HashSumFourChar) o;

        return Objects.equals(key, that.key);
    }

    @Override
    public int hashCode() {
        int charSum = 0;
        for (int i = 0; i < key.length() && i < 4; i++) {
            charSum += key.charAt(i);
        }

        return key != null ? charSum : 0;
    }
}
