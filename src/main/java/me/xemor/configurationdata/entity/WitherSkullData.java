package me.xemor.configurationdata.entity;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.WitherSkull;

public class WitherSkullData extends EntityData {

    private final boolean isCharged;

    public WitherSkullData(ConfigurationSection configurationSection) {
        super(configurationSection);

        isCharged = configurationSection.getBoolean("charged", false);
    }

    @Override
    public void applyExtraMetadata(Entity entity) {
        super.applyExtraMetadata(entity);

        WitherSkull witherSkull = (WitherSkull) entity;
        witherSkull.setCharged(isCharged);
    }
}
