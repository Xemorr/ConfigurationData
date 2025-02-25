package me.xemor.configurationdata;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Optional;

public class PotionEffectData {

    @JsonProperty
    private PotionEffectType type;
    @JsonProperty
    private double duration = 5;
    @JsonProperty
    private int potency = 0;
    @JsonProperty
    private boolean ambient = true;
    @JsonProperty
    private boolean hasParticles = true;

    public PotionEffectData() {
        if (potency > 0) potency--;
    }

    public PotionEffect getPotionEffect() {
        return createPotion();
    }

    private PotionEffect createPotion() {
        if (type.isInstant()) {
            return new PotionEffect(type, 1, potency, ambient, hasParticles);
        }
        else if (duration != 0) {
            return new PotionEffect(type, (int) Math.round(duration * 20), potency, ambient, hasParticles);
        }
        else {
            return new PotionEffect(type, Integer.MAX_VALUE, potency, ambient, hasParticles);
        }
    }

}