package me.xemor.configurationdata;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Optional;

public class PotionEffectData {

    @CompulsoryJsonProperty
    private PotionEffectType type;
    @JsonPropertyWithDefault
    private double duration = 5;
    @JsonPropertyWithDefault
    private int potency = 0;
    @JsonPropertyWithDefault
    private boolean ambient = true;
    @JsonPropertyWithDefault
    private boolean hasParticles = true;

    public PotionEffectData() {

    }

    public PotionEffect getPotionEffect() {
        return createPotion();
    }

    private PotionEffect createPotion() {
        int workingPotency = potency;
        if (workingPotency > 0) workingPotency--;
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
        return new PotionEffect(type, duration, workingPotency, ambient, hasParticles);
    }

}