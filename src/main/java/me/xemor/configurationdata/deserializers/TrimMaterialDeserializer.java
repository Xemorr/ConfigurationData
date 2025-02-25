package me.xemor.configurationdata.deserializers;

import org.bukkit.Registry;
import org.bukkit.inventory.meta.trim.TrimMaterial;

import java.util.Locale;

public class TrimMaterialDeserializer extends TextDeserializer<TrimMaterial> {
    @Override
    public Deserialized<TrimMaterial> deserialize(String text) {
        return new Deserialized<>(Registry.TRIM_MATERIAL.match(text.toUpperCase(Locale.ROOT)), false);
    }
}
