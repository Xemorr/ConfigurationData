package me.xemor.configurationdata.deserializers.text;

import org.bukkit.Registry;
import org.bukkit.inventory.meta.trim.TrimPattern;

import java.util.Locale;

public class TrimPatternDeserializer extends TextDeserializer<TrimPattern> {
    @Override
    public TrimPattern deserialize(String text) {
        return Registry.TRIM_PATTERN.match(text.toUpperCase(Locale.ROOT));
    }
}
