package me.xemor.configurationdata.deserializers.text;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;
import org.bukkit.Keyed;
import org.bukkit.Registry;

import java.io.IOException;

public class RegistryKeyDeserializer<T extends Keyed> extends KeyDeserializer {

    private final Registry<T> registry;

    public RegistryKeyDeserializer(Registry<T> registry) {
        this.registry = registry;
    }

    @Override
    public Object deserializeKey(String key, DeserializationContext ctxt) throws IOException {
        return registry.match(key);
    }
}
