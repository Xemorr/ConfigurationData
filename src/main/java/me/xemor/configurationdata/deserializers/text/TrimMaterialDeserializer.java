package me.xemor.configurationdata.deserializers.text;

import org.bukkit.Registry;
import org.bukkit.inventory.meta.trim.TrimMaterial;

import java.util.Locale;

public class TrimMaterialDeserializer extends TextDeserializer<TrimMaterial> {
    @Override
    public TrimMaterial deserialize(String text) {
        return Registry.TRIM_MATERIAL.match(text.toUpperCase(Locale.ROOT));
    }
}
