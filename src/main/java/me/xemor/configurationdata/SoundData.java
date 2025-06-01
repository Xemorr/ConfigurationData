package me.xemor.configurationdata;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.bukkit.Registry;
import org.bukkit.Sound;

public class SoundData {

    @JsonPropertyWithDefault
    private Sound sound = Sound.ENTITY_GENERIC_EXPLODE;
    @JsonPropertyWithDefault
    private float volume = 1.0f;
    @JsonPropertyWithDefault
    private float pitch = 1.0f;

    public Sound sound() {
        return sound;
    }

    public float volume() {
        return volume;
    }

    public float pitch() {
        return pitch;
    }
}
