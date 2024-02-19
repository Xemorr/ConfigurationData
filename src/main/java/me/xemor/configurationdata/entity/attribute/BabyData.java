package me.xemor.configurationdata.entity.attribute;

import me.xemor.configurationdata.ConfigurationData;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Ageable;
import org.bukkit.entity.Entity;

public class BabyData extends EntityAttributeData {

    private final boolean isBaby;

    public BabyData(ConfigurationSection configurationSection) {
        super(configurationSection);
        if (configurationSection.contains("extra")) {
            configurationSection = configurationSection.getConfigurationSection("extra");
            ConfigurationData.getLogger().severe("Deprecation warning at '" + configurationSection.getCurrentPath() + "', the contents of the 'extra' section should now be placed in the root of the entity section");
        }

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
