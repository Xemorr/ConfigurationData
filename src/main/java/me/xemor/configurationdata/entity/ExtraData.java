package me.xemor.configurationdata.entity;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;

public abstract class ExtraData {

    public ExtraData(ConfigurationSection configurationSection) {
    }

    public abstract void applyData(Entity entity);

}
