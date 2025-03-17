package me.xemor.configurationdata;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.bukkit.Registry;
import org.bukkit.Sound;

public record SoundData(Sound sound, float volume, float pitch) {
    @JsonCreator
    public SoundData(Sound sound, Float volume, Float pitch) {
        this(
            sound != null ? sound : Registry.SOUNDS.match("ENTITY_GENERIC_EXPLODE"),
            volume != null ? volume : 1.0f,
            pitch != null ? pitch : 1.0f
        );
    }
}
