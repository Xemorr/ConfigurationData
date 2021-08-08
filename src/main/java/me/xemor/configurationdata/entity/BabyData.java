package me.xemor.configurationdata.entity;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Ageable;
import org.bukkit.entity.Entity;

public class BabyData extends ExtraData {

    private final boolean isBaby;

    public BabyData(ConfigurationSection configurationSection) {
        super(configurationSection);
        isBaby = configurationSection.getBoolean("isBaby", false);
    }

    @Override
    public void applyData(Entity entity) {
        Ageable ageable = (Ageable) entity;
        if (isBaby) {
            ageable.setBaby();
        }
        else {
            ageable.setAdult();
        }
    }
}
