package me.xemor.configurationdata.deserializers.text;

import org.bukkit.Registry;
import org.bukkit.Sound;

import java.util.Locale;

public class SoundDeserializer extends TextDeserializer<Sound> {

    @Override
    public Sound deserialize(String text) {
        return Registry.SOUNDS.match(text.toUpperCase(Locale.ROOT));
    }
}
