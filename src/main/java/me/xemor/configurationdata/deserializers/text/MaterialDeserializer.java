package me.xemor.configurationdata.deserializers.text;

import org.bukkit.Material;
import org.bukkit.Registry;

import java.util.Locale;

public class MaterialDeserializer extends TextDeserializer<Material> {

    @Override
    public Material deserialize(String text) {
        return Registry.MATERIAL.match(text.toUpperCase(Locale.ROOT));
    }
}
