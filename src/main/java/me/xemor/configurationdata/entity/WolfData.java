package me.xemor.configurationdata.entity;

import me.xemor.configurationdata.ConfigurationData;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Wolf;

public class WolfData extends LivingEntityData {

    private final boolean angry;

    public WolfData(ConfigurationSection configurationSection) {
        super(configurationSection);
        if (configurationSection.contains("extra")) {
            configurationSection = configurationSection.getConfigurationSection("extra");
            ConfigurationData.getLogger().severe("Deprecation warning at '" + configurationSection.getCurrentPath() + "', the contents of the 'extra' section should now be placed in the root of the entity section");
        }

        angry = configurationSection.getBoolean("angry", false);
    }

    @Override
    public void applyAttributes(Entity entity) {
        super.applyAttributes(entity);

        Wolf wolf = (Wolf) entity;
        wolf.setAngry(angry);
    }
}
