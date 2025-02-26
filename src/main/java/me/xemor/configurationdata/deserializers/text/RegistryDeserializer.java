package me.xemor.configurationdata.deserializers.text;

import org.bukkit.Keyed;
import org.bukkit.Registry;

public class RegistryDeserializer<T extends Keyed> extends TextDeserializer<T> {
    private final Registry<T> registry;
    private final String def;

    public RegistryDeserializer(Registry<T> registry, String def) {
        this.registry = registry;
        this.def = def;
    }

    @Override
    public Deserialized<T> deserialize(String text) {
        T value = registry.match(text);
        if (value == null) {
            return new Deserialized<>(registry.match(def), true);
        }

        return new Deserialized<>(value, false);
    }
}
