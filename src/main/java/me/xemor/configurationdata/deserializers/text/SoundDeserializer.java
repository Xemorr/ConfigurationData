package me.xemor.configurationdata.deserializers.text;

import org.bukkit.Registry;
import org.bukkit.Sound;

import java.util.Locale;

public class SoundDeserializer extends TextDeserializer<Sound> {

    @Override
    public Deserialized<Sound> deserialize(String text) {
        Sound sound = Registry.SOUNDS.match(text.toUpperCase(Locale.ROOT));
        if (sound == null) {
            return new Deserialized<>(Registry.SOUNDS.match("ENTITY_GENERIC_EXPLODE"), true);
        }
        return new Deserialized<>(sound, true);
    }
}
