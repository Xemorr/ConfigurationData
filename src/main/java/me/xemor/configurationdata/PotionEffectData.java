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
        int duration;
        if (type.isInstant()) {
            duration = 1;
        }
        else if (this.duration != 0) {
            duration = (int) Math.round(this.duration * 20);
        }
        else {
            duration = Integer.MAX_VALUE;
        }
        return new PotionEffect(type, duration, potency, ambient, hasParticles);
    }

}