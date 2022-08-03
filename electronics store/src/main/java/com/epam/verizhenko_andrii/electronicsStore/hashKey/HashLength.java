package com.epam.verizhenko_andrii.electronicsStore.hashKey;
import java.util.Objects;

public class HashLength {
    public String key;

    public HashLength(String key) {
        this.key = key;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (!(o instanceof HashLength)) return false;

        HashLength that = (HashLength) o;

        return Objects.equals(key, that.key);
    }

    @Override
    public int hashCode() {
        return key != null ? key.length() : 0;
    }
}
