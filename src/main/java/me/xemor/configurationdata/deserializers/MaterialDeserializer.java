package me.xemor.configurationdata.deserializers;

import org.bukkit.Material;
import org.bukkit.Registry;

import java.util.Locale;

public class MaterialDeserializer extends TextDeserializer<Material> {

    @Override
    public Deserialized<Material> deserialize(String text) {
        Material material = Registry.MATERIAL.match(text.toUpperCase(Locale.ROOT));
        if (material == null) {
            return new Deserialized<>(Registry.MATERIAL.match("AIR"), true);
        }
        return new Deserialized<>(material, false);
    }
}
