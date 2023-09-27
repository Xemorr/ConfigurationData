package me.xemor.configurationdata.entity;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.WitherSkull;

public class WitherSkullData extends ExtraData {

    private final boolean isCharged;

    public WitherSkullData(ConfigurationSection configurationSection) {
        super(configurationSection);
        isCharged = configurationSection.getBoolean("charged", false);
    }

    @Override
    public void applyData(Entity entity) {
        if (entity instanceof WitherSkull witherSkull) {
            witherSkull.setCharged(isCharged);
        }
    }
}
