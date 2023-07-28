package me.xemor.configurationdata;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PotionEffectData {

    private PotionEffect potionEffect;

    public PotionEffectData(ConfigurationSection configurationSection, PotionEffectType defaultType, int defaultDuration, int defaultPotency) {
        int potency = configurationSection.getInt("potency", defaultPotency);
        if (potency > 0) {
            potency--;
        }
        PotionEffectType potionType = PotionEffectType.getByName(configurationSection.getString("type", "d").toUpperCase());
        if (potionType == null) {
            potionType = defaultType;
            ConfigurationData.getLogger().severe("Invalid potion effect type specified at " + configurationSection.getCurrentPath() + ".type");
        }
        boolean ambient = configurationSection.getBoolean("ambient", true);
        boolean hasParticles = configurationSection.getBoolean("hasParticles", true);
        if (potionType != null) {
            double duration = configurationSection.getDouble("duration", defaultDuration);
            createPotion(potionType, duration, potency, ambient, hasParticles);
        }
    }

    public PotionEffect getPotionEffect() {
        return potionEffect;
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