package me.xemor.configurationdata.entity.attribute;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;

public abstract class EntityAttributeData {

    public EntityAttributeData(ConfigurationSection configurationSection) {
        if (configurationSection.contains("extra")) {
            configurationSection = configurationSection.getConfigurationSection("extra");
        }
    }

    public abstract void apply(Entity entity);
}
