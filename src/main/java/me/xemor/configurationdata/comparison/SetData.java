package me.xemor.configurationdata.comparison;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.HashSet;
import java.util.Set;

public class SetData<T> {

    private final Set<T> set;

    public SetData() { this.set = new HashSet<>(); }

    @JsonCreator
    public SetData(Set<T> set) {
        this.set = new HashSet<>(set);
    }

    public boolean inSet(T t) {
        return set.isEmpty() || set.contains(t);
    }

    public Set<T> getSet() {
        return set;
    }

    public void addValue(T value) {
        set.add(value);
    }
}
