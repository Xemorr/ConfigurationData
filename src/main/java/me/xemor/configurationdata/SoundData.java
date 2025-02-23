package me.xemor.configurationdata;

import org.bukkit.Registry;
import org.bukkit.Sound;
import org.bukkit.configuration.ConfigurationSection;

public class SoundData {

    private Sound sound;
    private float volume;
    private float pitch;

    public SoundData(ConfigurationSection configurationSection) {
        sound = Registry.SOUNDS.match(configurationSection.getString("sound", "ENTITY_GENERIC_EXPLODE").toUpperCase());
        volume = (float) configurationSection.getDouble("volume", 1.0);
        pitch = (float) configurationSection.getDouble("pitch", 1.0);
    }

    public Sound getSound() {
        return sound;
    }

    public float getVolume() {
        return volume;
    }

    public float getPitch() {
        return pitch;
    }

}
