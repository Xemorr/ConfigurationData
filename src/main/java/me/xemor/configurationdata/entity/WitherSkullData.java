package me.xemor.configurationdata.entity;

import me.xemor.configurationdata.ConfigurationData;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.WitherSkull;

public class WitherSkullData extends EntityData {

    private final boolean isCharged;

    public WitherSkullData(ConfigurationSection configurationSection) {
        super(configurationSection);
        if (configurationSection.contains("extra")) {
            configurationSection = configurationSection.getConfigurationSection("extra");
            ConfigurationData.getLogger().severe("Deprecation warning at '" + configurationSection.getCurrentPath() + "', the contents of the 'extra' section should now be placed in the root of the entity section");
        }

        isCharged = configurationSection.getBoolean("charged", false);
    }

    @Override
    public void applyAttributes(Entity entity) {
        super.applyAttributes(entity);

        WitherSkull witherSkull = (WitherSkull) entity;
        witherSkull.setCharged(isCharged);
    }
}
