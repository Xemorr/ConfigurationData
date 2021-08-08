package me.xemor.configurationdata.comparison;

import me.xemor.configurationdata.ConfigurationData;
import org.bukkit.configuration.ConfigurationSection;

import java.util.Set;
import java.util.stream.Collectors;

public class SetData<T extends Enum<T>> {

    private final Set<T> set;

    public SetData(Class<T> tClass, String variable, ConfigurationSection section) {
        set = section.getStringList(variable).stream().map(String::toUpperCase).map(value -> valueOf(section, variable, tClass, value)).collect(Collectors.toSet());
    }

    public boolean inSet(T t) {
        return set.size() == 0 || set.contains(t);
    }

    public Set<T> getSet() {
        return set;
    }

    private T valueOf(ConfigurationSection section, String variable, Class<T> tClass, String value) {
        try {
            return Enum.valueOf(tClass, value);
        } catch (IllegalArgumentException e) {
            ConfigurationData.getLogger().severe("You have entered an invalid " + tClass.getName() + " at " + section.getCurrentPath() + "." + variable);
        }
        return null;
    }

}
