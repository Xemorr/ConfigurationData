package me.xemor.configurationdata.entity.attribute;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Ageable;
import org.bukkit.entity.Entity;

public class BabyData extends EntityAttributeData {

    private final boolean isBaby;

    public BabyData(ConfigurationSection configurationSection) {
        super(configurationSection);
        isBaby = configurationSection.getBoolean("isBaby", false);
    }

    @Override
    public void apply(Entity entity) {
        Ageable ageable = (Ageable) entity;
        if (isBaby) {
            ageable.setBaby();
        } else {
            ageable.setAdult();
        }
    }
}
