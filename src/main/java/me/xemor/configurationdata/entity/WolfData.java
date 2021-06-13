package me.xemor.configurationdata.entity;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Wolf;

public class WolfData extends ExtraData {

    boolean angry;

    public WolfData(ConfigurationSection configurationSection) {
        super(configurationSection);
        angry = configurationSection.getBoolean("angry", false);
    }

    @Override
    public void applyData(Entity entity) {
        if (entity instanceof Wolf) {
            Wolf wolf = (Wolf) entity;
            wolf.setAngry(angry);
        }
    }
}
