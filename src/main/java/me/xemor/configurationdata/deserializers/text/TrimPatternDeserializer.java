package me.xemor.configurationdata.deserializers.text;

import org.bukkit.Registry;
import org.bukkit.inventory.meta.trim.TrimPattern;

import java.util.Locale;

public class TrimPatternDeserializer extends TextDeserializer<TrimPattern> {
    @Override
    public TextDeserializer.Deserialized<TrimPattern> deserialize(String text) {
        return new TextDeserializer.Deserialized<>(Registry.TRIM_PATTERN.match(text.toUpperCase(Locale.ROOT)), false);
    }
}
