package me.xemor.configurationdata.deserializers.text;

import org.bukkit.Keyed;
import org.bukkit.Registry;

public class RegistryDeserializer<T extends Keyed> extends TextDeserializer<T> {
    private final Registry<T> registry;

    public RegistryDeserializer(Registry<T> registry) {
        this.registry = registry;
    }

    @Override
    public T deserialize(String text) {
        return registry.match(text);
    }
}