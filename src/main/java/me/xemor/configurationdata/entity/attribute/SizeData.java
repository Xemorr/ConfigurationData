package me.xemor.configurationdata.entity.attribute;

import me.xemor.configurationdata.ConfigurationData;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Phantom;
import org.bukkit.entity.Slime;

public class SizeData extends EntityAttributeData {

    private final int size;

    public SizeData(ConfigurationSection configurationSection) {
        super(configurationSection);
        if (configurationSection.contains("extra")) {
            configurationSection = configurationSection.getConfigurationSection("extra");
            ConfigurationData.getLogger().severe("Deprecation warning at '" + configurationSection.getCurrentPath() + "', the contents of the 'extra' section should now be placed in the root of the entity section");
        }

        size = configurationSection.getInt("size");
    }

    @Override
    public void apply(Entity entity) {
        if (entity instanceof Slime) {
            Slime slime = (Slime) entity;
            slime.setSize(size);
        }
        else if (entity instanceof Phantom) {
            Phantom phantom = (Phantom) entity;
            phantom.setSize(size);
        }
    }
}
