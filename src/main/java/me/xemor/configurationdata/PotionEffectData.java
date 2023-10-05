package me.xemor.configurationdata;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Optional;

public class PotionEffectData {

    private PotionEffect potionEffect = null;

    public PotionEffectData(ConfigurationSection configurationSection) {
        PotionEffectType potionType = PotionEffectType.getByName(configurationSection.getString("type", "d").toUpperCase());
        if (potionType == null) {
            ConfigurationData.getLogger().severe("Invalid potion effect type specified at " + configurationSection.getCurrentPath() + ".type");
            return;
        }
        int potency = configurationSection.getInt("potency");
        if (potency > 0) {
            potency--;
        }
        boolean ambient = configurationSection.getBoolean("ambient", true);
        boolean hasParticles = configurationSection.getBoolean("hasParticles", true);
        double duration = configurationSection.getDouble("duration", 5);
        createPotion(potionType, duration, potency, ambient, hasParticles);
    }

    public Optional<PotionEffect> getPotionEffect() {
        return Optional.ofNullable(potionEffect);
    }

    protected void createPotion(PotionEffectType type, double duration, int potency, boolean ambient, boolean hasParticles) {
        if (type.isInstant()) {
            potionEffect = new PotionEffect(type, 1, potency, ambient, hasParticles);
        }
        else if (duration != 0) {
            potionEffect = new PotionEffect(type, (int) Math.round(duration * 20), potency, ambient, hasParticles);
        }
        else {
            potionEffect = new PotionEffect(type, Integer.MAX_VALUE, potency, ambient, hasParticles);
        }
    }

}