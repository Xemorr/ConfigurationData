package me.xemor.configurationdata.comparison;

import org.bukkit.configuration.ConfigurationSection;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class RegistrySetData<T> {

    private final Set<T> set;

    public RegistrySetData(Function<String, T> stringToType, String variable, ConfigurationSection section) {
        set = section.getStringList(variable).stream().map(String::toLowerCase).map(stringToType).collect(Collectors.toSet());
    }

    public boolean inSet(T t) {
        return set.isEmpty() || set.contains(t);
    }

    public Set<T> getSet() {
        return set;
    }

}
